package lv2;

interface Executable {
    int execute(int cnt);
}

interface Executabler {
    int execute(String len);
}

class Runner {
    public void run(Executable e) {
        System.out.println("Executing code block...");
        System.out.println("Value : " + e.execute(10));
    }
}

class Run {

    public void run(Executable e) {
        System.out.println("Executing code block...");
        System.out.println("Value : " + e.execute(10));
    }

    public void run(Executabler e) {
        System.out.println("Executing code block...");
        System.out.println("Value : " + e.execute("hell"));
    }
}

public class LambdaV2 {

    public static void main(String[] args) {

        Runner runner = new Runner();

        runner.run(new Executable() {

            public int execute(int cnt) {
                System.out.println("Anon Class call");
                return cnt * cnt;
            }
        });

        System.out.println();
        System.out.println("..................................");
        System.out.println();

        runner.run(lamPar -> 8 - lamPar);

        System.out.println();
        System.out.println("..................................");
        System.out.println();

        Run ru = new Run();

        ru.run((String a) -> 8 + a.length());

        System.out.println();
        System.out.println("..................................");
        System.out.println();
    }
}
