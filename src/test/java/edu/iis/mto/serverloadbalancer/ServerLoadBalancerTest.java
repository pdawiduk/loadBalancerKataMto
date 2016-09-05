package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasVmCountOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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
	public void balancingServer_noVm_ServerStaysEmpty(){
		
		Server server = a(server().withCapacity(1));
		balancing(listWithServer(),emptyListWithVms());
		assertThat(server, hasCurrentLoadOf(0.0d));

		
	}
	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){
		Server server = a(server().withCapacity(1));
		Vm vm = a(vm().sizeOf(1));
		balancing(listWithServer(server),ListWithVms(vm));
		assertThat(server, hasCurrentLoadOf(100.0d));
		assertThat("server should contain the vm", server.contains(vm));
	}

	@Test
	public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillTheServerWithTenPercent (){
		Server server = a(server().withCapacity(10));
		Vm vm = a(vm().sizeOf(1));
		balancing(listWithServer(server),ListWithVms(vm));
		assertThat(server, hasCurrentLoadOf(10.0d));
		assertThat("server should contain the vm", server.contains(vm));
	}

	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAllVms(){
		Server server = a(server().withCapacity(10));
		Vm vm1 = a(vm().sizeOf(1));
		Vm vm2 = a(vm().sizeOf(1));
		balancing(listWithServer(server),ListWithVms(vm1,vm2));
		assertThat(server, hasVmCountOf(2));
		assertThat("server should contain the vm", server.contains(vm1));
		assertThat("server should contain the vm", server.contains(vm2));

	}
	@Test
	public void aVm_shouldBeBalanced_onLessLoadedServerFirst(){
		Server server1 = a(server().withCapacity(100).withCurrentLoadOf(25.0d));
		Server server2 = a(server().withCapacity(100).withCurrentLoadOf(55.0d));

		Vm vm = a(vm().sizeOf(1));

		balancing(listWithServer(server1,server2),ListWithVms(vm));

		assertThat("server should contain the vm", server1.contains(vm));
		assertThat("server should not contain the vm", !server2.contains(vm));
	}



	private Vm[] ListWithVms(Vm... vms) {
		return vms;
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] emptyListWithVms(Vm... vms) {
		return new Vm[0];
	}

	private Server[] listWithServer(Server... servers) {
		return servers;
	}


	private <T> T a(Builder<T> builder){
		return builder.build();
	}




}
