package hello.world;

public class JavaToScala {

    public static void main(String[] args) {

        HelloWorldTrait trait = new ScalaTraitToJavaInterface();

        trait.sayHello();
        trait.sayHelloMoreTimes();
    }
}
