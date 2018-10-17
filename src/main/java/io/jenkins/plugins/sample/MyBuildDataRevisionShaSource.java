package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.Run;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.github.extension.status.GitHubCommitShaSource;
import org.jenkinsci.plugins.github.status.sources.BuildDataRevisionShaSource;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyBuildDataRevisionShaSource extends BuildDataRevisionShaSource {
    private static final Logger LOGGER = Logger.getLogger(MyBuildDataRevisionShaSource.class.getName());

    public MyBuildDataRevisionShaSource() {
        super();
        LOGGER.log(Level.FINER, "MyBuildDataRevisionShaSource(): {0}",
                new Object[]{ this });
    }

    @Override
    public String get(@Nonnull Run<?, ?> run, @Nonnull TaskListener listener) throws IOException {
        LOGGER.log(Level.FINER, "MyBuildDataRevisionShaSource.get(): {0} {1}",
                new Object[]{ run, listener });
        return super.get(run, listener);
    }

    @Extension
    public static class BuildDataRevisionShaSourceDescriptor extends Descriptor<GitHubCommitShaSource> {
        @Override
        public String getDisplayName() {
            return "my Latest build revision";
        }
    }
}
