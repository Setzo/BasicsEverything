
public class ChildClass extends PubClass {

    @Override                                        //sprawdza czy na pewno to jest Override a nie nowa metoda
    public void f1() {

        System.out.println("f1 child wykonane (ChildClass)");
        super.f1();                                    //odwołanie się do nadklasy

        System.out.println(name);                    //public można używać wszędzie
        System.out.println(name2);                    //protected można używać tylko w pakiecie
        //System.out.println(name3);				//private można używać tylko od { do } danej klasy

    }
}
