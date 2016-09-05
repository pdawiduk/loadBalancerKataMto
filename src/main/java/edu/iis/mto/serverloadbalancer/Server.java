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
        return true;
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage =(double)vm.size/ (double) capacity * MAXIMUM;
        vmList.add(vm);
    }

    public int countVms() {
        return vmList.size();
    }
}
