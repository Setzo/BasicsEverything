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


if __name__ == '__main__':
    # These "asserts" using only for self-checking and not necessary for auto-testing
    assert long_repeat('aa') == 2, "First"
    assert long_repeat('ddvvrwwwrggg') == 3, "Second"
    assert long_repeat('abababaab') == 2, "Third"
    assert long_repeat('') == 0, "Empty"
    print('"Run" is good. How is "Check"?')
