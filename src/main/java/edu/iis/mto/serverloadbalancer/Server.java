package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Shogun on 2016-05-16.
 */
public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
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
        currentLoadPercentage = loadOfVm(vm);
        this.vms.add(vm);
    }

    private double loadOfVm(Vm vm) {
        return (double) vm.size / (double) capacity * MAXIMUM_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm)
    {
        return currentLoadPercentage + (loadOfVm(vm)) <= MAXIMUM_LOAD ;
    }
}
