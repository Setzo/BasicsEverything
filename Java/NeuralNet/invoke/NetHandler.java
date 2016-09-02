package invoke;

import gui.events.OptionsChangedEvent;

import java.util.Vector;

import controller.neural.Net;
import controller.neural.Neuron;

public class NetHandler {

    private static Net net;

    public NetHandler(Vector<Integer> topology) {

        net = new Net(topology);
    }

    public static Net getNet() {
        return net;
    }

    public void setSettings(OptionsChangedEvent e) {

        Neuron.setEta(e.getEta());
        Neuron.setAlpha(e.getAlpha());
        net.setConnectionWeights(net.getTopologyy(), e.getConnectionWeights());
    }
}
