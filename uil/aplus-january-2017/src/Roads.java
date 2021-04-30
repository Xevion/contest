import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

interface Intersection
{
    void register(int intersection, Node node);

    Intersection[] vertices();

    int getVertexCount();

    List<Integer> getEdges();
}

/**
 * One of the two intersection variations where one of the edges faces upwards.
 */
class UpIntersection implements Intersection
{
    Node edgeUp; // via Edge C
    Node edgeLeft; // via Edge A
    Node edgeRight; // via Edge E

    Intersection vertexDown; // edgeLeft.b || edgeRight.d
    Intersection vertexLeft; // edgeLeft.f || edgeUp.d
    Intersection vertexRight; // edgeRight.f || edgeUp.b

    public void registerA(Node node)
    {
        assert node != null;
        this.edgeLeft = node;
        if (vertexDown == null) vertexDown = edgeLeft.b;
        if (vertexLeft == null) vertexLeft = edgeLeft.f;
        node.a = this;
    }

    public void registerC(Node node)
    {
        assert node != null;
        this.edgeUp = node;
        if (vertexLeft == null) vertexLeft = edgeUp.d;
        if (vertexRight == null) vertexRight = edgeUp.b;
        node.c = this;
    }

    public void registerE(Node node)
    {
        assert node != null;
        this.edgeRight = node;
        if (vertexDown == null) vertexDown = edgeRight.d;
        if (vertexRight == null) vertexRight = edgeRight.f;
        node.e = this;
    }

    @Override
    public void register(int intersectionIndex, Node node)
    {
        switch (intersectionIndex % 6) {
            case 0: // Intersection A
                registerA(node);
                break;
            case 2: // Intersection C
                registerC(node);
                break;
            case 4: // Intersection E
                registerE(node);
                break;
            default:
                throw new IllegalArgumentException(
                        String.format(Locale.ENGLISH, "Intersection index (%d) must be a positive even number.", intersectionIndex)
                );
        }
    }

    public Intersection[] vertices()
    {
        return new Intersection[]{vertexDown, vertexLeft, vertexRight};
    }

    @Override
    public int getVertexCount()
    {
        int i = 0;
        if (vertexRight != null) i++;
        if (vertexLeft != null) i++;
        if (vertexDown != null) i++;
        return i;
    }

    @Override
    public List<Integer> getEdges()
    {
        LinkedList<Integer> edges = new LinkedList<>();
        if (edgeLeft != null) edges.offer(edgeLeft.id);
        if (edgeRight != null) edges.offer(edgeRight.id);
        if (edgeUp != null) edges.offer(edgeUp.id);
        Collections.sort(edges);
        return edges;
    }

    @Override
    public String toString()
    {
        return String.format("UpIntersection{%d, %d, %d}", edgeUp != null ? edgeUp.id : null,
                edgeLeft != null ? edgeLeft.id : null,
                edgeRight != null ? edgeRight.id : null);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpIntersection that = (UpIntersection) o;
        return Objects.equals(edgeUp, that.edgeUp) &&
                Objects.equals(edgeLeft, that.edgeLeft) &&
                Objects.equals(edgeRight, that.edgeRight);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(edgeUp, edgeLeft, edgeRight);
    }
}

/**
 * One of the two intersection variations where one of the edges faces downwards.
 */
class DownIntersection implements Intersection
{
    Node edgeDown; // via Edge F
    Node edgeLeft; // via Edge B
    Node edgeRight; // via Edge D

    Intersection vertexUp; // edgeLeft.b || edgeRight.e
    Intersection vertexLeft; // edgeLeft.c || edgeDown.e
    Intersection vertexRight; // edgeRight.c || edgeDown.a

    public void registerB(Node node)
    {
        assert node != null;
        this.edgeLeft = node;
        if (vertexUp == null) vertexUp = edgeLeft.a;
        if (vertexLeft == null) vertexLeft = edgeLeft.c;
        node.b = this;
    }

