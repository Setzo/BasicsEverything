import re


def to_encrypt(text, delta):
    result = ''
    for letter in text:
        if re.match('[a-z]', letter):
            n = ord(letter) + delta
            if n > ord('z'):
                n = ord('a') + (n % ord('z')) - 1
            if n < ord('a'):
                n = ord('z') - (ord('a') % n) + 1
            result += chr(n)
        else:
            result += letter
    return result
