/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import utils.DistanceDriver;

/**
 *
 * @author aliha
 */
public class TSPInput {
    private List<Point> points;
    private String EDGE_WEIGHT_TYPE;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getEDGE_WEIGHT_TYPE() {
        return EDGE_WEIGHT_TYPE;
    }

    public void setEDGE_WEIGHT_TYPE(String EDGE_WEIGHT_TYPE) {
        this.EDGE_WEIGHT_TYPE = EDGE_WEIGHT_TYPE;
    }

    public TSPInput(List<Point> points, String EDGE_WEIGHT_TYPE) {
        this.points = points;
        this.EDGE_WEIGHT_TYPE = EDGE_WEIGHT_TYPE;
    }
    public int[][] generate_adjancency_matrix()
    {
        //generate distances according to the edge weight type using the Distance Driver
        int[][] result=new int[points.size()][points.size()];
        DistanceDriver driver=new DistanceDriver();
        for(int i=0;i<points.size();i++)
        {
            for(int j=i+1;j<points.size();j++)
            {
                switch(EDGE_WEIGHT_TYPE)
                {
                    case "EUC_2D": {
                        result[i][j]=driver.EUCLIDEAN_2D(points.get(i),points.get(j));
                    }
                }
            }
        }
        return result;
    }
    public void printMatrix(int[][] result)
    {
        for(int i=0;i<result.length;i++)
        {
            for(int j=0;j<result.length;j++)
            {
                System.out.print(result[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
