import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Tests {

    Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph();
        // Dodawanie wierzchołków
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);

        // Dodawanie krawędzi
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 15);
        graph.addEdge(1, 4, 20);
        graph.addEdge(2, 3, 35);
        graph.addEdge(2, 4, 25);
        graph.addEdge(3, 4, 30);
        graph.addEdge(3,5, 20);

    }

    @Test
    public void kruskalTest() {
        List<Edge> mst = graph.kruskal();
        System.out.println("Minimalne drzewo rozpinające (MST) uzyskane za pomocą algorytmu Kruskala:");
        for (Edge edge : mst) {
            System.out.println(edge.v1.id + " - " + edge.v2.id + " : " + edge.weight);
        }
    }
    @Test
    public void primTest() {
        System.out.println("Algorytm Prima:");

        List<Edge> mst = graph.prim(1);
        int totalWeight = 0;

        for (Edge edge : mst) {
            System.out.println("Krawędź: " + edge.v1.id + " <-> " + edge.v2.id + ", Waga: " + edge.weight);
            totalWeight += edge.weight;
        }

        System.out.println("Suma wag MST: " + totalWeight);
    }
    @Test
    public void coloringTest() {
        // Kolorowanie grafu
        int numColors = graph.greedyColoring();

        // Wyświetlanie wyniku
        System.out.println("Minimalna liczba kolorów potrzebnych do pokolorowania grafu: " + numColors);
    }
}
