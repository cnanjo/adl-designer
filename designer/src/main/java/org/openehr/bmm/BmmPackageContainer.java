package org.openehr.bmm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmPackageContainer extends BmmModelElement implements IBmmPackageContainer {

    private String name;
    private Map<String, BmmPackage> packages;

    public BmmPackageContainer() {
        super();
        packages = new HashMap<String, BmmPackage>();
    }

    public BmmPackageContainer(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPackage(BmmPackage bmmPackage) {
        packages.put(bmmPackage.getName(), bmmPackage);
    }

    public BmmPackage getPackage(String packageName) {
        return packages.get(packageName);
    }
}
