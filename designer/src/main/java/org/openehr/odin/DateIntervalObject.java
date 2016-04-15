package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class DateIntervalObject extends IntervalObject<DateObject, odinParser.Date_valueContext> {

    @Override
    protected DateObject extractFromContext(odinParser.Date_valueContext ctx) {
        return DateObject.extractDateFromContext(ctx);
    }

}
