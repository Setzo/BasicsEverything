
public class App {
     
    public static void main(String[] args) {
         
        Machine mach1 = new Machine();
        mach1.start();
         
        Person person1 = new Person("Bob");
        person1.greet();
         
        IInfo info1 = new Machine();			//Warto używać interfejsów, bo nie musisz patrzeć na wszystkie metody danej klasy
        info1.showInfo();						//a tylko na te, które Cię interesują
         
        IInfo info2 = person1;
        info2.showInfo();
         
        System.out.println();
         
        outputInfo(mach1);
        outputInfo(person1);
    }
     
    private static void outputInfo(IInfo info) {	//Static kompiluje się przed samym programem, więc można go używać
        info.showInfo();
    }
 
}
