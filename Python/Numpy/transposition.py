import numpy as np

arr = np.arange(50)
print(arr)
print('*' * 40)
arr = arr.reshape((10, 5))

print(arr)
print('*' * 40)
print(arr.T)
print('*' * 40)
print(arr)

print('*' * 40)
print(np.dot(arr.T, arr))

print('*' * 40)
arr3d = np.arange(50).reshape((5, 5, 2))
print(arr3d)

print('*' * 40)
print(arr3d.transpose(1, 0, 2))  # Specific transposition axis.

print('*' * 40)
arr = np.array([[1, 2, 3, 4]])
print(arr)

print('*' * 40)
arr.swapaxes(0, 1)
print(arr)
print('*' * 40)
print(arr.swapaxes(0, 1))
print('*' * 40)
