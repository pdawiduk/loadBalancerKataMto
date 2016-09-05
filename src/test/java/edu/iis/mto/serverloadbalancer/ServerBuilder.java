package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class ServerBuilder implements Builder<Server> {
    private int capacity;
    private double initialValue;
    private Server server;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity= capacity;

        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        addInitialLoad(server);
        return server;
    }

    private void addInitialLoad(Server server) {
        if(initialValue >0){
        int initialVmSize = (int) ((double)initialValue / (double) capacity * Server.MAXIMUM_FILLING);
        Vm initialVm = VmBuilder.vm().ofSize(initialVmSize).build();
        server.addVm(initialVm);}
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withInitialLoad(double initialValue) {
        this.initialValue = initialValue;
        return this;
    }
}
