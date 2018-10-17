package io.jenkins.plugins.sample;

import hudson.model.Item;
import org.jenkinsci.plugins.github.extension.GHSubscriberEvent;
import org.jenkinsci.plugins.github_branch_source.GitHubRepositoryEventSubscriber;
import org.kohsuke.github.GHEvent;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubRepositoryEventSubscriber extends GitHubRepositoryEventSubscriber {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubRepositoryEventSubscriber.class.getName());

    public MyGitHubRepositoryEventSubscriber() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubRepositoryEventSubscriber(): {0}",
                new Object[]{ this });
    }

    @Override
    protected boolean isApplicable(@Nullable Item item) {
        LOGGER.log(Level.FINER, "MyGitHubRepositoryEventSubscriber.isApplicable: {0}",
                new Object[]{ item });
        return super.isApplicable(item);
    }

    @Override
    protected Set<GHEvent> events() {
        Set<GHEvent> events = super.events();
        LOGGER.log(Level.FINER, "MyGitHubRepositoryEventSubscriber.events: {0}",
                new Object[]{ events });
        return events;
    }

    @Override
    protected void onEvent(GHSubscriberEvent event) {
        LOGGER.log(Level.FINER, "MyGitHubRepositoryEventSubscriber.onEvent: {0}",
                new Object[]{ event });
        super.onEvent(event);
    }
}
