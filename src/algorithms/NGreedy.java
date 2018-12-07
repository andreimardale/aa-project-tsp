/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import model.TSPInput;

/**
 *
 * @author My pc
 */
public class NGreedy extends AbstractTSP{

    int [][]matrix;
    boolean []visited;
    ArrayList<Integer> solution;
    int cost=0;
    @Override
    public void execute(TSPInput tspInput) {
        matrix=tspInput.getDist();
        for(int i=0;i<matrix.length;i++)
        {
            cost=0;
            solution=new ArrayList<>();
            visited=new boolean[matrix.length];
            CostAndTour costAndTour=nGreedy(i,visited);
            if(costAndTour.getCost()<getMinimumCost())
            {
                setMinimumCost(costAndTour.getCost());
                setBestCircuit(costAndTour.getTour());
            }
        }
    }
    public CostAndTour nGreedy(int city,boolean [] visited)
    {
        visited[city]=true;
        solution.add(city);
//        System.out.println(solution);
//        System.out.println(cost); 
       if(solution.size()==matrix.length)
        {
            cost+=matrix[solution.get(matrix.length-1)][solution.get(0)];
            return new CostAndTour(cost, solution);
        }
        int minCity=getMinimumCity(city, visited);
//        System.out.println("Min city: "+minCity);
        cost+=matrix[city][minCity];
        return nGreedy(minCity,visited);
    }
    public int getMinimumCity(int n,boolean[] visited)
    {
        int minimumCity=Integer.MAX_VALUE;
        int minimumCost=Integer.MAX_VALUE;
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[n][i]<minimumCost && !visited[i]&&i!=n)
            {
                minimumCost=matrix[n][i];
                minimumCity=i;
            }
        }
        visited[minimumCity]=true;
        if(minimumCity!=Integer.MAX_VALUE)
            return minimumCity;
        return 0;
    }    
}
class CostAndTour{
    int cost;
    ArrayList<Integer> tour;
    public CostAndTour(int cost,ArrayList<Integer> tour)
    {
        this.cost=cost;
        this.tour=tour;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setTour(ArrayList<Integer> tour) {
        this.tour = tour;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<Integer> getTour() {
        return tour;
    }
    
}
