package io.jenkins.plugins.sample;

import hudson.model.TaskListener;
import org.jenkinsci.plugins.github.pullrequest.GitHubPRCause;
import org.jenkinsci.plugins.github.pullrequest.GitHubPRPullRequest;
import org.jenkinsci.plugins.github.pullrequest.GitHubPRTrigger;
import org.jenkinsci.plugins.github.pullrequest.events.impl.GitHubPRCloseEvent;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: many more here: https://github.com/KostyaSha/github-integration-plugin/blob/13f894c2423ddd27f28f080af6d5a9629763b765/github-pullrequest-plugin/src/main/java/org/jenkinsci/plugins/github/pullrequest/events/impl/GitHubPRCloseEvent.java
public class MyGitHubPRCloseEvent extends GitHubPRCloseEvent {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubPRCloseEvent.class.getName());

    public MyGitHubPRCloseEvent() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubPRCloseEvent(): {0}",
                new Object[]{ this });
    }

    @Override
    public GitHubPRCause check(GitHubPRTrigger gitHubPRTrigger, GHPullRequest remotePR, GitHubPRPullRequest localPR, TaskListener listener) throws IOException {
        LOGGER.log(Level.FINER, "MyGitHubPRCloseEvent.check: {0} {1} {2} {3}",
                new Object[]{ gitHubPRTrigger, remotePR, localPR, listener });
        return super.check(gitHubPRTrigger, remotePR, localPR, listener);
    }
}
