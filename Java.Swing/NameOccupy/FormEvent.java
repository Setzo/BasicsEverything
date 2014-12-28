package nameOccupy;

import java.util.EventObject;

public class FormEvent extends EventObject {

	private static final long serialVersionUID = 6409245800344985837L;
	
	private String name;
	private String occupation;
	
	public FormEvent(Object source) {
		
		super(source);
	}
	
	public FormEvent(Object source, String name, String occupation) {
		super(source);
		
		this.name = name;
		this.occupation = occupation;
	}

	public String getName() {
		return name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
}
