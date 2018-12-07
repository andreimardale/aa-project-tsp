package algorithms;

import gui.MainFrame;
import java.util.ArrayList;
import java.util.List;

import model.TSPInput;

/* Base abstract class for the TSP algorithms. It provides the methods needed to set and get the minimum costs and tours.
 * All Child classes must implement the mandatory execute() method, as this will be called from outside.
 * */
public abstract class AbstractTSP {

    public static int[][] DISTANCES;
    public static List<Integer> cityIndexes;
    public static MainFrame mainFrame;
    private static int minimumCost = Integer.MAX_VALUE;
    private static List<Integer> bestCircuit = new ArrayList<>();

    public static int getMinimumCost() {
        return minimumCost;
    }

    public static void setMinimumCost(int minimumCost) {
        AbstractTSP.minimumCost = minimumCost;
    }

    public static List<Integer> getBestCircuit() {
        return bestCircuit;
    }

    /* The setter for bestCircuit also updates the GUI thread to display the partial results. */
    public static void setBestCircuit(List<Integer> bestCircuit) {
        AbstractTSP.bestCircuit = new ArrayList<>(bestCircuit);
        if (bestCircuit.size() != 0) {
			if (mainFrame != null) {
				mainFrame.getTourCostLabel().setText(minimumCost + "");
				mainFrame.getGraph().setNewTour(new ArrayList<>(AbstractTSP.bestCircuit));
			}
        }
    }
    
    /* Overloaded method for setting the best circuit.*/
    public static void setBestCircuit(int[] bestCircuit) {
        AbstractTSP.bestCircuit = new ArrayList<>();
        for (int i : bestCircuit) {
            AbstractTSP.bestCircuit.add(i);
        }
        if (AbstractTSP.bestCircuit.size() != 0) {
        	if (mainFrame != null) {
				mainFrame.getTourCostLabel().setText(minimumCost + "");
				mainFrame.getGraph().setNewTour(new ArrayList<>(AbstractTSP.bestCircuit));
			}
        }
    }

    /* This method must be implemented by every subclass, as it will be called from the AlgorithmDriver. */
    public abstract void execute(TSPInput tspInput);
}
