
def numbers = [0, 1, 2, 5, 64]
def str = "Number: "

println 'Single Bracket'

//for(def i = 0; i < testArray.size; i++) {
	
for(num in numbers) {
	println "$str$num"
//	println '$number$num'
	println "\r\n${Math.powerOfTwoD(num)}"
}
