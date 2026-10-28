package com.example.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Undirected graph backed by an adjacency list.
 */
public final class Graph {

    private final Map<Integer, List<Integer>> adjacency = new HashMap<>();

    public void addEdge(int u, int v) {
        adjacency.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjacency.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void addDirectedEdge(int from, int to) {
        adjacency.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        adjacency.computeIfAbsent(to, k -> new ArrayList<>());
    }

    public List<Integer> neighbors(int vertex) {
        List<Integer> neighbors = adjacency.get(vertex);
        return neighbors == null ? Collections.emptyList() : neighbors;
    }

    public List<Integer> vertices() {
        List<Integer> keys = new ArrayList<>(adjacency.keySet());
        Collections.sort(keys);
        return keys;
    }
}
