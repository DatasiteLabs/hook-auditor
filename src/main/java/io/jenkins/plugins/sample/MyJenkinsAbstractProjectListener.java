package io.jenkins.plugins.sample;

import hudson.model.ParameterValue;
import hudson.plugins.git.GitStatus;
import org.eclipse.jgit.transport.URIish;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyJenkinsAbstractProjectListener extends GitStatus.JenkinsAbstractProjectListener {
    private static final Logger LOGGER = Logger.getLogger(MyJenkinsAbstractProjectListener.class.getName());

    @Override
    public List<GitStatus.ResponseContributor> onNotifyCommit(String origin, URIish uri, String sha1, List<ParameterValue> buildParameters, String... branches) {
        LOGGER.log(Level.FINER, "myGitSCMSource.onNotifyCommit: {0} {1} {2} {3} {4}", new Object[]{ origin, uri, sha1, buildParameters, branches });

        return super.onNotifyCommit(origin, uri, sha1, buildParameters, branches);
    }
}
