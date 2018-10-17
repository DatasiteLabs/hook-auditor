package io.jenkins.plugins.sample;

import hudson.model.TaskListener;
import hudson.plugins.git.GitException;
import hudson.plugins.git.Revision;
import hudson.plugins.git.util.BuildChooserContext;
import hudson.plugins.git.util.BuildData;
import hudson.plugins.git.util.InverseBuildChooser;
import org.jenkinsci.plugins.gitclient.GitClient;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyInverseBuildChooser extends InverseBuildChooser {
    private static final Logger LOGGER = Logger.getLogger(MyInverseBuildChooser.class.getName());

    @Override
    public Collection<Revision> getCandidateRevisions(boolean isPollCall, String singleBranch, GitClient git, TaskListener listener, BuildData buildData, BuildChooserContext context) throws GitException, IOException, InterruptedException {
        LOGGER.log(Level.FINER, "myGitSCMSource.onNotifyCommit: {0} {1} {2} {3} {4} {5}", new Object[]{isPollCall, singleBranch, git, listener, buildData, context});
        return super.getCandidateRevisions(isPollCall, singleBranch, git, listener, buildData, context);
    }
}
