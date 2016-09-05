package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class ServerBuilder implements Builder<Server>{
    private int capacity;
    private double initialLoad;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        addInitialLoad(server);
        return server;
    }

    private void addInitialLoad(Server server) {
        if(initialLoad>0){
        int initialVmSize = (int) (initialLoad /(double) capacity *100.0d);
        Vm initialVm = VmBuilder.vm().sizeOf(initialVmSize).build();
        server.addVm(initialVm);}
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withCurrentLoadOf(double initialLoad) {

        this.initialLoad= this.initialLoad;
        return this;
    }
}
