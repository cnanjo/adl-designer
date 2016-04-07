package org.cimi.designer;

/**
 * Created by cnanjo on 3/31/16.
 */
public class Attribute<T> {
    private String name;
    private T value;

    public Attribute() {}

    public Attribute(String name) {
        this();
        this.name = name;
    }

    public Attribute(String name, T value) {
        this(name);
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String toString() {
        return name;
    }
}
