package main;

import java.util.List;

import algorithms.AbstractTSP;
import javafx.util.Pair;
import model.Point;

public class AlgorithmDriver {
	private AbstractTSP strategy;

	public AlgorithmDriver(AbstractTSP strategy) {
		this.strategy = strategy;
	}

	public Pair<List<Point>, Double> executeStrategy(List<Point> points) {
		return strategy.execute(points);
	}
}