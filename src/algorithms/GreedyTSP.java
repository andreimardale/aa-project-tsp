package algorithms;

import model.TSPInput;

import java.lang.reflect.Array;

public class GreedyTSP extends AbstractTSP {



    private void greedy (){


        int shortestPath = 0;
        int bestCity = 0;
        int path = 0;
        int cost = 0;

        int size = cityIndexes.size();
        int currentCity = 0;

        getBestCircuit().add(cityIndexes.get(0));
        cityIndexes.remove(0);

        for(int i = 0; i < size - 1; i++){

            shortestPath = DISTANCES[currentCity][cityIndexes.get(0)];
            bestCity = cityIndexes.get(0);
            for (int to : cityIndexes) {
                path = DISTANCES[currentCity][to];

                if(path < shortestPath){
                    shortestPath = path;
                    bestCity = to;
                }
            }
            currentCity = bestCity;
            getBestCircuit().add(bestCity);
            cityIndexes.remove(cityIndexes.indexOf(bestCity));
            cost += shortestPath;
            }

            getBestCircuit().add(0);
            cost += DISTANCES[currentCity][0];
            setMinimumCost(cost);
    }


    @Override
    public void execute(TSPInput tspInput) {
        greedy();
    }
}
