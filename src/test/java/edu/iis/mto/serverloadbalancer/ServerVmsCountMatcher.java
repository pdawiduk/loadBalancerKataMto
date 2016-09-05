package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Pawełek on 05.09.2016.
 */

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private  int expectedCount;


    public ServerVmsCountMatcher(int expectedCount) {
        this.expectedCount= expectedCount;
    }

    protected boolean matchesSafely(Server item) {
        return expectedCount ==item.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server server, Description description) {
        description.appendText("a server with vmos count of ").appendValue(server.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("a server with vmos count of ").appendValue(expectedCount);
    }
}
