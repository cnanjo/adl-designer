package org.openehr.bmm;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmContainerProperty extends BmmProperty<BmmContainerType> {

    private BmmMultiplicityInterval cardinality;

    private String containerType;

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public BmmMultiplicityInterval getCardinality() {
        return cardinality;
    }

    public void setCardinality(BmmMultiplicityInterval cardinality) {
        this.cardinality = cardinality;
    }
}
