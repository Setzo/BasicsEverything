package invoke;
import gui.listeners.OutputLineListener;

import java.util.Vector;

import controller.neural.Net;
import controller.training.CreateSampleData;
import controller.training.TrainingData;


public class NeuralNetInvoker {

	private OutputLineListener outputListener;
	
	public void setOutputLineListener(OutputLineListener listener) {
		this.outputListener = listener;
	}
	
	public void invokeNet() {
		
		CreateSampleData csd = new CreateSampleData("xor.txt"
				, CreateSampleData.xor
				, "2 3 1"
				, 5000);
		
		TrainingData trainingData = new TrainingData(csd.getSampleFile());
		
		Vector<Integer> topology = trainingData.getTopology();
		
		Net myNet = new Net(topology);
		
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
			myNet.feedForward(input);
			
			Vector<Double> result = myNet.getResultValues();
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
			
			myNet.backPropagation(desiredOutput);
			
			sb.delete(0, sb.length());
		}
	}
	
}
