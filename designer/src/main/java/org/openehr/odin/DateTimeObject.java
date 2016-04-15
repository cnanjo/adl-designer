package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class DateTimeObject extends PrimitiveObject<String> {

    public static DateTimeObject extractDateTimeFromContext(odinParser.Date_time_valueContext ctx) {
        String value = ctx.getText();
        DateTimeObject datetime = new DateTimeObject();
        datetime.setValue(value);
        return datetime;
    }

}
