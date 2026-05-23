public class Edge {
    private final Vertex source;
    private final Vertex destination;
    private final int weight; // Интеграция для бонусного задания

    // Конструктор для обычного графа (по умолчанию вес = 1)
    public Edge(Vertex source, Vertex destination) {
        this(source, destination, 1);
    }

    // Конструктор для взвешенного графа (Бонус)
    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -> " + destination + " (w:" + weight + ")";
    }
}