
def numbers = 1..10

numbers.each {
	println it
}

def enum DAYS {
	MON,
	TUE,
	WED,
	THU,
	FRI,
	SAT,
	SUN
}

def weekdays = DAYS.MON..DAYS.FRI

DAYS.each { day ->
	println day
}

println weekdays.from
println weekdays.to
