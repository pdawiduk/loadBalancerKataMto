package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static edu.iis.mto.serverloadbalancer.ServerBuilider.*;
import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.*;

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
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsServerWithTheVm(){
        Server server = a(server().withCapacity(1));
        Vm vm = a(vm().ofSize(1));
        balancing(aServersListwith(server), aVmsListWith(vm));
        assertThat(server, hasCurrentLoadOf(100.0d));
        assertThat("server should contain the vm", server.contains(vm));
    }

    private Vm[] aVmsListWith(Vm... vms) {
        return vms;
    }

    private Vm a(VmBuilider vmBuilider) {
        return vmBuilider.build();
    }

    private VmBuilider vm() {
        return new VmBuilider();
    }

    private void balancing(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers,vms);
    }

    private Vm[] anEmptyListofVms(){
        return new Vm[0];
    }

    private Server[] aServersListwith(Server... servers) {
        return servers;
    }

    private Server a(ServerBuilider builder) {
        return builder.build();
    }



}
