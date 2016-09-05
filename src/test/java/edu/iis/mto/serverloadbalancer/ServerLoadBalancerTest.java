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
		assertThat("server should contain the first vm ", server.contains(vm1));
		assertThat("server should contain the second vm ", server.contains(vm2));
	}
	@Test
	public void aVm_shouldBeBalanced_onLessLoadedServerFirst(){
		Server moreLoadedServer = a(server().withCapacity(100).withInitialLoad(30.0d));
		Server lessLoadedServer = a(server().withCapacity(100).withInitialLoad(25.0d));
		Vm vm = a(vm().ofSize(50));
		balancing(serverListWithServers(moreLoadedServer,lessLoadedServer), vmsListWith(vm));

		assertThat("less server should  contain the vm ", lessLoadedServer.contains(vm));
		assertThat("more server should not contain the vm ", !moreLoadedServer.contains(vm));

	}
	@Test
	public void balanceAServerWithNotEnoughRoom_shouldNotBeFilledWithAVm(){
		Server server= a(server().withCapacity(15).withInitialLoad(99.0d));
		Vm vm = a(vm().ofSize(40));
		balancing(serverListWithServers(server), vmsListWith(vm));
		assertThat("server should contain the vm ", !server.contains(vm));
	}
	@Test
	public void balance_serversAndVms(){
		Server server1 = a(server().withCapacity(4));
		Server server2= a(server().withCapacity(6));

		Vm vm1 = a(vm().ofSize(1));
		Vm vm2 = a(vm().ofSize(4));
		Vm vm3 = a(vm().ofSize(2));

		balancing(serverListWithServers(server1,server2), vmsListWith(vm1,vm2,vm3));

		assertThat("server should contain the vm ", server1.contains(vm1));
		assertThat("server should contain the vm ", server2.contains(vm2));
		assertThat("server should contain the vm ", server1.contains(vm3));

		assertThat(server1, hastCurrentLoadOf(75.0d));
		assertThat(server2, hastCurrentLoadOf(66.66d));


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
