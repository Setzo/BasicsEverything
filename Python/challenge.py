ip_addresses = ['100.111.111.111', '1.44.2', '1', 'nope', '192.169.1.1',
                '.1.1.1', '1.1.1.', '', '190.111,23,222', '111,111,111,111']


def is_ip_address(ip_address: str):
    if len(ip_address) == 0 or ip_address[0] == '.' or ip_address[-1] == '.':
        return False

    for symbol in ip_address:
        if symbol not in '.0123456789':
            return False

    segments = ip_address.split('.')
    print("Ip address {}, split by '.' produces '{}'".format(ip_address, segments))
    if len(segments) == 4:
        return True

    return False

for ip_address in ip_addresses:
    print("Ip address {}, valid: {}".format(ip_address, is_ip_address(ip_address)))
