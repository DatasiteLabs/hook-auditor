package io.jenkins.plugins.sample;

import hudson.Plugin;
import org.kohsuke.stapler.DataBoundConstructor;

public class AuditorPlugin extends Plugin {
    /** Indicates if the plugin is activated. */
    private boolean activated = true;

    public AuditorPlugin() {

    }

    @DataBoundConstructor
    public AuditorPlugin(boolean activated) {
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public static final String FIELD_ACTIVATED = "auditor_activated";
}
