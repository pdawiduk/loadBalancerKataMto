package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hastCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.*;
import static edu.iis.mto.serverloadbalancer.VmBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest  {
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

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){
		Server server = a(server().withCapacity(1));
		Vm vm = a(vm().ofSize(1));

		balancing(serverListWithServers(server),vmsListWith(vm));
		assertThat(server, hastCurrentLoadOf(100.0d));
		assertThat("server should contain the vm", server.contains(vm));

	}
	@Test
	public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillTheServerWithTenPercen(){
		Server server = a(server().withCapacity(10));
		Vm vm = a(vm().ofSize(3));

		balancing(serverListWithServers(server), vmsListWith(vm));
		assertThat(server,hastCurrentLoadOf(30.0d));
		assertThat("server should contain the vm", server.contains(vm));

	}



	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAllVms(){
		Server server =  a(server().withCapacity(10));
		Vm vm1 = a(vm().ofSize(1));
		Vm vm2 = a(vm().ofSize(1));

		balancing(serverListWithServers(server), vmsListWith(vm1,vm2));
		assertThat(server, hasCountofVms(2));
		assertThat("server should contain the first vm", server.contains(vm1));
		assertThat("server should contain the second vm", server.contains(vm2));
	}

	private Vm[] vmsListWith(Vm... vms) {
		return vms;
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


	private <T> T a(Builder<T> builder){
		return builder.build();
	}



}
