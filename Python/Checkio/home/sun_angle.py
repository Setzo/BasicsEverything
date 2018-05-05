def sun_angle(time_str: str):
    hour, minutes = parse_time(time_str)
    if hour < 6 or hour > 18 or (hour == 18 and minutes != 0):
        return 'I don\'t see the sun!'

    time = (hour * 60) + minutes - (6 * 60)
    if time == 0:
        return 0

    eighteen_time = 12 * 60
    percentile = time / eighteen_time
    angle = 180 * percentile

    return round(angle, 2)


def parse_time(time: str) -> list:
    return [int(t) for t in time.split(':')]
