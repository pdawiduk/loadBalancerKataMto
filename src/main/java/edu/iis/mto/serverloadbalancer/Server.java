package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class Server {
    public double currentLoadPercentage;
    public int capacity;
    public static final double  MAXIMIM_LOAD = 100.0d;;
    List<Vm> vms = new ArrayList<Vm>();

    public boolean contains(Vm vm) {
        return vms.contains(vm);
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {

        currentLoadPercentage = loadOfVM(vm) * MAXIMIM_LOAD;
        vms.add(vm);
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return currentLoadPercentage + loadOfVM(vm) <= Server.MAXIMIM_LOAD;
    }

    private double loadOfVM(Vm vm) {
        return (double) vm.size/(double) capacity;
    }
}
