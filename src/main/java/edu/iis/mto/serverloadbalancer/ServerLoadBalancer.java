package edu.iis.mto.serverloadbalancer;

/**
 * Created by Shogun on 2016-05-16.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm : vms) {
            servers[0].addVm(vm);
        }
    }
}
