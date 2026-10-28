package dfs;

import java.util.List;

/**
 * Sample directed graph:
 *
 * <pre>
 *     A → B → D
 *     ↓   ↓
 *     C → E → F
 * </pre>
 */
public final class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "E");
        graph.addEdge("E", "F");

        String start = args.length > 0 ? args[0] : "A";
        List<String> recursive = DepthFirstSearch.recursive(graph, start);
        List<String> iterative = DepthFirstSearch.iterative(graph, start);

        System.out.println("Recursive DFS from " + start + ": " + recursive);
        System.out.println("Iterative DFS from " + start + ": " + iterative);
    }
}
