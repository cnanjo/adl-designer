package org.openehr.bmm;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmGenericParameter extends BmmClassifier {

    private String name;

    public BmmGenericParameter() {
        super();
    }

    public BmmGenericParameter(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