    public void registerD(Node node)
    {
        assert node != null;
        this.edgeRight = node;
        if (vertexUp == null) vertexUp = edgeRight.e;
        if (vertexRight == null) vertexRight = edgeRight.c;
        node.d = this;
    }

    public void registerF(Node node)
    {
        assert node != null;
        this.edgeDown = node;
        if (vertexLeft == null) vertexLeft = edgeDown.e;
        if (vertexRight == null) vertexRight = edgeDown.a;
        node.f = this;
    }

    @Override
    public void register(int intersectionIndex, Node node)
    {
        switch (intersectionIndex % 6) {
            case 1: // Intersection B
                registerB(node);
                break;
            case 3: // Intersection D
                registerD(node);
                break;
            case 5: // Intersection F
                registerF(node);
                break;
            default:
                throw new IllegalArgumentException(
                        String.format(Locale.ENGLISH, "Intersection index (%d) must be a positive odd number.", intersectionIndex)
                );
        }
    }

    public Intersection[] vertices()
    {
        return new Intersection[]{vertexUp, vertexLeft, vertexRight};
    }

    @Override
    public int getVertexCount()
    {
        int i = 0;
        if (vertexRight != null) i++;
        if (vertexLeft != null) i++;
        if (vertexUp != null) i++;
        return i;
    }

    @Override
    public List<Integer> getEdges()
    {
        LinkedList<Integer> edges = new LinkedList<>();
        if (edgeLeft != null) edges.offer(edgeLeft.id);
        if (edgeRight != null) edges.offer(edgeRight.id);
        if (edgeDown != null) edges.offer(edgeDown.id);
        Collections.sort(edges);
        return edges;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownIntersection that = (DownIntersection) o;
        return Objects.equals(edgeDown, that.edgeDown) &&
                Objects.equals(edgeLeft, that.edgeLeft) &&
                Objects.equals(edgeRight, that.edgeRight);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(edgeDown, edgeLeft, edgeRight);
    }

    @Override
    public String toString()
    {
        return String.format("DownIntersection(%X, edges: [%d, %d, %d], vertices: [%X, %X, %X])",
                this.hashCode(),
                edgeDown != null ? edgeDown.id : null,
                edgeLeft != null ? edgeLeft.id : null,
                edgeRight != null ? edgeRight.id : null,
                vertexLeft != null ? vertexLeft.hashCode() : null,
                vertexRight != null ? vertexRight.hashCode() : null,
                vertexUp != null ? vertexUp.hashCode() : null);
    }
}

enum OffsetStyle
{
    ODD_R,
    EVEN_R,
    ODD_Q,
    EVEN_Q
}

class OffsetCoordinate
{
    public int c;
    public int r;
    private final OffsetStyle style;

    OffsetCoordinate()
    {
        this(0, 0);
    }

    OffsetCoordinate(int c, int r)
    {
        this(c, r, OffsetStyle.ODD_R);
    }

    OffsetCoordinate(int c, int r, OffsetStyle style)
    {
        this.c = c;
        this.r = r;
        this.style = style;
    }


    /**
     * @return Cube coordinate conversion of these coordinates, accounting for offset style.
     */
    public Cube toCube()
    {
        int x, y, z;
        switch (style) {
            case ODD_R:
                x = c - (r - (r & 1)) / 2;
                z = r;
                break;
            case EVEN_R:
                x = c - (r + (r & 1)) / 2;
                z = r;
                break;
            case ODD_Q:
                x = c;
                z = r - (c - (c & 1)) / 2;
                break;
            case EVEN_Q:
                x = c;
                z = r - (c + (c & 1)) / 2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + style);
        }
        y = -x - z;
        return new Cube(x, y, z);
    }
}

class Cube
{
    public int x;
    public int y;
    public int z;


    Cube()
    {
        this(0, 0, 0);
    }

