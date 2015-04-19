package tst;

public class User {

	private int id;
	private String login;
	
	private Address address;
	
	private int count;
	
	public User() {
		
	}
	
	public User(int id, String login) {

		this.id = id;
		this.login = login;
	}
	
	public void onCreate() {
		
		System.out.println("Created");
	}
	
	public void onDestroy() {
		
		System.out.println("Destroyed");
	}

	public void sayHello() {
		System.out.println("Hello!");
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", address=" + address
				+ ", count=" + count + "]";
	}
}
