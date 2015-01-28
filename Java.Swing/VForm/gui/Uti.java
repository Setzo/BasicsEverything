package gui;

import javax.swing.ImageIcon;

public class Uti {
	
	public static String getExtension(String name) {
		
		int point = name.lastIndexOf(".");
		
		if(point == -1) {
			return null;
		}
		
		if(point == name.length()-1) {
			return null;
		}
		
		return name.substring(point + 1, name.length());
	}
	
	public static ImageIcon createIcon(String path, String description) {
		
		if(System.class.getResource(path) == null) {
			System.out.println("Wrong path");
		}
		
		return new ImageIcon(System.class.getResource(path), description);
	}
}
