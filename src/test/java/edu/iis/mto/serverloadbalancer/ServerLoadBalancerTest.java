package edu.iis.mto.serverloadbalancer;


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
	public void oneServerWithOutVms(){
		Server server = a(server().withCapacity(10));

		balance(serverList(server), emptyListOfVms());

		assertThat(server, hasCurrentLoadOf(0.0d));
	}

	private Matcher<? super Server> hasCurrentLoadOf(double expectatedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectatedLoadPercentage);
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balancing(servers,vms);
	}

	private Vm[] emptyListOfVms() {
		return new Vm[0];
	}

	private Server[] serverList(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}


	private ServerBuilder server() {
		return new ServerBuilder();
		
	}


}
