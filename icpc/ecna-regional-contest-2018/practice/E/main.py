import os, sys

# Process a single input
def process(profit, pita, pizza):
    maxPita, maxPizza = int(profit / pita), int(profit / pizza)
    combos = [(x, y) for x in range(0, maxPita + 1) for y in range(0, maxPizza + 1)]
    combos = filter(lambda item : profit == (pita * item[0]) + (pizza * item[1]), combos)
    return '\n'.join(' '.join(map(str, combo)) for combo in combos)

# Driver code for all inputs in folder
def main():
    # Read inputs
    inputs = [os.path.join(sys.path[0], 'inputs', x) for x in 
    os.listdir(os.path.join(sys.path[0], 'inputs'))]
    # Parse inputs
    inputs = [list(map(float, open(path).read().split())) for path in inputs]
    # Process inputs and print outputs
    print('\n{}\n'.format('-' * 10).join(map(lambda item : process(*item), inputs)))

if __name__ == "__main__":
    main()