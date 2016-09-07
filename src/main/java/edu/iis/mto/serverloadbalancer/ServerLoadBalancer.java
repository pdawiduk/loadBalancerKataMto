package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class ServerLoadBalancer {
    public void balancing(Server[] servers, Vm[] vms) {

        for (Vm vm : vms) {

            Server lessLoadedServer = null;

            for (Server server : servers) {
                if (lessLoadedServer == null
                        || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage) {
                    lessLoadedServer = server;
                }

            }
            lessLoadedServer.addVm(vm);

        }

    }
}
