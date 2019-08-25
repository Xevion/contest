import os, sys

mappings = {
    '@' : ['at'],
    '&' : ['and'],
    '1' : ['one', 'won'],
    '2' : ['to', 'too', 'two'],
    '4' : ['for', 'four'],
    'b' : ['bea', 'be', 'bee'],
    'c' : ['sea', 'see'],
    'i' : ['eye'],
    'o' : ['oh', 'owe'],
    'r' : ['are'],
    'u' : ['you'],
    'y' : ['why']
}

# gets the largest and smallest possible subs
minSub = min([min(list(map(len, vals))) for vals in mappings.values()])
maxSub = max([max(list(map(len, vals))) for vals in mappings.values()])

# Gets the token ('are' -> 'r') for a sub, case sensitive
def getToken(sub):
    for key in mappings.keys():
        if sub in mappings[key]:
            return key

def processWord(word):
    processed = [False] * len(word)
    # For every character in the word
    index = 0
    while index < len(word):
        possible = []
        for length in range(minSub, maxSub + 1):
            if index + length > len(word):
                continue
            sub = word[index:index + length]
            token = getToken(sub.lower())
            if token:
                # Sometimes mulitple tokens can appear for the same one, we just select the one that is longest
                possible.append((token, len(sub), sub))
        # A token replacement has been found at this index
        if possible:
            # Find the best one, based on the sub
            select = max(possible, key=lambda item : item[1])
            # print(index, select, word[index:index + select[1]])
            word = word[:index] + (select[0].title() if select[2].istitle() else select[0]) + word[index + select[1]:]
            # word[index:index + select[1]] = select[0]
        index += 1
    return word

# Process a single line
def processLine(line):
    return ' '.join([processWord(word) for word in line.split(' ')])

# Drive Code
def main():
    path = os.path.join(sys.path[0], 'input')
    data = open(path, 'r').read().split('\n')[1:]
    print('\n'.join(map(processLine, data)))

if __name__ == "__main__":
    main()