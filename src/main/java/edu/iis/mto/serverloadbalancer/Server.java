package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class Server {
    public double currentLoadPercentage;
    public int capacity;
    private static final double  MAXIMIM_LOAD = 100.0d;;
    List<Vm> servers = new ArrayList<Vm>();

    public boolean contains(Vm vm) {
        return true;
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {

        currentLoadPercentage = (double) vm.size/(double) capacity * MAXIMIM_LOAD;
        servers.add(vm);
    }

    public int countVms() {
        return servers.size();
    }
}
