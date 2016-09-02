package controller.neural;

import java.util.Vector;


public class Net {

    private static double recentAverageSmoothingFactor = 100.0;
    private double recentAverageError = 0.5;

    private double error;

    private Vector<Layer> layers;

    private Vector<Integer> topologyy;

    public Net(Vector<Integer> topology) {

        layers = new Vector<Layer>();

        int numLayers = topology.size();

        for (int layerNum = 0; layerNum < numLayers; ++layerNum) {

            layers.add(new Layer());

            int numOutputs = layerNum == topology.size() - 1 ? 0 : topology.get(layerNum + 1);

            for (int nNeuron = 0; nNeuron <= topology.get(layerNum); ++nNeuron) {

                layers.lastElement().add(new Neuron(numOutputs, nNeuron));
            }

            layers.lastElement().lastElement().setOutputValue(1.0);
        }
    }

    public void feedForward(Vector<Double> inputValues) {

        assert (inputValues.size() == layers.get(0).size() - 1);

        for (int nNeuron = 0; nNeuron < inputValues.size(); ++nNeuron) {

            layers.get(0).get(nNeuron).setOutputValue(inputValues.get(nNeuron));
        }

        for (int layerNum = 1; layerNum < layers.size(); ++layerNum) {

            Layer previousLayer = layers.get(layerNum - 1);
            for (int nNeuron = 0; nNeuron < layers.get(layerNum).size() - 1; ++nNeuron) {

                layers.get(layerNum).get(nNeuron).feedForward(previousLayer);
            }
        }
    }

    public void backPropagation(Vector<Double> desiredValues) {

        Layer outputLayer = layers.lastElement();
        error = 0.0;

        for (int nNeuron = 0; nNeuron < outputLayer.size() - 1; ++nNeuron) {

            double delta = desiredValues.get(nNeuron) - outputLayer.get(nNeuron).getOutputValue();
            error += (delta * delta);
        }

        error /= outputLayer.size() - 1;
        error = Math.sqrt(error);

        recentAverageError = (recentAverageError
                * recentAverageSmoothingFactor + error)
                / (recentAverageSmoothingFactor + 1.0);

        for (int nNeuron = 0; nNeuron < outputLayer.size() - 1; ++nNeuron) {

            outputLayer.get(nNeuron).calculateOutputGradients(desiredValues.get(nNeuron));
        }

        for (int layerNum = layers.size() - 2; layerNum > 0; --layerNum) {

            Layer hiddenLayer = layers.get(layerNum);
            Layer nextLayer = layers.get(layerNum + 1);

            for (int nNeuron = 0; nNeuron < hiddenLayer.size(); ++nNeuron) {

                hiddenLayer.get(nNeuron).calculateHiddenGradients(nextLayer);
            }
        }

        for (int layerNum = layers.size() - 1; layerNum > 0; --layerNum) {

            Layer layer = layers.get(layerNum);
            Layer prevLayer = layers.get(layerNum - 1);

            for (int nNeuron = 0; nNeuron < layer.size() - 1; ++nNeuron) {

                layer.get(nNeuron).updateInputWeights(prevLayer);
            }
        }
    }

    public Vector<Double> getResultValues() {

        Vector<Double> resultValues = new Vector<Double>();

        for (int nNeuron = 0; nNeuron < layers.lastElement().size() - 1; ++nNeuron) {

            resultValues.add(layers.lastElement().get(nNeuron).getOutputValue());
        }

        return resultValues;
    }

    public Vector<Vector<Vector<Connection>>> getConnectionWeights() {

        Vector<Vector<Vector<Connection>>> connections = new Vector<Vector<Vector<Connection>>>();

        for (int layNum = 0; layNum < layers.size() - 1; layNum++) {

            connections.add(new Vector<Vector<Connection>>());

            for (int nNeuron = 0; nNeuron < layers.get(layNum).size(); nNeuron++) {

                connections.get(layNum).add(layers.get(layNum).get(nNeuron).getOutputWeights());
            }
        }

        return connections;
    }

    public void setConnectionWeights(Vector<Integer> topology, Vector<Vector<Vector<Connection>>> connectionWeights) {

        int numLayers = topology.size();

        for (int layNum = 0; layNum < numLayers - 1; ++layNum) {

            for (int nNeuron = 0; nNeuron <= topology.get(layNum); ++nNeuron) {

                layers.get(layNum).get(nNeuron).setOutputWeights(connectionWeights.get(layNum).get(nNeuron));
            }
        }
    }

    public double getRecentAverageError() {

        return recentAverageError;
    }

    public Vector<Integer> getTopologyy() {
        return this.topologyy;
    }
}