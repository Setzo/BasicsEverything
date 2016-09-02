
public class StringBuilderx {

    public static void main(String[] args) {

        String d;

        String a = "";
        a += "Hello";                                        //Tworzenie łącznie 4 Stringów (każde "") alokuje pamięć jak na całego Stringa
        a += " ";
        a += "I'm a fucking inefficient piece of shit";

        System.out.println(a);

        StringBuilder b = new StringBuilder("");        //StringBuilder za wszystkie "" zabierze tylko pamięć jak na 1 Stringa
        b.append("Hello");
        b.append(" ");
        b.append("I'm efficient");

        System.out.println(b.toString());

        b.reverse();

        System.out.println(b.toString());

        StringBuilder c = new StringBuilder();

        c.append("Hello")
                .append(" ")
                .append("I'm also efficient");

        d = c.toString();                                    //d = String od c

        System.out.println(d);
        //__> = ENTER
        System.out.print("Tab : \tNewline : \nNewline");    //String formatting /t - tab /n - newline
        System.out.println("Pisanie w tej samej linijce.");    //println = tekst __>   ||  print = tekst    || printf = tekst

    }
}
