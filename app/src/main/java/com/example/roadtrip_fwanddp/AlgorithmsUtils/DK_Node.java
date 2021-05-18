package com.example.roadtrip_fwanddp.AlgorithmsUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Reference: Code copied form here: https://www.baeldung.com/java-dijkstra
 */

public class DK_Node {

    private String name;

    private List<DK_Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<DK_Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(DK_Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public DK_Node(String name) {
        this.name = name;
    }

    // getters and setters


    public String getName() {
        return name;
    }

    public List<DK_Node> getShortestPath() {
        return shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public Map<DK_Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setShortestPath(List<DK_Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setAdjacentNodes(Map<DK_Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
