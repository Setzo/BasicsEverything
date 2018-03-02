dictionary = {
    "key1": "somethgin",
    'key2': 'sperieofejr',
    'iejfw': 'fewoihfw'
}

print(dictionary)
del dictionary['key1']
print(dictionary)
dictionary['key2'] = 2
print(dictionary)

# Error
# print(dictionary['non existing key'])

# No error
print(dictionary.get('non existing key'))

print(dictionary.items())
t_tuple = tuple(dictionary.items())
print(t_tuple)

print(dict(t_tuple))
