package com.example.roadtrip_fwanddp.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Reference: Code copied form here: https://www.baeldung.com/java-dijkstra
 */

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    // getters and setters

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
