package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class ServerBuilder implements Builder<Server> {
    private int capacity;
    private double currenLoadInit;

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
        if (currenLoadInit > 0) {
            int initialVmSize = (int) (currenLoadInit / (double) capacity * Server.MAXIMIM_LOAD);
            Vm initialVm = VmBuilder.vm().theSizeOf(initialVmSize).build();
            server.addVm(initialVm);
        }
    }

    public static ServerBuilder server() {

        return new ServerBuilder();

    }

    public ServerBuilder withCurrentLoaded(double currenLoadInit) {
        this.currenLoadInit = currenLoadInit;
        return this;
    }
}
