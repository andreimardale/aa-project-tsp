package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Point;
import model.TSPInput;

public class BruteForceTSP extends AbstractTSP {
	
	@Override
	public void execute(TSPInput tspInput) {
		generatePermutation(0, tspInput.getPoints());
	}

	private void generatePermutation(int currentIndexOfExchange, List<Point> points) {
		if (currentIndexOfExchange == points.size()) {
			double costOfTour = getCostOfTour(points);
			if (costOfTour <= minimumCost) {
				minimumCost = costOfTour;
				bestCircuit = new ArrayList<>(points);
			}
		}
		
		for (int i = currentIndexOfExchange; i < points.size(); i++) {
			Collections.swap(points, currentIndexOfExchange, i);
			generatePermutation(currentIndexOfExchange + 1, points);
			Collections.swap(points, i, currentIndexOfExchange);
		}
	}
	
	private double getCostOfTour(List<Point> points) {
		double total = 0;
		for (int i = 0; i < points.size() - 1; i++) {
			Point sourcePoint = points.get(i);
			Point destinationPoint = points.get(i + 1);
			
			total += sourcePoint.distanceTo(destinationPoint);
		}
		
		total += points.get(points.size() - 1).distanceTo(points.get(0));
		if (total == 8)
			System.out.println();
		
		return total;
	}
	
}
