package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.model.Item;
import org.jenkinsci.plugins.github.extension.GHEventsSubscriber;
import org.jenkinsci.plugins.github.extension.GHSubscriberEvent;
import org.kohsuke.github.GHEvent;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.collect.Sets.immutableEnumSet;
import static org.kohsuke.github.GHEvent.REPOSITORY;

@Extension
public class GHEventSubscriber extends GHEventsSubscriber {
    private static final Logger LOGGER = Logger.getLogger(GHEventSubscriber.class.getName());

    @Override
    protected boolean isApplicable(@Nullable Item item) {
        LOGGER.log(Level.FINER, "GHEventSubscriber.isApplicable: {0}",  new Object[]{ item });
        return true;
    }

    @Override
    protected Set<GHEvent> events() {
        return immutableEnumSet(REPOSITORY);
    }

    @Override
    protected void onEvent(GHSubscriberEvent event) {
        LOGGER.log(Level.FINER, "GHEventSubscriber.onEvent: {0}",  new Object[]{ event });
        LOGGER.log(Level.FINER, "GHEventSubscriber.onnEvent.getGHEvent: {0}", new Object[] { event.getGHEvent() });
        super.onEvent(event);
    }
}
