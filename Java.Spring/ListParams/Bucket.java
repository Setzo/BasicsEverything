package bucket;

import java.util.List;

public class Bucket {

	private String id;
	
	private List<String> sth;
	
	public Bucket(String id, List<String> sth) {
		
		this.id = id;
		this.sth = sth;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(id);
		
		for(String st : sth) {
			
			sb.append("\n");
			sb.append(st);
		}
		
		return sb.toString();
	}
}
