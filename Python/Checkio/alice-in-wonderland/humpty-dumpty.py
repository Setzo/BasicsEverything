from math import pi, asin, atanh, sqrt


def checkio(h, w):
    if h == w:
        area = round(pi * w * w, 2)
    elif h < w:
        e = 1 - (h * h / 4) / (w * w / 4)
        area = round(pi * w * w / 2.0 * (1 + (1 - e) / sqrt(e) * atanh(sqrt(e))), 2)
    else:
        e = 1 - w * w * 0.25 / (h * 0.5) / (h * 0.5)
        area = round(pi * w * w * 0.5 * (1 + (h * 0.5) / (w * 0.5) / sqrt(e) * asin(sqrt(e))), 2)

    return [round(pi * w * w * h / 6.0, 2), area]
