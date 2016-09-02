package lv3;

interface Executable {
    int execute(int cnt, int val);
}

class Runner {
    public void run(Executable e) {
        System.out.println("Executing code block...");
        System.out.println("Value : " + e.execute(10, 5));
    }
}

public class LambdaV3 {

    public static void main(String[] args) {

        Runner runner = new Runner();

        runner.run(new Executable() {

            public int execute(int cnt, int val) {
                System.out.println("Anon Class call");
                return cnt * val;
            }
        });

        System.out.println();
        System.out.println("..................................");
        System.out.println();

        runner.run((lamPar, ve) -> ve - lamPar);

        System.out.println();
        System.out.println("..................................");
        System.out.println();

        Executable ex = (lamPar, ve) -> ve - lamPar;

        System.out.println(ex.execute(5, 3));
    }
}
