package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm:vms) {
            servers[0].addVm(vm);

        }
    }
}
