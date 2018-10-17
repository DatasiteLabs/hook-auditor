package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.model.Cause;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.model.listeners.RunListener;
import jenkins.model.Jenkins;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Extension
public class RunListenerImpl extends RunListener<Run> {
    private static final Logger LOGGER = Logger.getLogger(RunListenerImpl.class.getName());

    public RunListenerImpl() {
        super(Run.class);
    }

    @Override
    public void onStarted(Run build, TaskListener listener) {
        AuditorPlugin plugin = Jenkins.getInstance().getPlugin(AuditorPlugin.class);
        if (plugin.isActivated()) {
            LOGGER.log(Level.FINER, "onStarted: ");
            List<Cause> causes = CauseFilter.filter((List<Cause>) build.getCauses());
            for (Cause cause : causes) {
                LOGGER.log(Level.FINER, "Cause: {0}", new Object[]{ cause });
                if (isEnabled(cause)) {
                    build.addAction(new AuditorAction(cause));
                }
            }
        }
        super.onStarted(build, listener);
    }

    private boolean isEnabled(Cause cause) {
        return true;
    }
}
