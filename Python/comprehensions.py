words = 'This is words words a long list of words'.split(' ')

print([len(word) for word in words])

print({len(word) for word in words})  # Set comprehension

from pprint import pprint

countries = {
    'Poland': 'Warsaw',
    'UK': 'London',
    'Germany': 'Berlin'
}

pprint(countries)
c = {capital: country for country, capital in countries.items()}
pprint(c)

pprint(words)
# Later keys will overwrite earlier duplicate ones (Map#put)
pprint({x[0]: x for x in words})
