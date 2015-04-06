package hello.world;

public class ScalaTraitToJavaInterface implements HelloWorldTrait{

	public void sayHello() {
		System.out.println("Hello");
	}

	public void sayHelloMoreTimes() {
		HelloWorldTrait$class.sayHelloMoreTimes(this);
	}
}
