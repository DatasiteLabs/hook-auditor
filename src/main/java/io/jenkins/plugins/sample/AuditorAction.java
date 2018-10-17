package io.jenkins.plugins.sample;

import hudson.model.Action;
import hudson.model.Cause;

import javax.annotation.CheckForNull;

public class AuditorAction implements Action {
    private final Cause cause;

    public AuditorAction(Cause cause) {
        this.cause = cause;
    }

    @CheckForNull
    @Override
    public String getIconFileName() {
        return null;
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return "Trigger " + cause.getClass().getSimpleName() + " : " + getTooltip();
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return "";
    }

    public String getTooltip() {
        return cause.getShortDescription();
    }
}
