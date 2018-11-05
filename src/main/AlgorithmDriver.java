package main;

import java.util.List;

import algorithms.AbstractTSP;
import javafx.util.Pair;
import model.Point;
import utils.DrawingUtils;

public class AlgorithmDriver {
	private AbstractTSP strategy;

	public AlgorithmDriver(AbstractTSP strategy) {
		this.strategy = strategy;
	}

	public void executeStrategy(List<Point> points) {
		strategy.execute(points);
		double minimumCost = AbstractTSP.minimumCost;
		List<Point> bestCircuit = AbstractTSP.bestCircuit;
		
		System.out.println("Best circuit has cost  " + minimumCost);
		System.out.println(bestCircuit);
		DrawingUtils.drawPointsOnMap(bestCircuit);

	}
}