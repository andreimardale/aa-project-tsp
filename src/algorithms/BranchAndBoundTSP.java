package algorithms;

import java.util.ArrayList;
import model.TSPInput;

public class BranchAndBoundTSP extends AbstractTSP {

    //what will be the output if its an adjancency matrix???
    int[][] adjacencyMatrix;

    @Override
    public void execute(TSPInput input) {
        
        adjacencyMatrix = input.getDist();
//        printMatrix(adjacencyMatrix);

        int[] array = new int[1];
        array[0] = 0;
        int predictedCost = getPredictedCost(array);
        TreeNodeInBranchAndBound root = new TreeNodeInBranchAndBound(0, predictedCost, array);
        branchAndBound(root);
    }


    public void branchAndBound(TreeNodeInBranchAndBound node) {
        if (node.getCostFunction() < getMinimumCost()) 
        {
            TreeNodeInBranchAndBound child;
            if (node.getCities().length < adjacencyMatrix.length) {
                for (int i = 0; i < adjacencyMatrix.length; i++) 
                {
                    if (!(isInArray(node.getCities(), i))) 
                    {
                    	int instantCost = node.getInstantCost() + adjacencyMatrix[node.getCities()[node.getCities().length - 1]][i];
                        int[] cities = new int[node.getCities().length + 1];
                        System.arraycopy(node.getCities(), 0, cities, 0, node.getCities().length);
                        cities[node.getCities().length] = i;
                        int predictedCost = getPredictedCost(cities);
                        int costFunction = instantCost + predictedCost;
                        child = new TreeNodeInBranchAndBound(instantCost, predictedCost, cities);
                        insertSorted(child, node.getChilds());
                    }
                }
                for (int i = 0; i < node.getChilds().size(); i++) {
                    branchAndBound(node.getChilds().get(i));
                }
            } 
            else if (node.getCities().length == adjacencyMatrix.length) //we are at the leaf
            {
                int[] nodeCities = node.getCities();
                int instantCost = node.getInstantCost() + adjacencyMatrix[nodeCities[nodeCities.length - 1]][nodeCities[0]];
                int[] cities = new int[nodeCities.length + 1];
                System.arraycopy(nodeCities, 0, cities, 0, nodeCities.length);
                cities[cities.length - 1] = nodeCities[0];  //put the last element as the first cause cycle
                int predictedCost = getPredictedCost(cities); //will be 0 since the cities are complete
                int costFunction = instantCost + predictedCost;
                child = new TreeNodeInBranchAndBound(instantCost, predictedCost, cities);
                branchAndBound(child);
            } 
            else //number of cities in the array is more than the number of cities (leaf of the tree)
            {
                int []array=new int[node.getCities().length-1];
                System.arraycopy(node.getCities(), 0, array, 0, node.getCities().length-1);
                node.setCities(array);
                if (node.getCostFunction() < getMinimumCost()) 
                {
                	setMinimumCost(node.getCostFunction());
                	setBestCircuit(node.getCities());
                }
            }
        }
    }
    
    public ArrayList<Integer> arrayToList(int [] array)
    {
        ArrayList<Integer> arrayList=new ArrayList<>();
        for(int i=0;i<array.length;i++)
        {
            arrayList.add(array[i]);
        }
        return arrayList;
    }


    public boolean isInArray(int[] array, int number) 
    {
        for (int i = 0; i < array.length; i++) 
        {
            if (array[i] == number) 
            {
                return true;
            }
        }
        return false;
    } 

    private int getPredictedCost(int[] cities) 
    {
        int[][] newMatrix = copyMatrix(adjacencyMatrix); //to modify the values freely
        int predictedCost = 0;
//check if the cities are more than 1 in order to start canceling the rows and columns that 
//are already linked to each other
        if (cities.length > 1) 
        {
//here we consider that if the array contains [1,2] then 1 could not go to any other city
//because it already got 2 and no one could go to 2 because 1 already did so we should delete 
//row 1 and column 2 only.
            for (int k = 0; k < cities.length; k++) 
            {
                if (k != cities.length - 1 || k == 0) 
                {
                    for (int i = 0; i < adjacencyMatrix.length; i++) 
                    {
                        newMatrix[cities[k]][i] = Integer.MAX_VALUE;  //like deleting row of i
                    }
                }
                if (k != 0) 
                {
                    for (int j = 0; j < adjacencyMatrix[0].length; j++) 
                    {
                        newMatrix[j][cities[k]] = Integer.MAX_VALUE;  //like deleting column of j
                    }
                }
            }
        }
        //start choosing the minimums of each row
        for (int i = 0; i < newMatrix.length; i++) 
        {
            int minimumInARow = Integer.MAX_VALUE;
            for (int j = 0; j < newMatrix[i].length; j++) 
            {
                if (newMatrix[i][j] < minimumInARow && i != j && newMatrix[i][j] != Integer.MAX_VALUE) 
                {
                    minimumInARow = newMatrix[i][j];
                }
            }
            //check if the minimum chosed is a number different from what it's initiaized as
            if (minimumInARow != Integer.MAX_VALUE) 
            {
                predictedCost += minimumInARow;
            }
        }
        return predictedCost;
    }
    
    //returns a copy of a matrix
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

    //insert the node to an arrayList sorted with respect to the cost
    //that's done to keep the less costed nodes on the left of the tree
    //then most likely cutting more branches
    void insertSorted(TreeNodeInBranchAndBound node, ArrayList<TreeNodeInBranchAndBound> list) 
    {
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getCostFunction() > node.getCostFunction()) {
                break;
            }
        }
        list.add(i, node);
    }
}

//A class to define the node of the tree. Each node will have the following:
//1) cost of the linked cities: instantCost
//2) cost of the heuristic function: predictedCost
//3) the sum of the above 2: costFunction
//4) an array of indexes, each which define a city. For intance if a node has [0,1,2] means that 0 
//is connected to 1, and 1 is connected to 2
//5) a list of child nodes

class TreeNodeInBranchAndBound {
    private int instantCost; //the cost of the linked branches
    private int predictedCost; //cost of the minimum of other edges
    private int costFunction; //instantCost + predictedCost
    //
    int [] cities;
    ArrayList<TreeNodeInBranchAndBound> childs;

    public TreeNodeInBranchAndBound(int instantCost,int predictedCost, int [] cities) {
        this.predictedCost = predictedCost;
        this.instantCost=instantCost;
        this.costFunction=predictedCost+instantCost;
        this.cities = cities;
        childs=new ArrayList<>();
    }

    public int getInstantCost() {
        return instantCost;
    }

    public int getPredictedCost() {
        return predictedCost;
    }

    public int getCostFunction() {
        return instantCost+predictedCost;
    }

    public int [] getCities() {
        return cities;
    }
    
    public void setCities(int [] cities)
    {
        this.cities=cities;
    }

    public ArrayList<TreeNodeInBranchAndBound> getChilds() {
        return childs;
    }
    @Override
    public String toString()
    {
        String s="";
        s+= "This node has:\n InstantCost: "+getInstantCost()+
                "\n PredictedCost:  "+getPredictedCost()+
                "\n CostFunction: "+getCostFunction()+
                "\n Cities: ";
        for(int i=0;i<cities.length;i++)
        {
            s+=cities[i]+" ";
        }
        s+="\n";
        return s;
    }
}

