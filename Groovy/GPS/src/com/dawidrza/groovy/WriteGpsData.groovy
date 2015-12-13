import groovy.xml.StreamingMarkupBuilder


def file = new File('../data/fells_loop.gpx')
def slurper = new XmlSlurper()
def gpx = slurper.parse(file)

def streamingMarkupBuilder = new StreamingMarkupBuilder()

def xml = streamingMarkupBuilder.bind {
	route {
		mkp.comment(gpx.name)
		gpx.rte.rtept.each { point ->
			routePoint(timestamp: point.time.toString()) {
				latitude(point.@lat)
				longitude(point.@lon)
			}
		}
	}
}

def outFile = new File('../data/new.xml')

outFile.write(xml.toString())
