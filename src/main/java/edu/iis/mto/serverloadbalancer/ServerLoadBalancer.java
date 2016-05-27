package edu.iis.mto.serverloadbalancer;

/**
 * Created by Shogun on 2016-05-16.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        if (vms.length > 0) {
            servers[0].currentLoadPercentage = (double) vms[0].size / (double) servers[0].capacity * 100.0d;
        }
    }
}
