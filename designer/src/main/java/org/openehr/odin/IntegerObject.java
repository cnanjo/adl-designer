package org.openehr.odin;

import org.cimi.designer.antlr.odinParser;

/**
 * Created by cnanjo on 4/8/16.
 */
public class IntegerObject extends PrimitiveObject<String> {

    public IntegerObject() {
        super();
    }

    public IntegerObject(String value) {
        this();
        setValue(value);
    }

    public Integer getAsInteger() {
        if(getValue() !=  null) {
            return Integer.parseInt(getValue());
        } else {
            return null;
        }

    }

    public static IntegerObject extractIntegerObject(odinParser.Integer_valueContext ctx) {
        String value = ctx.getText();
        IntegerObject cInteger = new IntegerObject();
        cInteger.setValue(value);
        return cInteger;
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        } else if(!(obj instanceof IntegerObject)) {
            return false;
        }
        IntegerObject other = (IntegerObject)obj;
        if(this == other) {
            return true;
        } else if(this.getValue() != null && other.getValue() != null && this.getValue().equals(other.getValue())) {
            return true;
        } else {
            return false;
        }
    }
}
