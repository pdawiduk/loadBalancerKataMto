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
        currentLoadPercentage += loadOfVms(vm);
        vms.add(vm);
    }

    private double loadOfVms(Vm vm) {
        return (double) vm.size / (double) capacity * MAXIMUM_FILLING;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return (currentLoadPercentage + loadOfVms(vm))<=MAXIMUM_FILLING;
    }
}
