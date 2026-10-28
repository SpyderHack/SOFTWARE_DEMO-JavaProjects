package dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Depth-first search over a {@link Graph}.
 * Both forms visit each vertex once and return the discovery order.
 */
public final class DepthFirstSearch {

    private DepthFirstSearch() {
    }

    public static List<String> recursive(Graph graph, String start) {
        requireStart(graph, start);
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        visit(graph, start, visited, order);
        return Collections.unmodifiableList(order);
    }

    public static List<String> iterative(Graph graph, String start) {
        requireStart(graph, start);
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.add(vertex)) {
                continue;
            }
            order.add(vertex);
            List<String> neighbors = graph.neighbors(vertex);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
        return Collections.unmodifiableList(order);
    }

    private static void visit(Graph graph, String vertex, Set<String> visited, List<String> order) {
        if (!visited.add(vertex)) {
            return;
        }
        order.add(vertex);
        for (String neighbor : graph.neighbors(vertex)) {
            visit(graph, neighbor, visited, order);
        }
    }

    private static void requireStart(Graph graph, String start) {
        if (graph == null) {
            throw new IllegalArgumentException("graph must not be null");
        }
        if (start == null || !graph.contains(start)) {
            throw new IllegalArgumentException("start vertex is not in the graph: " + start);
        }
    }
}
