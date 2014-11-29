import java.util.ArrayList;
import java.util.HashMap;


public class AL {

	public static void main(String[] args) {
		
		ArrayList al1 = new ArrayList();
		al1.add("s0");
        al1.add("s1");
        al1.add("s2");
         
        System.out.println((String)al1.get(1));
         
        ArrayList<String> al2 = new ArrayList<String>();
         
        al2.add("n0");
        al2.add("n1");
        al2.add("n2");
         
        System.out.println(al2.get(2));
         
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        
        map.put(0, "m0");
        map.put(1, "m1");
        map.put(2, "m2");
        
        System.out.println(map.get(0));				//Wypisuje Stringa przy odpowiednim ID mapy
        System.out.println(map.get(5));
	}
}
