package org.openehr.odin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnanjo on 3/31/16.
 */
public class OdinAttribute {
    private String name;
    private List<OdinObject> children;

    public OdinAttribute() {
        children = new ArrayList<OdinObject>();
    }

    public OdinAttribute(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(OdinObject child) {
        children.add(child);
    }

    /**
     * Method returns true if attributes has exactly one child and this child is
     * a primitive attribute.
     *
     * @return
     */
    public boolean isPrimitiveValuedAttribute() {
        return children.size() == 1 && children.get(0)  instanceof PrimitiveObject;
    }

    public boolean isPrimitiveList() {
        boolean primitiveList = true;
        if(children.size() < 2) {
            primitiveList = false;
        } else {
            String type = children.get(0).getClass().getName();
            for(OdinObject child : children) {
                if(!(child instanceof PrimitiveObject) && !child.getClass().getName().equals(type)) {
                    primitiveList = false;
                    break;
                }
            }
        }
        return primitiveList;
    }

    /**
     * Returns the primitive sole child object of this attribute or null otherwise
     *
     * @return
     */
    public PrimitiveObject<?> getPrimitiveObjectChild() {
        if(isPrimitiveValuedAttribute()) {
            return (PrimitiveObject<?>) children.get(0);
        } else {
            return null;
        }
    }

    public StringObject getStringObject() {
        if(isPrimitiveValuedAttribute() && getPrimitiveObjectChild() instanceof StringObject) {
            return (StringObject) children.get(0);
        } else {
            return null;
        }
    }

    public String getStringValue() {
        return getStringObject().getValue();
    }

    public List<String> getChildrenAsStringList() {
        List<String> retVal = new ArrayList<>();
        if(isPrimitiveList()) {
            for(OdinObject child : children) {
                retVal.add(((StringObject)child).getValue());
            }
        } else {
            throw new RuntimeException("Attribute must only have StringObject children");
        }
        return retVal;
    }

    public StringObject getStringObjectAt(int index) {
        return (StringObject)children.get(index);
    }

    public String getStringValueAt(int index) {
        return getStringObjectAt(index).getValue();
    }

    /**
     * Method returns child attribute with name attributeName or null if no such
     * child attribute exists.
     *
     * @param attributeName
     * @return
     */
    public OdinAttribute getChildAttribute(String attributeName) {
        OdinAttribute retVal = null;
        CompositeOdinObject attrBody = getSoleCompositeObjectBody();
        if(attrBody != null) {
            retVal = attrBody.getAttribute(attributeName);
        }
        return retVal;
    }

    public BooleanObject getBooleanObject() {
        if(isPrimitiveValuedAttribute() && getPrimitiveObjectChild() instanceof BooleanObject) {
            return (BooleanObject) children.get(0);
        } else {
            return null;
        }
    }

    public Boolean getBooleanValue() {
        return getBooleanObject().getValue();
    }

    public RealObject getRealObject() {
        if(isPrimitiveValuedAttribute() && getPrimitiveObjectChild() instanceof RealObject) {
            return (RealObject) children.get(0);
        } else {
            return null;
        }
    }

    public IntegerObject getIntegerObject() {
        if(isPrimitiveValuedAttribute() && getPrimitiveObjectChild() instanceof IntegerObject) {
            return (IntegerObject) children.get(0);
        } else {
            return null;
        }
    }

    public IntegerIntervalObject getIntegerIntervalObject() {
        if(children.get(0) instanceof IntegerIntervalObject) {
            return (IntegerIntervalObject) children.get(0);
        } else {
            return null;
        }
    }

    public CharObject getCharacterObject() {
        if(isPrimitiveValuedAttribute() && getPrimitiveObjectChild() instanceof CharObject) {
            return (CharObject) children.get(0);
        } else {
            return null;
        }
    }

    /**
     * Method returns the composite object body of this attribute or
     * null if the attribute is a primitive type, primitive list, or contains more
     * than one child.
     *
     * @return
     */
    public CompositeOdinObject getSoleCompositeObjectBody() {
        if(!isPrimitiveValuedAttribute() && children.size() == 1) {
            if(children.get(0) instanceof CompositeOdinObject) {
                return (CompositeOdinObject)children.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<OdinObject> getChildren() {
        return children;
    }

    public int getChildCount() { return children.size(); }

    public String toString() {
        return name;
    }
}
