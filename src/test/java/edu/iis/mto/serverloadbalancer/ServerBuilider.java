package edu.iis.mto.serverloadbalancer;

/**
 * Created by Shogun on 2016-05-16.
 */
public class ServerBuilider implements Builider<Server> {
    private int capacity;
    private double initialLoad;

    public ServerBuilider withCapacity(int capacity) {
        this.capacity = capacity;
        return this;

    }

    public Server build() {

        Server server = new Server(capacity);
        if(initialLoad > 0){
        int initialVmSize = (int) (initialLoad / (double) capacity * 100.0d);
        Vm initialVm = VmBuilider.vm().ofSize(initialVmSize).build();
        server.addVm(initialVm);}
        return server;
    }

    public static ServerBuilider server() {
        return new ServerBuilider();
    }

    public ServerBuilider withCurrentLoadOf(double initialLoad) {
        this.initialLoad = initialLoad;
        return this;
    }
}
