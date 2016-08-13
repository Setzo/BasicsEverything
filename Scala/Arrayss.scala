
object Arrayss extends App {

	val array0 = Array("a", 2, true)
	println(array0.mkString("[", ", ", "]"))

	val array1 = Array("a", "b", "c")
	println(array1.mkString(", "))

	val itemAtIndex0 = array1(0)
	println(itemAtIndex0)

	array1(0) = "cc"
	println(array1.mkString(", "))

	val concatenated = "prepend" +: (array0 ++ array1) :+ "append"
	println(concatenated.mkString(", "))

	println(concatenated.indexOf(true))

	val diffArray = Array(1, 2, 3, 4).diff(Array(2, 3))
	println(diffArray.mkString("[", ", ", "]"))

	val personArray = Array(("Alice", 1), ("Bob", 2), ("Carol", 3))
	def findByName(name: String) = personArray.find(_._1.equals(name)).getOrElse(("David", 4))
	
	val findBob = findByName("Bob")
	val findEli = findByName("Eli")

	val bobFound = findBob._2
	val eliFound = findEli._2
	
	println(personArray.mkString(", "))
	println(findBob + " : " + bobFound)
	println(findEli + " : " + eliFound)
}
