import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    List<Node> nodes;
    List<Edge> edges;

    public Graph(){
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
    public void addEdge(int id1, int id2, int weight){
        Node v1 = findNode(id1);
        Node v2 = findNode(id2);
        for (Edge edge : edges) {
            if ((edge.v1 == v1 && edge.v2 == v2) || (edge.v1 == v2 && edge.v2 == v1)) {
                System.out.println("Krawędź już istnieje.");
                return;
            }
        }

        if (v1 != null && v2 != null) {
            edges.add(new Edge(v1, v2, weight));
            System.out.println("Dodano nową Krawędź o wierzchołkach (które mają id) v1: " + v1.id + " " + "v2: " + v2.id + " " + "weight: " + weight);
        } else {
            System.out.println("Nie ma Node, który połączyłby się z tą krawędzią");
        }
    }


    public Edge findEdge(int id1, int id2) {
        if (this.edges != null) {
            for (int i = 0; i < this.edges.size(); i++) {
                if (id1 == this.edges.get(i).v1.id && id2 == this.edges.get(i).v2.id) {
                    return edges.get(i);
                }
            }

        }
        return null;
    }
    public List<Edge> getEdgeConnection(int id){
        List<Edge> edgesTemp = new ArrayList<Edge>();

        if (edges != null){
            int i = 0;
            for (i = 0; i < edges.size(); i++){
                if (id == edges.get(i).v1.id || id == edges.get(i).v2.id){
                    edgesTemp.add(edges.get(i));
                }
            }
        }
        else{
            return null;
        }

        return edgesTemp;
    }

    public void removeNodes(int id) {
        Node node = findNode(id);
        if (node != null) {
            List<Edge> edgesRemoval = getEdgeConnection(id);
            Edge edgeLog;
                if (edgesRemoval != null) {
                    for (int i = 0; i < edgesRemoval.size(); i++) {
                        System.out.println("Usunięto krawędź z wagą" + edgesRemoval.get(i).weight + " i id1: " + edgesRemoval.get(i).v1.id + "oraz id2: " + edgesRemoval.get(i).v2.id);
                        edges.remove(edgesRemoval.get(i));

                    }
                }
                nodes.remove(node);
                System.out.println("usunięto wierzchołek z id: " + id);
        }
        else {
            System.out.println("Wierzchołek z id: " + id + " " + "nie istnieje");
        }

    }

    public void removeEdge(int id1, int id2) {
        if (findEdge(id1, id2) != null) {
            edges.remove(findEdge(id1, id2));
            System.out.println("Usunięto krawędź przyczepionej do wierzchołków o id1 :" + id1 + " " + "id2: " + id2);
        }
        else{
            System.out.println("Nie znaleziono krawędzi o wierzchołkah o id1 :" + id1 + " " + "id2: " + id2);
        }
    }

    public void addNode(int id) {
        if (findNode(id) == null) {
            this.nodes.add(new Node(id));
            System.out.println("Dodano nowy NODE z id:" + id);
        }
        else {
            System.out.println("Node z id: " + id + " " + "Już istnieje");
        }
    }

    public Node findNode(int id) {
        for (int i = 0; i < this.nodes.size(); i++) {
            if(this.nodes.get(i).id == id) {
                return nodes.get(i);
            }
        }
        return null;
    }
    public void djikstra(int sourceId) {
    Node source = findNode(sourceId);
    if (source == null) {
        System.out.println("Nie istnieje wierzchołek o id: " + sourceId);
        return;
    }


    Map<Node, Integer> distance = new HashMap<>();
    Map<Node, Node> previous = new HashMap<>(); 
    for (Node node : nodes) {
        distance.put(node, Integer.MAX_VALUE);
        previous.put(node, null);
    }
    distance.put(source, 0);

    Set<Node> visited = new HashSet<>();
    while (!visited.containsAll(nodes)) {

        int minDistance = Integer.MAX_VALUE;
        Node u = null;
        for (Node node : distance.keySet()) {
            if (!visited.contains(node) && distance.get(node) < minDistance) {
                u = node;
                minDistance = distance.get(node);
            }
        }
        if (u == null) {

            break;
        }

        visited.add(u);

 
        for (Edge edge : getEdgeConnection(u.id)) {
            Node v = (edge.v1 == u) ? edge.v2 : edge.v1;
            int alt = distance.get(u) + edge.weight;
            if (alt < distance.get(v)) {
                distance.put(v, alt);
                previous.put(v, u); // Update previous node for v
            }
        }
    }


    for (Node node : nodes) {
        if (node != source) {
            List<Node> path = new ArrayList<>();
            Node current = node;
            while (current != null) {
                path.add(current);
                current = previous.get(current);
            }
            Collections.reverse(path);
            System.out.print("Shortest path from " + sourceId + " to " + node.id + ": ");
            if (distance.get(node) < Integer.MAX_VALUE) {
                System.out.print(path.stream().map(n -> String.valueOf(n.id)).collect(Collectors.joining(" -> ")));
                System.out.println(" (Distance: " + distance.get(node) + ")");
            } else {
                System.out.println("unreachable");
            }
        }
    }
}
    public List<Edge> kruskal() {
        List<Edge> mst = new ArrayList();
        edges.sort(Comparator.comparingInt(e -> e.weight));
        int[] parent = new int[nodes.size()];
        Arrays.fill(parent, -1);
        int edgesAdded = 0;
        int index =0;
        while (mst.size() < nodes.size() -1 && edges.size() > index) {
            Edge nextEdge = edges.get(index++);
            int root1 = find(parent, nextEdge.v1.id);
            int root2 = find(parent, nextEdge.v2.id);
            if (root1 != root2) {
                mst.add(nextEdge);
                edgesAdded++;
                union(parent, root1, root2);

            }
            return mstl;

        }

    }

}
