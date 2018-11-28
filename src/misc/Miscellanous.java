package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import utils.TSPGenerator;

public class Miscellanous {

	private static void generateDifferentSizesMediumGaussian(TSPGenerator generator) {
		generator = new TSPGenerator("generated_5_sym_1_50_GaussianDistrib_noSparse.tsp", 5);
		generator.generate();
		
		generator = new TSPGenerator("generated_7_sym_1_50_GaussianDistrib_noSparse.tsp", 7);
		generator.generate();
		
		generator = new TSPGenerator("generated_10_sym_1_50_GaussianDistrib_noSparse.tsp", 10);
		generator.generate();
		
		generator = new TSPGenerator("generated_12_sym_1_50_GaussianDistrib_noSparse.tsp", 12);
		generator.generate();
		
		generator = new TSPGenerator("generated_15_sym_1_50_GaussianDistrib_noSparse.tsp", 15);
		generator.generate();
		
		generator = new TSPGenerator("generated_20_sym_1_50_GaussianDistrib_noSparse.tsp", 20);
		generator.generate();
		
		generator = new TSPGenerator("generated_25_sym_1_50_GaussianDistrib_noSparse.tsp", 25);
		generator.generate();
		
		generator = new TSPGenerator("generated_30_sym_1_50_GaussianDistrib_noSparse.tsp", 30);
		generator.generate();
		
		generator = new TSPGenerator("generated_35_sym_1_50_GaussianDistrib_noSparse.tsp", 35);
		generator.generate();
		
		generator = new TSPGenerator("generated_40_sym_1_50_GaussianDistrib_noSparse.tsp", 40);
		generator.generate();
		
		generator = new TSPGenerator("generated_45_sym_1_50_GaussianDistrib_noSparse.tsp", 45);
		generator.generate();
		
		generator = new TSPGenerator("generated_50_sym_1_50_GaussianDistrib_noSparse.tsp", 50);
		generator.generate();
		
		generator = new TSPGenerator("generated_55_sym_1_50_GaussianDistrib_noSparse.tsp", 55);
		generator.generate();
		
		generator = new TSPGenerator("generated_60_sym_1_50_GaussianDistrib_noSparse.tsp", 60);
		generator.generate();
		
		generator = new TSPGenerator("generated_65_sym_1_50_GaussianDistrib_noSparse.tsp", 65);
		generator.generate();
		
		generator = new TSPGenerator("generated_70_sym_1_50_GaussianDistrib_noSparse.tsp", 70);
		generator.generate();
		
		generator = new TSPGenerator("generated_75_sym_1_50_GaussianDistrib_noSparse.tsp", 75);
		generator.generate();
		
		generator = new TSPGenerator("generated_80_sym_1_50_GaussianDistrib_noSparse.tsp", 80);
		generator.generate();
		
		generator = new TSPGenerator("generated_85_sym_1_50_GaussianDistrib_noSparse.tsp", 85);
		generator.generate();
		
		generator = new TSPGenerator("generated_90_sym_1_50_GaussianDistrib_noSparse.tsp", 90);
		generator.generate();
		
		generator = new TSPGenerator("generated_95_sym_1_50_GaussianDistrib_noSparse.tsp", 95);
		generator.generate();
		
		generator = new TSPGenerator("generated_100_sym_1_50_GaussianDistrib_noSparse.tsp", 100);
		generator.generate();
		
		generator = new TSPGenerator("generated_120_sym_1_50_GaussianDistrib_noSparse.tsp", 120);
		generator.generate();
		
		generator = new TSPGenerator("generated_150_sym_1_50_GaussianDistrib_noSparse.tsp", 150);
		generator.generate();
	}
	
