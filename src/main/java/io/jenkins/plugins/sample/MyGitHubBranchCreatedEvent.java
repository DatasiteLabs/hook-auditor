package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import com.github.kostyasha.github.integration.branch.events.impl.GitHubBranchCreatedEvent;
import com.github.kostyasha.github.integration.generic.GitHubBranchDecisionContext;
import hudson.Extension;
import org.jenkinsci.Symbol;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchCreatedEvent extends GitHubBranchCreatedEvent {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubBranchCreatedEvent.class.getName());

    public MyGitHubBranchCreatedEvent() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubBranchCreatedEvent(): {0}",
                new Object[]{this});
    }

    @Override
    public GitHubBranchCause check(@Nonnull GitHubBranchDecisionContext context) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubBranchCreatedEvent.check: {0} {1} {2} {3} {4}",
                new Object[]{context.getTrigger(), context.getRemoteBranch(), context.getLocalBranch(), context.getLocalRepo(), context.getListener()});
        return super.check(context);
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
