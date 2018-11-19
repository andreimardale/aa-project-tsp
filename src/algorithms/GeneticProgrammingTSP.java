package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import model.TSPInput;

public class GeneticProgrammingTSP extends AbstractTSP{
    
	private static final double mutationRate = 0.015;
    private static final int tournamentSize = 10;
    private static TSPInput input;
    
	@Override
	public void execute(TSPInput tspInput) {
		input = tspInput;
		Population pop = new Population(25, true);
		
		System.out.println("Initial cost: "+pop.getFittest().distance);
		pop = evolvePopulation(pop);
		/*int min = Integer.MAX_VALUE;
		
		for(int i=0;i<pop.tours.size();i++) {
			int distance = 0 ;
			for(int j=0;j<pop.tours.get(i).cities.size() - 1;j++) {
				int from = pop.tours.get(i).cities.get(j);
				int to = pop.tours.get(i).cities.get(j+1);
				distance += tspInput.getDist()[from][to];
			}
			distance += tspInput.getDist()[pop.tours.get(i).cities.get(pop.tours.get(i).cities.size()-1)][pop.tours.get(i).cities.get(0)]; ;
			if(distance < min)
				min = distance ;
		}
		System.out.println("Minum tour after one evolution : "+min);
		System.out.println("Fittest : "+pop.getFittest().getDistance());
		*/
		
		for(int i=0;i<1000;i++) {
			pop = evolvePopulation(pop);
		}
	
		System.out.println("Final cost: "+pop.getFittest().getDistance());
	
	}
	boolean keep=true;
	public Population evolvePopulation(Population population) {
		
		Population newPopulation = new Population(population.getSize(),false);
		int offset = 0;
		if(keep) {
			newPopulation.tours.add(0,population.getFittest());
			offset = 1;
		}
		for(int i=offset;i<newPopulation.getSize() ; i++) {
			
			Tour parent_1 = tournament(population);
			Tour parent_2 = tournament(population);
			
			Tour child = crossOver(parent_1,parent_2);
			child.distance = child.getDistance();
			newPopulation.tours.add(child);
		}
		
		for (int i = offset ; i < newPopulation.getSize() ; i++) {
			mutate(newPopulation.tours.get(i));
		}
		return newPopulation;
	}
	private void mutate(Tour tour) {
		for (int i = 0; i < tour.cities.size() ; i++) {
			if(Math.random() < mutationRate) {
				int city2_position = (int) (Math.random() * tour.cities.size());
				
				int city1 = tour.cities.get(i);
				int city2 = tour.cities.get(city2_position);
				
				//swap cities
				
				tour.cities.set(city2_position, city1);
				tour.cities.set(i, city2);
				
			}
		}
	}
	private Tour crossOver(Tour parent_1, Tour parent_2) {
		Tour child = new Tour();
		
		int startIndex = (int)(Math.random() * parent_1.cities.size());
		int endIndex  = (int)(Math.random() * parent_1.cities.size());
		
		/* fill child with cities from first parent */ 
		for (int i = 0; i < child.cities.size() ; i++) {
			
			// index between the start and end
			
			if(startIndex < endIndex && i > startIndex && i < endIndex) {
				child.cities.set(i,parent_1.cities.get(i));

			}
			else if (startIndex > endIndex) {
				if( i <= startIndex || i>=endIndex) {
					child.cities.set(i,parent_1.cities.get(i));
				}
			}
		}
		/*fill child with the rest from second parent */ 
		
		for (int i = 0; i < parent_2.cities.size(); i++) {
			
			/* if the child miss this city */
			
			if(!child.cities.contains(parent_2.cities.get(i))){
				
				/* loop through child's cities to find a empty place */
				
				for (int j = 0; j <child.cities.size(); j++) {
					if(child.cities.get(j) == null) {
						child.cities.set(j,parent_2.cities.get(i));
						break;
					}
				}
			}
		}
		return child;
	}
	private Tour tournament(Population population) {
		Population tournament = new Population(tournamentSize, false);
		for( int i = 0 ; i<tournamentSize ; i++) {
			int randId = (int) (Math.random() * population.getSize()); 
			tournament.tours.add(i,population.tours.get(randId));
		}
		Tour fittest = tournament.getFittest();
		return fittest;
	}
	private class Tour{
		
		private ArrayList<Integer> cities = new ArrayList<>();
		private double fitness = 0;
		private double distance = 0;
		public Tour() {
			for (int i = 0; i < input.getDimension(); i++) {
				cities.add(null);
			}
		}
		
		public void generateIndividual() {
			
			for (int i = 0; i < input.getDimension() ; i++) {
				cities.set(i,i);
			}
			Collections.shuffle(cities);
		}
		public double getFitness() {
				fitness = 1 / (double)getDistance();
			return fitness;
				
		}
		public double getDistance() {
				/*calculate distance of the tour*/
				
				int tourDistance=0;
				for(int i=0;i<cities.size();i++) { 
					
					int fromCity = cities.get(i);
					int destCity;
					if(i+1 < cities.size()) {
						destCity = cities.get(i+1);
					} else {
						destCity = cities.get(0);
					}
					tourDistance += input.getDist()[fromCity][destCity];
				}
				distance=tourDistance;
			return distance;
		}
		@Override
		public String toString() {
			String str = "";
			for (int i = 0; i < cities.size(); i++) {
				str += cities.get(i)+" -> ";
			}
			return str+"\n";
		}

		
	}
	private class Population {
		private ArrayList<Tour> tours = new ArrayList<>();
		private int pop_size;
	    public Population(int popSize,boolean init) {
			pop_size = popSize;
	    	if (init) {
				for (int i = 0; i < popSize; i++) {
					Tour tour = new Tour();
					tour.generateIndividual();
					tours.add(tour);
				}
			}
			
	    }
	    public Tour getFittest() {
	    	Tour fittestTour = tours.get(0);
	    	for(Tour currentTour:tours) {
	    		if(currentTour.getDistance() < fittestTour.getDistance()) {
	    			fittestTour=currentTour;
	    		}
	    	}
	    	return fittestTour;
	    }
	    public int getSize() {
	    	return pop_size;
	    }
	}

}
