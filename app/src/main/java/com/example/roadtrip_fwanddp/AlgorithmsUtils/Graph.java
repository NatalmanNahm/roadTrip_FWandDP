package com.example.roadtrip_fwanddp.AlgorithmsUtils;

import com.example.roadtrip_fwanddp.AlgorithmsUtils.DK_Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Reference: Code copied form here: https://www.baeldung.com/java-dijkstra
 */

public class Graph {

    private Set<DK_Node> DKNodes = new HashSet<>();

    public void addNode(DK_Node DKNodeA) {
        DKNodes.add(DKNodeA);
    }

    // getters and setters

    public Set<DK_Node> getDKNodes() {
        return DKNodes;
    }

    public void setDKNodes(Set<DK_Node> DKNodes) {
        this.DKNodes = DKNodes;
    }
}
