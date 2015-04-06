
object Returning extends App {

	def swap(x: String, y: String) = (y, x)

	val (a, b) = swap("hello", "world")

	println(swap("First", "Second"))

	var x, y, z = 0
	var c, python, java = false

	println(x, y, z, c, python, java)

	var (x0, y0, z0, c0, python0, java0) = (1, 2, 3, true, false, "no!")
	println(x0, y0, z0, c0, python0, java0)

	var i, sum = 0
	while (i < 10) {
		sum += i
		i += 1
	}
	println(sum)

	var sum0 = 0
	for (i <- 0 until 10) {
		sum0 += i
	}
	println(sum0)
	
	println((0 until 10).sum  )

}
