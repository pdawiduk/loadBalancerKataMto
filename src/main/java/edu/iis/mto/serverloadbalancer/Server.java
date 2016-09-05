package edu.iis.mto.serverloadbalancer;

import javafx.util.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class Server {
    public static final double MAXIMUM_FILLING = 100.0d;
    public double currentLoadPercentage;
    public int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(Vm vm) {
        return vms.contains(vm);
    }


    public void addVm(Vm vm) {
        currentLoadPercentage = (double) vm.size / (double) capacity * MAXIMUM_FILLING;
        vms.add(vm);
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return (currentLoadPercentage + (double)vm.size/capacity * MAXIMUM_FILLING)<=MAXIMUM_FILLING;
    }
}
