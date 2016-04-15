package org.openehr.bmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmPackage extends BmmModelElement {

    private String name;
    private Map<String, BmmClass> rootClasses;
    private String path;

    public BmmPackage() {
        super();
        rootClasses = new HashMap<String, BmmClass>();
    }

    public BmmPackage(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BmmClass> getRootClasses() {
        List<BmmClass> classes = new ArrayList<>();
        classes.addAll(rootClasses.values());
        return classes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addClass(String className, BmmClass bmmClass) {
        rootClasses.put(className, bmmClass);
    }

    public void addClass(BmmClass bmmClass) {
        rootClasses.put(bmmClass.getName(), bmmClass);
    }
}
