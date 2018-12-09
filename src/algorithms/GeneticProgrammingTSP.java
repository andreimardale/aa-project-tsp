package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import model.TSPInput;

public class GeneticProgrammingTSP extends AbstractTSP {

	private static int tournamentSize = 0;
	private static TSPInput input;
	private static final String OX_CROSS_OVER = "OX";
	private static final String NORMAL_CROSS_OVER = "Normal";
	private static final String PMX_CROSS_OVER = "PMX";
	private static final String NWOX_CROSS_OVER = "NWOX";

	private int iterationSize;
	private String crossOverType;
	private double mutationRate;
	private int popSize;

	public GeneticProgrammingTSP(int iterationSize, String crossOverType, double mutationRate, int popSize) {
		this.iterationSize = iterationSize;
		this.crossOverType = crossOverType;
		this.mutationRate = mutationRate;
		this.popSize = popSize;
	}

	@Override
	public void execute(TSPInput tspInput) {
		input = tspInput;
		tournamentSize = popSize / 5;
		Population pop = new Population(popSize, true);

		/*
		 * we evovle the population to a limit in order to get new generations with
		 * better fittness
		 */
		for (int i = 0; i < iterationSize; i++) {
			pop = evolvePopulation(pop, crossOverType);

			Tour fittest_tour = pop.getFittest();
//			System.out.println(fittest_tour.getDistance()+"\t"+fittest_tour);
			setBestCircuit(fittest_tour.cities);
			setMinimumCost((int) fittest_tour.getDistance());
		}
		Tour fittest_tour = pop.getFittest();
		setMinimumCost((int) fittest_tour.getDistance());
		setBestCircuit(pop.getFittest().cities);
	}

	boolean keep = true;

	/* this function takes the old population and evolve a new population from it */
	public Population evolvePopulation(Population population, String crossOverType) {

		Population newPopulation = new Population(population.getSize(), false);
		int offset = 0;
		/* we will keep the fittest child from the population before if keep = true */
		if (keep) {
			newPopulation.tours.add(0, population.getFittest());
			offset = 1;
		}
		/*
		 * traverse the hole population and generate new children for the new population
		 */

		for (int i = offset; i < newPopulation.getSize(); i++) {
			/* get the two parents using the tournament function */

			Tour parent_1 = population.getFittest();
			Tour parent_2 = getSecondParent(population);
			/*
			 * tournament can be used also where a tournament of specified size could be
			 * used to get the best two parents for mating
			 * 
			 * while(true) { parent_2 = tournament(population);
			 * if(!parent_1.equals(parent_2)) { break; } }
			 */
			/* make a child by performing a crossOver on the two parents */

			Tour child = null;
			switch (crossOverType) {
			case OX_CROSS_OVER: {
				child = crossOver_OX(parent_1, parent_2);
				break;
			}
			case NORMAL_CROSS_OVER: {
				child = crossOver(parent_1, parent_2);
				break;
			}
			case PMX_CROSS_OVER: {
				child = crossOver_PMX(parent_1, parent_2);
				break;
			}
			case NWOX_CROSS_OVER: {
				child = crossOver_NWOX(parent_1, parent_2);
				break;
			}
			}

			child.distance = child.getDistance();
			/* add the new child to the new population */
			newPopulation.tours.add(child);
		}
		/*
		 * traverse the new population and do some mutations on its indviduals i.e TOURS
		 */
		for (int i = offset; i < newPopulation.getSize(); i++) {
			mutate(newPopulation.tours.get(i));
		}
		return newPopulation;
	}

	/*
	 * this function gets the second fittest child for crossover if tournament is
	 * not used
	 */
	private Tour getSecondParent(Population pop) {
		int first = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		int index_of_p2 = 0;
		for (int j = 0; j < popSize; j++) {
			if (pop.tours.get(j).getDistance() <= first) {
				second = first;
				first = (int) pop.tours.get(j).getDistance();
			} else if (pop.tours.get(j).getDistance() < second && pop.tours.get(j).getDistance() != first) {
				second = (int) pop.tours.get(j).getDistance();
				index_of_p2 = j;
			}
		}
		return pop.tours.get(index_of_p2);

	}

