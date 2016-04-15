package org.openehr.odin;

/**
 * Created by cnanjo on 4/8/16.
 */
public class StringObject extends PrimitiveObject<String> {

    public StringObject() {
        super();
    }

    public StringObject(String value) {
        this();
        setValue(value);
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        } else if(!(obj instanceof StringObject)) {
            return false;
        }
        StringObject other = (StringObject)obj;
        if(this == other) {
            return true;
        } else if(this.getValue() != null && other.getValue() != null && this.getValue().equals(other.getValue())) {
            return true;
        } else {
            return false;
        }
    }
}
