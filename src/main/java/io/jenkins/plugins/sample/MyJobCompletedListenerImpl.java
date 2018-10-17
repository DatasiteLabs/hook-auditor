package io.jenkins.plugins.sample;

import hudson.model.Run;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.github_branch_source.GitHubBuildStatusNotification;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyJobCompletedListenerImpl extends GitHubBuildStatusNotification.JobCompletedListener {
    private static final Logger LOGGER = Logger.getLogger(MyJobCompletedListenerImpl.class.getName());

    @Override
    public void onCompleted(Run<?, ?> build, TaskListener listener) {
        LOGGER.log(Level.FINER, "MyJobCompletedListenerImpl.onCompleted: {0} {1}",
                new Object[]{ build, listener });
        super.onCompleted(build, listener);
    }
}