	/*
	 * this function given a tour it mutate its genes which are the cities by
	 * swaping them
	 */
	private void mutate(Tour tour) {
		for (int i = 0; i < tour.cities.size(); i++) {

			if (Math.random() < mutationRate) {
				int city1_position = i;
				int city2_position = (int) (Math.random() * tour.cities.size());

				/* get the two cities to swap them */
				int city1 = tour.cities.get(city1_position);
				int city2 = tour.cities.get(city2_position);

				// swap cities
				/* put city1 in the position of city2 and vice versa */

				tour.cities.set(city2_position, city1);
				tour.cities.set(city1_position, city2);
			}
		}
	}
	/* partially matched crossOver */

	private Tour crossOver_PMX(Tour parent_1, Tour parent_2) {
		Tour child = new Tour();

		// generate random start and end indexes
		int startIndex = (int) (Math.random() * parent_1.cities.size());
		int endIndex = (int) (Math.random() * parent_1.cities.size());
		while (startIndex == endIndex) {
			endIndex = (int) (Math.random() * parent_1.cities.size());
		}
		if (startIndex > endIndex) {
			int sw = startIndex;
			startIndex = endIndex;
			endIndex = sw;
		}
		ArrayList<Integer> segment1 = new ArrayList<>();
		ArrayList<Integer> segment2 = new ArrayList<>();

		for (int i = startIndex; i <= endIndex; i++) {

			/* save the segments truncated */
			segment1.add(parent_1.cities.get(i));
			segment2.add(parent_2.cities.get(i));

			/* put the cities of parent2 in child within the segment */
			child.cities.set(i, parent_2.cities.get(i));

			/*
			 * get the desired city and go search for its index in the cities of the first
			 * parent then put the city facing it from parent1 in the city index fetched
			 * before
			 */
			int desiredCity = parent_2.cities.get(i);
			int cityIndex = getIndexOfCity(parent_1.cities, desiredCity);
			child.cities.set(cityIndex, parent_1.cities.get(i));

		}
		/* put the remaining cities from parent 1 outside the segment */
		for (int i = 0; i < child.cities.size(); i++) {
			if ((i < startIndex) || (i > endIndex))
				child.cities.set(i, parent_1.cities.get(i));
		}
		/* check for duplicates and remove them */
		for (int i = 0; i < child.cities.size(); i++) {
			while (checkDuplicates(child.cities, i)) {
				removeDuplicates(child.cities, i, segment1, segment2);
			}

		}
		return child;
	}

	/*
	 * function used by PMX to remove duplicated cities by replacing them with there
	 * mappings for second parent
	 */
	private void removeDuplicates(ArrayList<Integer> cities, int index, ArrayList<Integer> segment1,
			ArrayList<Integer> segment2) {
		for (int i = 0; i < segment1.size(); i++) {
			if (segment1.get(i) == cities.get(index))
				cities.set(index, segment2.get(i));
			else if (segment2.get(i) == cities.get(index))
				cities.set(index, segment1.get(i));
		}
	}

