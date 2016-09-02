package maine.person;

public class Person {

    private String name;

    private String city;

    private int age;

    public Person(String name, int age, String city) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", email=" + city + ", age=" + age + "]";
    }

}
