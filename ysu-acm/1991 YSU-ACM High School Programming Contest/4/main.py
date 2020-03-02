import sys, os

def evaluate(patterns, a, b, c):
    mapping = str.maketrans('ABC', str(a) + str(b) + str(c))
    x, y, z = patterns[0].translate(mapping), patterns[1].translate(mapping), patterns[2].translate(mapping)
    x, y, z = int(x), int(y), int(z)
    if x + y == z:
        return [x, y, z]
    
def main():
    # Read Input Data
    path = os.path.join(sys.path[0], 'input')
    data = open(path, 'r').read().split('\n')

    # Just cycle through all iterations until one is found.
    for x in range(10):
        for y in range(10):
            for z in range(10):
                result = evaluate(data, x, y, z)
                if result:
                    return result

if __name__ == "__main__":
    print(main())