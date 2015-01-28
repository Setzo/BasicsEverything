package gui;

public class ServerInfo {

	private int id;
	private String name;
	
	protected ServerInfo(int id, String name) {
		
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return name;
	}
}
