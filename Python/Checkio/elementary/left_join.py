def left_join(phrases):
    phrases = [a.replace('right', 'left') for a in phrases]
    return ",".join(phrases)
