package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private final int expectedCountofVms;

    public static ServerVmsCountMatcher hasCountOfVms(int expectedCountofVms) {
        return new ServerVmsCountMatcher(expectedCountofVms);
    }

    public ServerVmsCountMatcher(int expectedCountofVms) {
        this.expectedCountofVms = expectedCountofVms;
    }

    protected boolean matchesSafely(Server server) {
        return expectedCountofVms == server.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server vms count of ").appendValue(item.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("a server vms count of ").appendValue(expectedCountofVms);

    }
}
