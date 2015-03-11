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
#include <fstream>
#include <sstream>

class Neuron;

typedef std::vector<Neuron> Layer;

//******************		TRAINING		******************

class TrainingData {

public:

	TrainingData(const std::string filename);
	bool isEof(void);
	void getTopology(std::vector<unsigned> &topology);
	unsigned getNextInputs(std::vector<double> &inputVals);		// Returns the number of input values read from the file:
	unsigned getTargetOutputs(std::vector<double> &targetOutputVals);

private:

	std::ifstream trainingDataFile;
};

TrainingData::TrainingData(const std::string filename) {

	trainingDataFile.open(filename.c_str());
}

bool TrainingData::isEof(void) {

	return trainingDataFile.eof();
}


void TrainingData::getTopology(std::vector<unsigned> &topology) {

	std::string line;
	std::string label;

	std::getline(trainingDataFile, line);
	std::stringstream ss(line);
	ss >> label;

	if (this->isEof() || label.compare("topology:") != 0) {

		abort();
	}

	while (!ss.eof()) {

		unsigned nNeuron;
		ss >> nNeuron;
		topology.push_back(nNeuron);
	}

	return;
}

unsigned TrainingData::getNextInputs(std::vector<double> &inputVals) {

	inputVals.clear();

	std::string line;
	getline(trainingDataFile, line);
	std::stringstream ss(line);

	std::string label;
	ss >> label;

	if (label.compare("in:") == 0) {

		double oneValue;
		while (ss >> oneValue) {

			inputVals.push_back(oneValue);
		}
	}

	return inputVals.size();
}

unsigned TrainingData::getTargetOutputs(std::vector<double> &targetOutputVals) {

	targetOutputVals.clear();

	std::string line;
	std::getline(trainingDataFile, line);
	std::stringstream ss(line);

	std::string label;
	ss >> label;

	if (label.compare("out:") == 0) {

		double oneValue;
		while (ss >> oneValue) {

			targetOutputVals.push_back(oneValue);
		}
	}

	return targetOutputVals.size();
}

//******************		CONNECTION		******************

class Connection {

public:

	double weight;
	double deltaWeight;
	Connection();
};

Connection::Connection() {

	weight = rand() / double(RAND_MAX);
}

//******************		NEURON		******************

class Neuron {

public:

	Neuron(unsigned numOfOutputs, unsigned myIndex);
	void setOutputValue(double val);
	double getOutputValue() const;
	void feedForward(const Layer &prevLayer);
	void calculateOutputGradients(double desiredValue);
	void calculateHiddenGradients(const Layer &nextLayer);
	void updateInputWeights(Layer &prevLayer);

private:

	static double eta;   // <0; 1>
	static double alpha; // <0; 1>
	static double transferFunction(double delta);
	static double transferFunctionDerivative(double delta);
	double sigmaDOW(const Layer &nextLayer) const;
	double outputValue;
	std::vector<Connection> outputWeights;
	unsigned myIndex;
	double gradient;
};

Neuron::Neuron(unsigned numOfOutputs, unsigned index) {

	for (unsigned currentConnection = 0; currentConnection < numOfOutputs; ++currentConnection) {

		outputWeights.push_back(Connection());
	}

	myIndex = index;
}

void Neuron::setOutputValue(double val) {

	outputValue = val;
}

double Neuron::getOutputValue(void) const {

	return outputValue;
}

void Neuron::feedForward(const Layer &prevLayer) {

	double sigma = 0.0;

	// Sum the previous layer's outputs // Include the bias node from the previous layer
	for (unsigned nNeuron = 0; nNeuron < prevLayer.size(); ++nNeuron) {

		sigma += prevLayer[nNeuron].getOutputValue()
				* prevLayer[nNeuron].outputWeights[myIndex].weight;
	}

	outputValue = Neuron::transferFunction(sigma);
}

void Neuron::calculateOutputGradients(double desiredValue) {

	double delta = desiredValue - outputValue;
	gradient = delta * Neuron::transferFunctionDerivative(outputValue);
}

void Neuron::calculateHiddenGradients(const Layer &nextLayer) {

	double dow = sigmaDOW(nextLayer);
	gradient = dow * Neuron::transferFunctionDerivative(outputValue);
}

void Neuron::updateInputWeights(Layer &prevLayer) {

	for (unsigned nNeuron = 0; nNeuron < prevLayer.size(); ++nNeuron) {

		Neuron &neuron = prevLayer[nNeuron];
		double oldDeltaWeight = neuron.outputWeights[myIndex].deltaWeight;

		double newDeltaWeight =
				eta * neuron.getOutputValue() * gradient
				+ alpha * oldDeltaWeight;

		neuron.outputWeights[myIndex].deltaWeight = newDeltaWeight;
		neuron.outputWeights[myIndex].weight += newDeltaWeight;
	}
}

double Neuron::eta = 0.15;	// Overall net learning rate <0; 1>
double Neuron::alpha = 0.5;	// Momentum - multiplier of last deltaWeight <0; 1>

double Neuron::transferFunction(double delta) {

	// Tanh - <0; 1>ss
	return tanh(delta);
}

double Neuron::transferFunctionDerivative(double delta) {

	// Tanh derivative
	return 1.0 - delta * delta;
}

