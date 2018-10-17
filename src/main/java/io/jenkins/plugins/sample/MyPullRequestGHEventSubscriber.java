package io.jenkins.plugins.sample;

import hudson.model.Item;
import org.jenkinsci.plugins.github.extension.GHSubscriberEvent;
import org.jenkinsci.plugins.github_branch_source.PullRequestGHEventSubscriber;
import org.kohsuke.github.GHEvent;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyPullRequestGHEventSubscriber extends PullRequestGHEventSubscriber {
    private static final Logger LOGGER = Logger.getLogger(MyPullRequestGHEventSubscriber.class.getName());

    public MyPullRequestGHEventSubscriber() {
        super();
        LOGGER.log(Level.FINER, "MyPullRequestGHEventSubscriber(): {0}",
                new Object[]{ this });
    }

    @Override
    protected boolean isApplicable(@Nullable Item project) {
        LOGGER.log(Level.FINER, "MyPullRequestGHEventSubscriber.isApplicable: {0}",
                new Object[]{ project });
        return super.isApplicable(project);
    }

    @Override
    protected Set<GHEvent> events() {
        Set<GHEvent> events = super.events();
        LOGGER.log(Level.FINER, "MyPullRequestGHEventSubscriber.events: {0}",
                new Object[]{ events });
        return events;
    }

    @Override
    protected void onEvent(GHSubscriberEvent event) {
        LOGGER.log(Level.FINER, "MyPullRequestGHEventSubscriber.onEvent: {0}",
                new Object[]{ event });
        super.onEvent(event);
    }
}
