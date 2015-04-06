package hello.world

trait HelloWorldTrait {

	def sayHello() : Unit
	
	def sayHelloMoreTimes() : Unit = {
		for(i <- 1 to 10) {
			println("Ohai")
		}
	}
}
