package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class DateObject extends PrimitiveObject<String> {

    public static DateObject extractDateFromContext(odinParser.Date_valueContext ctx) {
        String value = ctx.getText();
        DateObject date = new DateObject();
        date.setValue(value);
        return date;
    }

}
