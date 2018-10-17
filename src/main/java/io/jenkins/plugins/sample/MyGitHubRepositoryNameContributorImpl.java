package io.jenkins.plugins.sample;

import com.cloudbees.jenkins.GitHubRepositoryName;
import com.cloudbees.jenkins.GitHubTrigger;
import hudson.model.Item;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubRepositoryNameContributorImpl extends GitHubTrigger.GitHubRepositoryNameContributorImpl {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubRepositoryNameContributorImpl.class.getName());

    public MyGitHubRepositoryNameContributorImpl() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubRepositoryNameContributorImpl(): {0}",
                new Object[]{ this });
    }

    @Override
    public void parseAssociatedNames(Item item, Collection<GitHubRepositoryName> result) {
        LOGGER.log(Level.FINER, "MyGitHubRepositoryNameContributorImpl.parseAssociatedNames: {0} {1}",
                new Object[]{ item, result });
        super.parseAssociatedNames(item, result);
    }
}
