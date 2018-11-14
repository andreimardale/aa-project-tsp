package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import utils.DistanceDriver;

public class TSPInput {

	private String name;
	private String type;
	private String comment;

	private int dimension;
	private String edgeWeightType;

	private int[][] dist;
	private List<Integer> cityIndexes;

	public TSPInput(String name, String type, String comment, int dimension, String edgeWeightType,	List<Point> points) {
		this(name, type, comment, dimension, edgeWeightType);
		this.dist = generateAdjancencyMatrix(points);
		this.cityIndexes = computeCityIndexes(points);
	}

	public TSPInput(String name, String type, String comment, int dimension, String edgeWeightType, int[][] dist) {
		this(name, type, comment, dimension, edgeWeightType);
		this.dist = dist;
		this.cityIndexes = computeCityIndexes(dist);
	}

	public TSPInput(String name, String type, String comment, int dimension, String edgeWeightType) {
		super();
		this.name = name;
		this.type = type;
		this.comment = comment;
		this.dimension = dimension;
		this.edgeWeightType = edgeWeightType;
	}
	
	private List<Integer> computeCityIndexes(List<Point> points) {
		List<Integer> cityIndexes = points.stream().map(point -> point.getIndex()).collect(Collectors.toList());
		Optional<Integer> min = cityIndexes.stream().min(Comparator.naturalOrder());
		if (min.isPresent() && min.get() != 0)
			cityIndexes = cityIndexes.stream().map(index -> index - min.get()).collect(Collectors.toList());
		
		return cityIndexes;
	}
	
	private List<Integer> computeCityIndexes(int[][] dist) {
		List<Integer> cityIndexes = new ArrayList<>();
		for (int i = 0; i < dist.length; i++)
			cityIndexes.add(i);
		
		return cityIndexes;
	}

	/* Generate distances according to the edge weight type using the Distance Driver */
	private int[][] generateAdjancencyMatrix(List<Point> points) {
		int[][] result = new int[points.size()][points.size()];
		DistanceDriver driver = new DistanceDriver();
		for (int i = 0; i < points.size(); i++) {
			for (int j = i + 1; j < points.size(); j++) {
				switch (edgeWeightType) {
				case "EUC_2D":
					int dist = driver.euclidian2Distance(points.get(i), points.get(j));
					result[i][j] = dist;
					result[j][i] = dist;
					break;

				default:
					break;
				}
			}
		}
		return result;
	}

	public void printMatrix(int[][] result) {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public String getEdgeWeightType() {
		return edgeWeightType;
	}

	public void setEdgeWeightType(String edgeWeightType) {
		this.edgeWeightType = edgeWeightType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int[][] getDist() {
		return dist;
	}

	public void setDist(int[][] dist) {
		this.dist = dist;
	}

	public List<Integer> getCityIndexes() {
		return cityIndexes;
	}
}
