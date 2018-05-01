# wt - write, text
f = open('text.txt', mode='wt', encoding='utf-8')

f.write('Text to write to file\n')
f.write('aa')
f.write('bb')

f.close()

f = open('text.txt', mode='rt', encoding='utf-8')
print(f.read())
f.close()

import sys

f = open('text.txt', mode='rt', encoding='utf-8')

for line in f:
    sys.stdout.write(line)
f.close()
