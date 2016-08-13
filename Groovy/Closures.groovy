
def myClosure = { println "Date: " + new Date() }

(1..10).findAll {
	it % 2 == 0
}.each {
	println it
}

/**
 * Each takes closure as an argument (like java8 lambdas)
 */
(1..3).each {
//	println it
	myClosure()
//	sleep 1000
}

(1..3).each({
	myClosure()
})

myClosure()