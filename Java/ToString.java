import java.util.Scanner;


class Person {
		
		private static int cnt = 0;
		private int id;
		private String name;
		
		public Person() {		
			id = cnt;
			cnt++;
		}
		
		public Person(String name) {
			id = cnt;
			cnt++;
			this.name = name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			//StringBuilder sb = new StringBuilder();
			//sb.append(id).append(" | ").append(name);
			//return sb.toString();	
			return String.format("%-10d : id || %-10s : name\n", id, name);
		}
}

public class ToStr {
	public static void main(String[] args) {
			
		Scanner data = new Scanner(System.in);
		
		Person x = new Person();
		Person y = new Person();
		
		x.setName(data.nextLine());
		y.setName(data.nextLine());
		
		System.out.println(x +""+ y);
		data.close();
	}
}
