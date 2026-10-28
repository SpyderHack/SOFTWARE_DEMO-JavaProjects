package com.example.dfs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DepthFirstSearchTest {

    @Test
    void recursiveAndIterativeMatchOnSameGraph() {
        Graph graph = sampleGraph();
        List<Integer> recursive = DepthFirstSearch.dfsRecursive(graph, 0);
        List<Integer> iterative = DepthFirstSearch.dfsIterative(graph, 0);
        assertEquals(recursive, iterative);
        assertEquals(java.util.Arrays.asList(0, 1, 3, 4, 2), recursive);
    }

    @Test
    void dfsAllVerticesVisitsEveryNode() {
        Graph graph = sampleGraph();
        graph.addEdge(5, 6);

        List<Integer> order = DepthFirstSearch.dfsAllVertices(graph, false);
        assertEquals(7, order.size());
        assertTrue(order.containsAll(java.util.Arrays.asList(0, 1, 2, 3, 4, 5, 6)));
    }

    private static Graph sampleGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        return graph;
    }
}
