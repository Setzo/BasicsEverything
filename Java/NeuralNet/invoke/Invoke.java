package invoke;
import java.util.Vector;

import neural.Net;
import training.CreateSampleData;
import training.TrainingData;


public class Invoke {

	public static void main(String[] args) {
		
		CreateSampleData csd = new CreateSampleData("adding.txt"
				, CreateSampleData.xor
				, "2 3 1");
		
		TrainingData trainingData = new TrainingData(csd.getSampleFile());
		
		Vector<Integer> topology = trainingData.getTopology();
		
		Net myNet = new Net(topology);
		
		int trainingPass = 0;
		
		while(!trainingData.isEof()) {
			
			System.out.printf("\nPass #%d\n", ++trainingPass);
			
			Vector<Double> input = trainingData.getInput();
			
			if(topology.get(0) != input.size()) {
				break;
			}
			
			System.out.println("Input: " + input.toString());
			myNet.feedForward(input);
			
			Vector<Double> result = myNet.getResultValues();
			System.out.print("Result: [");
			for(int i = 0; i < result.size(); i++) {
				System.out.printf("%1.6f", result.get(i));
				if(i + 1 != result.size()) {
					System.out.print(" ");
				}
			}
			System.out.print("]\n");
			
			Vector<Double> desiredOutput = trainingData.getOutput();
			System.out.println("Target: " + desiredOutput.toString());
			
			myNet.backPropagation(desiredOutput);
		}
	}
}
