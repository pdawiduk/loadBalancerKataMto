package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 04.09.2016.
 */
public class ServerBulider implements Builder<Server> {
    private int capacity;

    public ServerBulider withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        return new Server(capacity);
    }
    public static ServerBulider server() {
        return new ServerBulider();
    }
}
