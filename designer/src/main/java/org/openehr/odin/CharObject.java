package org.openehr.odin;

/**
 * Created by cnanjo on 4/8/16.
 */
public class CharObject extends PrimitiveObject<String> {
    public Character getAsChar() {
        return new Character(getValue().charAt(0));
    }
}
