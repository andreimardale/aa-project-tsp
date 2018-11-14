/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;

/**
 *
 * @author My pc
 */
public class TreeNodeInBranchAndBound {
    private double instantCost; //the cost of the linked branches
    private double predictedCost; //cost of the minimum of other edges
    private double costFunction; //instantCost + predictedCost
    //
    int [] cities;
    ArrayList<TreeNodeInBranchAndBound> childs;

    public TreeNodeInBranchAndBound(double instantCost,double predictedCost, int [] cities) {
        this.predictedCost = predictedCost;
        this.instantCost=instantCost;
        this.costFunction=predictedCost+instantCost;
        this.cities = cities;
        childs=new ArrayList<>();
    }

    public double getInstantCost() {
        return instantCost;
    }

    public double getPredictedCost() {
        return predictedCost;
    }

    public double getCostFunction() {
        return instantCost+predictedCost;
    }

    public int [] getCities() {
        return cities;
    }

    public ArrayList<TreeNodeInBranchAndBound> getChilds() {
        return childs;
    }
    @Override
    public String toString()
    {
        String s="";
        /*s+= "This node has:\n InstantCost: "+getInstantCost()+
                "\n PredictedCost:  "+getPredictedCost()+
                "\n CostFunction: "+getCostFunction()+
                "\n Cities: ";*/
        for(int i=0;i<cities.length;i++)
        {
            s+=cities[i]+" ";
        }
        s+="\n";
        return s;
    }
}
