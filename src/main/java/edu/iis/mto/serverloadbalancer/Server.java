package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Shogun on 2016-05-16.
 */
public class Server {
    public double currentLoadPercentage;

    public Server() {
    }

    public boolean contains(Vm vm) {
        return true;
    }
}
