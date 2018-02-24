import numpy as np

arr = np.arange(0, 11)
print(arr)

print(arr[2])
print(arr[1:5])
arr[1:5] *= 2
print(arr)

slice_arr = arr[1:5]
print(slice_arr)
slice_arr += 3
print(slice_arr)
print(arr)

arr_copy = arr[1:5].copy()
print(arr_copy)
arr_copy *= 33
print(arr_copy)
print(arr)

print('*' * 30)

a2d = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
print(a2d)
print(a2d[:2, 1:])

print('*' * 30)

a2d2 = np.zeros((10, 10))
print(a2d2)
a2d2_length = a2d2.shape[1]
print(a2d2.shape)
print(a2d2_length)

print('*' * 30)

for i in range(a2d2.shape[1]):
    # Fancy indexing. Will not overwrite a2d2[i] array to i, will
    # change every value in a2d2[i] to i.
    a2d2[i] = i

print(a2d2)

print('*' * 30)

# Shows 2, 3, 4, 9 columns.
print(a2d2[[2, 3, 4, 9]])

# Shows only one record per column.
print(a2d2[[2, 3, 4, 1], [1, 2, 3, 4]])
