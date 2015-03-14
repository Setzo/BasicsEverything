package controller.neural;
import java.util.Vector;


public class Net {

	private static double recentAverageSmoothingFactor = 100.0;
	private double recentAverageError;
	
	private double error;
	
	private Vector<Layer> layers;
	
	public Net(Vector<Integer> topology) {

		layers = new Vector<Layer>();
		
		int numLayers = topology.size();

		for (int layerNum = 0; layerNum < numLayers; ++layerNum) {

			layers.add(new Layer());

			int numOutputs = layerNum == topology.size() - 1 ? 0 : topology.get(layerNum + 1);

			// Fill newly created layer with neurons
			for (int nNeuron = 0; nNeuron <= topology.get(layerNum); ++nNeuron) {

				layers.lastElement().add(new Neuron(numOutputs, nNeuron));
			}

			// Set bias node out to 1.0
			layers.lastElement().lastElement().setOutputValue(1.0);
		}
	}
	
	public void feedForward(Vector<Double> inputValues) {

		assert(inputValues.size() == layers.get(0).size() - 1);

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
			error += delta * delta;
		}

		error /= outputLayer.size() - 1;
		// RMS
		error = Math.sqrt(error);

		recentAverageError = (recentAverageError
			* recentAverageSmoothingFactor + error)
			/ (recentAverageSmoothingFactor + 1.0);

		// Calculate output layer gradients
		for (int nNeuron = 0; nNeuron < outputLayer.size() - 1; ++nNeuron) {

			outputLayer.get(nNeuron).calculateOutputGradients(desiredValues.get(nNeuron));
		}

		// Calculate hidden layer gradients
		for (int layerNum = layers.size() - 2; layerNum > 0; --layerNum) {

			Layer hiddenLayer = layers.get(layerNum);
			Layer nextLayer = layers.get(layerNum + 1);

			for (int nNeuron = 0; nNeuron < hiddenLayer.size(); ++nNeuron) {

				hiddenLayer.get(nNeuron).calculateHiddenGradients(nextLayer);
			}
		}

		// For all layers from outputs to first hidden layer, update connection weights
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

	public double getRecentAverageError() {

		return recentAverageError;
	}
}
