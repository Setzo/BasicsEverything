import numpy as np

list1 = [1, 2, 3, 4]
arr = np.array(list1)
print(arr)

list2 = [4, 5, 6, 7]

lists = [list1, list2]

arr2 = np.array(lists)

print(arr2)
print(arr2.shape)
print(arr2.dtype)

z = np.zeros((5, 5, 5))
print(z)
print(z.dtype)

ones = np.ones((5, 2))
print(ones)

identity_matrix = np.eye(3)
print(identity_matrix)

print(np.arange(5))
print(np.arange(5, 10, 2))
print(np.arange(5, 7))

array1 = np.array([1, 2, 3, 4])
array1 *= 2
print(array1)
