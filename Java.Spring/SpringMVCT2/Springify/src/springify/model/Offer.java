package springify.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import springify.model.validation.ValidEmail;

public class Offer {

	private int id;
	
	@NotNull(message="Name field cannot be empty.")
	@Pattern(regexp="^[\\p{L} .'-]+$", message="Name seems to be invalid.")
	@Size(min=1, max=100, message="Name must be between 1 and 100 characters long.")
	private String name;
	
	@NotNull(message="Email field cannot be empty.")
	@ValidEmail
	@Size(min=5, max=100, message="Email must be between 5 and 100 characters long.")
	private String email;
	
	@NotNull(message="Offer text cannot be empty.")
	@Size(min=20, max=10000, message="Offer text must be between 20 and 10000 characters long.")
	private String text;
	
	public Offer() {
		
	}
	
	public Offer(String name, String email, String text) {
		
		this.name = name;
		this.email = email;
		this.text = text;
	}
	
	public Offer(int id, String name, String email, String text) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", email=" + email
				+ ", text=" + text + "]";
	}
}
