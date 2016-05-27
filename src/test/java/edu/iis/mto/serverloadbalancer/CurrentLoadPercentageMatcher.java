package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;


/**
 * Created by Shogun on 2016-05-16.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoadPercentage;
    private static final double EPSILON=0.01d;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    public boolean matchesSafely(Server server) {

        return doublesAreEqual(expectedLoadPercentage, server.getCurrentLoadPercentage());
    }

    protected boolean doublesAreEqual(double d1, double d2) {

        return d1 == d2 ||
                Math.abs(d1 - d2) <  EPSILON;
    }



    public void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with load percentage of ").appendValue(item.getCurrentLoadPercentage());
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of ").appendValue(expectedLoadPercentage);
    }

    public static CurrentLoadPercentageMatcher hasCurrentLoadOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }
}
