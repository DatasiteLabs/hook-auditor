package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.TopLevelItem;
import hudson.model.View;
import hudson.views.ViewJobFilter;
import org.jenkinsci.plugins.github_branch_source.GitHubPullRequestFilter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGitHubPullRequestFilter extends GitHubPullRequestFilter {
    private static final Logger LOGGER = Logger.getLogger(MyGitHubPullRequestFilter.class.getName());

    public MyGitHubPullRequestFilter() {
        super();
        LOGGER.log(Level.FINER, "MyGitHubPullRequestFilter(): {0}",
                new Object[]{ this });
    }

    @Override
    public List<TopLevelItem> filter(List<TopLevelItem> added, List<TopLevelItem> all, View filteringView) {
        LOGGER.log(Level.FINER, "MyGithubBranchFilter.filter: {0} {1} {2}",
                new Object[]{ added, all, filteringView });
        return super.filter(added, all, filteringView);
    }

    /**
     * Our descriptor.
     */
    @Extension(optional = true)
    public static class DescriptorImpl extends Descriptor<ViewJobFilter> {

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return "MyGitHubPullRequestFilter";
        }
    }
}
