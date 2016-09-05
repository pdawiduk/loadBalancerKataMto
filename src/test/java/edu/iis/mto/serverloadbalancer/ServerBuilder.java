package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class ServerBuilder implements Builder<Server>{
    private int capacity;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        return new Server(capacity);
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }
}
