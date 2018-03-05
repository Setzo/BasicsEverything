import numpy as np

x = np.arange(11)
print(np.sqrt(x))
print(np.exp(x))

random_arr = np.random.randn(10)
print(random_arr)
random_arr2 = np.random.randn(10)
print(random_arr2)

print(np.add(random_arr, random_arr2))
print(np.maximum(random_arr, random_arr2))
