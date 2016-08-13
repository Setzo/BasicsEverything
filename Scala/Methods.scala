object Methods extends App {

	def add(x:Int, y:Long) = {
		x + y + "x".toString() + 5.55
	}
	
	def add(x:Int, y:Int) = x + y
	
	println(add(4, 3))
	println(add(4, 3l))
	println(add(4, 3.asInstanceOf[Long]))

	def onePlusTwo(f: (Int, Int) => Int) = {
		f(1, 2)
	}
	
	//Anonymous methods
	val call1 = onePlusTwo((x: Int, y: Int) => x + y)

	val call2 = onePlusTwo((x, y) => x + y)

	val call3 = onePlusTwo(_ + _)

	println(call1, call2, call3)

	def add1(x: Int, y: Int) = x + y 
	
	val add2 = (x: Int, y: Int) => x + y 
	val add3: (Int, Int) => Int = _ + _ 
	val add4 = (_ + _): (Int, Int) => Int

	println(add1(1, 2) toDouble)
	println(add2(1, 2))
	println(add3(1, 2))
	println(add4(1, 2))
	println(add2(2, 3))
	
	var xx = """ \n\n\n\n\n\\n\n\ """
	println(xx)
	
	val age = 5;
	println(s"this is string $age")
}
