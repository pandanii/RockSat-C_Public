/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    //------------------------------------------------
    //JZY3dStuff
    //public Chart2d velocityChart2d;
    //public Chart2d accelerationChart2d;
    //public Chart2d gyroChart2d;
    //------------------------------------------------

    // Java FX Stuff for Velocity
    public JFXPanel velocityJFXPanel;
    private LineChart<Number, Number> velocityLineChart;
    private XYChart.Series velocitySeries;
    private Scene velocityScene;

    // Java FX Stuff for Accel
    private JFXPanel accelJFXPanel;
    private LineChart<Number, Number> accelLineChart;
    private XYChart.Series accelSeries;
    private Scene accelScene;

    // Java FX Stuff for Accel
    private JFXPanel gyroJFXPanel;
    private LineChart<Number, Number> gyroLineChart;
    private XYChart.Series gyroSeries;
    private Scene gyroScene;

    // Java FX Stuff for Distance
    private JFXPanel distJFXPanel;
    private LineChart<Number, Number> distLineChart;
    private XYChart.Series distSeries;
    private Scene distScene;

    /**
     * Creates new form FlightPanel (Taking NO ARGUMENTS)
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
        velocityXAxis.setLabel("X-Axis");
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
        accelXAxis.setLabel("X-Axis");
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
        gyroXAxis.setLabel("X-Axis");
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
        distJFXPanel = new JFXPanel();
        final NumberAxis distXAxis = new NumberAxis();
        final NumberAxis distYAxis = new NumberAxis();
        distXAxis.setLabel("X-Axis");
        distYAxis.setLabel("Y-Axis");
        distLineChart = new LineChart<>(distXAxis, distYAxis);
        distSeries = new XYChart.Series();
        distLineChart.setTitle("Distance Chart");
        distSeries.setName("Data Point");
        distSeries.getData().add(new XYChart.Data(1, 1));
        distSeries.getData().add(new XYChart.Data(2, 2));
        distSeries.getData().add(new XYChart.Data(3, 3));
        distSeries.getData().add(new XYChart.Data(4, 4));
        distSeries.getData().add(new XYChart.Data(5, 5));
        distSeries.getData().add(new XYChart.Data(6, 7));
        distSeries.getData().add(new XYChart.Data(7, 8));
        distSeries.getData().add(new XYChart.Data(8, 0));
        distSeries.getData().add(new XYChart.Data(9, 2));
        distSeries.getData().add(new XYChart.Data(10, 5));
        distSeries.getData().add(new XYChart.Data(11, 1));
        distSeries.getData().add(new XYChart.Data(12, 27));
        distScene = new Scene(distLineChart, 800, 600);
        distLineChart.getData().add(distSeries);
        distJFXPanel.setScene(distScene);
        distancePanel.add(distJFXPanel);

    }

    /**
     * Creates new form FlightPanel
     *
     * @param file
     */
    @SuppressWarnings("unchecked")
    public FlightPanel(File file)
    {
        initComponents();

        // Setup Axis
        // NOTE a JFXPanel() must be setup to initialize the toolkit to stop an exception from occuring with NumberAxis initialization.
        velocityJFXPanel = new JFXPanel();
        final NumberAxis velocityXAxis = new NumberAxis();
        final NumberAxis velocityYAxis = new NumberAxis();
        velocityXAxis.setLabel("X-Axis");
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
        accelXAxis.setLabel("X-Axis");
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
        gyroXAxis.setLabel("X-Axis");
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
        distJFXPanel = new JFXPanel();
        final NumberAxis distXAxis = new NumberAxis();
        final NumberAxis distYAxis = new NumberAxis();
        distXAxis.setLabel("X-Axis");
        distYAxis.setLabel("Y-Axis");
        distLineChart = new LineChart<>(distXAxis, distYAxis);
        distSeries = new XYChart.Series();
        distLineChart.setTitle("Distance Chart");
        distSeries.setName("Data Point");
        distScene = new Scene(distLineChart, 800, 600);
        distLineChart.getData().add(distSeries);
        distJFXPanel.setScene(distScene);
        distancePanel.add(distJFXPanel);

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        AccelerationSplitPane = new javax.swing.JSplitPane();
        accelPanel = new javax.swing.JPanel();
        accelOptionPanel = new javax.swing.JPanel();
        accelXAxisButton = new javax.swing.JRadioButton();
        accelYAxisButton = new javax.swing.JRadioButton();
        accelZAxisButton = new javax.swing.JRadioButton();
        gyroscopeSplitPane = new javax.swing.JSplitPane();
        gyroPanel = new javax.swing.JPanel();
        gyroAccelPanel = new javax.swing.JPanel();
        gyroXAxis = new javax.swing.JRadioButton();
        gyroYAxis = new javax.swing.JRadioButton();
        gyroZAxis = new javax.swing.JRadioButton();
        velocitySplitPlane = new javax.swing.JSplitPane();
        velocityPanel = new javax.swing.JPanel();
        velocityOptionsPanel = new javax.swing.JPanel();
        velocityXAxisButton = new javax.swing.JRadioButton();
        velocityYAxisButton = new javax.swing.JRadioButton();
        velocityZAxisButton = new javax.swing.JRadioButton();
        distanceSplitPane = new javax.swing.JSplitPane();
        distancePanel = new javax.swing.JPanel();
        distanceOptionPanel = new javax.swing.JPanel();
        distXAxisButton = new javax.swing.JRadioButton();
        distYAxisButton = new javax.swing.JRadioButton();
        distZAxisButton = new javax.swing.JRadioButton();

        accelPanel.setLayout(new java.awt.BorderLayout());
        AccelerationSplitPane.setRightComponent(accelPanel);

        accelXAxisButton.setText("Acceleration X Axis");
        accelXAxisButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                accelXAxisButtonActionPerformed(evt);
            }
        });

        accelYAxisButton.setText("Acceleration Y Axis");
        accelYAxisButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                accelYAxisButtonActionPerformed(evt);
            }
        });

        accelZAxisButton.setText("Acceleration Z Axis");
        accelZAxisButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                accelZAxisButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout accelOptionPanelLayout = new javax.swing.GroupLayout(accelOptionPanel);
        accelOptionPanel.setLayout(accelOptionPanelLayout);
        accelOptionPanelLayout.setHorizontalGroup(
            accelOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accelOptionPanelLayout.createSequentialGroup()
                .addGroup(accelOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accelXAxisButton)
                    .addComponent(accelYAxisButton)
                    .addComponent(accelZAxisButton))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        accelOptionPanelLayout.setVerticalGroup(
            accelOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accelOptionPanelLayout.createSequentialGroup()
                .addComponent(accelXAxisButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accelYAxisButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accelZAxisButton)
                .addContainerGap(520, Short.MAX_VALUE))
        );

        AccelerationSplitPane.setLeftComponent(accelOptionPanel);

        jTabbedPane1.addTab("Acceleration", AccelerationSplitPane);

        gyroPanel.setLayout(new java.awt.BorderLayout());
        gyroscopeSplitPane.setRightComponent(gyroPanel);

        gyroXAxis.setText("Gyroscope X Axis");

        gyroYAxis.setText("Gyroscope Y Axis");

        gyroZAxis.setText("Gyroscope Z Axis");

        javax.swing.GroupLayout gyroAccelPanelLayout = new javax.swing.GroupLayout(gyroAccelPanel);
        gyroAccelPanel.setLayout(gyroAccelPanelLayout);
        gyroAccelPanelLayout.setHorizontalGroup(
            gyroAccelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gyroAccelPanelLayout.createSequentialGroup()
                .addGroup(gyroAccelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gyroXAxis)
                    .addComponent(gyroYAxis)
                    .addComponent(gyroZAxis))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        gyroAccelPanelLayout.setVerticalGroup(
            gyroAccelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gyroAccelPanelLayout.createSequentialGroup()
                .addComponent(gyroXAxis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gyroYAxis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gyroZAxis)
                .addContainerGap(520, Short.MAX_VALUE))
        );

        gyroscopeSplitPane.setLeftComponent(gyroAccelPanel);

        jTabbedPane1.addTab("GyroScope", gyroscopeSplitPane);

        velocityPanel.setLayout(new java.awt.BorderLayout());
        velocitySplitPlane.setRightComponent(velocityPanel);

        velocityXAxisButton.setText("Velocity X Axis");
        velocityXAxisButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                velocityXAxisButtonActionPerformed(evt);
            }
        });

        velocityYAxisButton.setText("Velocity Y Axis");
        velocityYAxisButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                velocityYAxisButtonActionPerformed(evt);
            }
        });

        velocityZAxisButton.setText("Velocity Z Axis");

        javax.swing.GroupLayout velocityOptionsPanelLayout = new javax.swing.GroupLayout(velocityOptionsPanel);
        velocityOptionsPanel.setLayout(velocityOptionsPanelLayout);
        velocityOptionsPanelLayout.setHorizontalGroup(
            velocityOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(velocityOptionsPanelLayout.createSequentialGroup()
                .addGroup(velocityOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(velocityXAxisButton)
                    .addComponent(velocityYAxisButton)
                    .addComponent(velocityZAxisButton))
                .addGap(0, 7, Short.MAX_VALUE))
        );
        velocityOptionsPanelLayout.setVerticalGroup(
            velocityOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(velocityOptionsPanelLayout.createSequentialGroup()
                .addComponent(velocityXAxisButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(velocityYAxisButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(velocityZAxisButton)
                .addContainerGap(520, Short.MAX_VALUE))
        );

        velocitySplitPlane.setLeftComponent(velocityOptionsPanel);

        jTabbedPane1.addTab("Velocity", velocitySplitPlane);

        distancePanel.setLayout(new java.awt.BorderLayout());
        distanceSplitPane.setRightComponent(distancePanel);

        distXAxisButton.setText("Distance X Axis");
        distXAxisButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                distXAxisButtonActionPerformed(evt);
            }
        });

        distYAxisButton.setText("Distance Y Axis");
        distYAxisButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                distYAxisButtonActionPerformed(evt);
            }
        });

        distZAxisButton.setText("Distance Z Axis");

        javax.swing.GroupLayout distanceOptionPanelLayout = new javax.swing.GroupLayout(distanceOptionPanel);
        distanceOptionPanel.setLayout(distanceOptionPanelLayout);
        distanceOptionPanelLayout.setHorizontalGroup(
            distanceOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(distanceOptionPanelLayout.createSequentialGroup()
                .addGroup(distanceOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(distXAxisButton)
                    .addComponent(distYAxisButton)
                    .addComponent(distZAxisButton))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        distanceOptionPanelLayout.setVerticalGroup(
            distanceOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(distanceOptionPanelLayout.createSequentialGroup()
                .addComponent(distXAxisButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distYAxisButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distZAxisButton)
                .addContainerGap(520, Short.MAX_VALUE))
        );

        distanceSplitPane.setLeftComponent(distanceOptionPanel);

        jTabbedPane1.addTab("Distance", distanceSplitPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1116, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void accelZAxisButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelZAxisButtonActionPerformed
    {//GEN-HEADEREND:event_accelZAxisButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accelZAxisButtonActionPerformed

    private void distXAxisButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_distXAxisButtonActionPerformed
    {//GEN-HEADEREND:event_distXAxisButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_distXAxisButtonActionPerformed

    private void distYAxisButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_distYAxisButtonActionPerformed
    {//GEN-HEADEREND:event_distYAxisButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_distYAxisButtonActionPerformed

    private void velocityXAxisButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velocityXAxisButtonActionPerformed
    {//GEN-HEADEREND:event_velocityXAxisButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_velocityXAxisButtonActionPerformed

    private void velocityYAxisButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velocityYAxisButtonActionPerformed
    {//GEN-HEADEREND:event_velocityYAxisButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_velocityYAxisButtonActionPerformed

    private void accelXAxisButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelXAxisButtonActionPerformed
    {//GEN-HEADEREND:event_accelXAxisButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accelXAxisButtonActionPerformed

    private void accelYAxisButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelYAxisButtonActionPerformed
    {//GEN-HEADEREND:event_accelYAxisButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accelYAxisButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane AccelerationSplitPane;
    private javax.swing.JPanel accelOptionPanel;
    private javax.swing.JPanel accelPanel;
    private javax.swing.JRadioButton accelXAxisButton;
    private javax.swing.JRadioButton accelYAxisButton;
    private javax.swing.JRadioButton accelZAxisButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton distXAxisButton;
    private javax.swing.JRadioButton distYAxisButton;
    private javax.swing.JRadioButton distZAxisButton;
    private javax.swing.JPanel distanceOptionPanel;
    private javax.swing.JPanel distancePanel;
    private javax.swing.JSplitPane distanceSplitPane;
    private javax.swing.JPanel gyroAccelPanel;
    private javax.swing.JPanel gyroPanel;
    private javax.swing.JRadioButton gyroXAxis;
    private javax.swing.JRadioButton gyroYAxis;
    private javax.swing.JRadioButton gyroZAxis;
    private javax.swing.JSplitPane gyroscopeSplitPane;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel velocityOptionsPanel;
    private javax.swing.JPanel velocityPanel;
    private javax.swing.JSplitPane velocitySplitPlane;
    private javax.swing.JRadioButton velocityXAxisButton;
    private javax.swing.JRadioButton velocityYAxisButton;
    private javax.swing.JRadioButton velocityZAxisButton;
    // End of variables declaration//GEN-END:variables
}
