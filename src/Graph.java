import java.util.*;

public class Graph {
    private final Map<Integer, Vertex> vertices;
    private final Map<Integer, List<Edge>> adjList;
    private final boolean isDirected;

    public Graph(boolean isDirected) {
        this.vertices = new HashMap<>();
        this.adjList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addVertex(Vertex v) {
        vertices.putIfAbsent(v.getId(), v);
        adjList.putIfAbsent(v.getId(), new ArrayList<>());
    }


    public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }


    public void addEdge(int from, int to, int weight) {
        Vertex src = vertices.get(from);
        Vertex dest = vertices.get(to);

        if (src == null || dest == null) {
            throw new IllegalArgumentException("Vertices must exist in the graph first.");
        }

        Edge edge = new Edge(src, dest, weight);
        adjList.get(from).add(edge);

        if (!isDirected) {
            Edge backEdge = new Edge(dest, src, weight);
            adjList.get(to).add(backEdge);
        }
    }

    public void printGraph() {
        for (int id : adjList.keySet()) {
            System.out.print("Vertex " + id + " connects to: ");
            for (Edge edge : adjList.get(id)) {
                System.out.print(edge.getDestination().getId() + "(" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }


    public void bfs(int start) {
        if (!vertices.containsKey(start)) return;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (Edge edge : adjList.getOrDefault(current, Collections.emptyList())) {
                int neighbor = edge.getDestination().getId();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }


    public void dfs(int start) {
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (Edge edge : adjList.getOrDefault(current, Collections.emptyList())) {
            int neighbor = edge.getDestination().getId();
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }


    public void dijkstra(int start) {
        if (!vertices.containsKey(start)) return;

        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();


        for (int id : vertices.keySet()) {
            distances.put(id, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        for (int i = 0; i < vertices.size(); i++) {

            int u = -1;
            int minDistance = Integer.MAX_VALUE;

            for (int id : vertices.keySet()) {
                if (!visited.contains(id) && distances.get(id) < minDistance) {
                    minDistance = distances.get(id);
                    u = id;
                }
            }

            if (u == -1) break;
            visited.add(u);


            for (Edge edge : adjList.getOrDefault(u, Collections.emptyList())) {
                int v = edge.getDestination().getId();
                if (!visited.contains(v)) {
                    int newDist = distances.get(u) + edge.getWeight();
                    if (newDist < distances.get(v)) {
                        distances.put(v, newDist);
                    }
                }
            }
        }


        System.out.println("Shortest paths from vertex " + start + ":");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            String distStr = entry.getValue() == Integer.MAX_VALUE ? "Unreachable" : String.valueOf(entry.getValue());
            System.out.println("  To " + entry.getKey() + " -> Distance: " + distStr);
        }
    }
}