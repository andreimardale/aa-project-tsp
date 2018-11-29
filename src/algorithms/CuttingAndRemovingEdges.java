package algorithms;

import java.util.ArrayList;
import java.util.List;
import model.TSPInput;

public class CuttingAndRemovingEdges extends AbstractTSP
{
    int bestCost=Integer.MAX_VALUE;
    int [][] reducedMatrix;
    int [][]originalMatrix;
    int numberOfEdges;
    int numberOfTours=0;
    @Override
    public void execute(TSPInput tspInput) 
    {
        originalMatrix=tspInput.getDist();
        numberOfEdges=((originalMatrix.length*(originalMatrix.length-1))/2);
        makeDiagonalInfinity(originalMatrix);
//        printMatrix(originalMatrix);
        int [][] newMatrix= copyMatrix(originalMatrix);
        int reducedCost = reduceMatrix(newMatrix);
        Subset subsets[];
        subsets=new Subset[newMatrix.length];
        initiateDisjointArray(subsets);
        ArrayList<Edge> arrayOfEdges=new ArrayList<>();
        TreeNode node=new TreeNode(arrayOfEdges,newMatrix,subsets,reducedCost);
        cutAndRemoveEdges(node);
    }
    public void cutAndRemoveEdges(TreeNode node)
    {
        //if there is more edges to complete a tour
        if (countAcceptedEdges(node.getEdges())<originalMatrix.length) 
        {
            //choose edge that maximizes the cost
            Edge edge = chooseEdgeThatMaximizesCost(node);
            if(edge!=null)
            {
                //check the cost to know if we should cut
                if(node.getReductionCost()<AbstractTSP.getMinimumCost())
                {
                    TreeNode leftNode = null;
                    TreeNode rightNode = null;
                    //check if we can exclude this edge
                    if (checkExcludingEdge(node, edge)) 
                    {
                        Edge edgeLeft = new Edge(edge.getCity1(), edge.getCity2(), false);
                        //add the edge to the new node
                        leftNode = addEdgeToNode(node, edgeLeft);
                        node.setLeftNode(leftNode);
                    }
                    //check if we can include this edge
                    if (checkIncludingEdge(node, edge)) 
                    {
                        Edge edgeRight = new Edge(edge.getCity1(), edge.getCity2(), true);
                        //add the edge to the new node
                        rightNode=addEdgeToNode(node, edgeRight);
                        node.setRightNode(rightNode);
                    }
                    if (node.getLeftNode() != null) {
                        cutAndRemoveEdges(node.getLeftNode());
                    }
                    if (node.getRightNode() != null) {
                        cutAndRemoveEdges(node.getRightNode());
                    }
                }
            }
        }
        //number of edges are enough to complete a tour
        else
        {
            if(node.getReductionCost()<AbstractTSP.getMinimumCost())
            {
                //set the tour as the best tour
                AbstractTSP.setMinimumCost(node.getReductionCost());
                AbstractTSP.setBestCircuit(getTour(node.getEdges()));
            }
        }
    }
    
    public TreeNode addEdgeToNode(TreeNode fatherNode, Edge edge)
    {
        int[][] newMatrix = copyMatrix(fatherNode.getMatrix());
        ArrayList<Edge> edges = new ArrayList<>(fatherNode.getEdges());
        edges.add(edge);
        Subset[] subsets = copySubset(fatherNode.getSubsets());
        if(edge.isTaken())
        {
            //edit subset is the union of the two cities of the edge, it's used to make disjoint sets
            editSubset(subsets, edge);
            //edit the matrix when we include an edge
            editMatrixWhenEdgeIsIncluded(newMatrix, edge);
        }
        else
        {
            //do not editSubset() since subsets should not be edited when edge is excluded
            //edit the matrix when we want to exclude an edge
            editMatrixWhenEdgeIsExcluded(newMatrix, edge);
        }
        //reduce the matrix
        int reductionCost = reduceMatrix(newMatrix);
        return (new TreeNode(edges, newMatrix, subsets, reductionCost));
    }
    
