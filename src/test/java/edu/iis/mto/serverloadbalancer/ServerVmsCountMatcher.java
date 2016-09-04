package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server>{
    private int expectedVmsCount;
    public ServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount=expectedVmsCount;
    }

    protected boolean matchesSafely(Server server) {
        return expectedVmsCount == server.countVms();
    }

    public void describeTo(Description description) {
        description.appendText(" a server with vmos count of ").appendValue(expectedVmsCount);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText(" a server with vmos count of ").appendValue(item.countVms());
    }
}
