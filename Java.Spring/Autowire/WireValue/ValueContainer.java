package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("valueContainer")
public class ValueContainer {

	private int id = 0;
	private String value = "value";

	@Autowired
	public void setId(@Value("10") int id) {
		this.id = id;
	}

	@Autowired
	public void setValue(@Value("String") String value) {
		this.value = value;
	}

	public void type() {
		System.out.println(this);
	}
	
	public String toString() {
		return this.value + " : " + this.id;
	}
	
}
