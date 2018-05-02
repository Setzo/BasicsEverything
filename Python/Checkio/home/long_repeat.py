def long_repeat(line):
    previous = ''
    cnt = 0
    longest = 0
    for letter in line:
        if previous == letter:
            cnt += 1
            longest = max(longest, cnt)
        else:
            previous = letter
            longest = max(longest, cnt)
            cnt = 1
    return longest
