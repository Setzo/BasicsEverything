def is_family(family):
    child_to_parent = {}
    parents = [family[0][0]]
    return is_family_recursive(family, parents, child_to_parent)


def is_family_recursive(suspects, parents, child_to_parent):
    suspicious_family = []
    for parent, child in sorted(suspects):
        if child_to_parent.get(child) is not None and child_to_parent.get(child) != parent:
            return False

        if parent in parents and child not in parents:
            parents.append(child)
            child_to_parent[child] = parent
        elif parent not in parents and child in parents:
            parents.append(parent)
            child_to_parent[child] = parent
        else:
            suspicious_family.append([parent, child])
    if len(suspects) == len(suspicious_family):
        return False
    if len(suspicious_family) == 0:
        return True
    return is_family_recursive(suspicious_family, parents, child_to_parent)
