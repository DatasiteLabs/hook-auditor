package io.jenkins.plugins.sample;

import org.jenkinsci.plugins.GitHubOAuthScope;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubOAuthScope extends GitHubOAuthScope {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubOAuthScope.class.getName());

    @Override
    public Collection<String> getScopesToRequest() {
        LOGGER.log(Level.FINER, "myGitOAuthScope");
        return null;
    }
}
