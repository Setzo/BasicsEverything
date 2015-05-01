package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("valueContainer")
public class ValueContainer {

	private Integer id = 0;
	private String value = "value";
	@Autowired(required = false)
	private String val0;
	@Autowired(required = false)
	private String val1;
	@Autowired(required = false)
	private String val2;
	@Autowired(required = false)
	private String val3;
	
	@Autowired
	public void setId(@Value("#{rng?.t?.length()}") Integer id) {
		this.id = id;
	}

	//rng.t == rng.getT()
	@Autowired
	public void setValue(@Value("#{'Random txt: ' + rng?.t}") String value) {
		this.value = value;
	}

	@Autowired
	public void setVal0(@Value("#{new java.util.Date().toString()}")String val0) {
		System.out.println(val0);
		this.val0 = val0;
	}

	@Autowired
	public void setVal1(@Value("#{T(Math).abs(T(Math).sin(56))}")String val1) {
		System.out.println(val1);
		this.val1 = val1;
	}

	@Autowired
	public void setVal2(@Value("#{T(Math).E}")String val2) {
		System.out.println(val2);
		this.val2 = val2;
	}

	/*
	 * lt <
	 * le <=
	 * gt >
	 * ge >=
	 * eq ==
	 */
	@Autowired
	public void setVal3(@Value("#{2 lt 5}")String val3) {
		System.out.println(val3);
		this.val3 = val3;
	}

	public void type() {
		System.out.println(this);
	}
	
	public String toString() {
		return this.value + " : " + this.id;
	}
	
}
