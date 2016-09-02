import java.io.Serializable;


public class Person implements Serializable {

    private static final long serialVersionUID = -4103124626927806588L;
    private static int cnt = 0;
    private int id;
    private String name;

    public Person(String name) {
        id = cnt;
        cnt++;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person #" + id + ", is named: " + name;
    }

}
