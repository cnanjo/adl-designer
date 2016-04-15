package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class DurationIntervalObject extends IntervalObject<DurationObject, odinParser.Duration_valueContext> {

    @Override
    protected DurationObject extractFromContext(odinParser.Duration_valueContext ctx) {
        return DurationObject.extractDurationFromContext(ctx);
    }

}
