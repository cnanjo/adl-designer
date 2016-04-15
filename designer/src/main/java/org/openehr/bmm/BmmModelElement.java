package org.openehr.bmm;

/**
 * Created by cnanjo on 4/11/16.
 */
public abstract class BmmModelElement extends BmmDefinitions {

    private String documentation;

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
}
