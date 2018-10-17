package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranch;
import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.GitHubBranchRepository;
import com.github.kostyasha.github.integration.branch.GitHubBranchTrigger;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import com.github.kostyasha.github.integration.branch.events.impl.GitHubBranchCreatedEvent;
import hudson.Extension;
import hudson.model.TaskListener;
import org.jenkinsci.Symbol;
import org.kohsuke.github.GHBranch;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchCreatedEvent extends GitHubBranchCreatedEvent {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubBranchCreatedEvent.class.getName());

    public MyGitHubBranchCreatedEvent() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubBranchCreatedEvent(): {0}",
                new Object[]{ this });
    }

    @Override
    public GitHubBranchCause check(GitHubBranchTrigger trigger, GHBranch remoteBranch, @CheckForNull GitHubBranch localBranch, GitHubBranchRepository locaRepo, TaskListener listener) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubBranchCreatedEvent.check: {0} {1} {2} {3} {4}",
                new Object[]{ trigger, remoteBranch, localBranch, locaRepo, listener });
        return super.check(trigger, remoteBranch, localBranch, locaRepo, listener);
    }

    @Symbol("MyBranchCreated")
    @Extension
    public static class DescriptorImpl extends GitHubBranchEventDescriptor {
        @Nonnull
        @Override
        public final String getDisplayName() {
            return "My Branch Created";
        }
    }
}
