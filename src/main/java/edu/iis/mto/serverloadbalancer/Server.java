package edu.iis.mto.serverloadbalancer;

import javafx.util.Builder;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class Server {
    public static final double MAXIMUM_FILLING = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(Vm vm) {
        return true;
    }


    public void addVm(Vm vm) {
        currentLoadPercentage = (double) vm.size / (double) capacity * MAXIMUM_FILLING;
    }
}
