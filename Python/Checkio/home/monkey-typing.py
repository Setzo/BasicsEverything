count_words = lambda text, words: sum([word.lower() in text.lower() for word in words])
