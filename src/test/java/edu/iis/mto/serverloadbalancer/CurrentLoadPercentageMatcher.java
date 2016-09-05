package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage =expectedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {

        return areEqals(this.expectedLoadPercentage, server.currentLoadPercentage);
    }

    private boolean areEqals(double d1, double d2) {
        return d1 == d2
                || Math.abs(d1 - d2)< 0.01d;
    }

    public void describeTo(Description description) {
description.appendText("a server with load percentage of ").appendValue(expectedLoadPercentage);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with load percentage of ").appendValue(item.currentLoadPercentage);
    }

    public static CurrentLoadPercentageMatcher hasCurrentLoadOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }
}
