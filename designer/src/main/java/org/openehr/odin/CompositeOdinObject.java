package org.openehr.odin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cnanjo on 3/31/16.
 */
public class CompositeOdinObject extends OdinObject {
    private List<OdinAttribute> attributes;
    private Map<OdinObject, OdinObject> keyedObjects;
    private Map<String, OdinAttribute> attributeIndex;
    private String type;
    private boolean isList = false;

    public CompositeOdinObject() {
        attributes = new ArrayList<OdinAttribute>();
        keyedObjects = new HashMap<OdinObject,OdinObject>();
        attributeIndex = new HashMap<String, OdinAttribute>();
    }

    public void addAttribute(OdinAttribute attribute) {
        attributes.add(attribute);
        attributeIndex.put(attribute.getName(), attribute);
    }

    public List<OdinAttribute> getAttributes() {
        List<OdinAttribute> attributeList = new ArrayList<OdinAttribute>();
        attributeList.addAll(attributes);
        return attributeList;
    }

    public OdinObject getKeyedObject(OdinObject key) {
        return keyedObjects.get(key);
    }

    public OdinObject getKeyedObject(String key) {
        return getKeyedObject(new StringObject(key));
    }

    public List<OdinObject> getKeyedObjects() {
        List<OdinObject> keyedObjects = new ArrayList<>();
        keyedObjects.addAll(this.keyedObjects.values());
        return keyedObjects;
    }

    public void addKeyedObject(OdinObject key, OdinObject type) {
        keyedObjects.put(key, type);
    }

    public OdinAttribute retrieveAttributeFromIndex(String attributeName) {
        return attributeIndex.get(attributeName);
    }

    public OdinAttribute getAttributeAtIndex(int index) {
        return attributes.get(index);
    }

    public OdinAttribute getAttribute(String attributeName) { return attributeIndex.get(attributeName); }

    public int getAttributeCount() {
        return attributes.size();
    }

    public int getKeyedObjectCount() {
        return keyedObjects.size();
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    public String toString() {
        return attributes.toString() + " -- " + keyedObjects;
    }
}
