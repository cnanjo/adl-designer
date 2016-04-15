package org.openehr.bmm;

import org.openehr.odin.IntegerIntervalObject;

/**
 * Created by cnanjo on 4/11/16.
 */
public class BmmMultiplicityInterval extends IntegerIntervalObject {

    public BmmMultiplicityInterval() { super(); }

    public BmmMultiplicityInterval(IntegerIntervalObject interval) {
        this();
        setLow(interval.getLow());
        setHigh(interval.getHigh());
        setExcludeLowerBound(interval.isExcludeLowerBound());
        setExcludeUpperBound(interval.isExcludeUpperBound());
        setIntervalExpression(interval.getIntervalExpression());
        setType(interval.getType());
    }
}
