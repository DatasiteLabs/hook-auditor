package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranch;
import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.GitHubBranchRepository;
import com.github.kostyasha.github.integration.branch.GitHubBranchTrigger;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEvent;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import hudson.Extension;
import hudson.model.TaskListener;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHEventPayload;

import javax.annotation.CheckForNull;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchEvent extends GitHubBranchEvent {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubBranchEvent.class.getName());

    public MyGitHubBranchEvent() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubBranchEvent(): {0}",
                new Object[]{ this });
    }

    @CheckForNull
    @Override
    public GitHubBranchCause check(GitHubBranchTrigger gitHubBranchTrigger, GHBranch remoteBranch, @CheckForNull GitHubBranch localBranch, GitHubBranchRepository localRepo, TaskListener listener) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubBranchEvent.check: {0} {1} {2} {3} {4}",
                new Object[]{ gitHubBranchTrigger, remoteBranch, localBranch, localRepo, listener });
        return super.check(gitHubBranchTrigger, remoteBranch, localBranch, localRepo, listener);
    }

    @Override
    public GitHubBranchCause checkHook(GitHubBranchTrigger githubTrigger, GHEventPayload payload, TaskListener listener) {
        LOGGER.log(Level.FINER, "MyGitHubBranchEvent.checkHook: {0} {1} {2} {3} {4}",
                new Object[]{ githubTrigger, payload, listener });
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
