package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
