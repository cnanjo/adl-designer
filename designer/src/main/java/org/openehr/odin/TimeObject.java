package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class TimeObject extends PrimitiveObject<String> {

    public static TimeObject extractTimeFromContext(odinParser.Time_valueContext ctx) {
        String value = ctx.getText();
        TimeObject time = new TimeObject();
        time.setValue(value);
        return time;
    }
}
