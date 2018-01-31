import re

string = "fu3rg2fh78uif32-9fn234=-9f23u"
number = ""
for i in string:
    number += i if re.match("\d", i) else ""

print(number)
