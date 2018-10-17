package io.jenkins.plugins.sample;

import hudson.model.TaskListener;
import hudson.plugins.git.GitException;
import hudson.plugins.git.Revision;
import hudson.plugins.git.util.BuildChooserContext;
import hudson.plugins.git.util.BuildData;
import hudson.plugins.git.util.DefaultBuildChooser;
import org.jenkinsci.plugins.gitclient.GitClient;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDefaultBuildChooser extends DefaultBuildChooser {
    private static final Logger LOGGER = Logger.getLogger(MyDefaultBuildChooser.class.getName());

    @Override
    public Collection<Revision> getCandidateRevisions(boolean isPollCall, String branchSpec, GitClient git, TaskListener listener, BuildData data, BuildChooserContext context) throws GitException, IOException, InterruptedException {
        LOGGER.log(Level.FINER, "myGitSCMSource.onNotifyCommit: {0} {1} {2} {3} {4} {5}", new Object[]{isPollCall, branchSpec, git, listener, data, context});
        return super.getCandidateRevisions(isPollCall, branchSpec, git, listener, data, context);
    }
}
