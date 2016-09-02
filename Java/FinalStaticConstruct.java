
class Thing {

    public final static int LN = 45;                //stała statyczna
    public static int cnt = 0;                    //counter na 0
    public int a, id;
    public static String word = null;                //statyczny string

    public Thing() {                            //constructor

        id = cnt;                                //przypisywanie ID każdemy rekordowi
        cnt++;                                    //counter wszystkich Thing'ów
    }

    public int getA() {

        return a;
    }

    public void setA(int a) {

        this.a = a;
    }

    public int getId() {

        return id;
    }

    public static void writeWord() {

        System.out.println(word);
    }
}

public class StaticFinalConstruct {
    public static void main(String[] args) {

        Thing thing1 = new Thing();
        Thing thing2 = new Thing();

        Thing.word = "Peace";
        Thing.writeWord();
        System.out.println("ID #1 : " + thing1.getId() + System.lineSeparator() + "ID #2 : " + thing2.getId());
        System.out.println("Total counter of Things : " + Thing.cnt);
    }

}
