package io.jenkins.plugins.sample;

import hudson.model.TaskListener;
import org.jenkinsci.plugins.github_branch_source.AbstractGitHubNotificationStrategy;
import org.jenkinsci.plugins.github_branch_source.GitHubNotificationContext;
import org.jenkinsci.plugins.github_branch_source.GitHubNotificationRequest;
import org.kohsuke.github.GHCommitState;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDefaultGitHubNotificationStrategy extends AbstractGitHubNotificationStrategy {
    private static final Logger LOGGER = Logger.getLogger(MyDefaultGitHubNotificationStrategy.class.getName());

    @Override
    public List<GitHubNotificationRequest> notifications(GitHubNotificationContext notificationContext, TaskListener listener) {
        String context = notificationContext.getDefaultContext(listener);
        String url = notificationContext.getDefaultUrl(listener);
        String message = notificationContext.getDefaultMessage(listener);
        GHCommitState state = notificationContext.getDefaultState(listener);
        boolean ignoreError = notificationContext.getDefaultIgnoreError(listener);

        LOGGER.log(Level.FINER, "myDefaultGithubNOtificationStrategy.notifications: {0} {1} {2} {3} {4}",
                new Object[]{
                        context, url, message, state, ignoreError
                });
        LOGGER.log(Level.FINER, "myDefaultGithubNOtificationStrategy.extras: {0} {1} {2} {3}",
                new Object[]{
                        notificationContext.getSource(), notificationContext.getBuild(), notificationContext.getHead(), notificationContext.getJob()
                });

        return Collections.singletonList(GitHubNotificationRequest.build(context,
                url,
                message,
                state,
                ignoreError));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return this == o || (o != null && getClass() == o.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return 42;
    }
}
