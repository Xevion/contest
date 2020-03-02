import os, sys, math

path = os.path.join(sys.path[0], 'input')
data = open(path, 'r').read().split('\n')
# To keep with the times of 1991, we will use the constraints of Python2 for max integers
maxInt = 9223372036854775807

def extend(n):
    res = 1
    for x in range(2, n + 1):
        res = x * res
    return res

x = 100
print(extend(x))
print(math.log(extend(x), 10))