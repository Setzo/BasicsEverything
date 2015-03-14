package controller.training;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;


public class TrainingData {

	private static Scanner sc;
	
	public TrainingData(String path) {
		
		try {
			sc = new Scanner(new File(path));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public TrainingData(File file) {
		
		try {
			sc = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEof() {
		
		return !sc.hasNextLine();
	}
	
	public Vector<Integer> getTopology() {
		
		Vector<Integer> topology = new Vector<Integer>();
		
		String line = sc.nextLine();
		
		String[] topologyArray = line.split(" ");
		
		for(int i = 1; i < topologyArray.length; i++) {
			topology.add(Integer.parseInt(topologyArray[i]));
		}
		
		return topology;
	}
	
	public Vector<Double> getInput() {
		
		Vector<Double> input = new Vector<Double>();
		
		String line = sc.nextLine();
		
		String[] inputArray = line.split(" ");
		
		for(int i = 1; i < inputArray.length; i++) {
			input.add(Double.parseDouble(inputArray[i]));
		}
		
		return input;
	}
	
	public Vector<Double> getOutput() {
		
		Vector<Double> output = new Vector<Double>();
		
		String line = sc.nextLine();
		
		String[] outputArray = line.split(" ");
		
		for(int i = 1; i < outputArray.length; i++) {
			output.add(Double.parseDouble(outputArray[i]));
		}
		
		return output;
	}
	
	public void test() {
		
		Vector<Integer> vec = new Vector<Integer>();
		Vector<Double> in = new Vector<Double>();
		Vector<Double> out = new Vector<Double>();
		
		String topologyLine = sc.nextLine();
		
		String[] topologyArray = topologyLine.split(" ");
		
		for(int i = 1; i < topologyArray.length; i++) {
			vec.add(Integer.parseInt(topologyArray[i]));
		}
		
		System.out.println(vec);

		boolean isInput = true;
		String[] inOutArray;
		
		if(sc.hasNextLine()) {
			
			String line = sc.nextLine();
			
			if(isInput) {
				
				inOutArray = line.split(" ");
				
				for(int i = 1; i < inOutArray.length; i++) {
					in.add(Double.parseDouble(inOutArray[i]));
				}
				System.out.println(in);
				
			} else {
				
				inOutArray = line.split(" ");
				
				for(int i = 1; i < inOutArray.length; i++) {
					out.add(Double.parseDouble(inOutArray[i]));
				}
				System.out.println(out);
			}
			
			isInput = !isInput;
		}
	}
}
