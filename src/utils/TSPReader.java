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
    private String FILE_NAME;
    private String EDGE_WEIGHT_TYPE="EDGE_WEIGHT_TYPE:";
    private String DIMENSION="DIMENSION:";
    
    public TSPReader(String FILE_NAME) {
        this.FILE_NAME=FILE_NAME;
    }
    /*reads from the desired input file and generate inputs
    according to the iformation of the file*/
    public TSPInput read()
    {
        TSPInput input=null;
        try {
            BufferedReader reader=new BufferedReader(new FileReader(new File(FILE_NAME)));
            HashMap<String,String> information=new HashMap<>();
            while(true) {
                String line=reader.readLine();
                String key=line.split(" ")[0];
                if(key.equals("EDGE_WEIGHT_SECTION")||key.equals("NODE_COORD_SECTION"))
                    break;
                String value=line.split(" ")[1];
                information.put(key, value);
            }
            int number_of_nodes=Integer.parseInt(information.get(DIMENSION));
            String method_to_calc_distance=information.get(EDGE_WEIGHT_TYPE);
            switch (method_to_calc_distance) {
                case "EUC_2D": {
                    List<Point> input_points = new ArrayList<>();
                    for (int i = 0; i < number_of_nodes; i++) {
                        String line=reader.readLine();
                        int index = Integer.parseInt(line.split(" ")[0]);
                        double x_coord = Double.parseDouble(line.split(" ")[1]);
                        double y_coord = Double.parseDouble(line.split(" ")[2]);
                        input_points.add(new Point(x_coord, y_coord, index));
                    }
                    input = new TSPInput(input_points, method_to_calc_distance);
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
        } catch (Exception ex) {
            Logger.getLogger(TSPReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return input;
    }
}
