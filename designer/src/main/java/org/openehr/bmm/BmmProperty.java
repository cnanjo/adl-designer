package org.openehr.bmm;

import org.openehr.adl.rm.RmCardinality;
import org.openehr.adl.rm.RmMultiplicity;
import org.openehr.odin.CompositeOdinObject;
import org.openehr.odin.IntegerIntervalObject;
import org.openehr.odin.OdinAttribute;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmProperty<T extends BmmType> extends BmmModelElement {

    public static final String P_BMM_SINGLE_PROPERTY = "P_BMM_SINGLE_PROPERTY";
    public static final String P_BMM_SINGLE_PROPERTY_OPEN = "P_BMM_SINGLE_PROPERTY_OPEN";
    public static final String P_BMM_GENERIC_PROPERTY = "P_BMM_GENERIC_PROPERTY";
    public static final String P_BMM_CONTAINER_PROPERTY = "P_BMM_CONTAINER_PROPERTY";

    private String name;
    private Boolean isMandatory;
    private Boolean isComputed;
    private T bmmType;
    private String propertyType;
    private Boolean isImRuntime;
    private Boolean isImInfrastructure;
    private BmmMultiplicityInterval existence;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return null; //TODO To be implemented
    }

    public Boolean getMandatory() {
        return isMandatory;
    }

    public void setMandatory(Boolean mandatory) {
        isMandatory = mandatory;
    }

    public Boolean getComputed() {
        return isComputed;
    }

    public void setComputed(Boolean computed) {
        isComputed = computed;
    }

    public T getBmmType() {
        return bmmType;
    }

    public void setBmmType(T bmmType) {
        this.bmmType = bmmType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Boolean getImRuntime() {
        return isImRuntime;
    }

    public void setImRuntime(Boolean imRuntime) {
        isImRuntime = imRuntime;
    }

    public Boolean getImInfrastructure() {
        return isImInfrastructure;
    }

    public void setImInfrastructure(Boolean imInfrastructure) {
        isImInfrastructure = imInfrastructure;
    }

    public BmmMultiplicityInterval getExistence() {
        return existence;
    }

    public static boolean isContainerType(String bmmType) {
        return bmmType.equals(BmmType.P_BMM_CONTAINER_TYPE) || bmmType.equals(BmmType.BMM_CONTAINER_TYPE);
    }

    public void setExistence(BmmMultiplicityInterval existence) {
        this.existence = existence;
    }

    public static BmmProperty configureBmmPropertyFromOdinObject(CompositeOdinObject propertyDef) {
        String bmmType = propertyDef.getType();
        BmmProperty property = BmmProperty.buildBmmProperty(bmmType);

        //Handle name
        String attrName = propertyDef.getAttribute("name").getStringValue();
        property.setName(attrName);

        //Handle bmmCoreType
        OdinAttribute typeAttribute = propertyDef.getAttribute("bmmCoreType");
        String attrType = null;
        if (typeAttribute != null) {
            attrType = typeAttribute.getStringValue();
            property.setPropertyType(attrType);
        }

        //Handle type_def
        OdinAttribute typeDefAttribute = propertyDef.getAttribute("type_def");
        if (typeDefAttribute != null) {
            String containerType = typeDefAttribute.getChildAttribute("container_type").getStringValue();
            String itemType = typeDefAttribute.getChildAttribute("bmmType").getStringValue();
            BmmContainerProperty containerProperty = (BmmContainerProperty)property;
            containerProperty.setPropertyType(itemType);
            containerProperty.setContainerType(containerType);
        }

        //Handle cardinality
        OdinAttribute cardinalityAttribute = propertyDef.getAttribute("cardinality");
        BmmMultiplicityInterval cardinality = null;
        if (cardinalityAttribute != null) {
            IntegerIntervalObject odinCardinality = (IntegerIntervalObject) cardinalityAttribute.getChildren().get(0);
            Integer low = null;
            Integer high = null;
            if (odinCardinality.getLow() != null) {
                low = odinCardinality.getLow().getAsInteger();
            }
            if (odinCardinality.getHigh() != null) {
                high = odinCardinality.getHigh().getAsInteger();
            }
            cardinality = new BmmMultiplicityInterval(odinCardinality);
        }

        //Handle is_mandatory
        OdinAttribute isMandatoryAttribute = propertyDef.getAttribute("is_mandatory");
        RmMultiplicity existence = null;
        if (isMandatoryAttribute != null) {
            Boolean isMandatory = isMandatoryAttribute.getBooleanValue();
            property.setMandatory(isMandatory);
        }

        return property;
    }

    public static BmmProperty<?> buildBmmProperty(String bmmCoreType) {
        BmmProperty property = null;
        if (bmmCoreType.equals(BmmType.BMM_SIMPLE_TYPE) || bmmCoreType.equals(BmmType.P_BMM_SIMPLE_TYPE) || bmmCoreType.equals(BmmProperty.P_BMM_SINGLE_PROPERTY)) {
            property = new BmmProperty<BmmSimpleType>();
            ((BmmProperty<BmmSimpleType>)property).setBmmType(new BmmSimpleType());
        } else if(bmmCoreType.equals(BmmType.BMM_SIMPLE_TYPE_OPEN) || bmmCoreType.equals(BmmType.P_BMM_SIMPLE_TYPE_OPEN) || bmmCoreType.equals(BmmProperty.P_BMM_SINGLE_PROPERTY_OPEN)) {
            property = new BmmProperty<BmmSimpleTypeOpen>();
            ((BmmProperty<BmmSimpleTypeOpen>)property).setBmmType(new BmmSimpleTypeOpen());
        } else if(bmmCoreType.equals(BmmType.BMM_GENERIC_TYPE) || bmmCoreType.equals(BmmType.BMM_GENERIC_TYPE) || bmmCoreType.equals(BmmProperty.P_BMM_GENERIC_PROPERTY)) {
            property = new BmmProperty<BmmGenericType>();
            ((BmmProperty<BmmGenericType>)property).setBmmType(new BmmGenericType());
        } else if(bmmCoreType.equals(BmmType.P_BMM_CONTAINER_TYPE) || bmmCoreType.equals(BmmType.BMM_CONTAINER_TYPE) || bmmCoreType.equals(BmmProperty.P_BMM_CONTAINER_PROPERTY)) {
            property = new BmmContainerProperty();
            property.setBmmType(new BmmContainerType());
        } else {
                throw new RuntimeException("Unrecognized bmmCoreType " + bmmCoreType);
        }
        return property;
    }
}
