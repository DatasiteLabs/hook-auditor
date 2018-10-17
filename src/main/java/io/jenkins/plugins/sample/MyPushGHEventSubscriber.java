package io.jenkins.plugins.sample;

import hudson.model.Item;
import org.jenkinsci.plugins.github.extension.GHSubscriberEvent;
import org.jenkinsci.plugins.github_branch_source.PushGHEventSubscriber;
import org.kohsuke.github.GHEvent;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyPushGHEventSubscriber extends PushGHEventSubscriber {
    private static final Logger LOGGER = Logger.getLogger(MyPushGHEventSubscriber.class.getName());

    public MyPushGHEventSubscriber() {
        super();
        LOGGER.log(Level.FINER, "MyPushGHEventSubscriber(): {0}",
                new Object[]{ this });
    }

    @Override
    protected boolean isApplicable(@Nullable Item project) {
        LOGGER.log(Level.FINER, "MyPushGHEventSubscriber.isApplicable: {0}",
                new Object[]{ project });
        return super.isApplicable(project);
    }

    @Override
    protected Set<GHEvent> events() {
        Set<GHEvent> events = super.events();
        LOGGER.log(Level.FINER, "MyPushGHEventSubscriber.events: {0}",
                new Object[]{ events });
        return events;
    }

    @Override
    protected void onEvent(GHSubscriberEvent event) {
        LOGGER.log(Level.FINER, "MyPushGHEventSubscriber.onEvent: {0}",
                new Object[]{ event });
        super.onEvent(event);
    }
}
