package org.openehr.bmm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmGenericClass extends BmmClass {

    private List<BmmGenericParameter> genericParameters;

    public BmmGenericClass() {
        super();
        genericParameters = new ArrayList<>();
    }

    public BmmGenericClass(String name) {
        this();
        setName(name);
    }

    public void addGenericParameter(BmmGenericParameter genericParameter) {
        genericParameters.add(genericParameter);
    }

    public List<BmmGenericParameter> getGenericParameters() {
        return genericParameters;
    }

    public void setGenericParameters(List<BmmGenericParameter> genericParameters) {
        this.genericParameters = genericParameters;
    }
}
