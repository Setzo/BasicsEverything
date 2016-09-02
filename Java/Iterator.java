import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;


public class Iter {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();

        list.add("snake");
        list.add("ant");
        list.add("lizard");
        list.add("cat");
        list.add("mosquito");

        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String el1 = it.next();
            System.out.println(el1);
            if (el1.equals("snake")) {
                it.remove();
            }
        }

        Collections.sort(list);
        System.out.println();

        for (String s : list) {
            System.out.println(s);
        }
    }
}
