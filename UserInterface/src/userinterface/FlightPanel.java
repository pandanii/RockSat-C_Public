package userinterface;

import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class FlightPanel extends javax.swing.JPanel
{

    private static final long serialVersionUID = 1L;
    public static final int LOAD_RAW = 0;
    public static final int LOAD_FLIGHT = 1;

    private Calculator calculator;
    private File flightFile;

// Java FX Stuff for Velocity
    private JFXPanel velocityJFXPanel;
    private LineChart<Number, Number> velocityLineChart;
    private XYChart.Series velocitySeries;
    private Scene velocityScene;
    private File velocityXFile;
    private File velocityYFile;
    private File velocityZFile;

    // Java FX Stuff for Accel
    private JFXPanel accelJFXPanel;
    private LineChart<Number, Number> accelLineChart;
    private XYChart.Series accelSeries;
    private Scene accelScene;
    private File accelXFile;
    private File accelYFile;
    private File accelZFile;

    // Java FX Stuff for Accel
    private JFXPanel gyroJFXPanel;
    private LineChart<Number, Number> gyroLineChart;
    private XYChart.Series gyroSeries;
    private Scene gyroScene;
    private File gyroXFile;
    private File gyroYFile;
    private File gyroZFile;

    // Java FX Stuff for Displacement
    private JFXPanel dispJFXPanel;
    private LineChart<Number, Number> dispLineChart;
    private XYChart.Series dispSeries;
    private Scene dispScene;
    private File dispXFile;
    private File dispYFile;
    private File dispZFile;

    /**
     * Creates new form FlightPanel (Taking NO ARGUMENTS)
     *
     * This default constructor is for simply showing off the gui design, others will have more detail.
     */
    @SuppressWarnings("unchecked")
    public FlightPanel()
    {
        initComponents();

        // Setup Axis
        // NOTE a JFXPanel() must be setup to initialize the toolkit to stop an exception from occuring with NumberAxis initialization.
        velocityJFXPanel = new JFXPanel();
        final NumberAxis velocityXAxis = new NumberAxis();
        final NumberAxis velocityYAxis = new NumberAxis();
        velocityXAxis.setLabel("Time");
        velocityYAxis.setLabel("Y-Axis");
        velocityLineChart = new LineChart<>(velocityXAxis, velocityYAxis);
        velocitySeries = new XYChart.Series();
        velocityLineChart.setTitle("Velocity Chart");
        velocitySeries.setName("Data Point");
        velocitySeries.getData().add(new XYChart.Data(1, 10));
        velocitySeries.getData().add(new XYChart.Data(2, 8));
        velocitySeries.getData().add(new XYChart.Data(3, 5));
        velocitySeries.getData().add(new XYChart.Data(4, 24));
        velocitySeries.getData().add(new XYChart.Data(5, 3));
        velocitySeries.getData().add(new XYChart.Data(6, 2));
        velocitySeries.getData().add(new XYChart.Data(7, 22));
        velocitySeries.getData().add(new XYChart.Data(8, 100));
        velocitySeries.getData().add(new XYChart.Data(9, 8));
        velocitySeries.getData().add(new XYChart.Data(10, 2));
        velocitySeries.getData().add(new XYChart.Data(11, 29));
        velocitySeries.getData().add(new XYChart.Data(12, 275));
        velocityScene = new Scene(velocityLineChart, 800, 600);
        velocityLineChart.getData().add(velocitySeries);
        velocityJFXPanel.setScene(velocityScene);
        velocityPanel.add(velocityJFXPanel);

        // Setup Axis
        accelJFXPanel = new JFXPanel();
        final NumberAxis accelXAxis = new NumberAxis();
        final NumberAxis accelYAxis = new NumberAxis();
        accelXAxis.setLabel("Time");
        accelYAxis.setLabel("Y-Axis");
        accelLineChart = new LineChart<>(accelXAxis, accelYAxis);
        accelSeries = new XYChart.Series();
        accelLineChart.setTitle("Acceleration Chart");
        accelSeries.setName("Data Point");
        accelSeries.getData().add(new XYChart.Data(1, 23));
        accelSeries.getData().add(new XYChart.Data(2, 14));
        accelSeries.getData().add(new XYChart.Data(3, 15));
        accelSeries.getData().add(new XYChart.Data(4, 24));
        accelSeries.getData().add(new XYChart.Data(5, 34));
        accelSeries.getData().add(new XYChart.Data(6, 36));
        accelSeries.getData().add(new XYChart.Data(7, 22));
        accelSeries.getData().add(new XYChart.Data(8, 45));
        accelSeries.getData().add(new XYChart.Data(9, 43));
        accelSeries.getData().add(new XYChart.Data(10, 17));
        accelSeries.getData().add(new XYChart.Data(11, 29));
        accelSeries.getData().add(new XYChart.Data(12, 25));
        accelScene = new Scene(accelLineChart, 800, 600);
        accelLineChart.getData().add(accelSeries);
        accelJFXPanel.setScene(accelScene);
        accelPanel.add(accelJFXPanel);

        // Setup Axis
        gyroJFXPanel = new JFXPanel();
        final NumberAxis gyroXAxis = new NumberAxis();
        final NumberAxis gyroYAxis = new NumberAxis();
        gyroXAxis.setLabel("Time");
        gyroYAxis.setLabel("Y-Axis");
        gyroLineChart = new LineChart<>(gyroXAxis, gyroYAxis);
        gyroSeries = new XYChart.Series();
        gyroLineChart.setTitle("Gyroscope Chart");
        gyroSeries.setName("Data Point");
        gyroSeries.getData().add(new XYChart.Data(1, 1));
        gyroSeries.getData().add(new XYChart.Data(2, 2));
        gyroSeries.getData().add(new XYChart.Data(3, 3));
        gyroSeries.getData().add(new XYChart.Data(4, 4));
        gyroSeries.getData().add(new XYChart.Data(5, 5));
        gyroSeries.getData().add(new XYChart.Data(6, 7));
        gyroSeries.getData().add(new XYChart.Data(7, 8));
        gyroSeries.getData().add(new XYChart.Data(8, 0));
        gyroSeries.getData().add(new XYChart.Data(9, 2));
        gyroSeries.getData().add(new XYChart.Data(10, 5));
        gyroSeries.getData().add(new XYChart.Data(11, 1));
        gyroSeries.getData().add(new XYChart.Data(12, 27));
        gyroScene = new Scene(gyroLineChart, 800, 600);
        gyroLineChart.getData().add(gyroSeries);
        gyroJFXPanel.setScene(gyroScene);
        gyroPanel.add(gyroJFXPanel);

        // Setup Axis
        dispJFXPanel = new JFXPanel();
        final NumberAxis distXAxis = new NumberAxis();
        final NumberAxis distYAxis = new NumberAxis();
        distXAxis.setLabel("Time");
        distYAxis.setLabel("Y-Axis");
        dispLineChart = new LineChart<>(distXAxis, distYAxis);
        dispSeries = new XYChart.Series();
        dispLineChart.setTitle("Displacement Chart");
        dispSeries.setName("Data Point");
        dispSeries.getData().add(new XYChart.Data(1, 1));
        dispSeries.getData().add(new XYChart.Data(2, 2));
        dispSeries.getData().add(new XYChart.Data(3, 3));
        dispSeries.getData().add(new XYChart.Data(4, 4));
        dispSeries.getData().add(new XYChart.Data(5, 5));
        dispSeries.getData().add(new XYChart.Data(6, 7));
        dispSeries.getData().add(new XYChart.Data(7, 8));
        dispSeries.getData().add(new XYChart.Data(8, 0));
        dispSeries.getData().add(new XYChart.Data(9, 2));
        dispSeries.getData().add(new XYChart.Data(10, 5));
        dispSeries.getData().add(new XYChart.Data(11, 1));
        dispSeries.getData().add(new XYChart.Data(12, 27));
        dispScene = new Scene(dispLineChart, 800, 600);
        dispLineChart.getData().add(dispSeries);
        dispJFXPanel.setScene(dispScene);
        displacementPanel.add(dispJFXPanel);

    }

    /**
     * Creates new form FlightPanel
     * This is the actual constructor method, it will take in a file and output
     *
     * @param file
     *      ** 'file' is used to either load the raw flight data into the calculator or it is to open the already created files.
     *      ** This all depends on the value of 'loadValue'
     * @param loadValue
     *      ** 'loadValue' is used to differentiate between the different types of loading performed.
     */
    @SuppressWarnings("unchecked")
    public FlightPanel(File file, int loadValue)
    {
        initComponents();
        if (loadValue == LOAD_RAW)
        {
            String tempFileName = file.toString().substring(0, file.toString().indexOf("."));
            System.out.println("fileName after substring: " + tempFileName);
            //flightFile = new File();

            calculator = new Calculator(file.toString());

            //TODO: MOVE THESE
            //      THESES SHOULD BE MOVED, BUT IM LEAVING THEM HERE FOR NOW!!!!!
            //      NEED TO FIGURE OUT WHAT TO DO ABOUT THE ISSUE WITH READING THEM IN.
            accelXFile = new File(tempFileName + "_TxA_x.dat");
            accelYFile = new File(tempFileName + "_TxA_y.dat");
            accelZFile = new File(tempFileName + "_TxA_z.dat");
            velocityXFile = new File(tempFileName + "_TxV_x.dat");
            velocityYFile = new File(tempFileName + "_TxV_y.dat");
            velocityZFile = new File(tempFileName + "_TxV_z.dat");
            dispXFile = new File(tempFileName + "_TxD_x.dat");
            dispYFile = new File(tempFileName + "_TxD_y.dat");
            dispZFile = new File(tempFileName + "_TxD_z.dat");
            gyroXFile = new File(tempFileName + "_TxGyro_x.dat");
            gyroYFile = new File(tempFileName + "_TxGyro_y.dat");
            gyroZFile = new File(tempFileName + "_TxGyro_z.dat");
        }
        else
        {
            // HERE we will load the files instead of creating new each time.
        }

        // Setup Axis
        // NOTE a JFXPanel() must be setup to initialize the toolkit to stop an exception from occuring with NumberAxis initialization.
        velocityJFXPanel = new JFXPanel();
        final NumberAxis velocityXAxis = new NumberAxis();
        final NumberAxis velocityYAxis = new NumberAxis();
        velocityXAxis.setLabel("Time");
        velocityYAxis.setLabel("Y-Axis");
        velocityLineChart = new LineChart<>(velocityXAxis, velocityYAxis);
        velocitySeries = new XYChart.Series();
        velocityLineChart.setTitle("Velocity Chart");
        velocitySeries.setName("Data Point");
        velocityScene = new Scene(velocityLineChart, 800, 600);
        velocityLineChart.getData().add(velocitySeries);
        velocityJFXPanel.setScene(velocityScene);
        velocityPanel.add(velocityJFXPanel);

        // Setup Axis
        accelJFXPanel = new JFXPanel();
        final NumberAxis accelXAxis = new NumberAxis();
        final NumberAxis accelYAxis = new NumberAxis();
        accelXAxis.setLabel("Time");
        accelYAxis.setLabel("Y-Axis");
        accelLineChart = new LineChart<>(accelXAxis, accelYAxis);
        accelSeries = new XYChart.Series();
        accelLineChart.setTitle("Acceleration Chart");
        accelSeries.setName("Data Point");
        accelScene = new Scene(accelLineChart, 800, 600);
        accelLineChart.getData().add(accelSeries);
        accelJFXPanel.setScene(accelScene);
        accelPanel.add(accelJFXPanel);

        // Setup Axis
        gyroJFXPanel = new JFXPanel();
        final NumberAxis gyroXAxis = new NumberAxis();
        final NumberAxis gyroYAxis = new NumberAxis();
        gyroXAxis.setLabel("Time");
        gyroYAxis.setLabel("Y-Axis");
        gyroLineChart = new LineChart<>(gyroXAxis, gyroYAxis);
        gyroSeries = new XYChart.Series();
        gyroLineChart.setTitle("Gyroscope Chart");
        gyroSeries.setName("Data Point");
        gyroScene = new Scene(gyroLineChart, 800, 600);
        gyroLineChart.getData().add(gyroSeries);
        gyroJFXPanel.setScene(gyroScene);
        gyroPanel.add(gyroJFXPanel);

        // Setup Axis
        dispJFXPanel = new JFXPanel();
        final NumberAxis distXAxis = new NumberAxis();
        final NumberAxis distYAxis = new NumberAxis();
        distXAxis.setLabel("Time");
        distYAxis.setLabel("Y-Axis");
        dispLineChart = new LineChart<>(distXAxis, distYAxis);
        dispSeries = new XYChart.Series();
        dispLineChart.setTitle("Displacement Chart");
        dispSeries.setName("Data Point");
        dispScene = new Scene(dispLineChart, 800, 600);
        dispLineChart.getData().add(dispSeries);
        dispJFXPanel.setScene(dispScene);
        displacementPanel.add(dispJFXPanel);

    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        velocitySplitPlane = new javax.swing.JSplitPane();
        velocityPanel = new javax.swing.JPanel();
        velocityOptionsPanel = new javax.swing.JPanel();
        velXToggleButton = new javax.swing.JToggleButton();
        velYToggleButton = new javax.swing.JToggleButton();
        velZToggleButton = new javax.swing.JToggleButton();
        AccelerationSplitPane = new javax.swing.JSplitPane();
        accelPanel = new javax.swing.JPanel();
        accelOptionPanel = new javax.swing.JPanel();
        accelXToggleButton = new javax.swing.JToggleButton();
        accelYToggleButton = new javax.swing.JToggleButton();
        accelZToggleButton = new javax.swing.JToggleButton();
        displacementSplitPane = new javax.swing.JSplitPane();
        displacementPanel = new javax.swing.JPanel();
        displacementOptionPanel = new javax.swing.JPanel();
        dispToggleButton = new javax.swing.JToggleButton();
        dispYToggleButton = new javax.swing.JToggleButton();
        dispZToggleButton = new javax.swing.JToggleButton();
        gyroscopeSplitPane = new javax.swing.JSplitPane();
        gyroPanel = new javax.swing.JPanel();
        gyroAccelPanel = new javax.swing.JPanel();
        gyroXToggleButton = new javax.swing.JToggleButton();
        gyroYToggleButton = new javax.swing.JToggleButton();
        gyroZToggleButton = new javax.swing.JToggleButton();

        velocityPanel.setLayout(new java.awt.BorderLayout());
        velocitySplitPlane.setRightComponent(velocityPanel);

        velXToggleButton.setText("Velocity X Axis");
        velXToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                velXToggleButtonActionPerformed(evt);
            }
        });

        velYToggleButton.setText("Velocity Y Axis");
        velYToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                velYToggleButtonActionPerformed(evt);
            }
        });

        velZToggleButton.setText("Velocity Z Axis");
        velZToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                velZToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout velocityOptionsPanelLayout = new javax.swing.GroupLayout(velocityOptionsPanel);
        velocityOptionsPanel.setLayout(velocityOptionsPanelLayout);
        velocityOptionsPanelLayout.setHorizontalGroup(
            velocityOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(velocityOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(velocityOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(velXToggleButton)
                    .addComponent(velYToggleButton)
                    .addComponent(velZToggleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        velocityOptionsPanelLayout.setVerticalGroup(
            velocityOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(velocityOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(velXToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(velYToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(velZToggleButton)
                .addContainerGap(502, Short.MAX_VALUE))
        );

        velocitySplitPlane.setLeftComponent(velocityOptionsPanel);

        jTabbedPane1.addTab("Velocity", velocitySplitPlane);

        accelPanel.setLayout(new java.awt.BorderLayout());
        AccelerationSplitPane.setRightComponent(accelPanel);

        accelXToggleButton.setText("Acceleration X Axis");
        accelXToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                accelXToggleButtonActionPerformed(evt);
            }
        });

        accelYToggleButton.setText("Acceleration Y Axis");
        accelYToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                accelYToggleButtonActionPerformed(evt);
            }
        });

        accelZToggleButton.setText("Acceleration Z Axis");
        accelZToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                accelZToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout accelOptionPanelLayout = new javax.swing.GroupLayout(accelOptionPanel);
        accelOptionPanel.setLayout(accelOptionPanelLayout);
        accelOptionPanelLayout.setHorizontalGroup(
            accelOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accelOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accelOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accelXToggleButton)
                    .addComponent(accelYToggleButton)
                    .addComponent(accelZToggleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        accelOptionPanelLayout.setVerticalGroup(
            accelOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accelOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accelXToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accelYToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accelZToggleButton)
                .addContainerGap(502, Short.MAX_VALUE))
        );

        AccelerationSplitPane.setLeftComponent(accelOptionPanel);

        jTabbedPane1.addTab("Acceleration", AccelerationSplitPane);

        displacementPanel.setLayout(new java.awt.BorderLayout());
        displacementSplitPane.setRightComponent(displacementPanel);

        dispToggleButton.setText("Displacement X Axis");
        dispToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dispToggleButtonActionPerformed(evt);
            }
        });

        dispYToggleButton.setText("Displacement Y Axis");
        dispYToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dispYToggleButtonActionPerformed(evt);
            }
        });

        dispZToggleButton.setText("Displacement Z Axis");
        dispZToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dispZToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displacementOptionPanelLayout = new javax.swing.GroupLayout(displacementOptionPanel);
        displacementOptionPanel.setLayout(displacementOptionPanelLayout);
        displacementOptionPanelLayout.setHorizontalGroup(
            displacementOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displacementOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displacementOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dispToggleButton)
                    .addComponent(dispYToggleButton)
                    .addComponent(dispZToggleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        displacementOptionPanelLayout.setVerticalGroup(
            displacementOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displacementOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dispToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dispYToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dispZToggleButton)
                .addContainerGap(502, Short.MAX_VALUE))
        );

        displacementSplitPane.setLeftComponent(displacementOptionPanel);

        jTabbedPane1.addTab("Displacement", displacementSplitPane);

        gyroPanel.setLayout(new java.awt.BorderLayout());
        gyroscopeSplitPane.setRightComponent(gyroPanel);

        gyroXToggleButton.setText("Gyroscope X Axis");
        gyroXToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                gyroXToggleButtonActionPerformed(evt);
            }
        });

        gyroYToggleButton.setText("Gyroscope Y Axis");
        gyroYToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                gyroYToggleButtonActionPerformed(evt);
            }
        });

        gyroZToggleButton.setText("Gyroscope Z Axis");
        gyroZToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                gyroZToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gyroAccelPanelLayout = new javax.swing.GroupLayout(gyroAccelPanel);
        gyroAccelPanel.setLayout(gyroAccelPanelLayout);
        gyroAccelPanelLayout.setHorizontalGroup(
            gyroAccelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gyroAccelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gyroAccelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gyroXToggleButton)
                    .addComponent(gyroYToggleButton)
                    .addComponent(gyroZToggleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gyroAccelPanelLayout.setVerticalGroup(
            gyroAccelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gyroAccelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gyroXToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gyroYToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gyroZToggleButton)
                .addContainerGap(502, Short.MAX_VALUE))
        );

        gyroscopeSplitPane.setLeftComponent(gyroAccelPanel);

        jTabbedPane1.addTab("GyroScope", gyroscopeSplitPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void accelYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelYToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accelYToggleButtonActionPerformed

    private void accelXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelXToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accelXToggleButtonActionPerformed

    private void gyroXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroXToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gyroXToggleButtonActionPerformed

    private void gyroYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroYToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gyroYToggleButtonActionPerformed

    private void velXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velXToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_velXToggleButtonActionPerformed

    private void velYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velYToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_velYToggleButtonActionPerformed

    private void dispToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dispToggleButtonActionPerformed

    private void dispYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispYToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dispYToggleButtonActionPerformed

    private void velZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velZToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_velZToggleButtonActionPerformed

    private void accelZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelZToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accelZToggleButtonActionPerformed

    private void dispZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispZToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dispZToggleButtonActionPerformed

    private void gyroZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroZToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gyroZToggleButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane AccelerationSplitPane;
    private javax.swing.JPanel accelOptionPanel;
    private javax.swing.JPanel accelPanel;
    private javax.swing.JToggleButton accelXToggleButton;
    private javax.swing.JToggleButton accelYToggleButton;
    private javax.swing.JToggleButton accelZToggleButton;
    private javax.swing.JToggleButton dispToggleButton;
    private javax.swing.JToggleButton dispYToggleButton;
    private javax.swing.JToggleButton dispZToggleButton;
    private javax.swing.JPanel displacementOptionPanel;
    private javax.swing.JPanel displacementPanel;
    private javax.swing.JSplitPane displacementSplitPane;
    private javax.swing.JPanel gyroAccelPanel;
    private javax.swing.JPanel gyroPanel;
    private javax.swing.JToggleButton gyroXToggleButton;
    private javax.swing.JToggleButton gyroYToggleButton;
    private javax.swing.JToggleButton gyroZToggleButton;
    private javax.swing.JSplitPane gyroscopeSplitPane;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton velXToggleButton;
    private javax.swing.JToggleButton velYToggleButton;
    private javax.swing.JToggleButton velZToggleButton;
    private javax.swing.JPanel velocityOptionsPanel;
    private javax.swing.JPanel velocityPanel;
    private javax.swing.JSplitPane velocitySplitPlane;
    // End of variables declaration//GEN-END:variables
}
