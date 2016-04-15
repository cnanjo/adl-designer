package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class RealIntervalObject extends IntervalObject<RealObject, odinParser.Real_valueContext> {

    @Override
    protected RealObject extractFromContext(odinParser.Real_valueContext ctx) {
        return RealObject.extractRealObject(ctx);
    }

}
