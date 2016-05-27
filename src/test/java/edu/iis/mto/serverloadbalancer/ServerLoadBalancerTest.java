package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static edu.iis.mto.serverloadbalancer.ServerBuilider.*;
import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.*;
import static edu.iis.mto.serverloadbalancer.VmBuilider.*;
import static edu.iis.mto.serverloadbalancer.ServerVMsCountMatcher.*;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
    @Test
    public void itCompiles() {
        assertThat(true, equalTo(true));
    }

    @Test
    public void balancingServer_noVm_ServerStaysEmpty() {
        Server server = a(server().withCapacity(1));

        balancing(aServersListwith(server), anEmptyListofVms());
        assertThat(server, hasCurrentLoadOf(0.0d));
    }

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsServerWithTheVm() {
        Server server = a(server().withCapacity(1));
        Vm vm = a(vm().ofSize(1));
        balancing(aServersListwith(server), aVmsListWith(vm));
        assertThat(server, hasCurrentLoadOf(100.0d));
        assertThat("server should contain the vm", server.contains(vm));
    }

    @Test
    public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillsTheServerWithTenPercent() {
        Server server = a(server().withCapacity(10));
        Vm vm = a(vm().ofSize(1));
        balancing(aServersListwith(server), aVmsListWith(vm));
        assertThat(server, hasCurrentLoadOf(10.0d));
        assertThat("server should contain the vm", server.contains(vm));
    }

    @Test
    public void balancingTheServerWithEnoughRoom_fillsTheServerWithAllVms() {
        Server server = a(server().withCapacity(100));
        Vm firstVm = a(vm().ofSize(1));
        Vm secondVm = a(vm().ofSize(1));
        balancing(aServersListwith(server), aVmsListWith(firstVm, secondVm));
        assertThat(server, hasAvmsCountOf(2));
        assertThat("server should contain the first vm", server.contains(firstVm));
        assertThat("server should contain the second vm", server.contains(secondVm));
    }

    @Test
    public void vmshouldBebalancedOnLessLoadedSererFirst() {
        Server moreLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(50.0d));
        Server lessLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(45.0d));
        Vm vm = a(vm().ofSize(10));

        balancing(aServersListwith(moreLoadedServer, lessLoadedServer), aVmsListWith(vm));

        assertThat("less loaded server should contain the vm", lessLoadedServer.contains(vm));
        assertThat("more loaded server should not contain the first vm", !moreLoadedServer.contains(vm));

    }

    @Test
    public void  balancingServerWithNotEnoughtRoom_shouldNotBeFilledWithTheVm(){
        Server server = a(server().withCapacity(10).withCurrentLoadOf(90.0d));
        Vm vm = a(vm().ofSize(2));
        balancing(aServersListwith(server), aVmsListWith(vm));
        assertThat("server should not contain the first vm", !server.contains(vm));
    }
    private Vm[] aVmsListWith(Vm... vms) {
        return vms;
    }


    private void balancing(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers, vms);
    }

    private Vm[] anEmptyListofVms() {
        return new Vm[0];
    }

    private Server[] aServersListwith(Server... servers) {
        return servers;
    }

    private <T> T a(Builider<T> builider) {
        return builider.build();
    }


}
