def friendly_number(num, base=1000, decimals=0, suffix='',
                    powers=['', 'k', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y']):
    power_cnt = 0
    sign = '' if num >= 0 else '-'
    num = abs(num)
    if num >= base:
        while num >= base and power_cnt < len(powers) - 1:
            num = num // base if decimals == 0 else num / base
            power_cnt += 1
    formatt = sign + '{0:.' + str(decimals) + 'f}{1}{2}'
    return formatt.format(float(num), powers[power_cnt], suffix)
