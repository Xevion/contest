import os, sys

def process(item):
    # Constants and initial values
    stocks, (n, m) = item[1], item[0]
    peaks, valleys = [], []
    # Check all possible peaks
    for index in range(len(stocks)):
        isPeak, isValley = True, True
        # Peak Checking
        for i in range(1, n + 1):
            # If we're no the first index
            if index - i >= 0:
                # If position behind us is lower
                if not stocks[index] > stocks[index - i]:
                    isPeak = False
                    break
            # If we're not the final index
            if index + i < len(stocks):
                # and position in front of us is lower
                if not stocks[index] > stocks[index + i]:
                    isPeak = False
                    break
        # Valley checking
        for i in range(1, m + 1):
            # If we're not  the first index
            if index - i >= 0:
                # if position behind us is higher
                if not stocks[index] < stocks[index - i]:
                    isValley = False
                    break
            # If we're not the final index
            if index + i < len(stocks):
                # and position in front of us is higher
                if not stocks[index] < stocks[index + i]: 
                    isValley = False
                    break
        if isPeak: peaks.append(index)
        if isValley: valleys.append(index)
    print(f'n{n}, m{m}')
    print('{0}{1}\n{0}{2}'.format('-> ', peaks, valleys))
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