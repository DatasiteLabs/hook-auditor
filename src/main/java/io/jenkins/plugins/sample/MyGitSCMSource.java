package io.jenkins.plugins.sample;

import edu.umd.cs.findbugs.annotations.Nullable;
import hudson.model.ParameterValue;
import hudson.plugins.git.GitStatus;
import jenkins.plugins.git.GitSCMSource;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitSCMSource extends GitSCMSource.ListenerImpl {
    private static final Logger LOGGER = Logger.getLogger(MyGitSCMSource.class.getName());

    @Override
    public List<GitStatus.ResponseContributor> onNotifyCommit(String origin, org.eclipse.jgit.transport.URIish uri, @Nullable String sha1, List<ParameterValue> buildParameters, String... branches) {
        LOGGER.log(Level.FINER, "myGitSCMSource.onNotifyCommit: {0} {1} {2} {3} {4}", new Object[]{ origin, uri, sha1, buildParameters, branches });
        return super.onNotifyCommit(origin, uri, sha1, buildParameters, branches);
    }
}
