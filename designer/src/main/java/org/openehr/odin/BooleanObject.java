package org.openehr.odin;

/**
 * Created by cnanjo on 4/10/16.
 */
public class BooleanObject extends PrimitiveObject<Boolean> {

    public BooleanObject() {
        super();
    }

    public BooleanObject(Boolean value) {
        this();
        setValue(value);
    }
}
