package org.openehr.bmm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmSchemaCore extends BmmDefinitions {

    private String rmPublisher;
    private String rmRelease;
    private String schemaName;
    private String schemaRevision;
    private String schemaLifecycleState;
    private String schemaAuthor;
    private String schemaDescription;
    private List<String> schemaContributors;
    private String archetypeParentClass;
    private String archetypeDatValueParentClass;
    private List<String> archetypeRmClosurePackages;
    private String archetypeVisualizeDescendantsOf;

    public BmmSchemaCore() {
        schemaContributors = new ArrayList<String>();
        archetypeRmClosurePackages = new ArrayList<String>();
    }

    public String getRmPublisher() {
        return rmPublisher;
    }

    public void setRmPublisher(String rmPublisher) {
        this.rmPublisher = rmPublisher;
    }

    public String getRmRelease() {
        return rmRelease;
    }

    public void setRmRelease(String rmRelease) {
        this.rmRelease = rmRelease;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaRevision() {
        return schemaRevision;
    }

    public void setSchemaRevision(String schemaRevision) {
        this.schemaRevision = schemaRevision;
    }

    public String getSchemaLifecycleState() {
        return schemaLifecycleState;
    }

    public void setSchemaLifecycleState(String schemaLifecycleState) {
        this.schemaLifecycleState = schemaLifecycleState;
    }

    public String getSchemaAuthor() {
        return schemaAuthor;
    }

    public void setSchemaAuthor(String schemaAuthor) {
        this.schemaAuthor = schemaAuthor;
    }

    public String getSchemaDescription() {
        return schemaDescription;
    }

    public void setSchemaDescription(String schemaDescription) {
        this.schemaDescription = schemaDescription;
    }

    public List<String> getSchemaContributors() {
        return schemaContributors;
    }

    public void setSchemaContributors(List<String> schemaContributors) {
        this.schemaContributors = schemaContributors;
    }

    public String getArchetypeParentClass() {
        return archetypeParentClass;
    }

    public void setArchetypeParentClass(String archetypeParentClass) {
        this.archetypeParentClass = archetypeParentClass;
    }

    public String getArchetypeDatValueParentClass() {
        return archetypeDatValueParentClass;
    }

    public void setArchetypeDatValueParentClass(String archetypeDatValueParentClass) {
        this.archetypeDatValueParentClass = archetypeDatValueParentClass;
    }

    public List<String> getArchetypeRmClosurePackages() {
        return archetypeRmClosurePackages;
    }

    public void setArchetypeRmClosurePackages(List<String> rmClosurePackages) {
        this.archetypeRmClosurePackages = rmClosurePackages;
    }

    public void addArchetypeRmClosurePackage(String rmClosurePackage) {
        archetypeRmClosurePackages.add(rmClosurePackage);
    }

    public String getArchetypeVisualizeDescendantsOf() {
        return archetypeVisualizeDescendantsOf;
    }

    public void setArchetypeVisualizeDescendantsOf(String archetypeVisualizeDescendantsOf) {
        this.archetypeVisualizeDescendantsOf = archetypeVisualizeDescendantsOf;
    }
}
