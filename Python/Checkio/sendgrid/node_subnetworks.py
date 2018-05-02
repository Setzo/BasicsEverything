def subnetworks(net, dead_nodes):
    subnets = []
    for connection in net:
        for node in connection:
            if node in dead_nodes:
                connection.remove(node)
    net.sort(key=len, reverse=True)
    for i, connection in enumerate(net):
        for node in connection:
            if node in dead_nodes:
                continue
            if len(subnets) == 0:
                subnets.append([connection])
                continue
            appended = False
            for subnet in subnets:
                for subnet_connection in subnet:
                    if node in subnet_connection and node not in dead_nodes:
                        subnet.append(connection)
                        appended = True
                        break
                if appended:
                    break
            if appended:
                break
            if not appended:
                subnets.append([connection])
    return len(subnets)
