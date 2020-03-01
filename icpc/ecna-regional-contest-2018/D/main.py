import datetime, re, os, sys

pattern = r'([HA]) (\d) (\d+):(\d+)'
mmsspattern = '%M:%S'

# Process the input for a single niput
def process(rawinput):
    def getBest():
        return max(score.items(), key=lambda x:x[1])[0]
    score = {'H' : 0, 'A' : 0}
    leads = {'H' : 0, 'A' : 0}
    rawinput = '\n'.join(rawinput.split('\n')[1:])
    events = re.finditer(pattern, rawinput)
    curLead = ''
    lastTime = datetime.datetime(1990, 1, 1, 0, 0, 0)
    gameEnd = datetime.datetime(1990, 1, 1, 1, 30)
    for event in events:
        # Create the formatted timecode, add the score
        timecode = '{}:{}'.format(event.group(3).zfill(2), event.group(4).zfill(2))
        curtime = datetime.datetime.strptime(timecode, mmsspattern)
        score[event.group(1)] += int(event.group(2))
        # Get the team in the lead and if it's different
        best = getBest()
        leads[best] += int((curtime - lastTime).seconds)
        lastTime = curtime
#     if gameEnd > lastTime:
        # leads[best] += (gameEnd - lastTime).seconds
    return '{} {} {}'.format(getBest(), leads['H'], leads['A'])

# Driver code    
def main():
    # Read inputs
    inputs = [os.path.join(sys.path[0], 'inputs', x) for x in 
    os.listdir(os.path.join(sys.path[0], 'inputs'))]
    inputs = [open(path).read() for path in inputs]
    for rawinput in inputs:
        print(process(rawinput))
        print('-'*19)

if __name__ == "__main__":
    main()