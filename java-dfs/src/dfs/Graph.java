package dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Directed graph stored as an adjacency list. Insertion order of vertices
 * and edges is preserved so traversals are deterministic.
 */
public final class Graph {
    private final Map<String, List<String>> adjacency = new LinkedHashMap<>();

    public void addVertex(String vertex) {
        adjacency.computeIfAbsent(vertex, key -> new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        addVertex(from);
        addVertex(to);
        adjacency.get(from).add(to);
    }

    public List<String> neighbors(String vertex) {
        List<String> neighbors = adjacency.get(vertex);
        if (neighbors == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList<String>(neighbors));
    }

    public boolean contains(String vertex) {
        return adjacency.containsKey(vertex);
    }
}
