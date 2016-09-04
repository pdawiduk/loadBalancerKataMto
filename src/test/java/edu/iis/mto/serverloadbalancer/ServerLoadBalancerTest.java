package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}
	 @Test
	public void balancingServerWithNoVms(){
	 	Server server = a(ServerBulider.server().withCapacity(1));
		 balancing(aServersListWith(server),anEmptyListOfVms());
		 assertThat(server, hasCurrentLoadOf(0.0d));
	 }


	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aServersListWith(Server... servers) {
		return servers;
	}

	private Server a(ServerBulider serverBulider) {
		return serverBulider.build();
	}



}
