package utils;

import java.awt.Font;
import java.util.List;

import model.Point;

public class DrawingUtils {
	
	public static void drawPointsOnMap(List<Point> points) {
		StdDraw.setCanvasSize(1600, 1200);
//		StdDraw.setXscale(0, 10);
//		StdDraw.setYscale(0, 10);
		StdDraw.setPenRadius(0.05);
		Font font = new Font("Arial", Font.ITALIC, 30);
		StdDraw.setFont(font);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).draw();
			StdDraw.text(points.get(i).getX(), points.get(i).getY() + 0.02, String.valueOf(i));
		}
		
		StdDraw.setPenRadius(0.0015);
		for (int i = 0; i < points.size() - 1; i++) {
			Point sourcePoint = points.get(i);
			Point destinationPoint = points.get(i + 1);
			sourcePoint.drawTo(destinationPoint);
		}
		
		points.get(points.size() - 1).drawTo(points.get(0));
	}

}
