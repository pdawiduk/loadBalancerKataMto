package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private final double expectatedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectatedLoadPercentage) {
        this.expectatedLoadPercentage=expectatedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {
        return expectatedLoadPercentage ==
                server.currentLoadPercentage || Math.abs(expectatedLoadPercentage - server.currentLoadPercentage)< 0.01d ;
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of ").appendValue(expectatedLoadPercentage);

    }
}
