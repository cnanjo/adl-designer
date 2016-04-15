package org.openehr.bmm;

import java.util.List;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmClassifier extends BmmModelElement {

    private String typeCategory;
    private String asConformanceType;
    private String asTypeString;
    private BmmClass baseClass;
    private List<String> flattenedTypeList;
}
