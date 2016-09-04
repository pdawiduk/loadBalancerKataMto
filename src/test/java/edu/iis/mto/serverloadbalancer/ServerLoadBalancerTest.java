package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hastCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
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
	public void balancingServernoVmServerStaysEmpty(){
		Server server = a(server().withCapacity(1));
		balancing(serverListWithServers(),emptyListOfVms());
		assertThat(server, hastCurrentLoadOf(0.0d));
	}



	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
	}

	private Vm[] emptyListOfVms() {
		return new Vm[0];
	}

	private Server[] serverListWithServers(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder serverBuilder) {
		return serverBuilder.build();
	}




}
