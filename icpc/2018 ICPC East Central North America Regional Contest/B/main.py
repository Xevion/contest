import os, sys

def harshad(n):
    return (n % sum(map(int, list(str(n))))) == 0

def main():
    inputs = [os.path.join(sys.path[0], 'inputs', x) for x in 
    os.listdir(os.path.join(sys.path[0], 'inputs'))]
    inputs = [int(open(path).read()) for path in inputs]

    for i in inputs:
        while not harshad(i):
            i += 1
        print(i)
        
if __name__ == "__main__":
    main()