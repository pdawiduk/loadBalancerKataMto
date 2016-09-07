package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 07.09.2016.
 */
public class VmBuilder {

    private int size;

    public VmBuilder theSizeOf(int size) {
        this.size = size;
        return this;
    }

    public Vm build() {
        return new Vm();
    }
}
