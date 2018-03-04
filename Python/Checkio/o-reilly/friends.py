class Friends:

    def __init__(self, connections):
        self._connections = []
        for connection in connections:
            self._connections.append(sorted(connection))

    def add(self, connection):
        if sorted(connection) in self._connections:
            return False
        self._connections.append(sorted(connection))
        return True

    def remove(self, connection):
        if sorted(connection) not in self._connections:
            return False
        del self._connections[self._connections.index(sorted(connection))]
        return True

    def names(self):
        result = set()
        for connection in self._connections:
            result.add(connection[0])
            result.add(connection[1])
        return set(sorted(result))

    def connected(self, name):
        result = set()
        for connection in self._connections:
            if connection[0] == name:
                result.add(connection[1])
            if connection[1] == name:
                result.add(connection[0])
        return result
