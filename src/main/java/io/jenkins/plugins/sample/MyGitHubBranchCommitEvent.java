package io.jenkins.plugins.sample;

import com.github.kostyasha.github.integration.branch.GitHubBranchCause;
import com.github.kostyasha.github.integration.branch.events.GitHubBranchEventDescriptor;
import com.github.kostyasha.github.integration.branch.events.impl.GitHubBranchCommitEvent;
import com.github.kostyasha.github.integration.branch.events.impl.commitchecks.GitHubBranchCommitCheck;
import com.github.kostyasha.github.integration.branch.events.impl.commitchecks.GitHubBranchCommitCheckDescriptor;
import com.github.kostyasha.github.integration.generic.GitHubBranchDecisionContext;
import hudson.Extension;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubBranchCommitEvent extends GitHubBranchCommitEvent {
    private static final Logger LOGGER = Logger.getLogger(GitHubBranchCommitEvent.class.getName());

    public MyGitHubBranchCommitEvent(List<GitHubBranchCommitCheck> checks) {
        super(checks);
        LOGGER.log(Level.FINER, "MyGitHubBranchCommitEvent(): {0}",
                new Object[]{ this });
    }

    @Override
    public GitHubBranchCause check(@Nonnull GitHubBranchDecisionContext context) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubBranchCommitEvent.check: {0} {1} {2} {3} {4}",
                new Object[]{context.getTrigger(), context.getRemoteBranch(), context.getLocalBranch(), context.getLocalRepo(), context.getListener()});
        return super.check(context);
    }

    @Symbol("mycommit")
    @Extension
    public static class DescriptorImpl extends GitHubBranchEventDescriptor {
        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            req.bindJSON(this, formData);

            save();
            return super.configure(req, formData);
        }

        @Nonnull
        @Override
        public final String getDisplayName() {
            return "MyGitHubBranchCommitEvent";
        }

        public List<GitHubBranchCommitCheckDescriptor> getEventDescriptors() {
            return GitHubBranchCommitCheckDescriptor.all();
        }
    }
}