    Cube(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Cube add(Cube other)
    {
        return new Cube(x + other.x, y + other.y, z + other.z);
    }

    public Cube subtract(Cube other)
    {
        return new Cube(x - other.x, y - other.y, z - other.z);
    }

    public Cube multiply(Cube other)
    {
        return new Cube(x * other.x, y * other.y, z * other.z);
    }

    public Cube divide(Cube other)
    {
        return new Cube(x / other.x, y / other.y, z / other.z);
    }

    @Override
    public String toString()
    {
        return String.format("Cube{x=%d, y=%d, z=%d}", x, y, z);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return x == cube.x &&
                y == cube.y &&
                z == cube.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }
}

class Node
{
    private HexGraph parent;
    public int id;
    public int x;
    public int y;
    public int z;
    public Cube cube;

    private boolean sideReady = false;
    private boolean intersectionReady = false;

    // Sides along other Nodes
    public Node[] sides;
    public Node A;
    public Node B;
    public Node C;
    public Node D;
    public Node E;
    public Node F;

    // Intersections touching two other Nodes
    public Intersection[] intersections;
    public UpIntersection a;
    public DownIntersection b;
    public UpIntersection c;
    public DownIntersection d;
    public UpIntersection e;
    public DownIntersection f;

    private static final Cube[] offsets;
    public final Cube[] neighbors;

    static {
        // A, B, C, D, E, F in that order
        offsets = new Cube[]{
                new Cube(+1, -1, 0), new Cube(+1, 0, -1), new Cube(0, +1, -1),
                new Cube(-1, +1, 0), new Cube(-1, 0, +1), new Cube(0, -1, +1)
        };
    }

    Node(int id, int c, int r)
    {
        this(id, new OffsetCoordinate(c, r));

    }

    Node(int id, OffsetCoordinate coordinates)
    {
        this(id, coordinates.toCube());
    }

    Node(int id, Cube cube)
    {
        this.id = id;
        this.x = cube.x;
        this.y = cube.y;
        this.z = cube.z;

        this.cube = cube;

        // Generate all Node neighbor coordinates
        int i = 0;
        neighbors = new Cube[offsets.length];
        for (Cube offset : offsets)
            neighbors[i++] = cube.add(offset);
    }

    /**
     * Initializes neighbor references.
     */
    public void initSides(HexGraph parent)
    {
        // Remember the parent
        this.parent = parent;

        // Acquire all neighbors
        A = parent.getNode(neighbors[0]);
        B = parent.getNode(neighbors[1]);
        C = parent.getNode(neighbors[2]);
        D = parent.getNode(neighbors[3]);
        E = parent.getNode(neighbors[4]);
        F = parent.getNode(neighbors[5]);
        sides = new Node[]{A, B, C, D, E, F};

        sideReady = true;

        // Recursively initialize all neighbors
        for (Node neighbor : sides)
            if (neighbor != null && !neighbor.sideReady)
                neighbor.initSides(parent);
    }

    public void initIntersections()
    {
        if (sideReady) {
            intersections = new Intersection[6];
            boolean[] intersectionsReady = new boolean[6];
            for (int i = 0; i < 6; i++) {
                Node[] pertinentSides = new Node[]{getSide(i), getSide(i + 1)};

                // For each of the other two nodes in the intersection
                for (int j = 0; j < 2; j++) {
                    Node possible = pertinentSides[j];

                    // Check that the neighbor is available & it has already completed it's intersection math
                    if (possible != null && possible.intersectionReady) {
                        Intersection inter = possible.getIntersection(i + (2 * (j + 1)));
                        inter.register(i, this); // Register this node to the intersection
                        intersectionsReady[i] = true;
                        break;
                    }
                }

                // If neighbors had no Intersection, create our own
                if (!intersectionsReady[i]) {
                    Intersection intersection;
                    // Alternate between Up and Down intersections on each edge
                    if (i % 2 == 0)
                        intersection = new UpIntersection();
                    else
                        intersection = new DownIntersection();
                    intersection.register(i, this); // Register ourselves
                    parent.intersections.add(intersection);

                    // Check the other two sides, register them to the intersection if valid sides
                    for (int j = 0; j < 2; j++) {
                        Node possible = pertinentSides[j];
                        if (possible != null && !possible.intersectionReady) {
                            int intersectionIndex = i + (2 * (j + 1));
                            intersection.register(intersectionIndex, possible);
                        }
                    }
                }

                intersections[i] = getIntersection(i);
            }

            intersectionReady = true;
            for (Node neighbor : sides)
                if (neighbor != null && !neighbor.intersectionReady)
                    neighbor.initIntersections();

        } else {
            throw new IllegalStateException("Must call initSide() before calling initIntersection().");
        }
    }

