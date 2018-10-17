package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranch;
import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.GitHubBranchRepository;
import com.github.kostyasha.github.integration.branch.GitHubBranchTrigger;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import com.github.kostyasha.github.integration.branch.events.impl.GitHubBranchHashChangedEvent;
import hudson.Extension;
import hudson.model.TaskListener;
import org.jenkinsci.Symbol;
import org.kohsuke.github.GHBranch;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchHashChangedEvent extends GitHubBranchHashChangedEvent {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubBranchHashChangedEvent.class.getName());

    public MyGitHubBranchHashChangedEvent() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubBranchHashChangedEvent(): {0}",
                new Object[]{ this });
    }

    @Override
    public GitHubBranchCause check(GitHubBranchTrigger trigger, GHBranch remoteBranch, @CheckForNull GitHubBranch localBranch, GitHubBranchRepository localRepo, TaskListener listener) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubBranchHashChangedEvent.check: {0} {1} {2} {3} {4}",
                new Object[]{ trigger, remoteBranch, localBranch, localRepo, listener });
        return super.check(trigger, remoteBranch, localBranch, localRepo, listener);
    }

    @Symbol("myHashChanged")
    @Extension
    public static class DescriptorImpl extends GitHubBranchEventDescriptor {
        @Nonnull
        @Override
        public final String getDisplayName() {
            return "my hash changed";
        }
    }
}
