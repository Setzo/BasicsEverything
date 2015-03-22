package invoke;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

import controller.Controller;
import controller.neural.Net;

public class NeuralNetInvokeFromFile {
	
	public static void main(String[] args) {
		
		File connectionWeightsFile = new File("conn.bin");
		
		Vector<Integer> topology = new Vector<Integer>();

		topology.add(2);
		topology.add(3);
		topology.add(1);
		
		Net myNet = new Net(topology);
		
		Controller.loadWeightsToNetwork(topology, connectionWeightsFile, myNet);
		
		try(Scanner sc = new Scanner(System.in)) {
			
			while(true) {
				
				Vector<Double> inp = new Vector<Double>();
				
				for(int i = 0; i < topology.get(0); i++) {
					
					inp.add(sc.nextDouble());
				}
	
				myNet.feedForward(inp);
				System.out.printf("%.18f\n", myNet.getResultValues().get(0));
			}
		}
	}
}