    public void initiateDisjointArray(Subset subsets[])
    {
        //set all subsets' parent as theirselfs and with rank =0  
        for(int i=0;i<subsets.length;i++)
        {
            subsets[i]= new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
    }
 
    public void editSubset(Subset[] subsets,Edge edge)
    {
        //check if the 2 cities of the edges belong to the same tree
        //if not make union
        int root1 = find(edge.getCity1(), subsets);
        int root2 = find(edge.getCity2(), subsets);
        if (root1 != root2)
        {
            union(root1, root2, subsets);
        }
    }
    public Subset [] copySubset(Subset [] subsets)
    {
        //copy the subsets in order to obtain a new copy and not editing the old one
    	Subset[]newSubsets =new Subset[subsets.length];
    	for(int i=0;i<subsets.length;i++)
    	{
            Subset subset=new Subset();
            subset.parent=subsets[i].parent;
            subset.rank=subsets[i].rank;
            newSubsets[i]=subset;
    	}
    	return newSubsets;
    }
    public void editMatrixWhenEdgeIsExcluded(int [][] matrix,Edge edge)
    {
        //when we exclude an edge, we put the index of [firstCity][secondCity]=infinity
        //so put the index as infinity in the matrix
        matrix[edge.getCity1()][edge.getCity2()]=Integer.MAX_VALUE;
    }
    
    public void editMatrixWhenEdgeIsIncluded(int [][] matrix, Edge edge)
    {
        //put the row of first city as infinity 
        //and the columnn of the second city as infinity
        //except for the index [first city][second city]
        //put index [second city] [first city] as infinity
        putRowAsInf_ExceptIndex_index2(edge.getCity1(), edge.getCity2(), matrix);
        putColumnAsInf_ExceptIndex_index2(edge.getCity2(), edge.getCity1(), matrix);
        matrix[edge.getCity2()][edge.getCity1()]=Integer.MAX_VALUE;
    }
    
    public void putRowAsInf_ExceptIndex_index2(int index, int index2, int[][]matrix)
    {
        for(int i=0;i<matrix.length;i++)
        {
            if(i!=index2)
                matrix[index][i]=Integer.MAX_VALUE;
        }
    }
    public void putColumnAsInf_ExceptIndex_index2(int index, int index2, int [][] matrix)
    {
        for(int i=0;i<matrix.length;i++)
        {
            if(i!=index2)
                matrix[i][index]=Integer.MAX_VALUE;
        }
    }
    public Edge chooseEdgeThatMaximizesCost(TreeNode node)
    {
        int minimum=Integer.MAX_VALUE;
        int city1=Integer.MAX_VALUE;
        int city2=Integer.MAX_VALUE;
        for(int i=0;i<node.getMatrix().length;i++)
        {
            for(int j=0;j<node.getMatrix().length;j++)
            {
                Edge edge=new Edge(i,j);
                if(node.getMatrix()[i][j]<minimum && !node.getEdges().contains(edge) && i != j)
                {
                    city1=i;
                    city2=j;
                    minimum=node.getMatrix()[i][j];
                }
            }
        }
        if(city1==Integer.MAX_VALUE || city2==Integer.MAX_VALUE)
        {
            return null;
        }
        Edge edge=new Edge(city1, city2);
        return edge;
        
        
//        int maximum=-1;
//        int city1=Integer.MAX_VALUE;
//        int city2=Integer.MAX_VALUE;
//        for(int i=0;i<node.getMatrix().length;i++)
//        {
//            for(int j=0;j<node.getMatrix().length;j++)
//            {
//                Edge edge = new Edge(i, j, false);
//                if(node.getMatrix()[i][j]!=Integer.MAX_VALUE && !node.getEdges().contains(edge) && i != j)
//                {
//                    int[][] newMatrix = copyMatrix(node.getMatrix());
//                    editMatrixWhenEdgeIsExcluded(newMatrix, edge);
//                    int reductionCost = reduceMatrix(newMatrix);
//                    if (reductionCost > maximum ) 
//                    {
//                        maximum = reductionCost;
//                        city1 = i;
//                        city2 = j;
//                    }
//                }
//            }
//        }
//        if(city1==Integer.MAX_VALUE || city2==Integer.MAX_VALUE)
//        {
//            return null;
//        }
//        Edge edge=new Edge(city1, city2);
//        return edge;
    }
    
    public boolean checkIncludingEdge (TreeNode node, Edge edge)
    {
        //if editing the matrix after including an edge oblige the matrix to have one row as infinites
        //or one column as infinities then we should not include it
        int [][]matrix=copyMatrix(node.getMatrix());
        editMatrixWhenEdgeIsIncluded(matrix, edge);
        for(int i=0;i<matrix.length;i++)
        {
            //count the number of nonn infinite values in each row
            int nbOfNonInfinityValuesInRow=0;
            for (int j = 0; j < matrix.length; j++) 
            {
                if(matrix[i][j]!=Integer.MAX_VALUE)
                {
                    nbOfNonInfinityValuesInRow++;
                }                
            }
            //if there is no non infinte values i.e all values are infinity
            if(nbOfNonInfinityValuesInRow==0)
                return false;
        }
        for(int i=0;i<matrix.length;i++)
        {
            //count number of infinite values in the columns
            int nbOfNonInfinityValuesInColumn=0;
            for (int j = 0; j < matrix.length; j++) 
            {
                if(matrix[j][i]!=Integer.MAX_VALUE)
                    nbOfNonInfinityValuesInColumn++;
            }
            //if there is no non infinte values i.e all values are infinity
            if(nbOfNonInfinityValuesInColumn==0)
                return false;
        }
        //if this is the last edge, accept it
        if(countAcceptedEdges(node.getEdges())==originalMatrix.length-1)
           return true;
        
        //if this edge makes a loop, reject it
        int root1=find(edge.getCity1(),node.getSubsets());
        int root2=find(edge.getCity2(),node.getSubsets());
        return root1!=root2;
    }
    
    //count the numbers of the edges we accept them 
    public int countAcceptedEdges(ArrayList<Edge> edges)
    {
        int numberOfAcceptedEdges=0;
        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).isTaken())
                numberOfAcceptedEdges++;
        }
        return numberOfAcceptedEdges;
    }
    
    public boolean checkExcludingEdge (TreeNode node, Edge edge)
    {
        //if editing the matrix after including an edge oblige the matrix to have one row as infinites
        //or one column as infinities then we should not include it
        
        int city1=edge.getCity1();
        int city2=edge.getCity2();
        int [][]matrix=copyMatrix(node.getMatrix());
        editMatrixWhenEdgeIsExcluded(matrix, edge);
        int nbOfNonInfinityValuesInRow=0;
        int nbOfNonInfinityValuesInColumn=0;
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[city1][i]!=Integer.MAX_VALUE)
            {
                nbOfNonInfinityValuesInRow++;
            }
            if(matrix[i][city2]!=Integer.MAX_VALUE)
            {
                nbOfNonInfinityValuesInColumn++;
            }
        }
        if(nbOfNonInfinityValuesInColumn==0||nbOfNonInfinityValuesInRow==0)
            return false;
        return true;
    }
    
    
    
    public boolean isInArray(Object[] array, Object number) 
    {
        for (int i = 0; i < array.length; i++) 
        {
            if (array[i].equals(number)) 
            {
                return true;
            }
        }
        return false;
    } 
    
    
    public void printMatrix(int [][] matrix)
    {
        for (int i = 0; i < matrix.length; i++) 
        {
            for (int j = 0; j < matrix.length; j++) 
            {
                if(matrix[i][j]!=Integer.MAX_VALUE)
                    System.out.print(matrix[i][j]+"         ");
                else
                    System.out.print("inf         ");
            }
            System.out.println("");
        }
    }
    
    
    public int reduceMatrix(int [][]Oldmatrix)
    {
        //calculate the cost of reduction of a matrix
    	int [][]matrix = copyMatrix(Oldmatrix);
    	int row_cost_reduction = reduceRows(matrix);
    	int column_cost_reduciton = reduceColumns(matrix);
        return row_cost_reduction + column_cost_reduciton;
    }
    
    //this function reduce the rows and return the reduced cost for rows
    public int reduceRows(int [][]matrix)
    {
        int reducedCost=0;
        makeDiagonalInfinity(matrix);
        for(int i=0;i<matrix.length;i++)
        {
            int minInARow=Integer.MAX_VALUE;
            for(int j=0;j<matrix[i].length;j++)
            {
                if(matrix[i][j]<minInARow)
                    minInARow=matrix[i][j];
            }
            if(minInARow!=Integer.MAX_VALUE)
            {
                reducedCost+=minInARow;
                for(int k=0;k<matrix.length;k++)
                {
                    if(matrix[i][k]!=Integer.MAX_VALUE)
                        matrix[i][k]-=minInARow;
                }
            }
        }
        return reducedCost;
    }
    
    
    public int reduceColumns(int [][]oldMatrix)
    {
        int [][]matrix=copyMatrix(oldMatrix);
        int reducedCost=0;
        makeDiagonalInfinity(matrix);
        for(int i=0;i<matrix.length;i++)
        {
            int minInAColumn=Integer.MAX_VALUE;
            for(int j=0;j<matrix[i].length;j++)
            {
                if(matrix[j][i]<minInAColumn)
                    minInAColumn=matrix[j][i];
            }
            if(minInAColumn!=Integer.MAX_VALUE)
            {
                reducedCost+=minInAColumn;
                for(int k=0;k<matrix.length;k++)
                {
                    if(matrix[k][i]!=Integer.MAX_VALUE)
                        matrix[k][i]-=minInAColumn;
                }
            }
        }
        return reducedCost;
    }
    
    public int[][] copyMatrix(int[][] matrix) 
    {
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }
    
    public void makeDiagonalInfinity(int [][]matrix)
    {
        for(int i=0;i<matrix.length;i++)
        {
            matrix[i][i]=Integer.MAX_VALUE;
        }
    }
    public void printArray(int array[]) 
    {
        for (int i = 0; i < array.length; i++) 
        {
            System.out.print("" + array[i]+ "  ");
        }
    }
    
    //find to work on disjoint sets
    public int find(int number,Subset [] subsets)
    {
        if (subsets[number].parent != number) 
        {
            return find(subsets[number].parent,subsets );
        }
        return subsets[number].parent;
    }
    
    //union for disjoint sets
    public void union(int number1,int number2,Subset subsets[])
    {
        if(number1!=number2)
        {
            if (subsets[number1].rank < subsets[number2].rank) 
            {
                subsets[number1].parent = number2;
            } 
            else if (subsets[number1].rank > subsets[number2].rank) 
            {
                subsets[number2].parent = number1;
            } 
            else 
            {
                subsets[number2].parent = number1;
                subsets[number1].rank++;
            }
        }
    }

    //get the edges that are accepted only
    private ArrayList<Edge> getAcceptedEdges(ArrayList<Edge> edges)
    {
        ArrayList<Edge> acceptedEdges=new ArrayList<>();
        for(int i=0;i<edges.size();i++)
        {
            if(edges.get(i).isTaken())
                acceptedEdges.add(edges.get(i));
        }
        return acceptedEdges;
    }
    
    //get the tour in terms of cities and not edges
    private List<Integer> getTour(ArrayList<Edge> edges) 
    {
        ArrayList<Integer> tour=new ArrayList<>();
        ArrayList<Edge> sortedEdges=getAcceptedEdges(edges);
        tour.add(sortedEdges.get(0).getCity1());
        tour.add(sortedEdges.get(0).getCity2());
        sortedEdges.remove(0);
        for(int i=0;i<sortedEdges.size();i++)
        {
            if(sortedEdges.get(i).getCity1()==tour.get(tour.size()-1))
            {
                tour.add(sortedEdges.get(i).getCity2());
                sortedEdges.remove(i);
                i=-1;
            }
        }
        return tour;
    }
}

