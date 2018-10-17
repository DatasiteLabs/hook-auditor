package io.jenkins.plugins.sample;

import hudson.model.Item;
import org.jenkinsci.plugins.github.extension.GHSubscriberEvent;
import org.jenkinsci.plugins.github.webhook.subscriber.DefaultPushGHEventSubscriber;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDefaultPushGHEventSubscriber extends DefaultPushGHEventSubscriber {
    private static final Logger LOGGER = Logger.getLogger(MyDefaultPushGHEventSubscriber.class.getName());

    public MyDefaultPushGHEventSubscriber() {
        super();
        LOGGER.log(Level.FINER, "MyDefaultPushGHEventSubscriber(): {0}",
                new Object[]{ this });
    }

    @Override
    protected boolean isApplicable(Item project) {
        LOGGER.log(Level.FINER, "MyDefaultPushGHEventSubscriber.isApplicable: {0}",
                new Object[]{ project });
        return super.isApplicable(project);
    }

    @Override
    protected void onEvent(GHSubscriberEvent event) {
        LOGGER.log(Level.FINER, "MyDefaultPushGHEventSubscriber.onEvent: {0}",
                new Object[]{ event });
        super.onEvent(event);
    }


}
