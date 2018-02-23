def between_markers(text: str, begin: str, end: str) -> str:
    if begin not in text and end not in text:
        return text
    elif end not in text:
        return text[text.find(begin) + len(begin):]
    elif begin not in text:
        return text[:text.find(end)]
    return text[text.find(begin) + len(begin):text.find(end)]
