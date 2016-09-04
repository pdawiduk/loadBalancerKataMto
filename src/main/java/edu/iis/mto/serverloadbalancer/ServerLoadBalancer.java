package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        if(vms.length>0){
             servers[0].currentLoadPercentage=(double) vms[0].size/(double) servers[0].capacity *100.0d;
        }
    }
}
