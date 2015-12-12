
def file = new File('../data/fells_loop.gpx')

//println file.exists()

def slurper = new XmlSlurper()

def gpx = slurper.parse(file)

println ''
println gpx.name
println ''
println gpx.desc
println ''
println gpx.@creator
println gpx.@version
println ''

// Doesn't throw an exception - returns null
//println gpx.deeee

gpx.rte.rtept.each {
	println ''
	println it.@lat
	println it.@lon
	println it.time
}