import java.util.Random;

public class Experiment {

    public static Graph generateRandomGraph(int numVertices, int numEdges, boolean isDirected) {
        Graph g = new Graph(isDirected);

        for (int i = 0; i < numVertices; i++) {
            g.addVertex(new Vertex(i));
        }

        Random rand = new Random(42); // Seed для воспроизводимости
        int edgesAdded = 0;

        while (edgesAdded < numEdges) {
            int from = rand.nextInt(numVertices);
            int to = rand.nextInt(numVertices);
            if (from != to) {
                try {
                    int weight = rand.nextInt(10) + 1; // Вес от 1 до 10
                    g.addEdge(from, to, weight);
                    edgesAdded++;
                } catch (IllegalArgumentException e) {
                    // Игнорируем ошибки дубликатов, если бы они проверялись
                }
            }
        }
        return g;
    }

    public void runTraversals(Graph g, String sizeLabel, boolean printOrder) {
        System.out.println("\n=== Testing " + sizeLabel + " Graph ===");

        if (printOrder) {
            System.out.println("Graph Structure:");
            g.printGraph();
        }

        // Тест BFS
        long startBfs = System.nanoTime();
        if (printOrder) System.out.print("BFS Order: ");
        g.bfs(0);
        long endBfs = System.nanoTime();

        // Тест DFS
        long startDfs = System.nanoTime();
        if (printOrder) System.out.print("DFS Order: ");
        g.dfs(0);
        long endDfs = System.nanoTime();

        System.out.println("BFS Execution Time: " + (endBfs - startBfs) + " ns");
        System.out.println("DFS Execution Time: " + (endDfs - startDfs) + " ns");

        // Тест Дейкстры (Бонус)
        System.out.println("Running Dijkstra from V0...");
        g.dijkstra(0);
    }

    public void runMultipleTests() {
        // Small
        Graph small = generateRandomGraph(10, 15, false);
        runTraversals(small, "Small (10 Vertices)", true);

        // Medium
        Graph medium = generateRandomGraph(30, 60, false);
        runTraversals(medium, "Medium (30 Vertices)", false);

        // Large
        Graph large = generateRandomGraph(100, 300, false);
        runTraversals(large, "Large (100 Vertices)", false);
    }
}