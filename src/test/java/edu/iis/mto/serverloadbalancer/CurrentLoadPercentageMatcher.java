package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private final double expected;

    public CurrentLoadPercentageMatcher(double expected) {
        this.expected = expected;
    }

    protected boolean matchesSafely(Server server) {
        return expected == server.currenLoadPercentage || Math.abs(expected - server.currenLoadPercentage) < 0.01d;
    }

    public void describeTo(Description description) {
        description.appendText("a server with Load Percentage").appendValue(expected);

    }

    public static CurrentLoadPercentageMatcher hasCurrentLoadOf(double expected) {
        return new CurrentLoadPercentageMatcher(expected);
    }
}
