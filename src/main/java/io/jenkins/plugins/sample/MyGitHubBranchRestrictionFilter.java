package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranch;
import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.GitHubBranchRepository;
import com.github.kostyasha.github.integration.branch.GitHubBranchTrigger;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import com.github.kostyasha.github.integration.branch.events.impl.GitHubBranchRestrictionFilter;
import hudson.Extension;
import hudson.model.TaskListener;
import org.jenkinsci.Symbol;
import org.kohsuke.github.GHBranch;

import javax.annotation.Nonnull;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchRestrictionFilter extends GitHubBranchRestrictionFilter {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubBranchRestrictionFilter.class.getName());

    public MyGitHubBranchRestrictionFilter() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubBranchRestrictionFilter(): {0}",
                new Object[]{this});
    }

    @Override
    public GitHubBranchCause check(GitHubBranchTrigger gitHubBranchTrigger, GHBranch remoteBranch, GitHubBranch localBranch, GitHubBranchRepository localRepo, TaskListener listener) {
        LOGGER.log(Level.FINER, "MyGitHubBranchRestrictionFilter.check: {0} {1} {2} {3} {4}",
                new Object[]{gitHubBranchTrigger, remoteBranch, localBranch, localRepo, listener});
        GitHubBranchCause branchCause = super.check(gitHubBranchTrigger, remoteBranch, localBranch, localRepo, listener);
        LOGGER.log(Level.FINER, "MyGitHubBranchRestrictionFilter.check.branchCause: {0}",
                new Object[]{branchCause});
        return branchCause;
    }

    @Symbol("myRestriction")
    @Extension
    public static class Descriptor extends GitHubBranchEventDescriptor {
        @Nonnull
        @Override
        public String getDisplayName() {
            return "my restriction";
        }
    }
}
