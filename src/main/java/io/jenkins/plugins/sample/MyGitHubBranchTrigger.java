package io.jenkins.plugins.sample;

import antlr.ANTLRException;
import com.github.kostyasha.github.integration.branch.GitHubBranchTrigger;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEvent;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import com.github.kostyasha.github.integration.generic.GitHubTriggerDescriptor;
import hudson.Extension;
import hudson.model.Job;
import hudson.triggers.Trigger;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.github.pullrequest.GitHubPRTriggerMode;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchTrigger extends GitHubBranchTrigger {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubBranchTrigger.class.getName());

    public MyGitHubBranchTrigger(String spec, GitHubPRTriggerMode triggerMode, List<GitHubBranchEvent> events) throws ANTLRException {
        super(spec, triggerMode, events);
        LOGGER.log(Level.FINER, "MyGitHubBranchTrigger(): {0} {1} {2}",
                new Object[]{ spec, triggerMode, events });
    }

    @Override
    public void start(Job item, boolean newInstance) {
        LOGGER.log(Level.FINER, "MyGitHubBranchTrigger().start: {0} {1}",
                new Object[]{item, newInstance});
        super.start(item, newInstance);
    }

    @Override
    public void run() {
        LOGGER.log(Level.FINER, "MyGitHubBranchTrigger().run");
        super.run();
    }

    @Override
    public void queueRun(Job<?, ?> job, String branch) {
        LOGGER.log(Level.FINER, "MyGitHubBranchTrigger().queueRun: {0} {1}",
                new Object[]{ job, branch });
        super.queueRun(job, branch);
    }

    @Override
    public void doRun(String branch) {
        LOGGER.log(Level.FINER, "MyGitHubBranchTrigger().doRun: {0} {1}",
                new Object[]{ branch });
        super.doRun(branch);
    }

    @Symbol("myGithubBranches")
    @Extension
    public static class DescriptorImpl extends GitHubTriggerDescriptor {

        public DescriptorImpl() {
            load();
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "my GitHub Branches";
        }

        // list all available descriptors for choosing in job configuration
        public List<GitHubBranchEventDescriptor> getEventDescriptors() {
            return GitHubBranchEventDescriptor.all();
        }

        public static DescriptorImpl get() {
            return Trigger.all().get(DescriptorImpl.class);
        }
    }
}
