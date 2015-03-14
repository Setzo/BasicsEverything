package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {

	public void saveToFile(File file, StringBuilder sb) {

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			
			String[] lines = sb.toString().split("\n");
			sb.delete(0, sb.length());
			for(String line : lines) {
				bw.write(line);
				bw.newLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
