
def numbers = 0..9

numbers.each { number -> 
	println "$number ${isEven number}"
}

/**
 * Return statements can be omitted in most cases.
 * 
 * @param number
 * @return
 */
def isEven(number) {
	number % 2 == 0
}