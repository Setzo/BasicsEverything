package iterab;

public class UsingIterable {
    public static void main(String[] args) {
        UrlLib ul = new UrlLib();

        for (String html : ul) {
            System.out.println(html);
            System.out.println(html.length());
        }
    }
}
