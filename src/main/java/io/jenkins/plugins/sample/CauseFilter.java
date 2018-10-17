package io.jenkins.plugins.sample;

import hudson.model.Cause;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CauseFilter {
    private static final Logger LOGGER = Logger.getLogger(CauseFilter.class.getName());
    public static List<Cause> filter(List<Cause> inputCauses) {
        LOGGER.log(Level.FINER, "filtering: {0}", new Object[]{ inputCauses.size() });
        if (inputCauses == null)
            return null;

        List<Cause> outCauses = new ArrayList<Cause>();
        Set<String> causeClasses = new HashSet<String>();
        for (Cause cause : inputCauses) {
            LOGGER.log(Level.FINER,"filter cause: {0}", new Object[]{ cause });
            // filter causes by Class type and description
            String filter = getCauseFilter(cause);
            if (!causeClasses.contains(filter)) {
                causeClasses.add(filter);
                outCauses.add(cause);
            }
        }

        return outCauses;
    }

    private static String getCauseFilter(Cause cause) {
        return cause.getClass().getCanonicalName() + "_" + cause.getShortDescription();
    }
}