package org.openehr.odin;

/**
 * Created by cnanjo on 4/8/16.
 */
public class PrimitiveObject<T> extends OdinObject {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isStringType() {
        return value instanceof java.lang.String;
    }

    public boolean isBooleanType() {
        return value instanceof java.lang.Boolean;
    }

    public boolean isIntegerType() {
        return value instanceof java.lang.Integer;
    }

    public String getType() {
        return this.value.getClass().getTypeName();
    }
}
