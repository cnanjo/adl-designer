package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class DurationObject extends PrimitiveObject<String> {

    public static DurationObject extractDurationFromContext(odinParser.Duration_valueContext ctx) {
        String value = ctx.getText();
        DurationObject duration = new DurationObject();
        duration.setValue(value);
        return duration;
    }

}
