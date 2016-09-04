package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasAVmsCountOf;
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

	 @Test
	 public void balancingServerWithOneVmsNotFillAllResources(){
		 Server server = a(ServerBulider.server().withCapacity(10));
		 Vm vm = a(vm().ofSize(1));
		 balancing(aServersListWith(server),aVmsListWith(vm));
		 assertThat(server,hasCurrentLoadOf(10.0d));
		 assertThat("server should contain the vm", server.contains(vm));
	 }

	@Test
	public void balancingServerWithEnoughRoom_fills_ServerWithAllVms(){
		Server server = a(ServerBulider.server().withCapacity(100));
		Vm firstVm = a(vm().ofSize(1));
		Vm secondVm = a(vm().ofSize(1));
		balancing(aServersListWith(server),aVmsListWith(firstVm,secondVm));
		assertThat(server,hasAVmsCountOf(2));
		assertThat("server should contain the first vm", server.contains(firstVm));
		assertThat("server should contain the second vm", server.contains(secondVm));
	}

	@Test
	public void balancingTwoServersWithOneVm(){
		Server moreLoadedServer = a(ServerBulider.server().withCapacity(100).withCurrentLoadOf(50.0d));
		Server lessLoadedServer = a(ServerBulider.server().withCapacity(100).withCurrentLoadOf(40.0d));
		Vm vm = a(vm().ofSize(10));

		balancing(aServersListWith(moreLoadedServer,lessLoadedServer),aVmsListWith(vm));

		assertThat("less server should contain vm", lessLoadedServer.contains(vm));
		assertThat("more server should not contain the vm", !moreLoadedServer.contains(vm));
	}

	@Test
	public void balanceAServerWithNotEnoughRoomShouldNOtBeFilledWithAVm(){
		Server server = a(ServerBulider.server().withCapacity(100).withCurrentLoadOf(90.0d));
		Vm vm = a(vm().ofSize(11));

		balancing(aServersListWith(server),aVmsListWith(vm));

		assertThat("server should not contain the vm", !server.contains(vm));
	}

	@Test
	public void balancingServersAndVms(){
		Server server1 = a(ServerBulider.server().withCapacity(4));
		Server server2 = a(ServerBulider.server().withCapacity(6));
		Vm vm1 = a(vm().ofSize(1));
		Vm vm2 = a(vm().ofSize(4));
		Vm vm3= a(vm().ofSize(2));

		balancing(aServersListWith(server1,server2),aVmsListWith(vm1,vm2,vm3));

		assertThat("less server 1 should contain vm1", server1.contains(vm1));
		assertThat("less server 2 should contain vm2", server2.contains(vm2));
		assertThat("less server 1 should contain vm3", server1.contains(vm3));

		assertThat(server1, hasCurrentLoadOf(75.0d));
		assertThat(server2, hasCurrentLoadOf(66.66d));


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
