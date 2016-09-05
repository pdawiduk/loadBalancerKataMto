package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private  int expectedVmsCount;

    public ServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount= expectedVmsCount;
    }

    protected boolean matchesSafely(Server server) {
        return expectedVmsCount == server.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with vms count of ").appendValue(item.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("a server with vms count of ").appendValue(expectedVmsCount);

    }

    public static ServerVmsCountMatcher hasCountofVms(int expectedVmsCount) {
        return new ServerVmsCountMatcher(expectedVmsCount);
    }
}
