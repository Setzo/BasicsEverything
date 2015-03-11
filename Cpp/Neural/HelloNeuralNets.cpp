/*
 * Neural.cpp
 *
 *  Created on: 10 mar 2015
 *      Author: Setzo
 */
#include <vector>
#include <iostream>
#include <cstdlib>
#include <cassert>
#include <math.h>

class Neuron;

typedef std::vector<Neuron> Layer;

// *********************	CONNECTION		*********************

class Connection {

public:
	Connection();
	double weight;
	double deltaWeight;
};

Connection::Connection() {

	weight = rand() / double(RAND_MAX);
}

// *********************	NEURON		*********************

class Neuron {

public:

	Neuron(unsigned numOfOutputs, unsigned index);
	void feedForward(const Layer &previousLayer);
	void setOutputValue(unsigned outputValue);
	unsigned getOutputValue() const;
	void calculateOutputGradients(double desiredValue);
	void calculateHiddenGradients(const Layer &nextLayer);
	void updateInputWeights (Layer &previousLayer);

private:

	static double eta;		//overall learning rate <0; 1>
	static double alpha; 	//momentum <0; n>
	static double transferFunction(double sigma);
	static double transferFunctionDerivative(double sigma);
	double sigmaDOW(const Layer &nextLayer);
	double outputValue;
	std::vector<Connection> outputWeights;
	unsigned myIndex;
	double gradient;
};

Neuron::Neuron(unsigned numOfOutputs, unsigned index) {

	//Fill weights with connection objects
	for(unsigned numOfConnections = 0; numOfConnections < numOfOutputs; ++numOfConnections) {

		outputWeights.push_back(Connection());
	}

	myIndex = index;
}

void Neuron::feedForward(const Layer &previousLayer) {

	double sigma = 0.0;

	//Sum previous Layer outputs //include bias node
	for(unsigned nNeuron = 0; nNeuron < previousLayer.size(); ++nNeuron) {

		sigma += previousLayer[nNeuron].getOutputValue() *
				previousLayer[nNeuron].outputWeights[myIndex].weight;
	}

	outputValue = Neuron::transferFunction(sigma);
}

void Neuron::setOutputValue(unsigned outputVal) {

	outputValue = outputVal;
}

unsigned Neuron::getOutputValue() const {

	return outputValue;
}

void Neuron::calculateOutputGradients(double desiredValue) {

	double delta = desiredValue - outputValue;
	gradient = delta * Neuron::transferFunctionDerivative(outputValue);
}

void Neuron::calculateHiddenGradients(const Layer &nextLayer) {

	double dow = Neuron::sigmaDOW(nextLayer);
	gradient = dow * Neuron::transferFunctionDerivative(outputValue);
}

void Neuron::updateInputWeights (Layer &previousLayer) {

	for(unsigned nNeuron = 0; nNeuron < previousLayer.size(); ++nNeuron) {

		Neuron &neuron = previousLayer[nNeuron];

		double oldDeltaWeight = neuron.outputWeights[myIndex].deltaWeight;

		double newDeltaWeight =
				eta * neuron.getOutputValue() * gradient
				+ alpha * oldDeltaWeight;

		neuron.outputWeights[myIndex].deltaWeight = newDeltaWeight;
		neuron.outputWeights[myIndex].weight += newDeltaWeight;
	}
}

double Neuron::eta = 0.15;

double Neuron::alpha = 0.5;

double Neuron::sigmaDOW(const Layer &nextLayer) {

	double sigma = 0.0;

	//Sum of the contributions of the errors at the nodes we feed
	for(unsigned nNeuron = 0; nNeuron < nextLayer.size() - 1; ++nNeuron) {

		sigma += outputWeights[nNeuron].weight * nextLayer[nNeuron].gradient;
	}

	return sigma;
}

double Neuron::transferFunction(double sigma) {

	//Tanh <-1 ; 1>
	return tanh(sigma);
}

double Neuron::transferFunctionDerivative(double sigma) {

	//Tanh derivative
	return 1.0 * sigma * sigma;
}

// *********************	NET		*********************

class Net {

public:

