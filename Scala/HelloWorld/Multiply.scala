package hello.world

object FirstProgram extends App {

	def multiply(x : Int, y : Int) : Int = {
		
		y match {
			case 0 => 0
			case 1 => 1
			case _ => x * y
		}
	}
	
	println(multiply(5, 6));
}
