package io.jenkins.plugins.sample;

import hudson.Extension;
import jenkins.scm.api.SCMEventListener;
import jenkins.scm.api.SCMHeadEvent;
import jenkins.scm.api.SCMNavigatorEvent;
import jenkins.scm.api.SCMSourceEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

@Extension
public class SCMListener extends SCMEventListener {
    private static final Logger LOGGER = Logger.getLogger(SCMListener.class.getName());

    @Override
    public void onSCMHeadEvent(SCMHeadEvent<?> event) {
        super.onSCMHeadEvent(event);
        LOGGER.log(Level.FINER, "onSCMHeadEvent: {0}",  new Object[]{ event });
    }

    @Override
    public void onSCMNavigatorEvent(SCMNavigatorEvent<?> event) {
        super.onSCMNavigatorEvent(event);
        LOGGER.log(Level.FINER, "onSCMNavigatorEvent: {0}",  new Object[]{ event });
    }

    @Override
    public void onSCMSourceEvent(SCMSourceEvent<?> event) {
        super.onSCMSourceEvent(event);
        LOGGER.log(Level.FINER, "onSCMSourceEvent: {0}",  new Object[]{ event });
    }
}
