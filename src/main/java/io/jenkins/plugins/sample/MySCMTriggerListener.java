package io.jenkins.plugins.sample;

import hudson.Extension;
import jenkins.scm.api.SCMHeadEvent;
import jenkins.scm.api.SCMNavigatorEvent;
import jenkins.scm.api.SCMSourceEvent;
import jenkins.scm.impl.SCMTriggerListener;
import org.kohsuke.github.GHEventPayload;

import java.util.logging.Level;
import java.util.logging.Logger;

@Extension
public class MySCMTriggerListener extends SCMTriggerListener {
    private static final Logger LOGGER = Logger.getLogger(MySCMTriggerListener.class.getName());

    @Override
    public void onSCMHeadEvent(SCMHeadEvent<?> event) {
        LOGGER.log(Level.FINER, "mySCMTriggerListner.onSCMHeadEvent: {0}", new Object[]{event});
        if (event.getPayload() instanceof GHEventPayload) {
            GHEventPayload payload = ((GHEventPayload) event.getPayload());
            LOGGER.log(Level.FINER, "mySCMTriggerListner.onSCMHeadEvent.GHEventPayload: {0} {1}", new Object[]{payload.getSender(), payload.toString()});
            if (payload instanceof GHEventPayload.PullRequest) {
                GHEventPayload.PullRequest pay = (GHEventPayload.PullRequest) payload;
                LOGGER.log(Level.FINER, "mySCMTriggerListner.onSCMHeadEvent.GHEventPayload.PullRequest: {0} {1}", new Object[]{ pay.getAction(), pay.getNumber()  });
            } else if (payload instanceof GHEventPayload.Push) {
                GHEventPayload.Push pay = (GHEventPayload.Push) payload;
                LOGGER.log(Level.FINER, "mySCMTriggerListner.onSCMHeadEvent.GHEventPayload.Push: {0} {1} {2}", new Object[]{ pay.getPusher(), pay.getHead(), pay.getRef() });
            }
        }
        super.onSCMHeadEvent(event);
    }

    @Override
    public void onSCMNavigatorEvent(SCMNavigatorEvent<?> event) {
        LOGGER.log(Level.FINER, "mySCMTriggerListner.onSCMNavigatorEvent: {0} {1}", new Object[]{event, event.getPayload()});
        super.onSCMNavigatorEvent(event);
    }

    @Override
    public void onSCMSourceEvent(SCMSourceEvent<?> event) {
        LOGGER.log(Level.FINER, "mySCMTriggerListner.onSCMSourceEvent: {0} {1}", new Object[]{event, event.getPayload()});
        super.onSCMSourceEvent(event);
    }
}
