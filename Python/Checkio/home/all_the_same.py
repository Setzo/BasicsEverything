from typing import List, Any


def all_the_same(elements: List[Any]) -> bool:
    return len(set(elements)) in [0, 1]
