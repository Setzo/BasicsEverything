import java.util.ArrayList;

class Type1 {

    public String toString() {
        return "Type1 toString";
    }

    public void start() {
        System.out.println("Type1 start");
    }

}

class Type2 extends Type1 {

    @Override
    public String toString() {
        return "Type2 toString";
    }

    public void snap() {
        System.out.println("Type2 snap");
    }
}

public class GenWAL {

    public static void main(String[] args) {

        ArrayList<Type1> list1 = new ArrayList<Type1>();

        list1.add(new Type1());
        list1.add(new Type1());

        ArrayList<Type2> list2 = new ArrayList<Type2>();

        list2.add(new Type2());
        list2.add(new Type2());

        showList(list2);
        showList2(list1);
        showList3(list1);
    }

    public static void showList(ArrayList<? extends Type1> list) {        //metoda z param ArrayList od childklas Type1, lub samo Type1
        for (Type1 i : list) {                                            //for przez całą listę
            System.out.println(i);                                        //wypisz toStringa od danej klasy
            i.start();                                                    //wiadomo, że wszystkie klasy będą miały metodę start, bo zostanie odziedziczona
        }

    }

    public static void showList2(ArrayList<? super Type2> list) {        //metoda z param ArrayList od nadklas Type2, lub samo Type2
        for (Object i : list) {                                            //for przez całą listę
            System.out.println(i);                                        //wypisz toStringa od danej klasy
        }                                                                //metod można uzywać tylko za pomocą downcastu Objectu
    }

    public static void showList3(ArrayList<?> list) {                    //metoda z param ArrayList nieznanego typu||   <?> = wildcard
        for (Object i : list) {                                            //metod można uzywać tylko za pomocą downcastu Objectu
            System.out.println(i);
        }
    }


}
