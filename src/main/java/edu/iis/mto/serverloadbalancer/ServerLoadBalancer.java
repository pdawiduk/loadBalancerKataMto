package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm:vms) {
            addToLoadedServer(servers, vm);

        }
    }

    private void addToLoadedServer(Server[] servers, Vm vm) {
        List<Server> capableServer = new ArrayList<Server>();
        for (Server server : servers) {
            if(server.canFit(vm) )
                capableServer.add(server);
        }

        Server lessLoadedServer = findLessLoadedServer(capableServer);
    if(lessLoadedServer !=null){
        lessLoadedServer.addVm(vm);}
    }

    private Server findLessLoadedServer(List<Server> servers) {
        Server lessLoadedServer =null;
        for (Server server : servers) {
            if(lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage ){
                lessLoadedServer =server;

            }
        }
        return lessLoadedServer;
    }
}
