package io.jenkins.plugins.sample;

import com.cloudbees.jenkins.GitHubWebHook;
import hudson.model.Item;
import org.kohsuke.github.GHEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubWebHook extends GitHubWebHook {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubWebHook.class.getName());

    public MyGitHubWebHook() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubWebHook(): {0}",
                new Object[]{this});
    }

    @Override
    public void registerHookFor(Item item) {
        LOGGER.log(Level.FINER, "MyGitHubWebHook.registerHookFor(): {0}",
                new Object[]{ item });
        super.registerHookFor(item);
    }

    @Override
    public List<Item> reRegisterAllHooks() {
        LOGGER.log(Level.FINER, "MyGitHubWebHook.reRegisterAllHooks()");
        return super.reRegisterAllHooks();
    }

    @Override
    public void doIndex(@Nonnull GHEvent event, @Nonnull String payload) {
        LOGGER.log(Level.FINER, "MyGitHubWebHook.doIndex(): {0} {1}",
                new Object[]{ event, payload });
        super.doIndex(event, payload);
    }
}