	private static void generateDifferentSizesSmallDistance(TSPGenerator generator) {
		generator = new TSPGenerator("generated_5_sym_1_60_UniformDistrib_noSparse.tsp", 5);
		generator.generate();
		
		generator = new TSPGenerator("generated_7_sym_1_60_UniformDistrib_noSparse.tsp", 7);
		generator.generate();
		
		generator = new TSPGenerator("generated_10_sym_1_60_UniformDistrib_noSparse.tsp", 10);
		generator.generate();
		
		generator = new TSPGenerator("generated_12_sym_1_60_UniformDistrib_noSparse.tsp", 12);
		generator.generate();
		
		generator = new TSPGenerator("generated_15_sym_1_60_UniformDistrib_noSparse.tsp", 15);
		generator.generate();
		
		generator = new TSPGenerator("generated_20_sym_1_60_UniformDistrib_noSparse.tsp", 20);
		generator.generate();
		
		generator = new TSPGenerator("generated_25_sym_1_60_UniformDistrib_noSparse.tsp", 25);
		generator.generate();
		
		generator = new TSPGenerator("generated_30_sym_1_60_UniformDistrib_noSparse.tsp", 30);
		generator.generate();
		
		generator = new TSPGenerator("generated_35_sym_1_60_UniformDistrib_noSparse.tsp", 35);
		generator.generate();
		
		generator = new TSPGenerator("generated_40_sym_1_60_UniformDistrib_noSparse.tsp", 40);
		generator.generate();
		
		generator = new TSPGenerator("generated_45_sym_1_60_UniformDistrib_noSparse.tsp", 45);
		generator.generate();
		
		generator = new TSPGenerator("generated_50_sym_1_60_UniformDistrib_noSparse.tsp", 50);
		generator.generate();
		
		generator = new TSPGenerator("generated_55_sym_1_60_UniformDistrib_noSparse.tsp", 55);
		generator.generate();
		
		generator = new TSPGenerator("generated_60_sym_1_60_UniformDistrib_noSparse.tsp", 60);
		generator.generate();
		
		generator = new TSPGenerator("generated_65_sym_1_60_UniformDistrib_noSparse.tsp", 65);
		generator.generate();
		
		generator = new TSPGenerator("generated_70_sym_1_60_UniformDistrib_noSparse.tsp", 70);
		generator.generate();
		
		generator = new TSPGenerator("generated_75_sym_1_60_UniformDistrib_noSparse.tsp", 75);
		generator.generate();
		
		generator = new TSPGenerator("generated_80_sym_1_60_UniformDistrib_noSparse.tsp", 80);
		generator.generate();
		
		generator = new TSPGenerator("generated_85_sym_1_60_UniformDistrib_noSparse.tsp", 85);
		generator.generate();
		
		generator = new TSPGenerator("generated_90_sym_1_60_UniformDistrib_noSparse.tsp", 90);
		generator.generate();
		
		generator = new TSPGenerator("generated_95_sym_1_60_UniformDistrib_noSparse.tsp", 95);
		generator.generate();
		
		generator = new TSPGenerator("generated_100_sym_1_60_UniformDistrib_noSparse.tsp", 100);
		generator.generate();
		
		generator = new TSPGenerator("generated_120_sym_1_60_UniformDistrib_noSparse.tsp", 120);
		generator.generate();
		
		generator = new TSPGenerator("generated_150_sym_1_60_UniformDistrib_noSparse.tsp", 150);
		generator.generate();
	}
	
	private static void generateDifferentSizes(TSPGenerator generator) {
		generator = new TSPGenerator("generated_5_sym_1_100_UniformDistrib_noSparse.tsp", 5);
		generator.generate();
		
		generator = new TSPGenerator("generated_7_sym_1_100_UniformDistrib_noSparse.tsp", 7);
		generator.generate();
		
		generator = new TSPGenerator("generated_10_sym_1_100_UniformDistrib_noSparse.tsp", 10);
		generator.generate();
		
		generator = new TSPGenerator("generated_12_sym_1_100_UniformDistrib_noSparse.tsp", 12);
		generator.generate();
		
		generator = new TSPGenerator("generated_15_sym_1_100_UniformDistrib_noSparse.tsp", 15);
		generator.generate();
		
		generator = new TSPGenerator("generated_20_sym_1_100_UniformDistrib_noSparse.tsp", 20);
		generator.generate();
		
		generator = new TSPGenerator("generated_25_sym_1_100_UniformDistrib_noSparse.tsp", 25);
		generator.generate();
		
		generator = new TSPGenerator("generated_30_sym_1_100_UniformDistrib_noSparse.tsp", 30);
		generator.generate();
		
		generator = new TSPGenerator("generated_35_sym_1_100_UniformDistrib_noSparse.tsp", 35);
		generator.generate();
		
		generator = new TSPGenerator("generated_40_sym_1_100_UniformDistrib_noSparse.tsp", 40);
		generator.generate();
		
		generator = new TSPGenerator("generated_45_sym_1_100_UniformDistrib_noSparse.tsp", 45);
		generator.generate();
		
		generator = new TSPGenerator("generated_50_sym_1_100_UniformDistrib_noSparse.tsp", 50);
		generator.generate();
		
		generator = new TSPGenerator("generated_55_sym_1_100_UniformDistrib_noSparse.tsp", 55);
		generator.generate();
		
		generator = new TSPGenerator("generated_60_sym_1_100_UniformDistrib_noSparse.tsp", 60);
		generator.generate();
		
		generator = new TSPGenerator("generated_65_sym_1_100_UniformDistrib_noSparse.tsp", 65);
		generator.generate();
		
		generator = new TSPGenerator("generated_70_sym_1_100_UniformDistrib_noSparse.tsp", 70);
		generator.generate();
		
		generator = new TSPGenerator("generated_75_sym_1_100_UniformDistrib_noSparse.tsp", 75);
		generator.generate();
		
		generator = new TSPGenerator("generated_80_sym_1_100_UniformDistrib_noSparse.tsp", 80);
		generator.generate();
		
		generator = new TSPGenerator("generated_85_sym_1_100_UniformDistrib_noSparse.tsp", 85);
		generator.generate();
		
		generator = new TSPGenerator("generated_90_sym_1_100_UniformDistrib_noSparse.tsp", 90);
		generator.generate();
		
		generator = new TSPGenerator("generated_95_sym_1_100_UniformDistrib_noSparse.tsp", 95);
		generator.generate();
		
		generator = new TSPGenerator("generated_100_sym_1_100_UniformDistrib_noSparse.tsp", 100);
		generator.generate();
		
		generator = new TSPGenerator("generated_120_sym_1_100_UniformDistrib_noSparse.tsp", 120);
		generator.generate();
		
		generator = new TSPGenerator("generated_150_sym_1_100_UniformDistrib_noSparse.tsp", 150);
		generator.generate();
	}

