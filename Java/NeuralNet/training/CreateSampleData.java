package training;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateSampleData {

	public static final int xor = 0;
	public static final int add = 1;
	
	private File sampleFile;
	
	private BufferedWriter bw;
	
	public CreateSampleData(String title, final int num, String topology) {
		
		sampleFile = new File(title);
		
		try {
			bw = new BufferedWriter(new FileWriter(sampleFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setTopology(topology.split(" "));
		
		switch(num) {
		case xor : {
			xorTraining();
			break;
		}
		case add : {
			additionTraining();
			break;
		}
		}
	}
	
	private void setTopology(String[] topology) {
		
		try {
			bw.write("topology: ");
			
			for(int i = 0; i < topology.length; i++) {
				bw.write(topology[i]);
				bw.write(" ");
			}
			
			bw.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void xorTraining() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 2000; i++) {
			
			int in0 = (int)(2 * Math.random());
			int in1 = (int)(2 * Math.random());
			
			int out = in0 ^ in1;
			
			try {
				
				sb.append("in: ").append(in0).append(" ").append(in1).append(" \n");
				bw.write(sb.toString());
				
				sb.delete(0, sb.length());
				
				sb.append("out: ").append(out).append(" \n");
				bw.write(sb.toString());
				
				sb.delete(0, sb.length());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void additionTraining() {
		
		StringBuilder sb = new StringBuilder();
		
		try {
			for(int i = 0; i < 10000; i++) {
	
				int[] in0 = new int[2];
				in0[0] = (int)(2.0 * Math.random());
				in0[1] = (int)(2.0 * Math.random());
	
				int[] in1 = new int[2];
				in1[0] = (int)(2.0 * Math.random());
				in1[1] = (int)(2.0 * Math.random());
	
				sb.append("in: ")
				.append(in0[0]).append(".0 ")
				.append(in0[1]).append(".0 ")
				.append(in1[0]).append(".0 ")
				.append(in1[1]).append(".0 ")
				.append("\n");
				
				bw.write(sb.toString());
				
				sb.delete(0, sb.length());
				
				int[] out = new int[4];
	
				if(in0[0] == 0 && in0[1] == 0 && in1[0] == 0 && in1[1] == 0) {	// 0 0 0 0
	
					out[0] = 0;
					out[1] = 0;
					out[2] = 0;
					out[3] = 0;
				}
	
				if((in0[0] == 1 && in0[1] == 0 && in1[0] == 0 && in1[1] == 1)	// 1 0 0 1
						|| (in0[0] == 0 && in0[1] == 1 && in1[0] == 1 && in1[1] == 0)) {	//0 1 1 0
	
					out[0] = 0;
					out[1] = 0;
					out[2] = 1;
					out[3] = 1;
				}
	
				if((in0[0] == 0 && in0[1] == 0 && in1[0] == 0 && in1[1] == 1)	// 0 0 0 1
						|| (in0[0] == 0 && in0[1] == 1 && in1[0] == 0 && in1[1] == 0)) { // 0 1 0 0
	
					out[0] = 0;
					out[1] = 0;
					out[2] = 0;
					out[3] = 1;
				}
	
				if((in0[0] == 0 && in0[1] == 0 && in1[0] == 1 && in1[1] == 1)	// 0 0 1 1
						|| (in0[0] == 1 && in0[1] == 1 && in1[0] == 0 && in1[1] == 0)) { // 1 1 0 0
	
					out[0] = 0;
					out[1] = 0;
					out[2] = 1;
					out[3] = 1;
				}
	
				if((in0[0] == 0 && in0[1] == 0 && in1[0] == 1 && in1[1] == 0)	// 0 0 1 0
						|| (in0[0] == 1 && in0[1] == 0 && in1[0] == 0 && in1[1] == 0)){	//1 0 0 0
	
					out[0] = 0;
					out[1] = 0;
					out[2] = 1;
					out[3] = 0;
				}
	
				if(in0[0] == 0 && in0[1] == 1 && in1[0] == 0 && in1[1] == 1) { // 0 1 0 1
	
					out[0] = 0;
					out[1] = 0;
					out[2] = 1;
					out[3] = 0;
				}
	
				if((in0[0] == 0 && in0[1] == 1 && in1[0] == 1 && in1[1] == 1)	 // 0 1 1 1
						|| (in0[0] == 1 && in0[1] == 1 && in1[0] == 0 && in1[1] == 1)){  // 1 1 0 1
	
					out[0] = 0;
					out[1] = 1;
					out[2] = 0;
					out[3] = 0;
				}
	
				if(in0[0] == 1 && in0[1] == 0 && in1[0] == 1 && in1[1] == 0) {	// 1 0 1 0
	
					out[0] = 0;
					out[1] = 1;
					out[2] = 0;
					out[3] = 0;
				}
	
				if((in0[0] == 1 && in0[1] == 0 && in1[0] == 1 && in1[1] == 1)	// 1 0 1 1
						|| (in0[0] == 1 && in0[1] == 1 && in1[0] == 1 && in1[1] == 0)) {	// 1 1 1 0
	
					out[0] = 0;
					out[1] = 1;
					out[2] = 0;
					out[3] = 1;
				}
	
				if(in0[0] == 1 && in0[1] == 1 && in1[0] == 1 && in1[1] == 1) { // 1 1 1 1
	
					out[0] = 0;
					out[1] = 1;
					out[2] = 1;
					out[3] = 0;
				}
	
				
				sb.append("out: ")
				.append(out[0]).append(".0 ")
				.append(out[1]).append(".0 ")
				.append(out[2]).append(".0 ")
				.append(out[3]).append(".0 ")
				.append("\n");
				
				bw.write(sb.toString());
				sb.delete(0, sb.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
