package gui;

import algorithms.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import main.AlgorithmDriver;
import main.Main;
import model.TSPInput;
import utils.TSPGenerator;
import utils.TSPReader;

/**
 *
 * @author aliha
 */

public class MainFrame extends javax.swing.JFrame {

	private class AlgorithmThread extends Thread {
		private long startTime = 0;
		private MainFrame frame;

		public AlgorithmThread(MainFrame frame) {
			this.frame = frame;
		}

		public void clearGraph() {
			frame.getGraph().setNewTour(null);
		}

		@Override
		public void run() {
			AlgorithmDriver driver = null;
			switch (algorithmName) {
			case "Brute Force": {
				driver = new AlgorithmDriver(new BruteForceTSP(), this.frame);
				break;
			}
			case "Branch and Bound": {
				driver = new AlgorithmDriver(new BranchAndBoundTSP(), this.frame);
				break;
			}
			case "Cutting and Removing Edges": {
				// driver = new AlgorithmDriver(new CuttingAndRemovingEdgesTSP(), this.frame);
				break;
			}
			case "Greedy": {
				driver = new AlgorithmDriver(new GreedyTSP(), this.frame);
				break;
			}
			case "Dynamic Programming": {
				driver = new AlgorithmDriver(new DynamicProgrammingTSP(), this.frame);
				break;
			}
			case "Minimum Spanning Tree": {
				driver = new AlgorithmDriver(new MinimumSpanningTreeTSP(), this.frame);
				break;
			}
			case "Randomized": {
				driver = new AlgorithmDriver(new RandomTSP(), this.frame);
				break;
			}
			case "Genetic Programming": {
				if (text_pop_size.getText().equals("") || text_iteration_genetic.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill the parameters", "Parameters Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					driver = new AlgorithmDriver(
							new GeneticProgrammingTSP(Integer.parseInt(text_iteration_genetic.getText().trim()),
									crossOverType, Double.parseDouble(label_mutatio_rate.getText().trim()),
									Integer.parseInt(text_pop_size.getText().trim())),
							this.frame);
					break;
				}
			}
			case "Ant Colony": {
				if (text_ants_number.getText().equals("") || text_max_iter.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill the parameters", "Parameters Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					driver = new AlgorithmDriver(new AntColonyTSP(Integer.parseInt(text_max_iter.getText().trim()),
							Integer.valueOf(text_ants_number.getText().trim())), this.frame);
					break;
				}
			}
			}
			if (inputFileName.equals("")) {
				JOptionPane.showMessageDialog(null, "Please choose a file", "Input Alert", JOptionPane.ERROR_MESSAGE);
			} else {

				TSPReader reader = new TSPReader(inputFileName);
				TSPInput input = reader.read();
				driver.executeStrategy(input);
			}

		}

	}
        private class GeneratorThread extends Thread{

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            if(text_generator_file_name.getText().equals("") || 
               text_generator_nb_cities.getText().equals("") ||
               minimumDistance_Gen.getText().equals("") ||
               maximumDisatnceGen.getText().equals("") ||
               levelOfSparsityGen.getText().equals("") )
            {
		JOptionPane.showMessageDialog(null, "Please fill the parameters", "Parameters Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                boolean symmetric = false;
                if(radio_asymmetric.isSelected()){
                    symmetric = false;
                }
                else if(radio_symmetric.isSelected()){
                    symmetric = true;
                }
                String fileName = text_generator_file_name.getText();
                int numberOfCities = Integer.parseInt(text_generator_nb_cities.getText().trim());
                int min = Integer.parseInt(minimumDistance_Gen.getText().trim());
                int max = Integer.parseInt(maximumDisatnceGen.getText().trim());
                double level = Double.parseDouble(levelOfSparsityGen.getText().trim());
                String dist = (String)comboTypeOfDistribution.getSelectedItem();
                TSPGenerator generator = new TSPGenerator(fileName, numberOfCities, symmetric, min, max,dist, level);
                generator.generate();
                text_area_output.setText(generator.getResultForUI());
            }
        }
            
        }
	/**
	 * Creates new form MainFrame
	 */
	public String inputFileName = "hm10.tsp";
	public String algorithmName = "";

