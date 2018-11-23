package algorithms;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.TSPInput;

public class AntColonyTSP extends AbstractTSP {
	
	
	private static final int MAX_INTERATIONS = 2500;
	private static final int NO_OF_ANTS = 10;
	private static final double ALPHA = 0.1, BETA = 2.0;
	private static final double INITIAL_PHEROMONE_FOR_EACH_EDGE = 1;
	private static final double RO = 0.1;
	
	private static final double Q0 = 0.9;
	private static final double ZETA = 0.1;
	private static final double TAU0 = 0.000024;
	
	private Random random = new Random();
	int[][] dist;
	int noOfCities;
	

	@Override
	public void execute(TSPInput tspInput) {
		noOfCities = tspInput.getDimension();
		dist = tspInput.getDist();
		
		double[][] tau = initializePheromones(noOfCities);
		List<Ant> ants = createAnts(noOfCities);
		
		for (int t = 1; t <= MAX_INTERATIONS; t++) {
			for (Ant ant : ants) {
				ant.reset();
				ant.visitCity(random.nextInt(noOfCities));
			}
			
			moveAnts(ants, noOfCities, tau);
			updateBestTour(ants);
			updatePheromones(tau);
			
		}
		
	}

	private void updateBestTour(List<Ant> ants) {
		
		if (getBestCircuit().isEmpty()) {
			setBestCircuit(ants.get(0).getTour());
			setMinimumCost(ants.get(0).getCostOfTour(dist));
        }
        for (Ant ant : ants) {
            if (ant.getCostOfTour(dist) < getMinimumCost()) {
            	setMinimumCost(ant.getCostOfTour(dist));
            	setBestCircuit(ant.getTour());
            }
        }
	}

	private void updatePheromones(double[][] tau) {
//		for (int i = 0; i < noOfCities; i++) {
//			for (int j = 0; j < noOfCities; j++) {
//				tau[i][j] = (1 - RO) * tau[i][j];
//			}
//		}
//		
//		
//        for (Ant ant : ants) {
//            double currentAntContribution = 1.0 / ant.getCostOfTour(dist);
//            
//            for (int i = 0; i < noOfCities - 1; i++) {
//                tau[ant.getCityAt(i)][ant.getCityAt(i + 1)] += currentAntContribution;
//            }
//            tau[ant.getCityAt(noOfCities - 1)][ant.getCityAt(0)] += currentAntContribution;
//        }
		
		List<Integer> globalBestTour = getBestCircuit();
		double globalBestAntContribution = 1.0 / getCostOfTour(globalBestTour);
      
		for (int i = 0; i < noOfCities - 1; i++) {
			tau[globalBestTour.get(i)][globalBestTour.get(i + 1)] = tau[globalBestTour.get(i)][globalBestTour.get(i + 1)] * (1 - RO) + RO * globalBestAntContribution;
		}
		tau[globalBestTour.get(noOfCities - 1)][globalBestTour.get(0)] = tau[globalBestTour.get(noOfCities - 1)][globalBestTour.get(0)] * (1 - RO) + RO * globalBestAntContribution;
		
	}

	private double[][] initializePheromones(int noOfCities) {
		double [][] tau = new double[noOfCities][noOfCities];
		
		for (int i = 0; i < noOfCities; i++) {
			for (int j = 0; j < noOfCities; j++) {
				tau[i][j] = INITIAL_PHEROMONE_FOR_EACH_EDGE;
			}
		}
		
		return tau;
	}
	
	private List<Ant> createAnts(int noOfCities) {
		List<Ant> ants = new ArrayList<>();
		
		for (int i = 0; i < NO_OF_ANTS; i++)
			ants.add(new Ant());

		return ants;
	}
 	
	public void moveAnts(List<Ant> ants, int noOfCities, double[][] tau) {
		for (Ant ant : ants) {
			for (int i = 1; i < noOfCities; i++) {
				int nextCity = selectNextCity(ant, noOfCities, tau);
				Integer currentCity = ant.getCurrentCity();
				ant.visitCity(nextCity);
				
				tau[currentCity][nextCity] = (1 - ZETA) * tau[currentCity][nextCity] + ZETA * TAU0;
			}
		}
	}
	
	public int selectNextCity(Ant ant, int noOfCities, double[][] tau) {
        if (random.nextDouble() < Q0)
        	return getMaximalCity(ant, noOfCities, tau); // Enhancement for ACS - with a probability of Q0, pick the maximal city wrt tau * (1/dist)
		
		double[] probabilities = calculateProbabilities(ant, noOfCities, tau);
		
		double r = random.nextDouble();
        double total = 0;
        for (int i = 0; i < noOfCities; i++) {
            total += probabilities[i];
            if (total >= r) {
                return i;
            }
        }
        
        return -1;
	}
	
	public int getMaximalCity(Ant ant, int noOfCities, double[][] tau) {
		double maxValue = Double.MIN_VALUE;
		int maximalCity = -1;
		
		int i = ant.getCurrentCity();

		for (int l = 0; l < noOfCities; l++) {
            if (ant.hasVisited(l))
            	continue;
            
            double valueToNodeL = tau[i][l] * Math.pow(1.0 / dist[i][l], BETA);
            if (valueToNodeL >= maxValue) {
            	maxValue = valueToNodeL;
            	maximalCity = l;
            }
        }
		
		return maximalCity;
	}
	
	public double[] calculateProbabilities(Ant ant, int noOfCities, double[][] tau) {
		double[] probabilities = new double[noOfCities];
        
		int i = ant.getCurrentCity();
        double sumOfAllPossibleValues = 0.0;
        for (int l = 0; l < noOfCities; l++) {
            if (!ant.hasVisited(l)) {
                sumOfAllPossibleValues += Math.pow(tau[i][l], ALPHA) * Math.pow(1.0 / dist[i][l], BETA);
            }
        }
        
        for (int j = 0; j < noOfCities; j++) {
            if (ant.hasVisited(j)) {
                probabilities[j] = 0.0;
            } else {
                double valueToNodeJ = Math.pow(tau[i][j], ALPHA) * Math.pow(1.0 / dist[i][j], BETA);
                probabilities[j] = valueToNodeJ / sumOfAllPossibleValues;
            }
        }
        
        return probabilities;
    }
	
	public int getCostOfTour(List<Integer> tour) {
		int total = 0;
		for (int i = 0; i < tour.size() - 1; i++) {
			total += dist[tour.get(i)][tour.get(i+1)];
		}
		
		total += dist[tour.get(tour.size() - 1)][tour.get(0)];
		
		return total;
	}
}

class Ant {
	
	private Set<Integer> visitedCities;
	private List<Integer> tour;
	
	public Ant() {
		this.visitedCities = new HashSet<>();
		this.tour = new ArrayList<>();
	}

	public void visitCity(int city) {
		tour.add(city);
		visitedCities.add(city);
	}
	
	public void reset() {
		visitedCities = new HashSet<>();
		tour = new ArrayList<>();
	}

	public Integer getCurrentCity() {
		return tour.get(tour.size() - 1);
	}

	public boolean hasVisited(Integer city) {
		return visitedCities.contains(city);
	}
	
	public int getCostOfTour(int[][] dist) {
		int total = 0;
		for (int i = 0; i < tour.size() - 1; i++) {
			total += dist[tour.get(i)][tour.get(i+1)];
		}
		
		total += dist[tour.get(tour.size() - 1)][tour.get(0)];
		
		return total;
	}
	
	public int getCityAt(int position) {
		return tour.get(position);
	}
	
	public List<Integer> getTour() {
		return tour;
	}
}
