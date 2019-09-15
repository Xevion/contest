import math, time, random

class Node(object):
    def __init__(self, parent=None, position=None):
        self.parent, self.position = parent, position
        
        self.g, self.h, self.f = 0, 0, 0

    def __eq__(self, other):
        assert type(other) == Node, "Can only compare equality against other Node objects"
        return self.position == other.position

    def __repr__(self):
        return f'<Node ({self.position[0]}, {self.position[1]})>'

# Generate a grid and return it to the user
class SnakeGrid(object):
    def __init__(self, x, y, length=3):
        self.x, self.y, self.length, self.sleepTime = x, y, length, 0.0
        self.offsets = [[0, 1], [1, 0], [-1, 0], [0, -1]]
        self.generate()
    
    def sleep(self, seconds):
        self.sleepTime += (seconds, time.sleep(seconds))[0]

    def generate(self):
        assert self.x > 2 + self.length and self.y > 3, "Dimensions must be able to at least fit the snake"

        # Grid is a matrix of single space strings
        self.matrix = [[' ' for xx in range(self.x + 1)] for yy in range(self.y + 1)]

        # Choose a initial position
        self.positions = [self.getPos(-1, -1)]
        self.mark(self.positions[0])
        curlength = self.length - 1

        # Start drawing up the snake
        while curlength > 0:
            left, right = [self.positions[0][0], self.positions[0][1] - 1], [self.positions[-1][0], self.positions[-1][1] + 1]
            canLeft, canRight = self.available(left), self.available(right)
            curlength -= 1
            
            # If both options are available, just choose one and act like the other is unavailable
            if canLeft and canRight:
                if random.choice([True, False]): canLeft = False
                else: canRight = False

            if canLeft != canRight:
                if canLeft:
                    self.mark(left)
                    self.positions.insert(0, left)
                if canRight:
                    self.mark(right)
                    self.positions.append(right)
            elif not (canLeft or canRight):
                print(positions, left, right)
                print("Could not resolve any position to use...?")

        # Populate with 3-7 pellets
        for _ in range(random.randint(2, 5)):
            while True:
                pos = self.getPos()
                if self.available(pos):
                    self.mark(pos, 'F')
                    break
    
    # Pythagorean distance calculation
    def distance(self, pos1, pos2):
        return math.sqrt(((pos1[0] - pos2[0]) ** 2) + ((pos1[1] - pos2[1]) ** 2))

    # Returns all positions with the specified character (by default, 'F', for pellets)
    def pellets(self, char='F'):
        pelletss = []
        for yy in range(self.y):
            for xx in range(self.x):
                if self.available([xx, yy], look=char):
                    pelletss.append([xx, yy])
        return pelletss

    # Returns the best pellet to path to based on distance and a specified blacklist of positions
    def bestPellet(self, curpos, blacklist=[]):
        potential = self.pellets()
        if blacklist:
            potential = list(filter(lambda item : item not in blacklist, potential))
            # Returns None if no potential pellets are in due to blacklist
            if not potential:
                return None
        return min(potential, key=lambda pos : self.distance(curpos, pos))

    # Quick code for calcul
    def merge(self, pos1, pos2):
        return [pos1[0] + pos2[0], pos1[1] + pos2[1]]

    # Generater a solution for the maz
    def solution(self, length=20):
        def build_path(current_node):
            path = []
            current = current_node
            while current is not None:
                path.append(current.position)
                current = current.parent
            return str(path[::-1])

        # Pathfinding initial constants
        start_node = Node(None, self.positions[-1])
        end_node = Node(None, self.bestPellet(start_node.position))
        open_list, closed_list = [start_node], []
        finished_end_nodes = []
        pathdist = 0
        output = ""

        while len(open_list) > 0:
            self.sleep(0.125)
            pathdist += 1
            # Choose the best node to work on
            current_index, current_node = min(enumerate(open_list), key=lambda item : item[1].f)
            open_list.pop(current_index)
            closed_list.append(current_node)


            # Check if we've hit the maximum distance
            if pathdist >= length:
                return build_path(current_node)

            # If we've hit the "end node", but still distance to travel, setup a new one
            if current_node == end_node: 
                finished_end_nodes.append(end_node)
                end_node = self.bestPellet(blacklist=finished_end_nodes)
                # if we've acquired all pellets by chance
                if end_node is None:
                    return build_path(current_node)
            
            # Basically iterates upon all positions next to the current node dependent on the cardinal directions
            for offset in self.offsets:
                child = self.merge(current_node.position, offset)
                # Ensure in bounds
                if self.inBounds(child):
                    child = Node(parent=current_node, position=child)
                    
                    # Ensure not already a closed position
                    if not child in closed_list:
                        child.g = current_node.g + 1
                        child.h = self.distance(child.position, end_node.position)
                        child.f = child.g + child.h
                    
                    # Ensure that child node is
                    for open_node in open_list:
                        if child == open_node:
                            if child.g > open_node.g:
                                continue
                    
                    open_list.append(child)

        return output

    # Quick method for getting a position with the offsets provided
    def getPos(self, xoffset=0, yoffset=0):
        return [random.randint(1, self.x + xoffset), random.randint(1, self.y + yoffset)]

    # Quick method for marking a postiion
    def mark(self, pos, marker='X'):
        self.matrix[pos[0]][pos[1]] = marker

    # Mechansim for determining whether a position is in the boundaries of the matrix
    def inBounds(self, pos):
        return all([pos[0] >= 0, pos[1] >= 0, pos[1] < self.y, pos[0] < self.x])

    # Determine whether a position is available for use
    def available(self, pos, look=' '):
        return (self.matrix[pos[0]][pos[1]] == look) if self.inBounds(pos) else False

    def __repr__(self):
        return '\n'.join(' - '.join(_) for _ in self.matrix)

# Driver Code
if __name__ == "__main__":
    # User Adjustable Constants
    timing = 0.050
    iterations = 1
    size = (15, 15)

    # Build Constants
    roundTime = lambda seconds : str(round((seconds) * 1000, 2)) + 'ms'
    centerSize = (2 + len(str(iterations)))
    dividerTotal = (4 * size[0]) - centerSize
    dividerCustom = ('-' * (dividerTotal // 2)) + ' {} ' + ('-' * (dividerTotal // 2))
    dividerTotal = '-' * (dividerTotal + 4)
    t1 = time.time()

    # Build and prints matrixes
    for x in range(iterations):
        snakegrid = SnakeGrid(size[0], size[1], 3)
        print(dividerCustom.format(str(x+1).zfill(len(str(iterations)))))
        print(snakegrid)
        print(dividerTotal)
        print(snakegrid.solution())
        time.sleep(timing)
    print(dividerTotal)

    # Finish and print timing statistics
    t2 = time.time()
    print(f'Processing Time : {roundTime(t2 - t1 - (timing * iterations) - snakegrid.sleepTime)}')
    print(f'Artificial Time : {roundTime((timing * iterations) + snakegrid.sleepTime)}')
    print(f'Total Time : {roundTime(t2 - t1)}')
    print(dividerTotal)