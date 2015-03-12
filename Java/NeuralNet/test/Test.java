package test;

import training.CreateSampleData;

public class Test {
	public static void main(String[] args) {
		
		String t = "2 3 1";
		CreateSampleData test = new CreateSampleData("file.txt", CreateSampleData.xor, t);
	}
}
