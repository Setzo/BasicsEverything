package neural;
import java.util.Vector;


public class Neuron {

	private static double eta = 0.15;
	private static double alpha = 0.5;
	
	private Vector<Connection> outputWeights;
	
	private double outputValue;
	private double gradient;
	
	private int index;
	
	public Neuron(int numOfOutputs, int index) {
		
		outputWeights = new Vector<Connection>();

		for(int currentConnection = 0; currentConnection < numOfOutputs; ++currentConnection) {
			
			outputWeights.add(new Connection());
		}
			
		this.index = index;
	}
	
	public void setOutputValue(double outputValue) {
		
		this.outputValue = outputValue;
	}
	
	public double getOutputValue() {
		
		return this.outputValue;
	}
	
	public void feedForward(Layer previousLayer) {
		
		double sigma = 0.0;
		
		for (int nNeuron = 0; nNeuron < previousLayer.size(); ++nNeuron) {

			sigma += previousLayer.get(nNeuron).getOutputValue()
				* previousLayer.get(nNeuron).outputWeights.get(this.index).weight;
		}

		outputValue = this.transferFunction(sigma);
	}
	
	public void calculateOutputGradients(double desiredValue) {
		
		double delta = desiredValue - outputValue;
		gradient = delta * this.transferFunctionDerivative(outputValue);
	}
	
	public void calculateHiddenGradients(Layer nextLayer) {

		double dow = this.sigmaDOW(nextLayer);
		gradient = dow * this.transferFunctionDerivative(outputValue);
	}
	
	public void updateInputWeights(Layer previousLayer) {

		for (int nNeuron = 0; nNeuron < previousLayer.size(); ++nNeuron) {

			Neuron neuron = previousLayer.get(nNeuron);
			double oldDeltaWeight = neuron.outputWeights.get(index).deltaWeight;

			double newDeltaWeight =
				eta * neuron.getOutputValue() * gradient
				+ alpha * oldDeltaWeight;

			neuron.outputWeights.get(index).deltaWeight = newDeltaWeight;
			neuron.outputWeights.get(index).weight += newDeltaWeight;
		}
	}
	
	private double transferFunction(double delta) {

		// Tanh - <0; 1>ss
		return Math.tanh(delta);
	}

	private double transferFunctionDerivative(double delta) {

		// Tanh derivative
		return 1.0 - delta * delta;
	}

	private double sigmaDOW(Layer nextLayer) {

		double sigma = 0.0;

		// Sum our contributions of the errors at the nodes we feed
		for (int nNeuron = 0; nNeuron < nextLayer.size() - 1; ++nNeuron) {

			sigma += outputWeights.get(nNeuron).weight * nextLayer.get(nNeuron).gradient;
		}

		return sigma;
	}
}
