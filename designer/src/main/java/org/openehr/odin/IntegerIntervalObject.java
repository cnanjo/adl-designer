package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;


/**
 * Created by cnanjo on 4/8/16.
 */
public class IntegerIntervalObject extends IntervalObject<IntegerObject, odinParser.Integer_valueContext> {

    @Override
    protected IntegerObject extractFromContext(odinParser.Integer_valueContext ctx) {
        return IntegerObject.extractIntegerObject(ctx);
    }
}
