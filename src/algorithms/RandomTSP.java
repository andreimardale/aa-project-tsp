package algorithms;

import model.TSPInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static algorithms.AbstractTSP.*;

public class RandomTSP extends AbstractTSP{

    private void randomStrat (){

        int destinationCity = 0;
        int cost = 0;
        int index = 0;

        List<Integer> notVisited = new ArrayList<>(cityIndexes);

        int size = cityIndexes.size();
        int currentCity = ThreadLocalRandom.current().nextInt(0, size);;
        int firstCity = currentCity;
        getBestCircuit().add(currentCity);
        notVisited.remove(currentCity);


        for(int i = 0; i < size - 1; i ++) {

            double totalCost = 0.0;
            // add each cost of edge to a new list and calculate total cost
            List<Double> probability = new ArrayList<>();
            for (int to : notVisited) {
                totalCost += DISTANCES[currentCity][to];
                probability.add(DISTANCES[currentCity][to]*1.0);
            }
            // divide each factor by the total cost and change to inverse to give edges with less cost higher probability
            double totalProbability = 0.0;
            for (int j = 0; j < probability.size(); j++) {
                probability.set(j, totalCost - probability.get(j));
                totalProbability += probability.get(j);
            }
            // choose edge based on probability.
            double probabilityFactor = Math.random() * totalProbability;
            double probabilityRoof = 0.0;
            for (double p : probability) {
                probabilityRoof += p;
                if (probabilityRoof >= probabilityFactor) {

                    index = probability.indexOf(p);
                    break;

                }
            }

            destinationCity = notVisited.get(index);
            cost += DISTANCES[currentCity][destinationCity];
            currentCity = destinationCity;
            getBestCircuit().add(destinationCity);
            notVisited.remove(notVisited.indexOf(destinationCity));
        }

        getBestCircuit().add(firstCity);
        cost += DISTANCES[currentCity][firstCity];
        setMinimumCost(cost);
    }

    public void execute(TSPInput tspInput){
        randomStrat();
    }

}