	public MainFrame() {

		initComponents();
		setSize(1200, 1000);
               
		initGeneticControls();
		initialAlgo();
	}

	public void initGeneticControls() {

	}

	public JButton getStartButton() {
		return Start_Algo_btn;
	}

	public JLabel getTourCostLabel() {
		return tour_cost_label;
	}

	public void initialAlgo() {
		file_name_label.setText(inputFileName);
		algorithmName = alogrithms_combo_box.getItemAt(0);
		panelAntColony.setVisible(false);
		panelGenetic.setVisible(false);
                buttonGroup1.add(radio_asymmetric);
                buttonGroup1.add(radio_symmetric);
	}

	public static boolean first = true;

	public TSPGraph getGraph() {
		return panelGraph;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Reset_Graph_Btn = new javax.swing.JButton();
        Start_Algo_btn = new javax.swing.JButton();
        Stop_Algo_btn = new javax.swing.JButton();
        panelAntColony = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        text_ants_number = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        text_max_iter = new javax.swing.JTextField();
        panelGenetic = new javax.swing.JPanel();
        text_pop_size = new javax.swing.JTextField();
        mutatio_rate_slider = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        combo_cross_over_type = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        label_mutatio_rate = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        text_iteration_genetic = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        alogrithms_combo_box = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        browse_input_btn = new javax.swing.JButton();
        file_name_label = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        running_time_label = new javax.swing.JLabel();
        tour_cost_label = new javax.swing.JLabel();
        panelGraph = new gui.TSPGraph();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        text_generator_file_name = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        text_generator_nb_cities = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        text_area_output = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        radio_symmetric = new javax.swing.JRadioButton();
        radio_asymmetric = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        minimumDistance_Gen = new javax.swing.JTextField();
        maximumDisatnceGen = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        comboTypeOfDistribution = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        levelOfSparsityGen = new javax.swing.JTextField();
        buttonGenerate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TSP Project 2018-2019");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Choose the algorithm:");

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        Reset_Graph_Btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Reset_Graph_Btn.setText("Reset Graph");
        Reset_Graph_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reset_Graph_BtnActionPerformed(evt);
            }
        });

        Start_Algo_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Start_Algo_btn.setText("start");
        Start_Algo_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Start_Algo_btnActionPerformed(evt);
            }
        });

        Stop_Algo_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Stop_Algo_btn.setText("Stop");
        Stop_Algo_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stop_Algo_btnActionPerformed(evt);
            }
        });

        jLabel11.setText("Ants Number");

        jLabel12.setText("Iterations Number");

        javax.swing.GroupLayout panelAntColonyLayout = new javax.swing.GroupLayout(panelAntColony);
        panelAntColony.setLayout(panelAntColonyLayout);
        panelAntColonyLayout.setHorizontalGroup(
            panelAntColonyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAntColonyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAntColonyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAntColonyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_ants_number)
                    .addComponent(text_max_iter))
                .addContainerGap())
        );
        panelAntColonyLayout.setVerticalGroup(
            panelAntColonyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAntColonyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelAntColonyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(text_ants_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAntColonyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(text_max_iter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        mutatio_rate_slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mutatio_rate_sliderStateChanged(evt);
            }
        });

        jLabel6.setText("Mutation Rate");

        jLabel9.setText("Populatio size");

        combo_cross_over_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "OX", "PMX", "NWOX" }));
        combo_cross_over_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_cross_over_typeActionPerformed(evt);
            }
        });

        jLabel10.setText("Cross Over Type");

        label_mutatio_rate.setForeground(new java.awt.Color(0, 102, 51));
        label_mutatio_rate.setText("0.50");

        jLabel13.setText("Iteration size");

        javax.swing.GroupLayout panelGeneticLayout = new javax.swing.GroupLayout(panelGenetic);
        panelGenetic.setLayout(panelGeneticLayout);
        panelGeneticLayout.setHorizontalGroup(
            panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneticLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_cross_over_type, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelGeneticLayout.createSequentialGroup()
                        .addGroup(panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_pop_size)
                            .addComponent(text_iteration_genetic)))
                    .addGroup(panelGeneticLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_mutatio_rate))
                    .addGroup(panelGeneticLayout.createSequentialGroup()
                        .addGroup(panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(mutatio_rate_slider, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelGeneticLayout.setVerticalGroup(
            panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneticLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_pop_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_iteration_genetic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(panelGeneticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(label_mutatio_rate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mutatio_rate_slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_cross_over_type, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAntColony, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Reset_Graph_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGenetic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Start_Algo_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Stop_Algo_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(Reset_Graph_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAntColony, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGenetic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(Start_Algo_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(451, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(85, 85, 85)
                    .addComponent(Stop_Algo_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(389, Short.MAX_VALUE)))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Controls");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Running Time:");

        alogrithms_combo_box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        alogrithms_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brute Force", "Branch and Bound", "Cutting and Removing Edges", "Dynamic Programming", "Minimum Spanning Tree", "Greedy", "Randomized", "Genetic Programming", "Ant Colony" }));
        alogrithms_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alogrithms_combo_boxActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Choose Input :");

        browse_input_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        browse_input_btn.setText("Browse...");
        browse_input_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        browse_input_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browse_input_btnActionPerformed(evt);
            }
        });

        file_name_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        file_name_label.setText("You didn't choose a file");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Tour Visualization");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Tour Cost:");

        running_time_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        running_time_label.setText("0");

        tour_cost_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tour_cost_label.setText("0");

        javax.swing.GroupLayout panelGraphLayout = new javax.swing.GroupLayout(panelGraph);
        panelGraph.setLayout(panelGraphLayout);
        panelGraphLayout.setHorizontalGroup(
            panelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraphLayout.setVerticalGroup(
            panelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(running_time_label)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tour_cost_label)
                        .addGap(113, 113, 113))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(browse_input_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alogrithms_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(file_name_label)
                        .addGap(99, 99, 99))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(83, 83, 83))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(alogrithms_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(browse_input_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(file_name_label))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(running_time_label)
                                    .addComponent(tour_cost_label))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addComponent(panelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Algorithms", jPanel2);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("TSP Graph Generator");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Fill these Paramaters");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("File name");

        jLabel17.setText("Number of Cities");

        text_area_output.setColumns(20);
        text_area_output.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18)); // NOI18N
        text_area_output.setRows(5);
        jScrollPane1.setViewportView(text_area_output);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setText("Generated Output");

        radio_symmetric.setText("Symmetirc");

        radio_asymmetric.setText("Asymmetric");

        jLabel19.setText("Minimum Distance");

        jLabel20.setText("Maximum Distance");

        jLabel21.setText("Distribution Type ");

        comboTypeOfDistribution.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UNIFORM_DISTRIBUTION", "NORMAL_DISTRIBUTION" }));

        jLabel22.setText("Level of Sparsity");

        buttonGenerate.setText("Generate");
        buttonGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(levelOfSparsityGen))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(text_generator_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(text_generator_nb_cities))))
                            .addComponent(radio_symmetric)
                            .addComponent(jSeparator1)
                            .addComponent(radio_asymmetric, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(minimumDistance_Gen)
                                    .addComponent(maximumDisatnceGen)))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTypeOfDistribution, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(buttonGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel15)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(text_generator_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(text_generator_nb_cities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_symmetric)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_asymmetric)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(minimumDistance_Gen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(maximumDisatnceGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(levelOfSparsityGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(comboTypeOfDistribution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(buttonGenerate)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Generator", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Algorithms");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformed
        // TODO add your handling code here:
        GeneratorThread thread = new GeneratorThread();
        thread.start();
    }//GEN-LAST:event_buttonGenerateActionPerformed

	private String crossOverType = "Normal";

	private void combo_cross_over_typeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_combo_cross_over_typeActionPerformed
		crossOverType = (String) combo_cross_over_type.getSelectedItem();
	}// GEN-LAST:event_combo_cross_over_typeActionPerformed

	private void Reset_Graph_BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Reset_Graph_BtnActionPerformed
		algorithmThread.clearGraph();
	}// GEN-LAST:event_Reset_Graph_BtnActionPerformed

	private void alogrithms_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_alogrithms_combo_boxActionPerformed
		// TODO add your handling code here
		algorithmName = (String) alogrithms_combo_box.getSelectedItem();
		if (algorithmName.equals("Ant Colony")) {
			panelGenetic.setVisible(false);
			panelAntColony.setVisible(true);
		} else if (algorithmName.equals("Genetic Programming")) {
			panelGenetic.setVisible(true);
			panelAntColony.setVisible(false);
		} else {
			panelAntColony.setVisible(false);
			panelGenetic.setVisible(false);
		}
	}// GEN-LAST:event_alogrithms_combo_boxActionPerformed

	private void Stop_Algo_btnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Stop_Algo_btnActionPerformed
		algorithmThread.stop();
	}// GEN-LAST:event_Stop_Algo_btnActionPerformed

	private void browse_input_btnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_browse_input_btnActionPerformed
		JFileChooser chooser = new JFileChooser();

		File workingDirectory = new File(System.getProperty("user.dir"));
		chooser.setCurrentDirectory(workingDirectory);
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION) {
			file_name_label.setText("You didn't choose a file");
			inputFileName = "";
		} else if (result == JFileChooser.APPROVE_OPTION) {
			file_name_label.setText(chooser.getSelectedFile().getName());
			inputFileName = chooser.getSelectedFile().getName();
		}
	}// GEN-LAST:event_browse_input_btnActionPerformed

	public static int i = 0;
	AlgorithmThread algorithmThread;

	private void Start_Algo_btnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Start_Algo_btnActionPerformed
		algorithmThread = new AlgorithmThread(this);
		algorithmThread.start();

	}// GEN-LAST:event_Start_Algo_btnActionPerformed

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField1ActionPerformed

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox1ActionPerformed

	private void mutatio_rate_sliderStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_mutatio_rate_sliderStateChanged
		double value = mutatio_rate_slider.getValue() / 100.0;
		label_mutatio_rate.setText(value + "");
	}// GEN-LAST:event_mutatio_rate_sliderStateChanged

	/**
	 * @param args the command line arguments
	 */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reset_Graph_Btn;
    private javax.swing.JButton Start_Algo_btn;
    private javax.swing.JButton Stop_Algo_btn;
    private javax.swing.JComboBox<String> alogrithms_combo_box;
    private javax.swing.JButton browse_input_btn;
    private javax.swing.JButton buttonGenerate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboTypeOfDistribution;
    private javax.swing.JComboBox<String> combo_cross_over_type;
    private javax.swing.JLabel file_name_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label_mutatio_rate;
    private javax.swing.JTextField levelOfSparsityGen;
    private javax.swing.JTextField maximumDisatnceGen;
    private javax.swing.JTextField minimumDistance_Gen;
    private javax.swing.JSlider mutatio_rate_slider;
    private javax.swing.JPanel panelAntColony;
    private javax.swing.JPanel panelGenetic;
    private gui.TSPGraph panelGraph;
    private javax.swing.JRadioButton radio_asymmetric;
    private javax.swing.JRadioButton radio_symmetric;
    private javax.swing.JLabel running_time_label;
    private javax.swing.JTextField text_ants_number;
    private javax.swing.JTextArea text_area_output;
    private javax.swing.JTextField text_generator_file_name;
    private javax.swing.JTextField text_generator_nb_cities;
    private javax.swing.JTextField text_iteration_genetic;
    private javax.swing.JTextField text_max_iter;
    private javax.swing.JTextField text_pop_size;
    private javax.swing.JLabel tour_cost_label;
    // End of variables declaration//GEN-END:variables
}
