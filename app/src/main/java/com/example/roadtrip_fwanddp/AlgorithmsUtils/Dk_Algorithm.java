package com.example.roadtrip_fwanddp.AlgorithmsUtils;

import android.util.Log;

import com.example.roadtrip_fwanddp.AlgorithmsUtils.DK_Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Reference: Code copied form here: https://www.baeldung.com/java-dijkstra
 * Modified to fit my need
 */

public class Dk_Algorithm {

    public static ArrayList<DK_Node> calculateShortestPathFromSource(DK_Node source) {
        source.setDistance(0);

        ArrayList<DK_Node> settledDKNodes = new ArrayList<>();
        Set<DK_Node> unsettledDKNodes = new HashSet<>();

        unsettledDKNodes.add(source);

        while (unsettledDKNodes.size() != 0) {
            DK_Node currentDKNode = getLowestDistanceNode(unsettledDKNodes);
            unsettledDKNodes.remove(currentDKNode);
            for (Map.Entry<DK_Node, Integer> adjacencyPair:
                    currentDKNode.getAdjacentNodes().entrySet()) {
                DK_Node adjacentDKNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledDKNodes.contains(adjacentDKNode)) {
                    calculateMinimumDistance(adjacentDKNode, edgeWeight, currentDKNode);
                    unsettledDKNodes.add(adjacentDKNode);
                }
            }
            settledDKNodes.add(currentDKNode);
            Log.i("NODEADDED", currentDKNode.getName());
            Log.i("NODEADDED", currentDKNode.getDistance().toString());
        }
        return settledDKNodes;
    }

    private static DK_Node getLowestDistanceNode(Set <DK_Node> unsettledDKNodes) {
        DK_Node lowestDistanceDKNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (DK_Node DKNode : unsettledDKNodes) {
            int nodeDistance = DKNode.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceDKNode = DKNode;
            }
        }
        return lowestDistanceDKNode;
    }

    private static void calculateMinimumDistance(DK_Node evaluationDKNode,
                                                 Integer edgeWeigh, DK_Node sourceDKNode) {
        Integer sourceDistance = sourceDKNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationDKNode.getDistance()) {
            evaluationDKNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<DK_Node> shortestPath = new LinkedList<>(sourceDKNode.getShortestPath());
            shortestPath.add(sourceDKNode);
            evaluationDKNode.setShortestPath(shortestPath);
        }
    }
}