	Net (const std::vector<unsigned> &topology);
	void feedForward(const std::vector<double> &inputValues);
	void backPropagation(const std::vector<double> &desiredOutputs);
	void getResults(std::vector<double> &resultValues) const;

private:
	std::vector<Layer> layers;
	double error;
	double recentAverageError;
	double recentAverageSmoothingFactor;
};

Net::Net (const std::vector<unsigned> &topology) {

	unsigned numOfLayers = topology.size();

	for(unsigned currentLayer = 0; currentLayer < numOfLayers; ++currentLayer) {

		layers.push_back(Layer());
		//Adding new layer of neurons

		unsigned numOfOutputs = currentLayer == topology.size() - 1 ? 0 : topology[currentLayer + 1];

		for(unsigned currentNeuron = 0; currentNeuron <= topology[currentLayer]; ++currentNeuron) {

			layers.back().push_back(Neuron(numOfOutputs, currentNeuron));
			//Filling newly created layer with neurons
		}

		layers.back().back().setOutputValue(1.0);
	}
}

void Net::feedForward(const std::vector<double> &inputValues) {

	assert(inputValues.size() == layers[0].size() - 1);

	//Assign input values into the input neurons
	for(unsigned currentInput = 0; currentInput < inputValues.size(); ++currentInput) {

		layers[0][currentInput].setOutputValue(inputValues[currentInput]);
	}

	//Forward propagation
	for(unsigned layerNum = 1; layerNum < layers.size(); ++layerNum) {

		Layer &previousLayer = layers[layerNum - 1];

		for(unsigned nNeuron = 0; nNeuron < layers[layerNum].size() - 1; ++nNeuron) {

			layers[layerNum][nNeuron].feedForward(previousLayer);
		}
	}
}

void Net::backPropagation(const std::vector<double> &desiredOutputs) {

	//Calculate overall net error RMS
	Layer &outputLayer = layers.back();

	error = 0.0;

	for(unsigned nNeuron = 0; nNeuron < outputLayer.size(); ++nNeuron) {

		double delta = desiredOutputs[nNeuron] - outputLayer[nNeuron].getOutputValue();
		error += delta * delta;
	}

	error /= outputLayer.size() - 1;
	error = sqrt(error);				//RMS

	//Implement a recent average measurement
	recentAverageError =
			(recentAverageError * recentAverageSmoothingFactor + error)
			/ (recentAverageSmoothingFactor + 1.0);


	//Calculate output layer gradients
	for(unsigned nNeuron = 0; nNeuron < outputLayer.size() - 1; ++nNeuron) {

		outputLayer[nNeuron].calculateOutputGradients(desiredOutputs[nNeuron]);
	}

	//Calculate gradients on hidden layers
	for(unsigned layerNum = layers.size() - 2; layerNum > 0; --layerNum) {

		Layer &hiddenLayer = layers[layerNum];
		Layer &nextLayer = layers[layerNum + 1];

		for(unsigned nNeuron = 0; nNeuron < hiddenLayer.size(); ++nNeuron) {

			hiddenLayer[nNeuron].calculateHiddenGradients(nextLayer);
		}
	}

	//For all layers update connection weights
	for(unsigned layerNum = layers.size() - 1; layerNum > 0; --layerNum) {

		Layer &currentLayer = layers[layerNum];
		Layer &previousLayer = layers[layerNum - 1];

		for(unsigned nNeuron = 0; nNeuron < layers.size(); ++nNeuron) {

			currentLayer[nNeuron].updateInputWeights(previousLayer);
		}
	}
}

void Net::getResults(std::vector<double> &resultValues) const {

	resultValues.clear();

	for(unsigned nNeuron = 0; nNeuron < layers.back().size() - 1; ++nNeuron) {

		resultValues.push_back(layers.back()[nNeuron].getOutputValue());
	}
}

// *********************	MAIN		*********************

int main() {

	std::vector<unsigned> topology;
	topology.push_back(2);
	topology.push_back(4);
	topology.push_back(1);
	Net myNet(topology);

	std::vector<double> inputValues;
	myNet.feedForward(inputValues);

	std::vector<double> desiredOutputs;
	myNet.backPropagation(desiredOutputs);

	std::vector<double> resultValues;
	myNet.getResults(resultValues);

	return 0;
}
