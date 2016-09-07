package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class ServerBuilder {

    private int capcity;

    public ServerBuilder withCapacity(int capcity) {
        this.capcity = capcity;
        return this;
    }

    public Server build() {
        return new Server();
    }
   public static ServerBuilder server() {
        return new ServerBuilder();
    }
}
