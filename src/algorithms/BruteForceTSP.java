package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Point;
import model.TSPInput;

public class BruteForceTSP extends AbstractTSP {
	
	@Override
	public void execute(TSPInput tspInput) {
		List<Integer> listOfCityIndexes = getListOfCityIndexes(tspInput.getDimension());
		generatePermutation(0, listOfCityIndexes);
	}

	private void generatePermutation(int currentIndexOfExchange, List<Integer> listOfCityIndexes) {
		if (currentIndexOfExchange == listOfCityIndexes.size()) {
			double costOfTour = getCostOfTour(listOfCityIndexes);
			if (costOfTour <= minimumCost) {
				minimumCost = costOfTour;
				bestCircuit = new ArrayList<>(listOfCityIndexes);
			}
		}
		
		for (int i = currentIndexOfExchange; i < listOfCityIndexes.size(); i++) {
			Collections.swap(listOfCityIndexes, currentIndexOfExchange, i);
			generatePermutation(currentIndexOfExchange + 1, listOfCityIndexes);
			Collections.swap(listOfCityIndexes, i, currentIndexOfExchange);
		}
	}
	
	private double getCostOfTour(List<Integer> points) {
		double total = 0;
		for (int i = 0; i < points.size() - 1; i++) {
			total += DISTANCES[i][i+1];
		}
		
		total += DISTANCES[points.size() - 1][0];
		
		return total;
	}
	
	private List<Integer> getListOfCityIndexes(int noOfCities) {
		List<Integer> listOFCityIndexes = new ArrayList<>(noOfCities);
		for (int i = 0; i < noOfCities; i++)
			listOFCityIndexes.set(i, i);
		
		return listOFCityIndexes;
	}
	
}
