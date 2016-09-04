package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class Server {

    public static final double FULL_PERCENTAGE = 100.0d;
    private double currentLoadPercentage;
    private int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }

    public void setCurrentLoadPercentage(double currentLoadPercentage) {
        this.currentLoadPercentage = currentLoadPercentage;
    }

    public int getCapacity() {
        return capacity;
    }

    public Server(int capacity) {
        this.capacity= capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }




    public boolean contains(Vm vm) {
        return vms.contains(vm);
    }

    public void addVm(Vm vm) {
        currentLoadPercentage+= valueOfFilling(vm);
        this.vms.add(vm);
    }

    private double valueOfFilling(Vm vm) {
        return (double) vm.getSize()/(double) capacity * FULL_PERCENTAGE;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        double valueOfFiiling = valueOfFilling(vm);
        return currentLoadPercentage +
                valueOfFiiling <=FULL_PERCENTAGE;
    }
}
