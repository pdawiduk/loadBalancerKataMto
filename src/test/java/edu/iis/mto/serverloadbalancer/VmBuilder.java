package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class VmBuilder {

    private int size;

    public VmBuilder sizeOf(int size) {
        this.size = size;
        return this;
    }

    public Vm build() {
        return new Vm();
    }
}
