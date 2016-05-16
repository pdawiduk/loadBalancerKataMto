package edu.iis.mto.serverloadbalancer;

/**
 * Created by Shogun on 2016-05-16.
 */
public class VmBuilider implements Builider<Vm>{
    private int size ;
    public VmBuilider ofSize(int size) {
        this.size= size;
        return this;
    }

    public Vm build() {
        return new Vm();
    }
    public static VmBuilider vm() {
        return new VmBuilider();
    }
}
