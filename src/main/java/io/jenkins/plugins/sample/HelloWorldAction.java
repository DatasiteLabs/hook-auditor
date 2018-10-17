package io.jenkins.plugins.sample;

import hudson.model.Action;
import hudson.model.Run;
import jenkins.model.RunAction2;

import javax.annotation.CheckForNull;

public class HelloWorldAction implements RunAction2 {

    private String name;
    private transient Run run;

    public HelloWorldAction(String name) {
        this.name = name;
    }

    @CheckForNull
    @Override
    public String getIconFileName() {
        return "document.png";
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return "Greeting";
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return "greeting";
    }

    public String getName() {
        return name;
    }

    @Override
    public void onAttached(Run<?, ?> run) {
        this.run = run;
    }

    @Override
    public void onLoad(Run<?, ?> run) {
        this.run = run;
    }

    public Run getRun() {
        return run;
    }
}
