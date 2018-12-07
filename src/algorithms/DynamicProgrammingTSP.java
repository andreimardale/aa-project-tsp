package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import model.TSPInput;

public class DynamicProgrammingTSP extends AbstractTSP {

	public static final int INFINITY = 9999999;
	
	@Override
	public void execute(TSPInput tspInput) {
		int[][] dist = tspInput.getDist();
		int n = tspInput.getDimension();
		
		/* Map C
		 * Key = <Pair> between a subset of nodes S and a reachable destination node 
		 * Value = x the shortest path from initial node to node x, passing through every node in S exactly once
		 **/
		Map<Pair, Integer> C = new HashMap<>(); /* This map replaces the normal Dynamic Prog. matrix, as we need for the row index a subset, not an integer*/
		Map<Pair, Integer> parents = new HashMap<>(); /* Map for keeping the parents. */
		
		/* Generate all possible subsets, put in a map where:
		 * Key = Integer - the size of subsets of the corresponding bucket
		 * Value: List<Sets> the list of subsets of size indicated by the key.
		 * Example: allSubSets.get(1) -> the list of all subsets of size 1, containing the original node iteself. */
		Map<Integer, List<Set<Integer>>> allSubSets = generateAllSubSets(n);

		/* Initialize the data structures. For example, the distance to reach node 0, passing through node {0} is 0*/
		C.put(new Pair(new HashSet<Integer>(Arrays.asList(0)), 0), 0);
		parents.put(new Pair(new HashSet<Integer>(Arrays.asList(0)), 0), 0);
	
		/* Iterate over all dimension of subsets: */
		for (int s = 2; s <= n; s++) {
			List<Set<Integer>> listOfSubsetsOfSizeS = allSubSets.get(s); /* Get all subsets of size s*/
			for (Set<Integer> S : listOfSubsetsOfSizeS) { /* Iterate on all subsets of size s*/
				C.put(new Pair(S, 0), INFINITY); /* To avoid going back to original node, ie situation like we start from 0, visit 1,2,3 and then we can choose between 4 and 5. We should not take 0 as a possible choice. */
				
				for (Integer j : S) {
					if (j == 0)
						continue;
					
					int minValue = INFINITY;
					int parent = -1;
					
					/* Try every possible available node in current subset to get the minimum path.*/
					for (Integer i : S) {
						if (i == j)
							continue;
						
						Set<Integer> tempS = new HashSet<>(S);
						tempS.remove(j);
						int crt = C.get(new Pair(tempS, i)) + dist[i][j];
						if (crt < minValue) { /* Compare and set the minimum. */
							minValue = crt;
							parent = i;
						}
					}
					/* Save the newly computed minimum. The minimum cost to reach node j, from 0, using all nodes in subset S of size s is minValue. */
					C.put(new Pair(S, j), minValue);
					parents.put(new Pair(S, j), parent);
				}
			}
		}

		int minDist = INFINITY;
		Pair lastPair = null;
		/* Get the minimum tour after also adding the path from last reachable node to original node. */
		for (Entry<Pair, Integer> entry : C.entrySet()) {
			if (entry.getKey().S.size() != n)
				continue;
			
			if ((entry.getValue() + dist[entry.getKey().currentNode][0]) < minDist) {
				minDist = (entry.getValue() + dist[entry.getKey().currentNode][0]);
				lastPair = entry.getKey();
			}
		
		}
		
		/* Build tour using parents map.*/
		List<Integer> tour = getTour(lastPair, parents);
		
		/* Set the global minimum. */
		setMinimumCost(minDist);
		setBestCircuit(tour);
	}
	
	public List<Integer> getTour(Pair lastPair, Map<Pair, Integer> parents) {
		List<Integer> tour = new ArrayList<>();
		
		if (lastPair == null)
			return tour;
		
		tour.add(lastPair.currentNode);
		
		while (lastPair.S.size() != 1) {
			Integer previousNode = parents.get(lastPair);
			tour.add(previousNode);
			lastPair.S.remove(lastPair.currentNode);
			lastPair.currentNode = previousNode;
		}
		
		Collections.reverse(tour);
		return tour;
	}
	
	/* The key is the size of the subset and the value is a list of subsets of size key*/
	public Map<Integer, List<Set<Integer>>> generateAllSubSets(int n) {
		Map<Integer, List<Set<Integer>>> allSubsets = new HashMap<>();
		int numberOfSubsets = (int) Math.pow(2, n);

		for (int i = 0; i < numberOfSubsets; i++) {
			Set<Integer> subset = new HashSet<>();

			/* if this subset won't contain 0, just skip it */
			if ((i & (1 << 0)) == 0) {
				continue;
			}
			
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					subset.add(j);
				}
			}
			allSubsets.computeIfAbsent(subset.size(), k -> new ArrayList<>()).add(subset);
		}
		
		return allSubsets;
	}
	
	class Pair {
		Set<Integer> S = new HashSet<>();
		int currentNode;
		
		public Pair(Set<Integer> S, int currentNode) {
			this.S = S;
			this.currentNode = currentNode;
		}

		
		
		@Override
		public String toString() {
			return "Pair [S=" + S + ", currentNode=" + currentNode + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((S == null) ? 0 : S.hashCode());
			result = prime * result + currentNode;
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
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (S == null) {
				if (other.S != null)
					return false;
			} else if (!S.equals(other.S))
				return false;
			if (currentNode != other.currentNode)
				return false;
			return true;
		}

		private DynamicProgrammingTSP getOuterType() {
			return DynamicProgrammingTSP.this;
		}
		
		
		
	}

}
