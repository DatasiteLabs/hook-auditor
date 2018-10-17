package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.XmlFile;
import hudson.model.Item;
import hudson.model.Saveable;
import jenkins.scm.impl.SCM2Notifier;
import jenkins.triggers.SCMTriggerItem;

import java.util.logging.Level;
import java.util.logging.Logger;

@Extension
public class MySCM2Notifier extends SCM2Notifier {
    private static final Logger LOGGER = Logger.getLogger(MySCM2Notifier.class.getName());

    @Override
    public void onChange(Saveable o, XmlFile file) {
        if (o instanceof Item) {
            SCMTriggerItem item = SCMTriggerItem.SCMTriggerItems.asSCMTriggerItem((Item) o);
            if (item != null) {
                LOGGER.log(Level.FINER, "mySCM2Notifier.onChange: {0}", new Object[]{ item });
            }
        }
        super.onChange(o, file);
    }
}
