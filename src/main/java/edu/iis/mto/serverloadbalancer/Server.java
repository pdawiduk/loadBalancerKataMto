package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class Server {
    public static final double MAXIMUM = 100.0d;
    public double currentLoadPercentage;
    public int capacity;
    List<Vm> vmList = new ArrayList<Vm>();

    public boolean contains(Vm vm) {
        return vmList.contains(vm);
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage += loadOfVms(vm);
        vmList.add(vm);
    }

    private double loadOfVms(Vm vm) {
        return (double)vm.size/ (double) capacity * MAXIMUM;
    }

    public int countVms() {
        return vmList.size();
    }

    public boolean canFit(Vm vm) {
        return currentLoadPercentage +(loadOfVms(vm)) <= MAXIMUM;
    }
}
