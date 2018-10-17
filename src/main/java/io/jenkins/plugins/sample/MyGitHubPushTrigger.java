package io.jenkins.plugins.sample;

import com.cloudbees.jenkins.GitHubPushTrigger;
import com.cloudbees.jenkins.GitHubRepositoryName;
import com.cloudbees.jenkins.GitHubRepositoryNameContributor;
import com.cloudbees.jenkins.GitHubTriggerEvent;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import hudson.Extension;
import hudson.XmlFile;
import hudson.model.Item;
import hudson.model.Job;
import hudson.triggers.SCMTrigger;
import hudson.triggers.Trigger;
import hudson.triggers.TriggerDescriptor;
import hudson.util.FormValidation;
import hudson.util.NamingThreadFactory;
import hudson.util.SequentialExecutionQueue;
import jenkins.model.Jenkins;
import jenkins.model.ParameterizedJobMixIn;
import jenkins.triggers.SCMTriggerItem;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.github.admin.GitHubHookRegisterProblemMonitor;
import org.jenkinsci.plugins.github.migration.Migrator;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.AncestorInPath;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubPushTrigger extends GitHubPushTrigger {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubPushTrigger.class.getName());

    public MyGitHubPushTrigger() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubPushTrigger(): {0}",
                new Object[]{this});
    }

    @Override
    public void onPost(String triggeredByUser) {
        LOGGER.log(Level.FINER, "MyGitHubPushTrigger().onPostWithTriggeredByUser: {0}",
                new Object[]{triggeredByUser});
        super.onPost(triggeredByUser);
    }

    @Override
    public void onPost(GitHubTriggerEvent event) {
        LOGGER.log(Level.FINER, "MyGitHubPushTrigger().onPostWithEvent: {0}",
                new Object[]{event});
        super.onPost(event);
    }

    @Override
    public void start(Job<?, ?> project, boolean newInstance) {
        LOGGER.log(Level.FINER, "MyGitHubPushTrigger().start: {0} {1}",
                new Object[]{project, newInstance});
        super.start(project, newInstance);
    }

    @Override
    public void registerHooks() {
        LOGGER.log(Level.FINER, "MyGitHubPushTrigger().registerHooks");
        super.registerHooks();
    }

    @Extension
    @Symbol("myGithubPush")
    public static class DescriptorImpl extends TriggerDescriptor {
        private final transient SequentialExecutionQueue queue =
                new SequentialExecutionQueue(Executors.newSingleThreadExecutor(threadFactory()));

        @Inject
        private transient GitHubHookRegisterProblemMonitor monitor;

        @Inject
        private transient SCMTrigger.DescriptorImpl scmTrigger;

        private transient int maximumThreads = Integer.MIN_VALUE;

        public DescriptorImpl() {
            checkThreadPoolSizeAndUpdateIfNecessary();
        }

        /**
         * Update the {@link java.util.concurrent.ExecutorService} instance.
         */
        /*package*/
        synchronized void checkThreadPoolSizeAndUpdateIfNecessary() {
            if (scmTrigger != null) {
                int count = scmTrigger.getPollingThreadCount();
                if (maximumThreads != count) {
                    maximumThreads = count;
                    queue.setExecutors(
                            (count == 0
                                    ? Executors.newCachedThreadPool(threadFactory())
                                    : Executors.newFixedThreadPool(maximumThreads, threadFactory())));
                }
            }
        }

        @Override
        public boolean isApplicable(Item item) {
            return item instanceof Job && SCMTriggerItem.SCMTriggerItems.asSCMTriggerItem(item) != null
                    && item instanceof ParameterizedJobMixIn.ParameterizedJob;
        }

        @Override
        public String getDisplayName() {
            return "GitHub hook trigger for GITScm polling";
        }

        /**
         * Uses global xstream to enable migration alias used in
         * {@link Migrator#enableCompatibilityAliases()}
         */
        @Override
        protected XmlFile getConfigFile() {
            return new XmlFile(Jenkins.XSTREAM2, super.getConfigFile().getFile());
        }

        public static DescriptorImpl get() {
            return Trigger.all().get(DescriptorImpl.class);
        }

        public static boolean allowsHookUrlOverride() {
            return ALLOW_HOOKURL_OVERRIDE;
        }

        private static ThreadFactory threadFactory() {
            return new NamingThreadFactory(Executors.defaultThreadFactory(), "my GitHubPushTrigger");
        }

        /**
         * Checks that repo defined in this item is not in administrative monitor as failed to be registered.
         * If that so, shows warning with some instructions
         *
         * @param item - to check against. Should be not null and have at least one repo defined
         * @return warning or empty string
         * @since 1.17.0
         */
        @SuppressWarnings("unused")
        @Restricted(NoExternalUse.class) // invoked from Stapler
        public FormValidation doCheckHookRegistered(@AncestorInPath Item item) {
            Preconditions.checkNotNull(item, "Item can't be null if wants to check hook in monitor");

            if (!item.hasPermission(Item.CONFIGURE)) {
                return FormValidation.ok();
            }

            Collection<GitHubRepositoryName> repos = GitHubRepositoryNameContributor.parseAssociatedNames(item);

            for (GitHubRepositoryName repo : repos) {
                if (monitor.isProblemWith(repo)) {
                    return FormValidation.warning(
                            org.jenkinsci.plugins.github.Messages.github_trigger_check_method_warning_details(
                                    repo.getUserName(), repo.getRepositoryName(), repo.getHost()
                            ));
                }
            }

            return FormValidation.ok();
        }
    }

}
