package org.cimi.designer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cnanjo on 3/31/16.
 */
public class Type {
    private List<Attribute<?>> attributes;
    private Map<String, Type> keyedObjects;
    private Map<String, Attribute<?>> attributeIndex;
    private String type;

    public Type() {
        attributes = new ArrayList<Attribute<?>>();
        keyedObjects = new HashMap<String,Type>();
        attributeIndex = new HashMap<String, Attribute<?>>();
    }

    public void addAttribute(Attribute<?> attribute) {
        attributes.add(attribute);
        attributeIndex.put(attribute.getName(), attribute);
    }

    public Type getKeyedObject(String key) {
        return keyedObjects.get(key);
    }

    public void addKeyedObject(String key, Type type) {
        keyedObjects.put(key, type);
    }

    public Attribute retrieveAttributeFromIndex(String attributeName) {
        return attributeIndex.get(attributeName);
    }

    public Attribute getAttributeAtIndex(int index) {
        return attributes.get(index);
    }

    public int getAttributeCount() {
        return attributes.size();
    }

    public int getKeyedObjectCount() {
        return keyedObjects.size();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return attributes.toString() + " -- " + keyedObjects;
    }
}
