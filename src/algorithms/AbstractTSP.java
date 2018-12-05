package algorithms;

import gui.MainFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import model.TSPInput;

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

    public static void setBestCircuit(List<Integer> bestCircuit) {
        AbstractTSP.bestCircuit = new ArrayList<>(bestCircuit);
        if (bestCircuit.size() != 0) 
        {
        	/*try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			if (mainFrame != null) {
				mainFrame.getTourCostLabel().setText(minimumCost + "");
				mainFrame.getGraph().setNewTour(new ArrayList<>(AbstractTSP.bestCircuit));
			}
        	
        }
        
    }
    public static void setBestCircuit(int[] bestCircuit) {
        AbstractTSP.bestCircuit = new ArrayList<>();
        for (int i : bestCircuit) {
            AbstractTSP.bestCircuit.add(i);
        }
        if (AbstractTSP.bestCircuit.size() != 0) {
        	/*try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        	if (mainFrame != null) {
				mainFrame.getTourCostLabel().setText(minimumCost + "");
				mainFrame.getGraph().setNewTour(new ArrayList<>(AbstractTSP.bestCircuit));
			}
        }
    }

    public abstract void execute(TSPInput tspInput);
}
