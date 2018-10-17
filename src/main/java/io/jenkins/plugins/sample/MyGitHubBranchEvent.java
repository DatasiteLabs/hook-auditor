package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.GitHubBranchTrigger;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEvent;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import com.github.kostyasha.github.integration.generic.GitHubBranchDecisionContext;
import hudson.Extension;
import hudson.model.TaskListener;
import org.kohsuke.github.GHEventPayload;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchEvent extends GitHubBranchEvent {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubBranchEvent.class.getName());

    public MyGitHubBranchEvent() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubBranchEvent(): {0}",
                new Object[]{this});
    }

    @CheckForNull
    @Override
    public GitHubBranchCause check(@Nonnull GitHubBranchDecisionContext context) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubBranchEvent.check: {0} {1} {2} {3} {4}",
                new Object[]{context.getTrigger(), context.getRemoteBranch(), context.getLocalBranch(), context.getLocalRepo(), context.getListener()});
        return super.check(context);
    }

    @Override
    public GitHubBranchCause checkHook(GitHubBranchTrigger githubTrigger, GHEventPayload payload, TaskListener listener) {
        LOGGER.log(Level.FINER, "MyGitHubBranchEvent.checkHook: {0} {1} {2} {3} {4}",
                new Object[]{githubTrigger, payload, listener});
        return super.checkHook(githubTrigger, payload, listener);
    }

    @Extension
    public static class DescriptorImpl extends GitHubBranchEventDescriptor {
        public DescriptorImpl() {
        }

        public final String getDisplayName() {
            return "My GitHubBranchEventDescriptor";
        }
    }
}
