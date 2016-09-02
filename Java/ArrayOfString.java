import java.util.Scanner;


public class ArrayOfString {
    public static void main(String[] args) {

        String[] tabX = new String[5];
        Scanner X = new Scanner(System.in);

        for (int i = 0; i < tabX.length; i++) {
            tabX[i] = X.nextLine();
        }

        for (String tab : tabX) {                    //przy kazdym przejsciu przypisz do tab wartosc kolejnego elementu tabX
            System.out.println(tab);            // writeLn(tab)
        }

        X.close();
    }
}
