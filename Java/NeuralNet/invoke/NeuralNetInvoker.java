package invoke;
import gui.listeners.OutputLineListener;

import java.io.File;
import java.util.Vector;

import controller.Controller;
import controller.neural.Net;
import controller.training.CreateSampleData;
import controller.training.TrainingData;


public class NeuralNetInvoker {

	private OutputLineListener outputListener;

	private Vector<Integer> topology;
	
	private Net neuralNetwork;
	private NetHandler netHandler;
	
	private File trainingFile = null;
	
	//private Vector<Vector<Vector<Connection>>> connectionWeights;
	
	public void setOutputLineListener(OutputLineListener listener) {
		this.outputListener = listener;
	}
	
	public void invokeNet() {
		
		CreateSampleData csd = new CreateSampleData("xor.txt"
				, CreateSampleData.xor
				, "2 3 1"
				, 1500);
		
		Controller.showInputFile(csd.getSampleFile());
		
		TrainingData trainingData = new TrainingData(csd.getSampleFile());
		
		topology = trainingData.getTopology();
		
		netHandler = new NetHandler(topology);
		neuralNetwork = NetHandler.getNet();
		
		int trainingPass = 0;
		StringBuilder sb = new StringBuilder();
		
		while(!trainingData.isEof()) {
			
			//System.out.printf("\nPass #%d\n", ++trainingPass);
			outputListener.lineToAppend(String.format("\nPass #%d\n", ++trainingPass));
			
			Vector<Double> input = trainingData.getInput();
			
			if(topology.get(0) != input.size()) {
				break;
			}
			
			//System.out.println("Input: " + input.toString());
			outputListener.lineToAppend(String.format("Input: %s\n", input.toString()));
			neuralNetwork.feedForward(input);
			
			Vector<Double> result = neuralNetwork.getResultValues();
			//System.out.print("Result: [");
			sb.append("Result: [");
			
			for(int i = 0; i < result.size(); i++) {
				//System.out.printf("%1.6f", result.get(i));
				sb.append(String.format("%1.6f", result.get(i)));
				if(i + 1 != result.size()) {
					//System.out.print(" ");
					sb.append(" ");
				}
			}
			//System.out.print("]\n");
			sb.append("]\n");
			outputListener.lineToAppend(sb.toString());
			
			Vector<Double> desiredOutput = trainingData.getOutput();
			//System.out.println("Target: " + desiredOutput.toString());
			outputListener.lineToAppend(String.format("Target: %s\n", desiredOutput.toString()));
			
			neuralNetwork.backPropagation(desiredOutput);
			
			sb.delete(0, sb.length());
		}
		
		/*
		connectionWeights = myNet.getConnectionWeights();
		File file = new File("conn.bin");
		System.out.println(connectionWeights);
		Controller.saveConnectionWeightsToFile(file, connectionWeights);
		System.out.println(Controller.loadConnectionWeightsFromFile(file));
		*/
	}
	
	public Net getNeuralNetwork() {
		
		return this.neuralNetwork;
	}
}