package algorithms;

import model.TSPInput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GreedyTSP extends AbstractTSP {



    private void greedy (int initialCity){

        // Variables to save the data
        int shortestPath = 0;
        int bestCity = 0;
        int path = 0;
        int cost = 0;
        int size = cityIndexes.size();

        // Select starting city
        int currentCity = initialCity;
        int startCity = currentCity;

        // List of the unvisited nodes
        List<Integer> notVisited = new ArrayList<>(cityIndexes);

        // Adding the first city to the circuit and removing it from the unvisited nodes
        getBestCircuit().add(notVisited.get(currentCity));
        notVisited.remove(currentCity);

        // Iterating i times | i = Number of nodes
        for(int i = 0; i < size - 1; i++){

            // The shortest path  and destination nodeis set to the first unvisited city
            shortestPath = DISTANCES[currentCity][notVisited.get(0)];
            bestCity = notVisited.get(0);
            // Check every path to each node to find the shortest path
            for (int to : notVisited) {
                path = DISTANCES[currentCity][to];

                if(path < shortestPath){
                    shortestPath = path;
                    bestCity = to;
                }
            }
            // Add path to cost and city to circuit and current city to destiantion node
            currentCity = bestCity;
            getBestCircuit().add(bestCity);
            notVisited.remove(notVisited.indexOf(bestCity));
            cost += shortestPath;
            }
            cost += DISTANCES[currentCity][startCity];
            setMinimumCost(cost);
            setBestCircuit(getBestCircuit());
    }


    @Override
    public void execute(TSPInput tspInput) {
        greedy(0);
    }
}
