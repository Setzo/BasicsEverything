import java.util.Scanner;


class Person {

    private String nam;
    private int age;

    public void setName(String newName) {

        nam = newName;
    }

    public void setAge(int age) {

        this.age = age;
    }

    void introduce() {

        System.out.println(nam + " | " + age);
    }

    int yearsToRet() {                                //oblicza lata do emerytury

        int y = 65;
        y = y - age;
        return y;
    }

    int getAge() {

        return age;
    }

    public void setInfo(String name, int age) {

        setName(name);
        setAge(age);
    }
}

public class Classes {
    public static void main(String[] args) {

        Person pr1 = new Person();
        Scanner data = new Scanner(System.in);
        pr1.setInfo(data.nextLine(), data.nextInt());
        pr1.introduce();

        System.out.println(pr1.yearsToRet());
        data.close();
    }
}
