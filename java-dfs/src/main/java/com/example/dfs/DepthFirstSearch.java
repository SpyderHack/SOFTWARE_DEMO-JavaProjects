package com.example.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Depth-first search on a {@link Graph}.
 */
public final class DepthFirstSearch {

    private DepthFirstSearch() {
    }

    /**
     * Recursive DFS from {@code start}. Visits each reachable vertex once, in discovery order.
     */
    public static List<Integer> dfsRecursive(Graph graph, int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsRecursiveVisit(graph, start, visited, order);
        return order;
    }

    private static void dfsRecursiveVisit(
            Graph graph,
            int vertex,
            Set<Integer> visited,
            List<Integer> order) {
        if (!visited.add(vertex)) {
            return;
        }
        order.add(vertex);
        for (int neighbor : graph.neighbors(vertex)) {
            dfsRecursiveVisit(graph, neighbor, visited, order);
        }
    }

    /**
     * Iterative DFS using an explicit stack (LIFO). Neighbors are pushed in reverse
     * so smaller indices are popped first, matching typical recursive tie-breaking.
     */
    public static List<Integer> dfsIterative(Graph graph, int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited.add(vertex)) {
                continue;
            }
            order.add(vertex);
            List<Integer> neighbors = new ArrayList<>(graph.neighbors(vertex));
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
        return order;
    }

    /**
     * Runs DFS over all connected components, starting each unvisited vertex in sorted order.
     */
    public static List<Integer> dfsAllVertices(Graph graph, boolean iterative) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> order = new ArrayList<>();

        for (int start : graph.vertices()) {
            if (visited.contains(start)) {
                continue;
            }
            List<Integer> component = iterative
                    ? dfsIterativeFrom(graph, start, visited)
                    : dfsRecursiveFrom(graph, start, visited);
            order.addAll(component);
        }
        return order;
    }

    private static List<Integer> dfsRecursiveFrom(Graph graph, int start, Set<Integer> visited) {
        List<Integer> order = new ArrayList<>();
        dfsRecursiveVisit(graph, start, visited, order);
        return order;
    }

    private static List<Integer> dfsIterativeFrom(Graph graph, int start, Set<Integer> visited) {
        List<Integer> order = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited.add(vertex)) {
                continue;
            }
            order.add(vertex);
            List<Integer> neighbors = new ArrayList<>(graph.neighbors(vertex));
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
        return order;
    }

    /**
     * DFS with a visitor callback invoked when each vertex is first discovered.
     */
    public static void dfsWithVisitor(Graph graph, int start, Consumer<Integer> onVisit) {
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited.add(vertex)) {
                continue;
            }
            onVisit.accept(vertex);
            List<Integer> neighbors = new ArrayList<>(graph.neighbors(vertex));
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }
}
