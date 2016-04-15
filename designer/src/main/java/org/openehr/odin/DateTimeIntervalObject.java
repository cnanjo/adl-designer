package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class DateTimeIntervalObject extends IntervalObject<DateTimeObject, odinParser.Date_time_valueContext> {

    @Override
    protected DateTimeObject extractFromContext(odinParser.Date_time_valueContext ctx) {
        return DateTimeObject.extractDateTimeFromContext(ctx);
    }

}
