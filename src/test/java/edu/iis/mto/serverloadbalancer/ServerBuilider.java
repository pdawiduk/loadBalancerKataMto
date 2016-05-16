package edu.iis.mto.serverloadbalancer;

/**
 * Created by Shogun on 2016-05-16.
 */
public class ServerBuilider {
    private int capacity;

    public ServerBuilider withCapacity(int capacity){
        this.capacity= capacity;
        return this;

    }

    public Server build(){
        return new Server();
    }
}
