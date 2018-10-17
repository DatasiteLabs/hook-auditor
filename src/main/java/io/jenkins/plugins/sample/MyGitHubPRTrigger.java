package io.jenkins.plugins.sample;

import antlr.ANTLRException;
import com.github.kostyasha.github.integration.generic.GitHubTriggerDescriptor;
import hudson.Extension;
import hudson.model.Job;
import hudson.triggers.Trigger;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.github.pullrequest.GitHubPRTrigger;
import org.jenkinsci.plugins.github.pullrequest.GitHubPRTriggerMode;
import org.jenkinsci.plugins.github.pullrequest.events.GitHubPREvent;
import org.jenkinsci.plugins.github.pullrequest.events.GitHubPREventDescriptor;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubPRTrigger extends GitHubPRTrigger {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubPRTrigger.class.getName());

    public MyGitHubPRTrigger(String spec, GitHubPRTriggerMode triggerMode, List<GitHubPREvent> events) throws ANTLRException {
        super(spec, triggerMode, events);
        LOGGER.log(Level.FINER, "MyGitHubPRTrigger(): {0} {1} {2}",
                new Object[]{ spec, triggerMode, events });
    }

    @Override
    public void start(Job<?, ?> job, boolean newInstance) {
        LOGGER.log(Level.FINER, "MyGitHubPRTrigger().start: {0} {1}",
                new Object[]{ job, newInstance });
        super.start(job, newInstance);
    }

    @Override
    public void run() {
        LOGGER.log(Level.FINER, "MyGitHubPRTrigger().run");
        super.run();
    }

    @Override
    public void queueRun(Job<?, ?> job, int prNumber) {
        LOGGER.log(Level.FINER, "MyGitHubPRTrigger().queueRun: {0} {1}",
                new Object[]{ job, prNumber });
        super.queueRun(job, prNumber);
    }

    @Override
    public void doRun(Integer prNumber) {
        LOGGER.log(Level.FINER, "MyGitHubPRTrigger().doRun: {0} {1}",
                new Object[]{ prNumber });
        super.doRun(prNumber);
    }

    @Symbol("myGithubPullRequests")
    @Extension
    public static class DescriptorImpl extends GitHubTriggerDescriptor {

        public DescriptorImpl() {
            load();
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "my GitHub Pull Requests";
        }

        // list all available descriptors for choosing in job configuration
        public static List<GitHubPREventDescriptor> getEventDescriptors() {
            return GitHubPREventDescriptor.all();
        }

        public static DescriptorImpl get() {
            return Trigger.all().get(DescriptorImpl.class);
        }
    }
}
