package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import algorithms.BranchAndBoundTSP;
import algorithms.BruteForceTSP;
import algorithms.DynamicProgrammingTSP;
import algorithms.NearestNeighbourTSP;
import main.Main.Node;
import model.Point;
import model.TSPInput;
import utils.DrawingUtils;
import utils.StdDraw;
import utils.TSPReader;

public class Main {

	public static final List<Point> TEST_POINTS = Arrays.asList(new Point(0.70501, 0.58793, 1),
			new Point(0.26077, 0.84765, 2), new Point(0.12284, 0.19949, 3), new Point(0.20125, 0.85198, 4),
			new Point(0.48505, 0.08244, 5), new Point(0.94810, 0.30570, 6), new Point(0.69991, 0.32967, 7),
			new Point(0.15261, 0.59770, 8), new Point(0.56046, 0.39271, 9), new Point(0.80336, 0.23430, 10));
	

	public static void main(String[] args) {
		Point p1 = new Point(0, 0, 1);
		Point p2 = new Point(2, 2, 2);
		Point p3 = new Point(2, 0, 3);
		Point p4 = new Point(0, 2, 4);
		
		List<Point> SMALL_TEST_POINTS = Arrays.asList(p1, p2, p3, p4);
//
		TSPReader tspReader = new TSPReader("hm10.tsp");
		TSPInput tspInput = tspReader.read();

//		TSPInput tspInput = new TSPInput("", "", "", 4, "EUC_2D", SMALL_TEST_POINTS);
//		AlgorithmDriver driver = new AlgorithmDriver(new BruteForceTSP());
//		driver.executeStrategy(tspInput);
		
		
//		AlgorithmDriver driver = new AlgorithmDriver(new BruteForceTSP());
//		driver.executeStrategy(TEST_POINTS);
		
//		driver = new AlgorithmDriver(new BranchAndBoundTSP());
//		driver = new AlgorithmDriver(new NearestNeighbourTSP());

		AlgorithmDriver driver = new AlgorithmDriver(new DynamicProgrammingTSP());
		driver.executeStrategy(tspInput);
		

		List<Pair> pairs = new ArrayList<>();
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		
		
		pairs.add(new Pair(n1, n2));
		pairs.add(new Pair(n1,n3));
		pairs.add(new Pair(n2, n4));
		pairs.add(new Pair(n3,n5));
		
		Node buildTree = buildTree(pairs);
		preoder(buildTree);
		System.out.println();
		
	}
	public static void preoder(Node root) {
		
		System.out.println(root);
		
		List<Node> children = root.children;
		for (Node node : children) {
			preoder(node);
		}
		
	}
	public static Node buildTree(List<Pair> edges) {
	    Set<Node> rootNodes = new HashSet<>();
	    Set<Node> childNodes = new HashSet<>();

	    for(Pair edge: edges){
	    	Node start = edge.start;
	    	Node end = edge.end;
	    	
	    	if (!childNodes.contains(start)) {
	    		rootNodes.add(start);
	    		childNodes.add(start);
	    	}
	    	
	    	if (!childNodes.contains(end))
	    		childNodes.add(end);
	    	else
	    		rootNodes.remove(end);
	    	
	    	start.children.add(end);
	    }


	    return rootNodes.iterator().next();
	}
	
	static class Node {
	    private final int data;
	    private List<Node> children = new ArrayList<>();

	    public Node(int data){
	        this.data = data;
	    }

	    public void addChild(Node node){
	        children.add(node);
	    }

	    public List<Node> getChildren(){
	        return children;
	    }

	    public int getData(){
	        return data;
	    }
	    
	    @Override
	    public String toString() {
	    	return data + "";
	    }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + data;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (data != other.data)
				return false;
			return true;
		}
	    
	    
	    
	}
	
	static class Pair {
		Node start;
		Node end;
		
		public Pair(Node a, Node b) {
			this.start = a;
			this.end = b;
		}

		@Override
		public String toString() {
			return "[start=" + start + ", end=" + end + "]";
		}
		
		
	}
	
}
