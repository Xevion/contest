import os, sys

def process(item):
    # Constants and initial values
    stocks, (n, m) = item[1], item[0]
    peaks, valleys = [], []
    possiblePeaks, possibleValleys = range(n, len(stocks) - n), range(m, len(stocks) - m) 
    # Check all possible peaks
    for index in possiblePeaks:
        isPeak = True
        for i in range(1, n + 1):
            if not stocks[index - i] < stocks[index] or not stocks[index] > stocks[index + i]:
                isPeak = False
        if isPeak:
            peaks.append(index)
    print(peaks)
    # Check all possible valleys
    for index in possibleValleys:
        isValley = True
        for i in range(1, m + 1):
            if not stocks[index - i] > stocks[index] or not stocks[index] < stocks[index + i]:
                isValley = False
        if isValley:
            valleys.append(index)
    print(valleys)
    return len(peaks), len(valleys)

# Driver code for all inputs in folder
def main():
    def parse(input):
        return [list(map(int, input[0].split()))[1:], list(map(int, input[1].split()))]
    # Read inputs
    inputs = [os.path.join(sys.path[0], 'inputs', x) for x in 
    os.listdir(os.path.join(sys.path[0], 'inputs'))]
    inputs = [parse(open(path).read().split('\n')) for path in inputs]
    # Parse inputs
    print('\n{}\n'.format('-' * 10).join(map(lambda item : str(process(item)), inputs)))

if __name__ == "__main__":
    main()