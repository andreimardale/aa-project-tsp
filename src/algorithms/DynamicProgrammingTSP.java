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
import model.Point;
import model.TSPInput;

public class DynamicProgrammingTSP extends AbstractTSP {

	public static final int INFINITY = 9999999;
	int n = 4;
//	public static final int dist[][] = {
//			{0, 10, 15, 20},
//			{10, 0, 35, 25},
//			{15, 35, 0, 30},
//			{20, 25, 30, 0}
//			};
	
	public static final int dist[][] = {
			{0, 2, 9, 10},
			{1, 0, 6, 4},
			{15, 7, 0, 8},
			{6, 3, 12, 0}
			};
	
//	public static final int dist[][] = {
//			{0, 1, 15, 6},
//			{2, 0, 7, 3},
//			{9, 6, 0, 12},
//			{10, 4, 8, 0}
//			};
	
	@Override
	public void execute(TSPInput tspInput) {
		int[][] dist = tspInput.getDist() != null ? tspInput.getDist() : tspInput.generate_adjancency_matrix();
		int n = tspInput.getDimension();
		
		Map<Pair, Integer> C = new HashMap<>();
		Map<Pair, Integer> parents = new HashMap<>();
		
		Map<Integer, List<Set<Integer>>> allSubSets = generateAllSubSets(n);

		C.put(new Pair(new HashSet<Integer>(Arrays.asList(1)), 1), 0);
		parents.put(new Pair(new HashSet<Integer>(Arrays.asList(1)), 1), 1);
	
		for (int s = 2; s <= n; s++) {
			List<Set<Integer>> listOfSubsetsOfSizeS = allSubSets.get(s);
			for (Set<Integer> S : listOfSubsetsOfSizeS) {
				C.put(new Pair(S, 1), INFINITY);
				
				for (Integer j : S) {
					if (j == 1)
						continue;
					
					int minValue = INFINITY;
					int parent = -1;
					
					for (Integer i : S) {
						if (i == j)
							continue;
						
						Set<Integer> tempS = new HashSet<>(S);
						tempS.remove(j);
						int crt = C.get(new Pair(tempS, i)) + dist[i-1][j-1];
						if (crt < minValue) {
							minValue = crt;
							parent = i;
						}
					}
					
					C.put(new Pair(S, j), minValue);
					parents.put(new Pair(S, j), parent);
				}
			}
		}

		int minDist = INFINITY;
		Pair lastPair = null;
		for (Entry<Pair, Integer> entry : C.entrySet()) {
			if (entry.getKey().S.size() != n)
				continue;
			
			if ((entry.getValue() + dist[entry.getKey().currentNode-1][0]) < minDist) {
				minDist = (entry.getValue() + dist[entry.getKey().currentNode-1][0]);
				lastPair = entry.getKey();
			}
		
		}
		
		List<Integer> tour = getTour(lastPair, parents);
		System.out.println(minDist);
		System.out.println(tour);
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

			/* if this subset won't contain 1, just skip it */
			if ((i & (1 << 0)) == 0) {
				continue;
			}
			
			for (int j = 0; j < n; j++) {
				
				if ((i & (1 << j)) != 0) {
					subset.add(j+1);
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
