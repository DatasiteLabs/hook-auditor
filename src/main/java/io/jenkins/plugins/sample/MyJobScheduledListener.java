package io.jenkins.plugins.sample;

import hudson.model.Queue;
import org.jenkinsci.plugins.github_branch_source.GitHubBuildStatusNotification;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyJobScheduledListener extends GitHubBuildStatusNotification.JobScheduledListener {
    private static final Logger LOGGER = Logger.getLogger(MyJobScheduledListener.class.getName());

    public MyJobScheduledListener() {
        super();
        LOGGER.log(Level.FINER, "MyJobScheduledListener(): {0}",
                new Object[]{this});
    }

    @Override
    public void onEnterWaiting(Queue.WaitingItem wi) {
        LOGGER.log(Level.FINER, "MyJobScheduledListener().onEnterWaiting: {0}",
                new Object[]{wi});
        super.onEnterWaiting(wi);
    }

    @Override
    public void onLeaveWaiting(Queue.WaitingItem wi) {
        LOGGER.log(Level.FINER, "MyJobScheduledListener().onLeaveWaiting: {0}",
                new Object[]{wi});
        super.onLeaveWaiting(wi);
    }

    @Override
    public void onEnterBlocked(Queue.BlockedItem bi) {
        LOGGER.log(Level.FINER, "MyJobScheduledListener().onEnterBlocked: {0}",
                new Object[]{bi});
        super.onEnterBlocked(bi);
    }

    @Override
    public void onLeaveBlocked(Queue.BlockedItem bi) {
        LOGGER.log(Level.FINER, "MyJobScheduledListener().onLeaveBlocked: {0}",
                new Object[]{bi});
        super.onLeaveBlocked(bi);
    }

    @Override
    public void onEnterBuildable(Queue.BuildableItem bi) {
        LOGGER.log(Level.FINER, "MyJobScheduledListener().onEnterBuildable: {0}",
                new Object[]{bi});
        super.onEnterBuildable(bi);
    }

    @Override
    public void onLeaveBuildable(Queue.BuildableItem bi) {
        LOGGER.log(Level.FINER, "MyJobScheduledListener().onLeaveBuildable: {0}",
                new Object[]{bi});
        super.onLeaveBuildable(bi);
    }

    @Override
    public void onLeft(Queue.LeftItem li) {
        LOGGER.log(Level.FINER, "MyJobScheduledListener().onLeft: {0}",
                new Object[]{li});
        super.onLeft(li);
    }
}


