package edu.iis.mto.serverloadbalancer;

/**
 * Created by Pawełek on 05.09.2016.
 */
public class VmBuilder implements Builder<Vm> {

    private int size;

    public VmBuilder sizeOf(int size) {
        this.size = size;
        return this;
    }

    public Vm build() {
        return new Vm();
    }

    public static  VmBuilder vm() {
        return new VmBuilder();
    }
}
