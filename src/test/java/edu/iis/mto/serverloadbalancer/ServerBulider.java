package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class ServerBulider implements Builder<Server> {
    private int capacity;
    private double initialLoad;


    public ServerBulider withCapacity(int capacity) {
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
        int initalVmSize =(int)((double) initialLoad/(double) capacity * Server.FULL_PERCENTAGE);
        Vm initialVm = VmBuilder.vm().ofSize(initalVmSize).build();
        server.addVm(initialVm);}
    }

    public static ServerBulider server() {
        return new ServerBulider();
    }

    public ServerBulider withCurrentLoadOf(double initialLoad) {
        this.initialLoad= initialLoad;
        return this;
    }
}
