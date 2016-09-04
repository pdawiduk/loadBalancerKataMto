package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class Server {

    public static final double FULL_PERCENTAGE = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    public Server(int capacity) {
        this.capacity= capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }




    public boolean contains(Vm vm) {
        return true;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage=(double) vm.size/(double) capacity * FULL_PERCENTAGE;
    }
}
