package com.example.dfs;

/**
 * Demo: depth-first traversal on a small graph.
 *
 * <pre>
 *     0 --- 1 --- 3
 *     |     |
 *     2     4
 * </pre>
 */
public final class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        int start = 0;

        System.out.println("Recursive DFS from " + start + ": "
                + DepthFirstSearch.dfsRecursive(graph, start));
        System.out.println("Iterative DFS from " + start + ": "
                + DepthFirstSearch.dfsIterative(graph, start));
        System.out.println("DFS all components (iterative): "
                + DepthFirstSearch.dfsAllVertices(graph, true));
    }
}
