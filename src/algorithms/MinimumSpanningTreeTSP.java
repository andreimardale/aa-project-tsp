/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import model.Point;
import model.TSPInput;
import utils.TSPReader;

/**
 *
 * @author aliha
 */
public class MinimumSpanningTreeTSP extends AbstractTSP {

    private class Subset {

        int parent;
        int rank;
    }

    private class Node {

        int index;
        ArrayList<Node> children;

        public Node(int index) {
            this.index = index;
            children = new ArrayList<>();
        }

        public void add_child(Node child) {
            children.add(child);
        }

        @Override
        public String toString() {
            return index + "";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + index;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Node other = (Node) obj;
            if (index != other.index) {
                return false;
            }
            return true;
        }

    }

    private class Edge {

        Node start;
        Node end;
        int weight;

        public Edge(Node start, Node end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return start + " -> " + end + " : " + weight;
        }

    }


    @Override
    public void execute(TSPInput input) {
        int[][] adj_matrix = input.getDist();
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Node> nodes = init_nodes(input.getDimension());
        for (int i = 0; i < adj_matrix.length; i++) {
            for (int j = i + 1; j < adj_matrix.length; j++) {
                edges.add(new Edge(nodes.get(i), nodes.get(j), adj_matrix[i][j]));
            }
        }
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge t, Edge t1) {
                return t.weight - t1.weight;
            }
        });
        preorder(build_tree(edges), input);
        getBestCircuit().add(getBestCircuit().get(0));
        calculate_route_cost(input);
    }
    

    public ArrayList<Node> init_nodes(int n) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i));
        }
        return nodes;
    }
    
    public int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    void Union(Subset subsets[], int root_of_x, int root_of_y) {

        if (subsets[root_of_x].rank < subsets[root_of_y].rank) {
            subsets[root_of_x].parent = root_of_y;
        } else if (subsets[root_of_x].rank > subsets[root_of_y].rank) {
            subsets[root_of_y].parent = root_of_x;
        } else {
            subsets[root_of_y].parent = root_of_x;
            subsets[root_of_x].rank++;
        }
    }

    public void calculate_route_cost(TSPInput input) {
        int sum = 0;
        for (int i = 0; i < input.getDimension() - 1; i++) {
            sum += input.getDist()[getBestCircuit().get(i)][getBestCircuit().get(i + 1)];
        }
        sum += input.getDist()[getBestCircuit().get(input.getDimension() - 1)][getBestCircuit().get(0)];
        setMinimumCost(sum);
    }

    public ArrayList<Edge> getMST(ArrayList<Edge> sorted_edges, int n) {
        ArrayList<Edge> result = new ArrayList<>();
        Subset[] sub_sets = new Subset[n];
        for (int i = 0; i < n; i++) {
            //init all nodes 
            sub_sets[i] = new Subset();
            sub_sets[i].parent = i;
            sub_sets[i].rank = 0;
        }
        for (int i = 0; i < sorted_edges.size(); i++) {
            int root1 = find(sub_sets, sorted_edges.get(i).start.index);
            int root2 = find(sub_sets, sorted_edges.get(i).end.index);
            if (root1 != root2) {
                result.add(sorted_edges.get(i));
                Union(sub_sets, root1, root2);
            }
        }
        return result;
    }
    public int getCostofMst(ArrayList<Edge> edges) {
        int sum = 0;
        for (int i = 0; i < edges.size(); i++) {
            sum += edges.get(i).weight;
        }
        return sum;
    }


    public Node build_tree(ArrayList<Edge> edges_of_mst) {
        Set<Node> rootNodes = new HashSet<>();
        Set<Node> childNodes = new HashSet<>();

        for (Edge edge : edges_of_mst) {
            Node start = edge.start;
            Node end = edge.end;

            if (!childNodes.contains(start)) {
                rootNodes.add(start);
                childNodes.add(start);
            }

            if (!childNodes.contains(end)) {
                childNodes.add(end);
            } else {
                rootNodes.remove(end);
            }

            start.children.add(end);
            end.children.add(start);
        }

        return rootNodes.iterator().next();
    }
    ArrayList<Node> visited_preorder = new ArrayList<>();

    public void preorder(Node n, TSPInput input) {
        visited_preorder.add(n);
        getBestCircuit().add(n.index);
        for (Node node : n.children) {
            if (!visited_preorder.contains(node)) {
                preorder(node, input);
            }
        }
    }
}
