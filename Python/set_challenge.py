import re

def consonants(text):
    return set([letter for letter in text.lower() if re.match('(?![aeiouy])[a-z]', letter)])


print(consonants('text lalaall'))
print(consonants("49038fyh34j8-34978f8nb3v9n34n-7g"))
print(consonants('qwertyuiopasdfghjklzxcvbnm'))
print(consonants('text \n\r \t \0@%#$%&$#@^&$%^,.23.4,./.25\'"fweewf><//./'))
