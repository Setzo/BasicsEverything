popular_words = lambda text, words: {key: text.lower().count(key) for key in words}
