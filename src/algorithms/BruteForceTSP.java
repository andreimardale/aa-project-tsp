package algorithms;

import java.util.Collections;
import java.util.List;

import model.TSPInput;

public class BruteForceTSP extends AbstractTSP {
	
	@Override
	public void execute(TSPInput tspInput) {
		generatePermutation(0, cityIndexes);
	}

	private void generatePermutation(int currentIndexOfExchange, List<Integer> permutationOfCityIndexes) {
		if (currentIndexOfExchange == permutationOfCityIndexes.size()) {
			int costOfTour = getCostOfTour(permutationOfCityIndexes);
			if (costOfTour <= getMinimumCost()) {
				setMinimumCost(costOfTour);
				setBestCircuit(permutationOfCityIndexes);
			}
		}
		
		for (int i = currentIndexOfExchange; i < permutationOfCityIndexes.size(); i++) {
			Collections.swap(permutationOfCityIndexes, currentIndexOfExchange, i);
			generatePermutation(currentIndexOfExchange + 1, permutationOfCityIndexes);
			Collections.swap(permutationOfCityIndexes, i, currentIndexOfExchange);
		}
	}
	
	private int getCostOfTour(List<Integer> permutationOfCityIndexes) {
		int total = 0;
		for (int i = 0; i < permutationOfCityIndexes.size() - 1; i++) {
			total += DISTANCES[permutationOfCityIndexes.get(i)][permutationOfCityIndexes.get(i+1)];
		}
		
		total += DISTANCES[permutationOfCityIndexes.get(permutationOfCityIndexes.size() - 1)][permutationOfCityIndexes.get(0)];
		
		return total;
	}
}
