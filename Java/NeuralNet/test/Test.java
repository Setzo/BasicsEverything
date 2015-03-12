package test;

import training.CreateSampleData;

public class Test {
	public static void main(String[] args) {
		
		String t = "1 2 3 7";
		CreateSampleData test = new CreateSampleData("file.txt", CreateSampleData.add, t);
	}
}
