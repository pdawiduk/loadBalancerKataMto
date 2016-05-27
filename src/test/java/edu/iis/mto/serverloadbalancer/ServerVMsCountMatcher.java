package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Shogun on 2016-05-27.
 */
public class ServerVMsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedVMsCount;

    public ServerVMsCountMatcher(int expectedVMsCount) {
        this.expectedVMsCount = expectedVMsCount;
    }

    protected boolean matchesSafely(Server server) {
        return expectedVMsCount == server.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with vms count of ").appendValue(item.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("a server with vms count of ").appendValue(expectedVMsCount);
    }
}
