package gui.events;

import java.io.File;
import java.util.EventObject;
import java.util.Vector;

import controller.neural.Connection;

public class OptionsChangedEvent extends EventObject {

	private static final long serialVersionUID = 2905469104752672187L;
	
	private double eta;
	private double alpha;
	private File inputFile;
	private Vector<Vector<Vector<Connection>>> connectionWeights;
	
	public OptionsChangedEvent(Object source) {
		super(source);
	}
	
	public OptionsChangedEvent(Object source, double eta, double alpha,
			File inputFile, Vector<Vector<Vector<Connection>>> connectionWeights) {
		
		super(source);
		
		this.eta = eta;
		this.alpha = alpha;
		this.inputFile = inputFile;
		this.connectionWeights = connectionWeights;
	}
	
	public double getEta() {
		return eta;
	}

	public double getAlpha() {
		return alpha;
	}

	public File getInputFile() {
		return inputFile;
	}

	public Vector<Vector<Vector<Connection>>> getConnectionWeights() {
		return connectionWeights;
	}

	public void setEta(double eta) {
		this.eta = eta;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public void setConnectionWeights(Vector<Vector<Vector<Connection>>> connectionWeights) {
		this.connectionWeights = connectionWeights;
	}
}