	/* functio used by PMX to check if there exit duplicates in cities of a tour */
	private boolean checkDuplicates(ArrayList<Integer> cities, int indexOfCity) {
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(indexOfCity) == cities.get(i) && i != indexOfCity)
				return true;
		}
		return false;
	}

	/*
	 * function used by PMX to get the index of a city from second parent given the
	 * city
	 */
	private int getIndexOfCity(ArrayList<Integer> cities, int city) {
		int index = 0;
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i) == city) {
				index = i;
				break;
			}
		}
		return index;
	}

	/*
	 * ordered cross over it takes the first half from the first parent and then
	 * fill the remaining cities from the second parent
	 */
	private Tour crossOver_OX(Tour parent_1, Tour parent_2) {
		Tour child = new Tour();
		for (int i = 0; i < parent_1.cities.size() / 2; i++) {
			child.cities.set(i, parent_1.cities.get(i));
		}
		int count = child.cities.size() / 2;
		for (int i = 0; i < parent_2.cities.size(); i++) {
			if (!child.cities.contains(parent_2.cities.get(i))) {
				child.cities.set(count, parent_2.cities.get(i));
				count++;
			}
		}
		return child;
	}

	private Tour crossOver_NWOX(Tour parent_1, Tour parent_2) {
		Tour child1 = new Tour();
		Tour child2 = new Tour();
		int startIndex = (int) (Math.random() * parent_1.cities.size());
		int endIndex = (int) (Math.random() * parent_1.cities.size());
		while (startIndex == endIndex) {
			endIndex = (int) (Math.random() * parent_1.cities.size());
		}
		if (startIndex > endIndex) {
			int x = startIndex;
			startIndex = endIndex;
			endIndex = x;
		}
		ArrayList<Integer> partition_1 = new ArrayList<>();
		ArrayList<Integer> partition_2 = new ArrayList<>();

		/* clone parents */
		for (int i = 0; i < child1.cities.size(); i++) {
			child1.cities.set(i, parent_1.cities.get(i));
			child2.cities.set(i, parent_2.cities.get(i));

		}
		/* save the partitions */
		for (int i = startIndex; i <= endIndex; i++) {

			partition_1.add(parent_1.cities.get(i));
			partition_2.add(parent_2.cities.get(i));

		}
		/*
		 * make holes or nulls in each of the parents according to the cities from the
		 * second parent within the interval
		 */
		for (int i = 0; i < partition_1.size(); i++) {

			makeHole(child1.cities, partition_2.get(i));
			makeHole(child2.cities, partition_1.get(i));

		}
		/*shift the holes to the middle of the interval*/
		shiftCities(child1, startIndex, endIndex);
		shiftCities(child2, startIndex, endIndex);

		/* now switch the two segments between parents */
		int index = startIndex;
		for (int i = 0; i < partition_1.size(); i++) {
			child1.cities.set(index, partition_2.get(i));
			child2.cities.set(index, partition_1.get(i));
			index++;
		}
		/* return the best generated child */
		if (child1.getDistance() < child2.getDistance()) {
			return child1;
		} else {
			return child2;
		}
	}
	/*function used by the shifting holes function */
	private boolean checkPlaceAtLeft(ArrayList<Integer> cities, int startIndex) {
		for (int i = 0; i < startIndex; i++) {
			if (cities.get(i) == null) {
				return true;
			}
		}
		return false;
	}
	/*function used by the shifting holes function */
	private boolean checkPlaceAtRight(ArrayList<Integer> cities, int endIndex) {
		for (int i = endIndex + 1; i < cities.size(); i++) {
			if (cities.get(i) == null)
				return true;
		}
		return false;
	}

	private void shiftCities(Tour child1, int startIndex, int endIndex) {
		for (int i = 0; i < child1.cities.size(); i++) {
			if (child1.cities.get(i) == null) {
				if (checkPlaceAtLeft(child1.cities, startIndex)) {
					for (int j = i; j < child1.cities.size(); j++) {
						if (child1.cities.get(j) != null) {
							child1.cities.set(i, child1.cities.get(j));
							child1.cities.set(j, null);
							break;
						}
					}
				} else {
					break;
				}
			}

		}

		for (int i = child1.cities.size() - 1; i >= 0; i--) {
			if (child1.cities.get(i) == null) {
				if (checkPlaceAtRight(child1.cities, endIndex)) {
					for (int j = i; j >= 0; j--) {
						if (child1.cities.get(j) != null) {
							child1.cities.set(i, child1.cities.get(j));
							child1.cities.set(j, null);
							break;
						}
					}
				} else
					break;
			}
		}
	}
	/* make hole (null) in the desired place of the city */
	private void makeHole(ArrayList<Integer> cities, int city) {
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i) != null && cities.get(i) == city) {
				cities.set(i, null);
				break;
			}
		}
	}
	/* this function given tow parent tours it generates a child */

	private Tour crossOver(Tour parent_1, Tour parent_2) {
		/* initialize a new child having null cities */
		Tour child = new Tour();

		/* get a random start and end index */

		int startIndex = (int) (Math.random() * parent_1.cities.size());
		int endIndex = (int) (Math.random() * parent_1.cities.size());

		while (startIndex == endIndex) {
			endIndex = (int) (Math.random() * parent_1.cities.size());
		}

		/* fill child with cities from first parent */
		for (int i = 0; i < child.cities.size(); i++) {

			// index between the start and end

			if (startIndex < endIndex && i > startIndex && i < endIndex) {
				child.cities.set(i, parent_1.cities.get(i));

			} else if (startIndex > endIndex) {
				/* we take the cities outside the start and end */

				if (i <= startIndex || i >= endIndex) {
					child.cities.set(i, parent_1.cities.get(i));
				}
			}
		}
		/* fill child with the rest from second parent */

		for (int i = 0; i < parent_2.cities.size(); i++) {

			/* if the child miss this city */

			if (!child.cities.contains(parent_2.cities.get(i))) {

				/* loop through child's cities to find a empty place */

				for (int j = 0; j < child.cities.size(); j++) {
					if (child.cities.get(j) == null) {
						/* add the missing city in its right place */
						child.cities.set(j, parent_2.cities.get(i));
						break;
					}
				}
			}
		}
		return child;
	}
	/*
	 * this function make a tournament of a specific size defined above that could
	 * be tuned , the winner of this tournament is the fittest tour i.e which have
	 * the minimum distance
	 */

	private Tour tournament(Population population) {
		Population tournament = new Population(tournamentSize, false);
		for (int i = 0; i < tournamentSize; i++) {
			/* fill the tournament randomly from the population */

			int randId = (int) (Math.random() * population.getSize());
			tournament.tours.add(i, population.tours.get(randId));
		}
		Tour fittest = tournament.getFittest();
		return fittest;
	}

	private class Tour {

		private ArrayList<Integer> cities = new ArrayList<>();
		private double fitness = 0;
		private double distance = 0;

		public Tour() {
			for (int i = 0; i < input.getDimension(); i++) {
				cities.add(null);
			}
		}

		public void generateIndividual() {
			/*
			 * for the first initialization indviduals i.e tours are generated randomly
			 * using the shuffle function
			 */
			for (int i = 0; i < input.getDimension(); i++) {
				cities.set(i, i);
			}
			Collections.shuffle(cities);
		}

		public double getFitness() {
			fitness = 1 / (double) getDistance();
			return fitness;

		}

		public double getDistance() {
			/*
			 * calculate distance of the tour from the begging city to the last city plus
			 * the last edge from the last city to the first one
			 */

			int tourDistance = 0;
			for (int i = 0; i < cities.size(); i++) {

				int fromCity = cities.get(i);
				int destCity;
				if (i + 1 < cities.size()) {
					destCity = cities.get(i + 1);
				} else {
					destCity = cities.get(0);
				}
				tourDistance += input.getDist()[fromCity][destCity];
			}
			distance = tourDistance;
			return distance;
		}

		@Override
		public String toString() {
			String str = "";
			for (int i = 0; i < cities.size(); i++) {
				str += cities.get(i) + " -> ";
			}
			return str + "\n";
		}

		@Override
		public boolean equals(Object obj) {
			for (int i = 0; i < cities.size(); i++) {
				if (cities.get(i) != ((Tour) obj).cities.get(i))
					return false;
			}
			return true;
		}

	}

	private class Population {
		private ArrayList<Tour> tours = new ArrayList<>();
		private int pop_size;

		public Population(int popSize, boolean init) {
			pop_size = popSize;
			if (init) {
				/*
				 * if init for first time we add the random generated individuals i.e tours to
				 * the population using the generateIndividual() function defined in the Tour
				 * class
				 */
				for (int i = 0; i < popSize; i++) {
					Tour tour = new Tour();
					tour.generateIndividual();
					tours.add(tour);
				}
			}

		}
		/*
		 * this function gets the fittest tour i.e min distance which will be the answer
		 * at some point
		 */

		public Tour getFittest() {

			Tour fittestTour = tours.get(0);
			for (Tour currentTour : tours) {
				if (currentTour.getDistance() < fittestTour.getDistance()) {
					fittestTour = currentTour;
				}
			}
			return fittestTour;
		}

		public int getSize() {
			return pop_size;
		}

		@Override
		public String toString() {
			String str = "";
			for (int i = 0; i < pop_size; i++) {
				str += tours.get(i) + "\n";
			}
			return str;
		}
	}

}
