package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.GitHubBranchRepository;
import com.github.kostyasha.github.integration.branch.events.impl.commitchecks.GitHubBranchCommitCheckDescriptor;
import com.github.kostyasha.github.integration.branch.events.impl.commitchecks.impl.GitHubBranchCommitMessageCheck;
import hudson.Extension;
import org.jenkinsci.Symbol;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCompare;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchCommitMessageCheck extends GitHubBranchCommitMessageCheck {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubBranchCommitMessageCheck.class.getName());

    public MyGitHubBranchCommitMessageCheck() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubBranchCommitMessageCheck(): {0}",
                new Object[]{ this });
    }

    @Override
    public GitHubBranchCause doCheck(GHBranch remoteBranch, GitHubBranchRepository localRepo, GHCommit commit) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubBranchCommitMessageCheck.doCheck: {0} {1} {2}",
                new Object[]{ remoteBranch, localRepo, commit });
        return super.doCheck(remoteBranch, localRepo, commit);
    }

    @Override
    public GitHubBranchCause check(GHBranch remoteBranch, GitHubBranchRepository localRepo, GHCompare.Commit[] commits) {
        LOGGER.log(Level.FINER, "MyGitHubBranchCommitMessageCheck.check: {0} {1} {2}",
                new Object[]{ remoteBranch, localRepo, commits });
        return super.check(remoteBranch, localRepo, commits);
    }

    @Symbol("myCommitMessagePattern")
    @Extension
    public static class DescriptorImpl extends GitHubBranchCommitCheckDescriptor {
        @Nonnull
        @Override
        public final String getDisplayName() {
            return "myCommitMessagePattern";
        }
    }
}
