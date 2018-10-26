package algorithms;

import java.util.List;

import javafx.util.Pair;
import model.Point;

public interface AbstractTSP {
	public Pair<List<Point>, Double> execute(List<Point> points);
}
