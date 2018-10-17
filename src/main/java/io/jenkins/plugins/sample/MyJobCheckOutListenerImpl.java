package io.jenkins.plugins.sample;

import hudson.FilePath;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.scm.SCM;
import hudson.scm.SCMRevisionState;
import org.jenkinsci.plugins.github_branch_source.GitHubBuildStatusNotification;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyJobCheckOutListenerImpl extends GitHubBuildStatusNotification.JobCheckOutListener {
    private static final Logger LOGGER = Logger.getLogger(MyJobCheckOutListenerImpl.class.getName());

    @Override
    public void onCheckout(Run<?, ?> build, SCM scm, FilePath workspace, TaskListener listener, File changelogFile, SCMRevisionState pollingBaseline) throws Exception {
        LOGGER.log(Level.FINER, "MyJobCheckOutListenerImpl.onCheckout: {0} {1} {2} {3} {4} {5}",
                new Object[]{ build, scm, workspace, listener, changelogFile, pollingBaseline });
        super.onCheckout(build, scm, workspace, listener, changelogFile, pollingBaseline);
    }
}
