package edu.iis.mto.serverloadbalancer;

import javafx.util.Builder;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class Server {
    public double currentLoadPercentage;
    public int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(Vm vm) {
        return true;
    }


}
