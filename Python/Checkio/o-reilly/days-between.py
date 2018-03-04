from datetime import date


def days_diff(date1, date2):
    return abs((date(*date1) - date(*date2)).days)
