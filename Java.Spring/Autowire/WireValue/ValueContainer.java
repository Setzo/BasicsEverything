package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("valueContainer")
public class ValueContainer {

	private Integer id = 0;
	private String value = "value";

	@Autowired
	public void setId(@Value("#{rng?.t?.length()}") Integer id) {
		this.id = id;
	}

	//rng.t == rng.getT()
	@Autowired
	public void setValue(@Value("#{'Random txt: ' + rng?.t}") String value) {
		this.value = value;
	}

	public void type() {
		System.out.println(this);
	}
	
	public String toString() {
		return this.value + " : " + this.id;
	}
	
}
