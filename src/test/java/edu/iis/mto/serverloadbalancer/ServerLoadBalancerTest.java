package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.*;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasCountOfVms;
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
	public void oneServerWithOutVms(){
		Server server = a(server().withCapacity(10));

		balance(serverList(server), emptyListOfVms());

		assertThat(server, hasCurrentLoadOf(0.0d));
	}

	@Test
	public void oneServerWithOneVms(){
		Server server = a(server().withCapacity(1));
		Vm vm = a(vm().theSizeOf(1));
		balance(serverList(server), listWithOfVms(vm));

		assertThat(server, hasCurrentLoadOf(100.0d));
		assertThat("server should contain the vm", server.contains(vm));
	}

	@Test
	public void balancingOneServerWithTenSlotsCapacity(){
		Server server = a(server().withCapacity(10));
		Vm vm = a(vm().theSizeOf(1));
		balance(serverList(server), listWithOfVms(vm));

		assertThat(server, hasCurrentLoadOf(10.0d));
		assertThat("server should contain the vm", server.contains(vm));
	}

	@Test
	public void balancingTheServerWithEnoughRoom_fillsTheServerWithAllVms(){

		Server server = a(server().withCapacity(10));
		Vm vm1 = a(vm().theSizeOf(1));
		Vm vm2 = a(vm().theSizeOf(1));
		balance(serverList(server), listWithOfVms(vm1,vm2));

		assertThat(server, hasCountOfVms(2));
		assertThat("server should contain the vm", server.contains(vm1));
		assertThat("server should contain the vm", server.contains(vm2));
	}



	private Vm[] listWithOfVms(Vm... vms) {
		return vms;
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

	private  <T> T a(Builder<T> builder){
		return builder.build();
	}



}
