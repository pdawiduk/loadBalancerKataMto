package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class ServerLoadBalancer {
    public void balancing(Server[] servers, Vm[] vms) {
        if(vms.length > 0)
            servers[0].currentLoadPercentage = 100.0d;
    }
}
