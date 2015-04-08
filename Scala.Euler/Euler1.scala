package euler1

object Euler1 extends App {
	
	println((1 until 1000).filter(n => n % 3 == 0 || n % 5 == 0).sum)
}
