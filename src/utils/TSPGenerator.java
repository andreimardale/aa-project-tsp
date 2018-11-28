package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TSPGenerator {
	
	Random random;
	
	private String fileName;
	private int noOfCities;
	private boolean symetric;
	
	private int minDistance;
	private int maxDistance;
	
	private String distribution;
	
	private double sparsity; // should be between 0 and 1
	
	
	public TSPGenerator(String name, int size) {
		this.fileName = name;
		this.noOfCities = size;
		random = new Random();
		
		this.symetric = true;
		this.minDistance = 1;
		this.maxDistance = 50;
		this.distribution = "UNIFORM_DISTRIBUTION";
		this.sparsity = 0.0001;
	}
	
	public TSPGenerator(String name, int size, boolean symetric) {
		this.fileName = name;
		this.noOfCities = size;
		random = new Random();
		this.symetric = symetric;
		
		this.minDistance = 1;
		this.maxDistance = 100;
		this.distribution = "UNIFORM_DISTRIBUTION";
		this.sparsity = 0.0001;
	}
	
	public TSPGenerator(String name, int size, boolean symetric, int minDist, int maxDist) {
		this.fileName = name;
		this.noOfCities = size;
		random = new Random();
		this.symetric = symetric;
		
		this.minDistance = minDist;
		this.maxDistance = maxDist;

		this.distribution = "UNIFORM_DISTRIBUTION";
		this.sparsity = 0.0001;
	}

	public TSPGenerator(String name, int size, boolean symetric, int minDist, int maxDist, String distribution) {
		this.fileName = name;
		this.noOfCities = size;
		random = new Random();
		this.symetric = symetric;
		
		this.maxDistance = maxDist;
		this.minDistance = minDist;
		this.distribution = distribution;
		
		this.sparsity = 0.0001;
	}
	
	public TSPGenerator(String name, int size, boolean symetric, int minDist, int maxDist, String distribution, double sparsity) {
		this.fileName = name;
		this.noOfCities = size;
		random = new Random();
		this.symetric = symetric;
		
		this.maxDistance = maxDist;
		this.minDistance = minDist;
		
		this.distribution = distribution;
		this.sparsity = sparsity;
	}
	
	public void generate() {
		int[][] generateDistances = generateDistances();
		String distances = generateDistancesAsString(generateDistances);
		System.out.println(distances);
		
		StringBuilder result = new StringBuilder();
		result.append("NAME: ").append(fileName).append('\n');
		result.append("TYPE: ").append("TSP").append('\n');
		result.append("COMMENT: ").append("Test case generated with custom generator!").append('\n');
		result.append("DIMENSION: ").append(noOfCities).append('\n');
		result.append("EDGE_WEIGHT_TYPE: ").append("EXPLICIT").append('\n');
		result.append("EDGE_WEIGHT_FORMAT: ").append("FULL_MATRIX").append('\n');
		result.append("DISPLAY_DATA_TYPE: ").append("TWOD_DISPLAY").append('\n');
		result.append("EDGE_WEIGHT_SECTION").append('\n');
		result.append(distances);

		
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(result.toString());
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	private int[][] generateDistances() {
		int[][] dist = new int[noOfCities][noOfCities];
		int i, j;
		
		if (symetric == false) {
			for (i = 0; i < noOfCities; i++) {
				for (j = 0; j < noOfCities; j++) {
					if (i == j)
						dist[i][j] = 0;
					else
						dist[i][j] = getNextInt();
				}
			}
		}
		else {
			for (i = 0; i < noOfCities; i++) {
				for (j = i; j < noOfCities; j++) {
					if (i == j)
						dist[i][j] = 0;
					else {
						int d = getNextInt();
						dist[i][j] = d;
						dist[j][i] = d;
					}
				}
			}
		}
		
		return dist;
	}
	
	private int getNextInt() {
		int mean = (maxDistance + minDistance) / 2;
		int value;
		
		switch (distribution) {
		case "UNIFORM_DISTRIBUTION":
			boolean isInfinite = random.nextDouble() < sparsity;
			
			if (isInfinite)
				value = 99999;
			else 
				value = random.nextInt(maxDistance - minDistance + 1) + minDistance;
			break;
		case "NORMAL_DISTRIBUTION":
			value = (int) (random.nextGaussian() + mean);
			break;

		default:
			value = random.nextInt();
			break;
		}
		
		
		return value;
	}
	
	private String generateDistancesAsString(int[][] result) {
		StringBuilder res = new StringBuilder();
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				res.append(result[i][j]).append(" ");
			}
			res.append('\n');
		}
		
		return res.toString();
	}
}
