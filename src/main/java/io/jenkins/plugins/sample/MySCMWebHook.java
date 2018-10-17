package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.model.UnprotectedRootAction;
import hudson.security.csrf.CrumbExclusion;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.HttpResponses;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.interceptor.RequirePOST;

import javax.annotation.CheckForNull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Extension
public class MySCMWebHook implements UnprotectedRootAction {
    private static final String URL_NAME = "my-scm-hook";
    private static final Logger LOGGER = Logger.getLogger(MySCMWebHook.class.getName());


    @CheckForNull
    @Override
    public String getIconFileName() {
        return null;
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return null;
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return URL_NAME;
    }

    @RequirePOST
    public HttpResponse doNotify(StaplerRequest req) {
        LOGGER.log(Level.FINER, "MySCMWebHook: {0}",  new Object[]{ req });
        // check if the event payload at least provides some proof of origin
        // this may be a query parameter or a HTTP header
        // if the proof of origin is missing, drop the event on the floor and return

        // extract the payload from the request
        // parse the payload
//        for (event : req) {
           /* switch (eventType) {
                case HEAD:
                    SCMHeadEvent.fireNow(new MySCMHeadEvent(eventType, payload, SCMEvent.originOf(req));
                    break;
                case SOURCE:
                    SCMHeadEvent.fireNow(new MySCMSourceEvent(eventType, payload, SCMEvent.originOf(req));
                    break;
                case NAVIGATOR:
                    SCMHeadEvent.fireNow(new MySCMNavigatorEvent(eventType, payload, SCMEvent.originOf(req));
                    break;
            }*/
//        }

        return HttpResponses.ok();
    }

    @Extension
    public static class CrumbExclusionImpl extends CrumbExclusion {
        public boolean process(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
            String pathInfo = req.getPathInfo();
            if (pathInfo != null && pathInfo.equals("/" + URL_NAME + "/notify")) {
                chain.doFilter(req, resp);
                return true;
            } else {
                return false;
            }
        }
    }
}