double Neuron::sigmaDOW(const Layer &nextLayer) const {

	double sigma = 0.0;

	// Sum our contributions of the errors at the nodes we feed
	for (unsigned nNeuron = 0; nNeuron < nextLayer.size() - 1; ++nNeuron) {

		sigma += outputWeights[nNeuron].weight * nextLayer[nNeuron].gradient;
	}

	return sigma;
}

//******************		NET		******************

class Net {

public:

	Net(const std::vector<unsigned> &topology);
	void feedForward(const std::vector<double> &inputValues);
	void backPropagation(const std::vector<double> &desiredValues);
	void getResultValues(std::vector<double> &resultValues) const;
	double getRecentAverageError(void) const;

private:

	std::vector<Layer> layers;
	double error;
	double recentAverageError;
	static double recentAverageSmoothingFactor;
};

Net::Net(const std::vector<unsigned> &topology) {

	unsigned numLayers = topology.size();

	for (unsigned layerNum = 0; layerNum < numLayers; ++layerNum) {

		layers.push_back(Layer());

		unsigned numOutputs = layerNum == topology.size() - 1 ? 0 : topology[layerNum + 1];

		// Fill newly created layer with neurons
		for (unsigned nNeuron = 0; nNeuron <= topology[layerNum]; ++nNeuron) {

			layers.back().push_back(Neuron(numOutputs, nNeuron));
		}

		// Set bias node out to 1.0
		layers.back().back().setOutputValue(1.0);
	}
}

void Net::feedForward(const std::vector<double> &inputValues) {

	assert(inputValues.size() == layers[0].size() - 1);

	for (unsigned i = 0; i < inputValues.size(); ++i) {

		layers[0][i].setOutputValue(inputValues[i]);
	}

	for (unsigned layerNum = 1; layerNum < layers.size(); ++layerNum) {

		Layer &prevLayer = layers[layerNum - 1];
		for (unsigned nNeuron = 0; nNeuron < layers[layerNum].size() - 1; ++nNeuron) {

			layers[layerNum][nNeuron].feedForward(prevLayer);
		}
	}
}

void Net::backPropagation(const std::vector<double> &desiredValues) {

	Layer &outputLayer = layers.back();
	error = 0.0;

	for (unsigned nNeuron = 0; nNeuron < outputLayer.size() - 1; ++nNeuron) {

		double delta = desiredValues[nNeuron] - outputLayer[nNeuron].getOutputValue();
		error += delta * delta;
	}

	error /= outputLayer.size() - 1;
	// RMS
	error = sqrt(error);

	recentAverageError = (recentAverageError
			* recentAverageSmoothingFactor + error)
			/ (recentAverageSmoothingFactor + 1.0);

	// Calculate output layer gradients
	for (unsigned nNeuron = 0; nNeuron < outputLayer.size() - 1; ++nNeuron) {

		outputLayer[nNeuron].calculateOutputGradients(desiredValues[nNeuron]);
	}

	// Calculate hidden layer gradients
	for (unsigned layerNum = layers.size() - 2; layerNum > 0; --layerNum) {

		Layer &hiddenLayer = layers[layerNum];
		Layer &nextLayer = layers[layerNum + 1];

		for (unsigned nNeuron = 0; nNeuron < hiddenLayer.size(); ++nNeuron) {

			hiddenLayer[nNeuron].calculateHiddenGradients(nextLayer);
		}
	}

	// For all layers from outputs to first hidden layer, update connection weights
	for (unsigned layerNum = layers.size() - 1; layerNum > 0; --layerNum) {

		Layer &layer = layers[layerNum];
		Layer &prevLayer = layers[layerNum - 1];

		for (unsigned nNeuron = 0; nNeuron < layer.size() - 1; ++nNeuron) {

			layer[nNeuron].updateInputWeights(prevLayer);
		}
	}
}

void Net::getResultValues(std::vector<double> &resultValues) const {

	resultValues.clear();

	for (unsigned nNeuron = 0; nNeuron < layers.back().size() - 1; ++nNeuron) {

		resultValues.push_back(layers.back()[nNeuron].getOutputValue());
	}
}

double Net::getRecentAverageError(void) const {

	return recentAverageError;
}

// Number of training samples to average over
double Net::recentAverageSmoothingFactor = 100.0;

//******************		MAIN		******************

void showVectorValues(std::string label, std::vector<double> &values) {

	std::cout << label << " ";

	for (unsigned i = 0; i < values.size(); ++i) {

		std::cout << values[i] << " ";
	}

	std::cout << std::endl;
}

int main() {

	TrainingData trainData("/tmp/xor.txt");

	std::vector<unsigned> topology;
	trainData.getTopology(topology);

	Net myNet(topology);

	std::vector<double> inputVals, targetVals, resultVals;
	int trainingPass = 0;

	while (!trainData.isEof()) {

		++trainingPass;
		std::cout << std::endl << "Pass " << trainingPass;

		if (trainData.getNextInputs(inputVals) != topology[0]) {
			break;
		}

		showVectorValues(": Inputs:", inputVals);
		myNet.feedForward(inputVals);

		myNet.getResultValues(resultVals);
		showVectorValues("Outputs:", resultVals);

		trainData.getTargetOutputs(targetVals);
		showVectorValues("Targets:", targetVals);

		assert(targetVals.size() == topology.back());

		myNet.backPropagation(targetVals);

		std::cout << "Net recent average error: "
				<< myNet.getRecentAverageError() << std::endl;
	}

	std::cout << std::endl << "Done" << std::endl;
}
