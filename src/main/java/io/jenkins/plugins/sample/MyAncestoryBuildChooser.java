package io.jenkins.plugins.sample;

import hudson.model.TaskListener;
import hudson.plugins.git.GitException;
import hudson.plugins.git.util.AncestryBuildChooser;
import hudson.plugins.git.util.BuildChooserContext;
import hudson.plugins.git.util.BuildData;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyAncestoryBuildChooser extends AncestryBuildChooser {
    private static final Logger LOGGER = Logger.getLogger(MyAncestoryBuildChooser.class.getName());

    public MyAncestoryBuildChooser(Integer maximumAgeInDays, String ancestorCommitSha1) {
        super(maximumAgeInDays, ancestorCommitSha1);
    }

    @Override
    public Collection<hudson.plugins.git.Revision> getCandidateRevisions(boolean isPollCall, String branchSpec, org.jenkinsci.plugins.gitclient.GitClient git, TaskListener listener, BuildData data, BuildChooserContext context) throws GitException, IOException, InterruptedException {
        LOGGER.log(Level.FINER, "myGitSCMSource.onNotifyCommit: {0} {1} {2} {3} {4} {5}", new Object[]{isPollCall, branchSpec, git, listener, data, context});
        return super.getCandidateRevisions(isPollCall, branchSpec, git, listener, data, context);
    }
}