//class to represent a node in the tree
class TreeNode 
{
    private ArrayList<Edge> edges;
    TreeNode leftNode, rightNode;
    int recutionCost;
    int [][] matrix;
    private Subset [] subsets;
    
    
    public TreeNode(ArrayList<Edge> edges,int [][]matrix,Subset [] subsets,int reductionCost)
    {
        this.edges=edges;
        this.matrix=matrix;
        this.subsets=subsets;
        this.recutionCost = reductionCost;
    }
    public int [][] getMatrix()
    {
        return matrix;
    }

    public int getReductionCost() {
        return recutionCost;
    }

    public void setReductionCost(int cost) {
    	this.recutionCost = cost;
	}
    public ArrayList<Edge> getEdges()
    {
        return this.edges;
    }
    
    public TreeNode getLeftNode()
    {
        return this.leftNode;
    }
    public TreeNode getRightNode()
    {
        return this.rightNode;
    }

    public Subset[] getSubsets() {
        return subsets;
    }    

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
    
    
    public void printArray(int array[]) 
    {
        for (int i = 0; i < array.length; i++) 
        {
            System.out.print(array[i]+"  ");
        }
    }
    
    public void printArrayList(ArrayList<Edge> array) 
    {
        for (int i = 0; i < array.size(); i++) 
        {
            System.out.print(array.get(i)+"  ");
        }
    }
    
    
    public void printMatrix(int[][] matrix) 
    {
        for (int i = 0; i < matrix.length; i++) 
        {
            for (int j = 0; j < matrix[0].length; j++) 
            {
                if(matrix[i][j]==Integer.MAX_VALUE)
                    System.out.print("inf      ");
                else
                    System.out.print(matrix[i][j]+ "      "); 
           }
            System.out.println();
        }
    }
    @Override
    public String toString()
    {
        String s = "";
        System.out.print("Edges: ");
        printArrayList(this.getEdges());
        System.out.println("\nMatrix: ");
        printMatrix(this.getMatrix());
        System.out.println("Reduction cost :"+recutionCost);
        System.out.println("");
        return s;
    }
}

//class to represent an edge
class Edge {

    private int city1;
    private int city2;
    private boolean taken;

    public Edge(int city1, int city2, boolean taken) 
    {
        this.city1 = city1;
        this.city2 = city2;
        this.taken = taken;
    }

    Edge(int city1, int city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

    public int getCity1() {
        return city1;
    }

    public int getCity2() {
        return city2;
    }

    public boolean isTaken() {
        return taken;
    }
    public void setTaken(boolean bool)
    {
        taken=bool;
    } 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Edge other = (Edge) obj;
        return this.city1 == other.city1 && this.city2==other.city2;
    }
    

    @Override
    public String toString() {
        String s = "";
        if(!this.isTaken())
            s+="Not";
        s +="("+getCity1();
        s += getCity2()+")";
        return s;
    }

}

//class to represent subsets for disjoint sets
class Subset
{
	int parent;
	int rank;
	public Subset()
	{
	}
	
	public int getParent()
	{
		return parent;
	}
	public int getRank()
	{
		return rank;
	}

    @Override
    public String toString() {
        return "Subset{" + "parent=" + parent + ", rank=" + rank + '}';
    }
        
}





