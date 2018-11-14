package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Miscellanous {

	public static void main(String[] args) {

		List<Pair> pairs = new ArrayList<>();
		Node n0 = new Node(0);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		
		
		pairs.add(new Pair(n3, n4));
		pairs.add(new Pair(n1,n2));
		pairs.add(new Pair(n2, n4));
		pairs.add(new Pair(n0,n1));
		
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
