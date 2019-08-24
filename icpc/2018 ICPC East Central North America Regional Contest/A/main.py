import sys, os, math

# Pythagorean Distance Formula
def pyth(x1, y1, x2, y2):
        return math.sqrt(((x1 - x2) ** 2) + ((y1 - y2) ** 2))

def dist(spot1, spot2):
    return pyth(spot1[0], spot1[1], spot2[0], spot2[1])

def main():
    def parse(dat):
        return list(map(int, dat.split()))


    path = os.path.join(sys.path[0], 'input')
    data = open(path, 'r').read().split('\n')
    listeners = []


    for dataset in data:
        listeners.append(parse(dataset))
    spy = listeners.pop(0)

    distances = sorted()

if __name__ == "__main__":
    main()