import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tests {

    Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph();
        graph.addNode(((int) 'a'));
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 4);
    }

    @Test
    public void kruskalTest() {
        Graph graph = new Graph();
        graph.kruskal();
    }
}
