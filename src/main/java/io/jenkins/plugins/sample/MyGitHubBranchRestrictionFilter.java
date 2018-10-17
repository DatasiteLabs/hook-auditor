package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import com.github.kostyasha.github.integration.branch.events.impl.GitHubBranchRestrictionFilter;
import com.github.kostyasha.github.integration.generic.GitHubBranchDecisionContext;
import hudson.Extension;
import org.jenkinsci.Symbol;

import javax.annotation.Nonnull;
import java.io.IOException;
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
//    public GitHubBranchCause check(GitHubBranchTrigger gitHubBranchTrigger, GHBranch remoteBranch, GitHubBranch localBranch, GitHubBranchRepository localRepo, TaskListener listener) {
    public GitHubBranchCause check(@Nonnull GitHubBranchDecisionContext context) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubBranchRestrictionFilter.check: {0} {1} {2} {3} {4}",
                new Object[]{context.getTrigger(), context.getRemoteBranch(), context.getLocalBranch(), context.getLocalRepo(), context.getListener()});
        GitHubBranchCause branchCause = super.check(context);
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
