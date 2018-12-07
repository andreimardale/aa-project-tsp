package algorithms;

import model.TSPInput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GreedyTSP extends AbstractTSP {



    private void greedy (int initialCity){

        int shortestPath = 0;
        int bestCity = 0;
        int path = 0;
        int cost = 0;

        int size = cityIndexes.size();
        int currentCity = initialCity;
        int startCity = currentCity;

        List<Integer> notVisited = new ArrayList<>(cityIndexes);

        getBestCircuit().add(notVisited.get(currentCity));
        notVisited.remove(currentCity);

        for(int i = 0; i < size - 1; i++){

            shortestPath = DISTANCES[currentCity][notVisited.get(0)];
            bestCity = notVisited.get(0);
            for (int to : notVisited) {
                path = DISTANCES[currentCity][to];

                if(path < shortestPath){
                    shortestPath = path;
                    bestCity = to;
                }
            }
            currentCity = bestCity;
            getBestCircuit().add(bestCity);
            notVisited.remove(notVisited.indexOf(bestCity));
            cost += shortestPath;
            }

            getBestCircuit().add(startCity);
            cost += DISTANCES[currentCity][startCity];
            setMinimumCost(cost);
    }


    @Override
    public void execute(TSPInput tspInput) {
        greedy(0);
    }
}
