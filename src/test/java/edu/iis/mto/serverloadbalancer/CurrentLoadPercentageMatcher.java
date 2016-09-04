package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private  double expectedLoadPercentage;


    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage= expectedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {

        return areEquals(expectedLoadPercentage, server.currentLoadPercentage);
    }

    private boolean areEquals(double v1, double v2) {
        return v1 == v2 || Math.abs(v1 - v2) < 0.01d;
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of ").appendValue(expectedLoadPercentage);

    }

    public static CurrentLoadPercentageMatcher hastCurrentLoadOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }
}
