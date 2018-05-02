to_write = ['aa', 'cc', 'b', 'few']

with open('words', 'w') as text_file:
    for word in to_write:
        print(word, file=text_file)
