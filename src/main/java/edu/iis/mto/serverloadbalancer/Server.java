package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class Server {
    public double currentLoadPercentage;
    public int capacity;

    public boolean contains(Vm vm) {
        return true;
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }
}
