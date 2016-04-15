package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class RealObject extends PrimitiveObject<String> {

    public Float getAsFloat() {
        return Float.parseFloat(getValue());
    }


    public static RealObject extractRealObject(odinParser.Real_valueContext ctx) {
        String value = ctx.getText();
        RealObject cReal = new RealObject();
        cReal.setValue(value);
        return cReal;
    }
}
