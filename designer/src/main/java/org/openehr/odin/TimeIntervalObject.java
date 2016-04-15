package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class TimeIntervalObject extends IntervalObject<TimeObject, odinParser.Time_valueContext> {

    @Override
    protected TimeObject extractFromContext(odinParser.Time_valueContext ctx) {
        return TimeObject.extractTimeFromContext(ctx);
    }

}
