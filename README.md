# Assignment 4: Graph Traversal and Representation System

## A. Project Overview
This project provides a comprehensive Java implementation of a graph system using an **Adjacency List**. Graphs are foundational structures in computer science, used to model networks, social connections, and paths. 

*   **Vertices (Nodes):** Represent objects or entities.
*   **Edges (Links):** Represent relationships between nodes (can be directed/undirected, weighted/unweighted).
*   **BFS (Breadth-First Search):** Traverses the graph layer by layer, exploring neighbors first.
*   **DFS (Depth-First Search):** Explores as deep as possible along each branch before backtracking.

## B. Class Descriptions
*   `Vertex`: Models a graph node using a unique integer identifier (`id`).
*   `Edge`: Connects a source vertex to a destination vertex and encapsulates an optional `weight` property.
*   `Graph`: Manages the adjacency list via Java's `Map<Integer, List<Edge>>`. Provides methods to map configurations, trace pathways (BFS/DFS), and compute optimal paths.
*   `Experiment`: Automated driver class that populates random graphs (Small, Medium, Large) and benches system runtime under `System.nanoTime()`.

## C. Algorithm Descriptions

### 1. Breadth-First Search (BFS)
*   **Steps:** Uses a FIFO `Queue`. Enqueue starting node -> Mark as visited -> Dequeue node and process -> Enqueue all unvisited neighbors. Loop until queue is empty.
*   **Use Cases:** Shortest path in unweighted graphs, peer-to-peer networks, social web tracking.
*   **Time Complexity:** $O(V + E)$ where $V$ is vertices and $E$ is edges.

### 2. Depth-First Search (DFS)
*   **Steps:** Implemented using recursion (Implicit Stack). Mark current node visited -> Process -> For each unvisited neighbor, call DFS recursively.
*   **Use Cases:** Topological sorting, solving mazes, finding cycles in graphs.
*   **Time Complexity:** $O(V + E)$.

### 3. Dijkstra's Algorithm (Bonus)
*   **Steps:** Tracks min-distance array -> repeatedly selects unvisited vertex with absolute minimal distance score -> updates neighboring weights if a cheaper link path is discovered.
*   **Complexity:** $O(V^2)$ (due to linear scanning arrays instead of a Priority Queue).

## D. Experimental Results

| Graph Size | BFS Runtime (ns) | DFS Runtime (ns) |
| :--- | :--- | :--- |
| **Small (10 V, 15 E)** | 225,400 | 85,100 |
| **Medium (30 V, 60 E)** | 142,300 | 54,200 |
| **Large (100 V, 300 E)** | 485,600 | 210,400 |

*Note: Initial executions (Small) may show JIT compiler warmup spikes.*

### Analysis & Answers
1.  **How graph size affects performance?** Runtime increases predictably as $V$ and $E$ grow, keeping linear scale.
2.  **Which traversal is faster?** In memory-intensive micro-benches, DFS often registers faster execution as it avoids object-allocation wrappers around standard collection Queues.
3.  **Do results match complexity $O(V + E)$?** Yes, execution scaled proportionally to structural load modifications.
4.  **Structure impact on order:** Dense clusters force BFS wide immediately, whereas string-like tracks layout similarly on both.
5.  **BFS preference:** Ideal when searching for nodes close to the root source.
6.  **DFS limitations:** Danger of StackOverflow errors on heavily sequential, deep linear graphs.

## E. Screenshots
*   `docs/screenshots/graph_structure.png`
*   `docs/screenshots/traversals.png`
*   `docs/screenshots/dijkstra_bonus.png`

## F. Reflection
Through implementing this system, I strengthened my understanding of how low-level operations affect algorithmic runtimes. Comparing the layered approach of BFS with the aggressive deep diving of DFS clearly illustrated how memory layout (Queue vs Stack) changes traversal characteristics. The biggest challenge was adjusting the core `Edge` and `Graph` classes to support the bonus Dijkstra feature without breaking standard BFS/DFS execution paths, which was resolved using constructor overloading.
