package tst;

public class PersonFactory {

	public User createPerson(int id, String login) {
		
		System.out.println("createPerson");
		return new User(id, login);
	}
}
