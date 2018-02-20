import re

i = ':e'
while not i == ':q':
    i = input("Gimme an ip address: ")
    segments = i.split(".")
    segmentLength = len(segments)
    if not segmentLength == 4:
        print("Has to be exactly 4 segments. Found: {}.".format(segmentLength))
        continue
    segmentLengthMap = {}
    for segment in segments:
        if not re.match('^\d{1,3}$', segment):
            print("Segment has to contain one to three digits only, found: '{}'.".format(segment))
            break
        segmentLengthMap[segment] = (len(segment))
    else:
        print("Found {} segments, with lengths: {}".format(segmentLength, segmentLengthMap))
