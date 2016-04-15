package org.openehr.bmm;

import org.openehr.adl.rm.RmType;
import org.openehr.odin.CompositeOdinObject;
import org.openehr.odin.OdinAttribute;
import org.openehr.odin.OdinObject;
import org.openehr.odin.StringObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmClass extends BmmClassifier {

    private String name;
    private Map<String, BmmClass> ancestors;
    private String sourceSchemaId;
    private List<String> immediateDescendants;
    private boolean isAbstract;
    private boolean isPrimitiveType;
    private boolean isOverride;
    private Map<String, BmmProperty<?>> properties;

    public BmmClass() {
        properties = new HashMap<String, BmmProperty<?>>();
        ancestors = new HashMap<String, BmmClass>();
        immediateDescendants = new ArrayList<String>();
        properties = new HashMap<String, BmmProperty<?>>();
    }

    public BmmClass(String name) {
        this();
        this.name = name;
    }

    // Add methods as needed for now.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, BmmClass> getAncestors() {
        return ancestors;
    }

    public void setAncestors(Map<String, BmmClass> ancestors) {
        this.ancestors = ancestors;
    }

    public String getSourceSchemaId() {
        return sourceSchemaId;
    }

    public void setSourceSchemaId(String sourceSchemaId) {
        this.sourceSchemaId = sourceSchemaId;
    }

    public List<String> getImmediateDescendants() {
        return immediateDescendants;
    }

    public void setImmediateDescendants(List<String> immediateDescendants) {
        this.immediateDescendants = immediateDescendants;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public boolean isPrimitiveType() {
        return isPrimitiveType;
    }

    public void setPrimitiveType(boolean primitiveType) {
        isPrimitiveType = primitiveType;
    }

    public boolean isOverride() {
        return isOverride;
    }

    public void setOverride(boolean override) {
        isOverride = override;
    }

    public void addAncestor(String name, BmmClass ancestor) {
        ancestors.put(ancestor.getName(), ancestor);
    }

    public void addProperty(BmmProperty property) {
        properties.put(property.getName(), property);
    }

    public static BmmClass configureBmmClassFromOdinObject(CompositeOdinObject odinClassDef) {
        //Handle name
        String name = odinClassDef.getAttribute("name").getStringValue();
        BmmClass classDefType = new BmmClass();
        classDefType.setName(name);

        //Handle ancestors
        OdinAttribute ancestors = odinClassDef.getAttribute("ancestors");
        if(ancestors != null) {
            List<OdinObject> ancestorList = ancestors.getChildren();
            for(OdinObject child : ancestorList) {
                String ancestor = ((StringObject) child).getValue();
                classDefType.addAncestor(ancestor, null); //TODO Handle this null
            }
        }

        //Handle is_abstract
        OdinAttribute isAbstract = odinClassDef.getAttribute("is_abstract");
        if(isAbstract != null) {
            classDefType.setAbstract(isAbstract.getBooleanValue());
        }

        //Handle properties
        OdinAttribute propertiesAttrDef = odinClassDef.getAttribute("properties");
        if(propertiesAttrDef != null) {
            List<OdinObject> properties = propertiesAttrDef.getSoleCompositeObjectBody().getKeyedObjects();
            for (int attrIndex = 0; attrIndex < properties.size(); attrIndex++) {
                BmmProperty property = BmmProperty.configureBmmPropertyFromOdinObject((CompositeOdinObject) properties.get(attrIndex));
                classDefType.addProperty(property);
            }
        }

        return classDefType;
    }
}