	private static void generateAsymetricDifferentSizes(TSPGenerator generator) {
		generator = new TSPGenerator("generated_5_asym_1_100_UniformDistrib_noSparse.tsp", 5, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_7_asym_1_100_UniformDistrib_noSparse.tsp", 7, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_10_asym_1_100_UniformDistrib_noSparse.tsp", 10, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_12_asym_1_100_UniformDistrib_noSparse.tsp", 12, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_15_asym_1_100_UniformDistrib_noSparse.tsp", 15, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_20_asym_1_100_UniformDistrib_noSparse.tsp", 20, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_25_asym_1_100_UniformDistrib_noSparse.tsp", 25, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_30_asym_1_100_UniformDistrib_noSparse.tsp", 30, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_35_asym_1_100_UniformDistrib_noSparse.tsp", 35, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_40_asym_1_100_UniformDistrib_noSparse.tsp", 40, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_45_asym_1_100_UniformDistrib_noSparse.tsp", 45, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_50_asym_1_100_UniformDistrib_noSparse.tsp", 50, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_55_asym_1_100_UniformDistrib_noSparse.tsp", 55, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_60_asym_1_100_UniformDistrib_noSparse.tsp", 60, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_65_asym_1_100_UniformDistrib_noSparse.tsp", 65, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_70_asym_1_100_UniformDistrib_noSparse.tsp", 70, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_75_asym_1_100_UniformDistrib_noSparse.tsp", 75, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_80_asym_1_100_UniformDistrib_noSparse.tsp", 80, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_85_asym_1_100_UniformDistrib_noSparse.tsp", 85, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_90_asym_1_100_UniformDistrib_noSparse.tsp", 90, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_95_asym_1_100_UniformDistrib_noSparse.tsp", 95, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_100_asym_1_100_UniformDistrib_noSparse.tsp", 100, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_120_asym_1_100_UniformDistrib_noSparse.tsp", 120, false);
		generator.generate();
		
		generator = new TSPGenerator("generated_150_asym_1_100_UniformDistrib_noSparse.tsp", 150, false);
		generator.generate();
	}
	
	public static void main(String[] args) {

		Graph graph = new SingleGraph("Tutorial 1");
		
//		org.graphstream.graph.Node nodeA = graph.addNode("A" );
//		org.graphstream.graph.Node nodeB = graph.addNode("B" );
//		org.graphstream.graph.Node nodeC = graph.addNode("C" );
		for (int i = 0; i < 52; i++) {
			graph.addNode(i + "");
		}
		
		for (int i = 0; i < 51; i++) {
			String crt = String.valueOf(i);
			String next = String.valueOf(i+1);
			
			graph.addEdge(crt + "_" + next, crt, next);
		}
		
		graph.addEdge("0_51", "0", "51");
		
		
		
//		List<Pair> pairs = new ArrayList<>();
//		Node n0 = new Node(0);
//		Node n1 = new Node(1);
//		Node n2 = new Node(2);
//		Node n3 = new Node(3);
//		Node n4 = new Node(4);
//		
//		
//		pairs.add(new Pair(n3, n4));
//		pairs.add(new Pair(n1,n2));
//		pairs.add(new Pair(n2, n4));
//		pairs.add(new Pair(n0,n1));
//		
//		Node buildTree = buildTree(pairs);
//		preoder(buildTree);
//		System.out.println();
		
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
	    	
	    	end.children.add(start);
	    	start.children.add(end);
	    }

	    return rootNodes.iterator().next();
	}
	
}



class Node {
    public final int data;
    public List<Node> children = new ArrayList<>();

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

class Pair {
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
