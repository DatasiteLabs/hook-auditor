package io.jenkins.plugins.sample;

import hudson.model.Item;
import org.jenkinsci.plugins.github.webhook.subscriber.PingGHEventSubscriber;
import org.kohsuke.github.GHEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyPingGHEventSubscriber extends PingGHEventSubscriber {
    private static final Logger LOGGER = Logger.getLogger(MyPingGHEventSubscriber.class.getName());

    public MyPingGHEventSubscriber() {
        super();
        LOGGER.log(Level.FINER, "MyPingGHEventSubscriber(): {0}",
                new Object[]{ this });
    }

    @Override
    protected boolean isApplicable(Item project) {
        LOGGER.log(Level.FINER, "MyPingGHEventSubscriber.isApplicable: {0}",
                new Object[]{ project });
        return super.isApplicable(project);
    }

    @Override
    protected void onEvent(GHEvent event, String payload) {
        LOGGER.log(Level.FINER, "MyPingGHEventSubscriber.onEvent: {0}",
                new Object[]{ event });
        super.onEvent(event, payload);
    }
}
