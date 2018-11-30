package utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Point;
import model.TSPInput;



public class TSPReader {
    private String fileName;
    private static final String NAME = "NAME";
    private static final String TYPE = "TYPE";
    private static final String COMMENT = "COMMENT";
    private static final String DIMENSION = "DIMENSION";
    private static final String EDGE_WEIGHT_TYPE = "EDGE_WEIGHT_TYPE";
    
    public TSPReader(String fileName) {
        this.fileName = fileName;
    }
    
    /* Reads from the desired input file and generate inputs according to the information of the file*/
    public TSPInput read() {
        TSPInput input = null;
        BufferedReader reader = null;
        
        try {
        	reader = new BufferedReader(new FileReader(new File(fileName)));
            HashMap<String, String> information = new HashMap<>();
            while(true) {
                String line = reader.readLine();
                String[] splittedLine = line.split(":");
                
                String key = splittedLine[0].trim();
                if(key.equals("EDGE_WEIGHT_SECTION") || key.equals("NODE_COORD_SECTION"))
                    break;
                String value = splittedLine[1].trim();
                information.put(key, value);
            }
            
            int number_of_nodes = Integer.parseInt(information.get(DIMENSION));
            String method_to_calc_distance=information.get(EDGE_WEIGHT_TYPE);
            switch (method_to_calc_distance) {
                case "EUC_2D": {
                    List<Point> input_points = new ArrayList<>();
                    for (int i = 0; i < number_of_nodes; i++) {
                        String line=reader.readLine().trim();
                        int index = Integer.parseInt(line.split("\\s+")[0]);
                        double x_coord = Double.parseDouble(line.split("\\s+")[1]);
                        double y_coord = Double.parseDouble(line.split("\\s+")[2]);
                        input_points.add(new Point(x_coord, y_coord, index));
                    }
                    input = new TSPInput(information.get(NAME), information.get(TYPE),
                    		information.get(COMMENT), Integer.parseInt(information.get(DIMENSION)), information.get(EDGE_WEIGHT_TYPE), input_points);
                    break;
                }
                case "EXPLICIT": {
                	int[][] dist = new int[number_of_nodes][number_of_nodes];
                	for (int i = 0; i < number_of_nodes; i++) {
                		String line = reader.readLine();
                		String[] splittedLine = line.trim().split("\\s+");
                		
                		for (int j = 0; j < number_of_nodes; j++) {
                			dist[i][j] = Integer.parseInt(splittedLine[j]);
                		}
                	}
                    input = new TSPInput(information.get(NAME), information.get(TYPE),
                    		information.get(COMMENT), Integer.parseInt(information.get(DIMENSION)), information.get(EDGE_WEIGHT_TYPE), dist);
                    break;
                }
                case "EUC_3D": {
                    //do something
                    break;
                }
                case "ATT":{
                    break;
                }
            }
            
            reader.close();
        } catch (Exception ex) {
            Logger.getLogger(TSPReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return input;
    }
}
