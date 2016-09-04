package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.*;
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
	 
	 @Test
	 	public void balancingServerWithNOneVms(){
	 		Server server = a(ServerBulider.server().withCapacity(1));
		 	Vm vm = a(vm().ofSize(1));
		 	balancing(aServersListWith(server),aVmsListWith(vm));
		 	assertThat(server,hasCurrentLoadOf(100.0d));
		 assertThat("server should contain the vm", server.contains(vm));
					
	 }

	private Vm[] aVmsListWith(Vm... vms) {
		return vms;
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

	private <T>T a(Builder<T> builder){
		return builder.build();
	}




}
