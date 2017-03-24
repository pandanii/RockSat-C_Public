/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package userinterface;

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

    /**
     * Creates new form FlightPanel
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
        VelocityPanel.add(velocityJFXPanel);

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

    }

    /*public void addChart(Chart chart, int id)
    {
        JPanel chartPanel = new JPanel(new BorderLayout());
        Border b = BorderFactory.createLineBorder(java.awt.Color.black);
        chartPanel.setBorder(b);
        chartPanel.add((java.awt.Component) chart.getCanvas(), BorderLayout.CENTER);
        add(chartPanel, "cell 0 " + id + ", grow");
    }*/
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
        VelocityPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        AccelerationSplitPane = new javax.swing.JSplitPane();
        jLabel5 = new javax.swing.JLabel();
        accelPanel = new javax.swing.JPanel();
        gyroscopeSplitPane = new javax.swing.JSplitPane();
        jLabel1 = new javax.swing.JLabel();
        gyroPanel = new javax.swing.JPanel();

        VelocityPanel.setLayout(new java.awt.BorderLayout());
        velocitySplitPlane.setRightComponent(VelocityPanel);

        jLabel3.setText("Options go here");
        velocitySplitPlane.setLeftComponent(jLabel3);

        jTabbedPane1.addTab("Velocity", velocitySplitPlane);

        jLabel5.setText("Options go here");
        AccelerationSplitPane.setLeftComponent(jLabel5);

        accelPanel.setLayout(new java.awt.BorderLayout());
        AccelerationSplitPane.setRightComponent(accelPanel);

        jTabbedPane1.addTab("Acceleration", AccelerationSplitPane);

        jLabel1.setText("OPTIONS HERE");
        gyroscopeSplitPane.setLeftComponent(jLabel1);

        gyroPanel.setLayout(new java.awt.BorderLayout());
        gyroscopeSplitPane.setRightComponent(gyroPanel);

        jTabbedPane1.addTab("GyroScope", gyroscopeSplitPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane AccelerationSplitPane;
    private javax.swing.JPanel VelocityPanel;
    private javax.swing.JPanel accelPanel;
    private javax.swing.JPanel gyroPanel;
    private javax.swing.JSplitPane gyroscopeSplitPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSplitPane velocitySplitPlane;
    // End of variables declaration//GEN-END:variables
}
