package algorithms;

import model.TSPInput;

public class BranchAndBoundEdited extends AbstractTSP{

	TSPInput tsp_input;
	boolean visited[];
	int finalResult = Integer.MAX_VALUE;
	@Override
	public void execute(TSPInput tspInput) {
		tsp_input = tspInput;
		visited = new boolean[tspInput.getDimension()];
		
		int[] currentPath = new int[tspInput.getDimension()+1];
		//init visited cities array
		for(int i=0;i<tspInput.getDimension();i++) {
			visited[i] = false;
		}
		visited[0] = true;
		currentPath[0] = 0;
		int currentWeight = 0;
		int currentBound = 0;
		for (int i = 0; i < tspInput.getDimension(); i++) {
			currentBound += (getFirstMin(tspInput.getDist(), i) + getSecondMin(tspInput.getDist(), i));
		}
		currentBound = (int)(currentBound/2 +0.5);
		buildTree(currentBound, currentWeight, currentPath,1, tspInput.getDist());
		setMinimumCost(finalResult);
	}
	public void buildTree(int currentBound,int currentWeight,int currentPath[],int level,int adjMatrix[][]) {
		if(level == tsp_input.getDimension()) {
			//add the final distance from the last node to the first node
			int res = currentWeight + adjMatrix[currentPath[level-1]][currentPath[0]];
			if (res < finalResult) {
				//setMinimumCost(res);
				setBestCircuit(currentPath);
				finalResult = res ;
			}
			return;
		}
		for(int i=0;i<tsp_input.getDimension();i++) {
			if(adjMatrix[currentPath[level - 1]][i] != 0 && !visited[i]) {
				int temp = currentBound ;
				currentWeight += adjMatrix[currentPath[level - 1]][i];
				if(level == 1) {
					currentBound -= ((getFirstMin(adjMatrix,currentPath[0]) + getFirstMin(adjMatrix,i))/2);
				}
				else { 
					currentBound -= ((getSecondMin(adjMatrix,currentPath[level-1]) + getFirstMin(adjMatrix,i))/2);
				}
				
				if(currentWeight + currentBound < finalResult) {
					visited[i] = true;
					currentPath[level] = i;
					buildTree(currentBound, currentWeight, currentPath, level + 1, adjMatrix);
				}
				/*else no need to continue further we have to cut the branch here */
				currentWeight -= adjMatrix[currentPath[level - 1]][i];
				//temp previously saved in case we want to cut
				currentBound = temp;
				resetVisited(currentPath,level);
	
			}
		}
	}
	private int getSecondMin(int[][] adjMatrix, int i) {
		int first = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		for (int j = 0; j < adjMatrix.length; j++) {
			if(adjMatrix[i][j]<=first) {
				second  = first;
				first = adjMatrix[i][j];
			} else if(adjMatrix[i][j]<second && adjMatrix[i][j] != first) {
				second = adjMatrix[i][j];
			}
		}
		return second;
	}
	private int getFirstMin(int[][] adjMatrix, int i) {
		int minimum = Integer.MAX_VALUE;
		for(int j=0;j<adjMatrix.length;j++) {
			if(adjMatrix[i][j] < minimum && i != j)
				minimum = adjMatrix[i][j];
		}
		return minimum;
	}
	public void resetVisited(int currentPath[],int level) {
		for (int i = 0; i < currentPath.length-1; i++) {
			visited[i] = false ;
		}
		for (int i = 0; i < level; i++) {
			visited[currentPath[i]] = true ;
		}
	}
}
