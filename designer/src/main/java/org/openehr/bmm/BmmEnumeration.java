package org.openehr.bmm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmEnumeration<T> extends BmmClass {

    private Map<String, T> enumerationItems;

    public BmmEnumeration() {
        this.enumerationItems = new HashMap<String, T>();
    }

    public void addEnumerationItem(String itemName, T item) {
        enumerationItems.put(itemName, item);
    }
}