    private Intersection getIntersection(int i)
    {
        switch (Math.abs(i) % 6) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            case 3:
                return d;
            case 4:
                return e;
            case 5:
                return f;
            default:
                return intersections[i % 6];
        }
    }

    public Node getSide(int i)
    {
        return sides[i % neighbors.length];
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id &&
                x == node.x &&
                y == node.y &&
                z == node.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString()
    {
        ArrayList<Integer> edges = new ArrayList<>();
        for (Node side : sides)
            if (side != null)
                edges.add(side.id);
        return String.format("Node{id=%d, x=%d, y=%d, z=%d, neighbors=%s}", id, x, y, z, edges);
    }
}

class HexGraph
{
    public LinkedList<Intersection> intersections;
    HashMap<Integer, Node> nodes; // HashCode -> Node
    HashMap<Integer, Node> nodesByID; // Id -> Node

    HexGraph(String data, int[] center)
    {
        String[] rows = data.split("\n");
        nodes = new HashMap<>();
        nodesByID = new HashMap<>();

        int r = 0, c = 0;
        for (String row : rows) {
            String[] items = row.split(" ");

            // Fill in the row with each item
            for (String item : items) {
                // Represent holes in this hexagon-shaped graph with null
                if (!item.equals("-")) {
                    Node node = new Node(Integer.parseInt(item), c - center[0], r - center[1]);
                    putNode(node);
                }
                c++;
            }

            c = 0;
            r++;
        }

        intersections = new LinkedList<>();

        Node centerNode = getNode(0, 0, 0);
        centerNode.initSides(this);
        centerNode.initIntersections();
    }

    /**
     * @param x Node X coordinate
     * @param y Node Y coordinate
     * @param z Node Z coordinate
     * @return The Node with the coordinates placed into the HashMap
     */
    public Node getNode(int x, int y, int z)
    {
        return nodes.get(Objects.hash(x, y, z));
    }

    /**
     * @param node The node to put into the HashMap
     */
    public void putNode(Node node)
    {
        nodes.put(node.hashCode(), node);
        nodesByID.put(node.id, node);
    }

    @Override
    public String toString()
    {
        return String.format(Locale.ENGLISH, "%d Nodes", nodes.size());
    }

    public Node getNode(Cube pos)
    {
        return getNode(pos.x, pos.y, pos.z);
    }

    public Intersection findIntersection(int a, int b, int c)
    {
        Set<Intersection> potentialA = new HashSet<>(Arrays.asList(nodesByID.get(a).intersections));
        Set<Intersection> potentialB = new HashSet<>(Arrays.asList(nodesByID.get(b).intersections));
        Set<Intersection> potentialC = new HashSet<>(Arrays.asList(nodesByID.get(c).intersections));

        potentialA.retainAll(potentialB);
        potentialA.retainAll(potentialC);

        return (Intersection) potentialA.toArray()[0];
    }
}

class Vertex implements Comparable<Vertex> {
    int id; // Unique Hashcode ID
    LinkedList<Vertex> adjacent; // All vertices this vertex touches
    int distance = Integer.MAX_VALUE; // How far this node is from the source vertex (MAX_VALUE = Infinite)
    boolean seen = false; // Whether or not the Node has been seen in the queue
    List<Integer> parentIDs; // IDs of the corresponding Node edges
    public Vertex touched; // The vertex this vertex was path'd by (LinkedList)

