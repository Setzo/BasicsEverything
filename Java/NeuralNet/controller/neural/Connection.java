package controller.neural;

import java.io.Serializable;

public class Connection implements Serializable {

	private static final long serialVersionUID = -7252378346237339337L;
	
	public double weight;
	public double deltaWeight;
	
	public Connection() {
		
		this.weight = Math.random();
	}
	
	public String toString() {
		return ((Double)this.weight).toString();
	}
}