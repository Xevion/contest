import sys, os

# Read the data
path = os.path.join(sys.path[0], 'input')
data = open(path, 'r').read().split('\n')
data = list(map(int, data))
assert all(map(lambda x : x >= 1, data))

print('\n\n'.join('\n'.join('*' * x for x in range(n, 0, -1))for n in data))