    Vertex(Intersection truth) {
        this.id = truth.hashCode();
        this.parentIDs = truth.getEdges();
        this.adjacent = new LinkedList<>();
    }

    @Override
    public int compareTo(Vertex o)
    {
        return this.distance - o.distance;
    }

    public void addAdjacent(Vertex vertex)
    {
        adjacent.offer(vertex);
    }

    public Stack<Vertex> getTouchStack() {
        Stack<Vertex> stack = new Stack<>();
        Vertex current = this;
        while (current.touched != null) {
            stack.add(current);
            current = current.touched;
        }
        return stack;
    }

    @Override
    public String toString() {
        return String.format("Vertex(%X, %b, distance: %d, vertices: %s, edges: %s, prev: %X)",
                id ,
                seen,
                distance,
                Arrays.toString(adjacent.stream().map(vertex -> String.format("%X", vertex.id)).toArray()),
                parentIDs,
                touched != null ? touched.id : null);
    }
}


/**
 * Manages a Dijkstra's Algorithm graph
 */
class Dijkstra
{
    PriorityQueue<Vertex> queue;
    HashMap<Intersection, Vertex> map;
    Dijkstra(HexGraph hexGraph) {
        queue = new PriorityQueue<>();
        map = new HashMap<>();

        // Create all Vertex objects
        for (Intersection intersection : hexGraph.intersections)
            map.put(intersection, new Vertex(intersection));

        // Add all edges to Vertex objects
        for (Map.Entry<Intersection, Vertex> pair : map.entrySet()) {
            Vertex vertex = pair.getValue();
            for (Intersection neighbor : pair.getKey().vertices())
                if (neighbor != null) {
                    vertex.addAdjacent(map.get(neighbor));
                }
        }
    }

    public void generate(Intersection source) {
        generate(source, null);
    }

    public void generate(Intersection source, Intersection destination) {
        Vertex starter = map.get(source);
        starter.distance = 0;

        Vertex end = map.get(destination);
        queue.offer(starter);

        while (!queue.isEmpty()) {
            Vertex extracted = queue.poll();

            // Only start on unseen vertexes
            if (!extracted.seen) {
                extracted.seen = true;

                if (end == extracted)
                    return;

                // Iterate along all unseen adjacents
                for (Vertex adjacent : extracted.adjacent) {
                    if (!adjacent.seen) {
                        int newKey = extracted.distance + 1;

                        if (newKey < adjacent.distance) {
                            adjacent.distance = newKey;
                            adjacent.touched = extracted;
                            queue.offer(adjacent);
                        }
                    }
                }
            }
        }
    }
}

public class Roads
{
    // Simple input graph representing
    public static final String data = "- 1 2 3 -\n4 5 6 7\n8 9 10 11 12\n13 14 15 16\n- 17 18 19 -";
    public static final int[] center = new int[]{2, 2};

    public static void main(String[] args) throws FileNotFoundException
    {
        HexGraph graph = new HexGraph(data, center);
        Scanner scanner = new Scanner(new File("roads.dat"));
        int inputs = scanner.nextInt();

        for (int i = 0; i < inputs; i++) {
            Dijkstra dijkstra = new Dijkstra(graph);
            Intersection source = graph.findIntersection(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            Intersection destination = graph.findIntersection(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            dijkstra.generate(source, destination);

            System.out.println(dijkstra.map.get(destination).distance);
        }
    }

    public static void debugPrint(HexGraph graph) {
        // Print every node, it's intersections, and those intersection's vertices.
        for (Node node : graph.nodes.values()) {
            System.out.printf("%s\n", node);
            for (Intersection intersection : node.intersections)
                if (intersection != null) {
                    System.out.println("\t" + intersection);
                    for (Intersection vertex : intersection.vertices())
                        System.out.println("\t\t" + vertex);
                }
        }
    }
}
