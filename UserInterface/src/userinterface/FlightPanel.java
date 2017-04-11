package userinterface;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
    private XYChart.Series velocityXSeries;
    private XYChart.Series velocityYSeries;
    private XYChart.Series velocityZSeries;
    private Scene velocityScene;
    private File velocityXFile;
    private File velocityYFile;
    private File velocityZFile;

    // Java FX Stuff for Accel
    private JFXPanel accelJFXPanel;
    private LineChart<Number, Number> accelLineChart;
    private XYChart.Series accelXSeries;
    private XYChart.Series accelYSeries;
    private XYChart.Series accelZSeries;
    private Scene accelScene;
    private File accelXFile;
    private File accelYFile;
    private File accelZFile;

    // Java FX Stuff for Accel
    private JFXPanel gyroJFXPanel;
    private LineChart<Number, Number> gyroLineChart;
    private XYChart.Series gyroXSeries;
    private XYChart.Series gyroYSeries;
    private XYChart.Series gyroZSeries;
    private Scene gyroScene;
    private File gyroXFile;
    private File gyroYFile;
    private File gyroZFile;

    // Java FX Stuff for Displacement
    private JFXPanel dispJFXPanel;
    private LineChart<Number, Number> dispLineChart;
    private XYChart.Series dispXSeries;
    private XYChart.Series dispYSeries;
    private XYChart.Series dispZSeries;
    private Scene dispScene;
    private File dispXFile;
    private File dispYFile;
    private File dispZFile;

    /**
     * Creates new form FlightPanel (Taking NO ARGUMENTS)
     *
     * This default constructor is for simply showing off the gui design, others
     * will have more detail.
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
        velocityXSeries = new XYChart.Series();
        velocityLineChart.setTitle("Velocity Chart");
        velocityXSeries.setName("Data Point");
        velocityXSeries.getData().add(new XYChart.Data(1, 10));
        velocityXSeries.getData().add(new XYChart.Data(2, 8));
        velocityXSeries.getData().add(new XYChart.Data(3, 5));
        velocityXSeries.getData().add(new XYChart.Data(4, 24));
        velocityXSeries.getData().add(new XYChart.Data(5, 3));
        velocityXSeries.getData().add(new XYChart.Data(6, 2));
        velocityXSeries.getData().add(new XYChart.Data(7, 22));
        velocityXSeries.getData().add(new XYChart.Data(8, 100));
        velocityXSeries.getData().add(new XYChart.Data(9, 8));
        velocityXSeries.getData().add(new XYChart.Data(10, 2));
        velocityXSeries.getData().add(new XYChart.Data(11, 29));
        velocityXSeries.getData().add(new XYChart.Data(12, 275));
        velocityScene = new Scene(velocityLineChart, 800, 600);
        velocityLineChart.getData().add(velocityXSeries);
        velocityJFXPanel.setScene(velocityScene);
        velocityPanel.add(velocityJFXPanel);

        // Setup Axis
        accelJFXPanel = new JFXPanel();
        final NumberAxis accelXAxis = new NumberAxis();
        final NumberAxis accelYAxis = new NumberAxis();
        accelXAxis.setLabel("Time");
        accelYAxis.setLabel("Y-Axis");
        accelLineChart = new LineChart<>(accelXAxis, accelYAxis);
        accelXSeries = new XYChart.Series();
        accelLineChart.setTitle("Acceleration Chart");
        accelXSeries.setName("Data Point");
        accelXSeries.getData().add(new XYChart.Data(1, 23));
        accelXSeries.getData().add(new XYChart.Data(2, 14));
        accelXSeries.getData().add(new XYChart.Data(3, 15));
        accelXSeries.getData().add(new XYChart.Data(4, 24));
        accelXSeries.getData().add(new XYChart.Data(5, 34));
        accelXSeries.getData().add(new XYChart.Data(6, 36));
        accelXSeries.getData().add(new XYChart.Data(7, 22));
        accelXSeries.getData().add(new XYChart.Data(8, 45));
        accelXSeries.getData().add(new XYChart.Data(9, 43));
        accelXSeries.getData().add(new XYChart.Data(10, 17));
        accelXSeries.getData().add(new XYChart.Data(11, 29));
        accelXSeries.getData().add(new XYChart.Data(12, 25));
        accelScene = new Scene(accelLineChart, 800, 600);
        accelLineChart.getData().add(accelXSeries);
        accelJFXPanel.setScene(accelScene);
        accelPanel.add(accelJFXPanel);

        // Setup Axis
        gyroJFXPanel = new JFXPanel();
        final NumberAxis gyroXAxis = new NumberAxis();
        final NumberAxis gyroYAxis = new NumberAxis();
        gyroXAxis.setLabel("Time");
        gyroYAxis.setLabel("Y-Axis");
        gyroLineChart = new LineChart<>(gyroXAxis, gyroYAxis);
        gyroXSeries = new XYChart.Series();
        gyroLineChart.setTitle("Gyroscope Chart");
        gyroXSeries.setName("Data Point");
        gyroXSeries.getData().add(new XYChart.Data(1, 1));
        gyroXSeries.getData().add(new XYChart.Data(2, 2));
        gyroXSeries.getData().add(new XYChart.Data(3, 3));
        gyroXSeries.getData().add(new XYChart.Data(4, 4));
        gyroXSeries.getData().add(new XYChart.Data(5, 5));
        gyroXSeries.getData().add(new XYChart.Data(6, 7));
        gyroXSeries.getData().add(new XYChart.Data(7, 8));
        gyroXSeries.getData().add(new XYChart.Data(8, 0));
        gyroXSeries.getData().add(new XYChart.Data(9, 2));
        gyroXSeries.getData().add(new XYChart.Data(10, 5));
        gyroXSeries.getData().add(new XYChart.Data(11, 1));
        gyroXSeries.getData().add(new XYChart.Data(12, 27));
        gyroScene = new Scene(gyroLineChart, 800, 600);
        gyroLineChart.getData().add(gyroXSeries);
        gyroJFXPanel.setScene(gyroScene);
        gyroPanel.add(gyroJFXPanel);

        // Setup Axis
        dispJFXPanel = new JFXPanel();
        final NumberAxis distXAxis = new NumberAxis();
        final NumberAxis distYAxis = new NumberAxis();
        distXAxis.setLabel("Time");
        distYAxis.setLabel("Y-Axis");
        dispLineChart = new LineChart<>(distXAxis, distYAxis);
        dispXSeries = new XYChart.Series();
        dispLineChart.setTitle("Displacement Chart");
        dispXSeries.setName("Data Point");
        dispXSeries.getData().add(new XYChart.Data(1, 1));
        dispXSeries.getData().add(new XYChart.Data(2, 2));
        dispXSeries.getData().add(new XYChart.Data(3, 3));
        dispXSeries.getData().add(new XYChart.Data(4, 4));
        dispXSeries.getData().add(new XYChart.Data(5, 5));
        dispXSeries.getData().add(new XYChart.Data(6, 7));
        dispXSeries.getData().add(new XYChart.Data(7, 8));
        dispXSeries.getData().add(new XYChart.Data(8, 0));
        dispXSeries.getData().add(new XYChart.Data(9, 2));
        dispXSeries.getData().add(new XYChart.Data(10, 5));
        dispXSeries.getData().add(new XYChart.Data(11, 1));
        dispXSeries.getData().add(new XYChart.Data(12, 27));
        dispScene = new Scene(dispLineChart, 800, 600);
        dispLineChart.getData().add(dispXSeries);
        dispJFXPanel.setScene(dispScene);
        displacementPanel.add(dispJFXPanel);

    }

    /**
     * Creates new form FlightPanel
     * This is the actual constructor method, it will take in a file and output
     *
     * @param file
     *      ** 'file' is used to either load the raw flight data into the calculator
     * or it is to open the already created files.
     *      ** This all depends on the value of 'loadValue'
     * @param loadValue
     *      ** 'loadValue' is used to differentiate between the different types of
     * loading performed.
     */
    public FlightPanel(File file, int loadValue)
    {
        initComponents();
        if (loadValue == LOAD_RAW)
        {
            calculator = new Calculator(file.toString());
            saveFlight(file);
        }
        else if (loadValue == LOAD_FLIGHT)
        {
            loadFlight(file);
        }

        // Setup Axis
        // NOTE a JFXPanel() must be setup to initialize the toolkit to stop an exception from occuring with NumberAxis initialization.
        velocityJFXPanel = new JFXPanel();
        final NumberAxis velocityXAxis = new NumberAxis();
        final NumberAxis velocityYAxis = new NumberAxis();
        velocityXAxis.setLabel("Time");
        velocityYAxis.setLabel("Y-Axis");
        velocityLineChart = new LineChart<>(velocityXAxis, velocityYAxis);
        velocityLineChart.setTitle("Velocity Chart");
        velocityXSeries = new XYChart.Series();
        velocityXSeries.setName("Velocity X Axis");
        velocityLineChart.getData().add(velocityXSeries);
        velocityYSeries = new XYChart.Series();
        velocityYSeries.setName("Velocity Y Axis");
        velocityLineChart.getData().add(velocityYSeries);
        velocityZSeries = new XYChart.Series();
        velocityZSeries.setName("Velocity Z Axis");
        velocityLineChart.getData().add(velocityZSeries);
        velocityScene = new Scene(velocityLineChart, 800, 600);
        velocityJFXPanel.setScene(velocityScene);
        velocityPanel.add(velocityJFXPanel);

        // Setup Axis
        accelJFXPanel = new JFXPanel();
        final NumberAxis accelXAxis = new NumberAxis();
        final NumberAxis accelYAxis = new NumberAxis();
        accelXAxis.setLabel("Time");
        accelYAxis.setLabel("Y-Axis");
        accelLineChart = new LineChart<>(accelXAxis, accelYAxis);

        accelLineChart.setTitle("Acceleration Chart");
        accelScene = new Scene(accelLineChart, 800, 600);
        accelXSeries = new XYChart.Series();
        accelXSeries.setName("Acceleration X Axis");
        accelLineChart.getData().add(accelXSeries);
        accelYSeries = new XYChart.Series();
        accelYSeries.setName("Acceleration Y Axis");
        accelLineChart.getData().add(accelYSeries);
        accelZSeries = new XYChart.Series();
        accelZSeries.setName("Acceleration Z Axis");
        accelLineChart.getData().add(accelZSeries);
        accelJFXPanel.setScene(accelScene);
        accelPanel.add(accelJFXPanel);

        // Setup Axis
        gyroJFXPanel = new JFXPanel();
        final NumberAxis gyroXAxis = new NumberAxis();
        final NumberAxis gyroYAxis = new NumberAxis();
        gyroXAxis.setLabel("Time");
        gyroYAxis.setLabel("Y-Axis");
        gyroLineChart = new LineChart<>(gyroXAxis, gyroYAxis);
        gyroLineChart.setTitle("Gyroscope Chart");
        gyroXSeries = new XYChart.Series();
        gyroXSeries.setName("Gyroscope X Axis");
        gyroLineChart.getData().add(gyroXSeries);
        gyroYSeries = new XYChart.Series();
        gyroYSeries.setName("Gyroscope Y Axis");
        gyroLineChart.getData().add(gyroYSeries);
        gyroZSeries = new XYChart.Series();
        gyroZSeries.setName("Gyroscope Z Axis");
        gyroLineChart.getData().add(gyroZSeries);
        gyroScene = new Scene(gyroLineChart, 800, 600);
        gyroJFXPanel.setScene(gyroScene);
        gyroPanel.add(gyroJFXPanel);

        // Setup Axis
        dispJFXPanel = new JFXPanel();
        final NumberAxis distXAxis = new NumberAxis();
        final NumberAxis distYAxis = new NumberAxis();
        distXAxis.setLabel("Time");
        distYAxis.setLabel("Y-Axis");
        dispLineChart = new LineChart<>(distXAxis, distYAxis);
        dispLineChart.setTitle("Displacement Chart");
        dispXSeries = new XYChart.Series();
        dispXSeries.setName("Displacement X Axis");
        dispLineChart.getData().add(dispXSeries);
        dispYSeries = new XYChart.Series();
        dispYSeries.setName("Displacement Y Axis");
        dispLineChart.getData().add(dispYSeries);
        dispZSeries = new XYChart.Series();
        dispZSeries.setName("Displacement Z Axis");
        dispLineChart.getData().add(dispZSeries);
        dispScene = new Scene(dispLineChart, 800, 600);
        dispJFXPanel.setScene(dispScene);
        displacementPanel.add(dispJFXPanel);

    }

    /*
     * This method is used for saving the flight once the calculator class has saved them all.
     * It is used to create the rsc file
     */
    public void saveFlight(File file)
    {
        try
        {
            String tempFileName = file.toString().substring(0, file.toString().indexOf("."));
            System.out.println("fileName after substring: " + tempFileName);
            DataOutputStream tempDOS;

            flightFile = new File(tempFileName + ".rsc");
            System.out.println("SAVING FILE: " + flightFile);
            tempDOS = new DataOutputStream(new FileOutputStream(flightFile));

            accelXFile = new File(tempFileName + "_MPU9250_TxA_x.dat");
            System.out.println("writing: " + accelXFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(accelXFile.toString());

            accelYFile = new File(tempFileName + "_MPU9250_TxA_y.dat");
            System.out.println("writing: " + accelYFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(accelYFile.toString());

            accelZFile = new File(tempFileName + "_MPU9250_TxA_z.dat");
            System.out.println("writing: " + accelZFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(accelZFile.toString());

            velocityXFile = new File(tempFileName + "_MPU9250_TxV_x.dat");
            System.out.println("writing: " + velocityXFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(velocityXFile.toString());

            velocityYFile = new File(tempFileName + "_MPU9250_TxV_y.dat");
            System.out.println("writing: " + velocityYFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(velocityYFile.toString());

            velocityZFile = new File(tempFileName + "_MPU9250_TxV_z.dat");
            System.out.println("writing: " + velocityZFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(velocityZFile.toString());

            dispXFile = new File(tempFileName + "_MPU9250_TxD_x.dat");
            System.out.println("writing: " + dispXFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(dispXFile.toString());

            dispYFile = new File(tempFileName + "_MPU9250_TxD_y.dat");
            System.out.println("writing: " + dispYFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(dispYFile.toString());

            dispZFile = new File(tempFileName + "_MPU9250_TxD_z.dat");
            System.out.println("writing: " + dispZFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(dispZFile.toString());

            gyroXFile = new File(tempFileName + "_MPU9250_TxGyro_x.dat");
            System.out.println("writing: " + gyroXFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(gyroXFile.toString());

            gyroYFile = new File(tempFileName + "_MPU9250_TxGyro_y.dat");
            System.out.println("writing: " + gyroYFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(gyroYFile.toString());

            gyroZFile = new File(tempFileName + "_MPU9250_TxGyro_z.dat");
            System.out.println("writing: " + gyroZFile.toString() + " to: " + flightFile.toString());
            tempDOS.writeUTF(gyroZFile.toString());
        }
        catch (IOException iOEException)
        {
            Logger.getLogger(FlightPanel.class.getName()).log(Level.SEVERE, null, iOEException);
            iOEException.printStackTrace();
        }

    }

    /*
     * This method is used for loading the flight once the calculator class has saved them all.
     * It is used to open the rsc file
     */
    public void loadFlight(File file)
    {
        try
        {
            DataInputStream tempDIS;
            flightFile = file;
            System.out.println("Loading FILE: " + flightFile);
            tempDIS = new DataInputStream(new FileInputStream(flightFile));
            accelXFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + accelXFile.toString() + " from: " + flightFile.toString());
            accelYFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + accelYFile.toString() + " from: " + flightFile.toString());
            accelZFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + accelZFile.toString() + " from: " + flightFile.toString());
            velocityXFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + velocityXFile.toString() + " from: " + flightFile.toString());
            velocityYFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + velocityYFile.toString() + " from: " + flightFile.toString());
            velocityZFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + velocityZFile.toString() + " from: " + flightFile.toString());
            dispXFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + dispXFile.toString() + " from: " + flightFile.toString());
            dispYFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + dispYFile.toString() + " from: " + flightFile.toString());
            dispZFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + dispZFile.toString() + " from: " + flightFile.toString());
            gyroXFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + gyroXFile.toString() + " from: " + flightFile.toString());
            gyroYFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + gyroYFile.toString() + " from: " + flightFile.toString());
            gyroZFile = new File(tempDIS.readUTF());
            System.out.println("Read: " + gyroZFile.toString() + " from: " + flightFile.toString());
        }
        catch (IOException iOEException)
        {
            Logger.getLogger(FlightPanel.class.getName()).log(Level.SEVERE, null, iOEException);
            iOEException.printStackTrace();
        }
    }

    private void generateGraph(File dataFile, XYChart.Series series)
    {
        if (dataFile != null)
        {
            OrderedPair op;
            FileInputStream fis;
            ObjectInputStream ois;
            XYChart.Data data;
            ObservableList tempList;
            int count = 0;

            try
            {
                fis = new FileInputStream(dataFile);
                ois = new ObjectInputStream(fis);
                tempList = series.getData();

                for (int i = 0; i < 10; i++)//while (true) // will stop once EOF has been reached.
                {
                    op = new OrderedPair();
                    op.readOrderedPair(ois);
                    System.out.println("OrderedPair xValue: " + op.xValue + " yValue: " + op.yValue);
                    data = new XYChart.Data(op.xValue, op.yValue);
                    tempList.add(data);
                    count++;
                }
                op = new OrderedPair();
                op.readOrderedPair(ois);
                System.out.println("OrderedPair xValue: " + op.xValue + " yValue: " + op.yValue);
                series.getData().add(new XYChart.Data(op.xValue, op.yValue));
            }
            catch (IOException iOEException)
            {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!AN IO EXCEPTION OCCURED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                Logger.getLogger(FlightPanel.class.getName()).log(Level.SEVERE, null, iOEException);
                iOEException.printStackTrace();
            }
            catch (Exception exception)
            {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!AN  EXCEPTION OCCURED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                Logger.getLogger(FlightPanel.class.getName()).log(Level.SEVERE, null, exception);
                exception.printStackTrace();
            }
            System.out.println("There were: " + count + " points printed");
        }
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

        flightPanelJTabbedPane = new javax.swing.JTabbedPane();
        velocitySplitPlane = new javax.swing.JSplitPane();
        velocityOptionsPanel = new javax.swing.JPanel();
        velXToggleButton = new javax.swing.JToggleButton();
        velYToggleButton = new javax.swing.JToggleButton();
        velZToggleButton = new javax.swing.JToggleButton();
        velocityscrollPane = new javax.swing.JScrollPane();
        velocityPanel = new javax.swing.JPanel();
        AccelerationSplitPane = new javax.swing.JSplitPane();
        accelOptionPanel = new javax.swing.JPanel();
        accelXToggleButton = new javax.swing.JToggleButton();
        accelYToggleButton = new javax.swing.JToggleButton();
        accelZToggleButton = new javax.swing.JToggleButton();
        accelScrollPane = new javax.swing.JScrollPane();
        accelPanel = new javax.swing.JPanel();
        displacementSplitPane = new javax.swing.JSplitPane();
        displacementOptionPanel = new javax.swing.JPanel();
        dispXToggleButton = new javax.swing.JToggleButton();
        dispYToggleButton = new javax.swing.JToggleButton();
        dispZToggleButton = new javax.swing.JToggleButton();
        displacementScrollPane = new javax.swing.JScrollPane();
        displacementPanel = new javax.swing.JPanel();
        gyroscopeSplitPane = new javax.swing.JSplitPane();
        gyroScrollPane = new javax.swing.JScrollPane();
        gyroPanel = new javax.swing.JPanel();
        gyroAccelPanel = new javax.swing.JPanel();
        gyroXToggleButton = new javax.swing.JToggleButton();
        gyroYToggleButton = new javax.swing.JToggleButton();
        gyroZToggleButton = new javax.swing.JToggleButton();

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

        velocityPanel.setLayout(new java.awt.BorderLayout());
        velocityscrollPane.setViewportView(velocityPanel);

        velocitySplitPlane.setRightComponent(velocityscrollPane);

        flightPanelJTabbedPane.addTab("Velocity", velocitySplitPlane);

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

        accelPanel.setLayout(new java.awt.BorderLayout());
        accelScrollPane.setViewportView(accelPanel);

        AccelerationSplitPane.setRightComponent(accelScrollPane);

        flightPanelJTabbedPane.addTab("Acceleration", AccelerationSplitPane);

        dispXToggleButton.setText("Displacement X Axis");
        dispXToggleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dispXToggleButtonActionPerformed(evt);
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
                    .addComponent(dispXToggleButton)
                    .addComponent(dispYToggleButton)
                    .addComponent(dispZToggleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        displacementOptionPanelLayout.setVerticalGroup(
            displacementOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displacementOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dispXToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dispYToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dispZToggleButton)
                .addContainerGap(502, Short.MAX_VALUE))
        );

        displacementSplitPane.setLeftComponent(displacementOptionPanel);

        displacementPanel.setLayout(new java.awt.BorderLayout());
        displacementScrollPane.setViewportView(displacementPanel);

        displacementSplitPane.setRightComponent(displacementScrollPane);

        flightPanelJTabbedPane.addTab("Displacement", displacementSplitPane);

        gyroPanel.setLayout(new java.awt.BorderLayout());
        gyroScrollPane.setViewportView(gyroPanel);

        gyroscopeSplitPane.setRightComponent(gyroScrollPane);

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

        flightPanelJTabbedPane.addTab("GyroScope", gyroscopeSplitPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(flightPanelJTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(flightPanelJTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void accelYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelYToggleButtonActionPerformed
        if (accelYToggleButton.isSelected())
        {
            generateGraph(accelYFile, accelYSeries);
        }
        else
        {
            if (!accelYSeries.getData().isEmpty())
            {
                accelYSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_accelYToggleButtonActionPerformed

    private void accelXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelXToggleButtonActionPerformed
        if (accelXToggleButton.isSelected())
        {
            generateGraph(accelXFile, accelXSeries);
        }
        else
        {
            if (!accelXSeries.getData().isEmpty())
            {
                accelXSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_accelXToggleButtonActionPerformed

    private void gyroXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroXToggleButtonActionPerformed
        if (gyroXToggleButton.isSelected())
        {
            generateGraph(gyroXFile, gyroXSeries);
        }
        else
        {
            if (!gyroXSeries.getData().isEmpty())
            {
                gyroXSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_gyroXToggleButtonActionPerformed

    private void gyroYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroYToggleButtonActionPerformed
        if (gyroYToggleButton.isSelected())
        {
            generateGraph(gyroYFile, gyroYSeries);
        }
        else
        {
            if (!gyroYSeries.getData().isEmpty())
            {
                gyroYSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_gyroYToggleButtonActionPerformed

    private void velXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velXToggleButtonActionPerformed
        if (velXToggleButton.isSelected())
        {
            generateGraph(velocityXFile, velocityXSeries);
        }
        else
        {
            if (!velocityXSeries.getData().isEmpty())
            {
                velocityXSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_velXToggleButtonActionPerformed

    private void velYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velYToggleButtonActionPerformed
        if (velYToggleButton.isSelected())
        {
            generateGraph(velocityYFile, velocityYSeries);
        }
        else
        {
            if (!velocityYSeries.getData().isEmpty())
            {
                velocityYSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_velYToggleButtonActionPerformed

    private void dispXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispXToggleButtonActionPerformed
        if (dispXToggleButton.isSelected())
        {
            generateGraph(dispXFile, dispXSeries);
        }
        else
        {
            if (!dispXSeries.getData().isEmpty())
            {
                dispXSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_dispXToggleButtonActionPerformed

    private void dispYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispYToggleButtonActionPerformed
        if (dispYToggleButton.isSelected())
        {
            generateGraph(dispYFile, dispYSeries);
        }
        else
        {
            if (!dispYSeries.getData().isEmpty())
            {
                dispYSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_dispYToggleButtonActionPerformed

    private void velZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velZToggleButtonActionPerformed
        if (velZToggleButton.isSelected())
        {
            generateGraph(velocityZFile, velocityZSeries);
        }
        else
        {
            if (!velocityZSeries.getData().isEmpty())
            {
                velocityZSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_velZToggleButtonActionPerformed

    private void accelZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelZToggleButtonActionPerformed
        if (accelZToggleButton.isSelected())
        {
            generateGraph(accelZFile, accelZSeries);
        }
        else
        {
            if (!accelZSeries.getData().isEmpty())
            {
                accelZSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_accelZToggleButtonActionPerformed

    private void dispZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispZToggleButtonActionPerformed
        if (dispZToggleButton.isSelected())
        {
            generateGraph(dispZFile, dispZSeries);
        }
        else
        {
            if (!dispZSeries.getData().isEmpty())
            {
                dispZSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_dispZToggleButtonActionPerformed

    private void gyroZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroZToggleButtonActionPerformed
        if (gyroZToggleButton.isSelected())
        {
            generateGraph(gyroZFile, gyroZSeries);
        }
        else
        {
            if (!gyroZSeries.getData().isEmpty())
            {
                gyroZSeries.getData().clear();
            }
        }
    }//GEN-LAST:event_gyroZToggleButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane AccelerationSplitPane;
    private javax.swing.JPanel accelOptionPanel;
    private javax.swing.JPanel accelPanel;
    private javax.swing.JScrollPane accelScrollPane;
    private javax.swing.JToggleButton accelXToggleButton;
    private javax.swing.JToggleButton accelYToggleButton;
    private javax.swing.JToggleButton accelZToggleButton;
    private javax.swing.JToggleButton dispXToggleButton;
    private javax.swing.JToggleButton dispYToggleButton;
    private javax.swing.JToggleButton dispZToggleButton;
    private javax.swing.JPanel displacementOptionPanel;
    private javax.swing.JPanel displacementPanel;
    private javax.swing.JScrollPane displacementScrollPane;
    private javax.swing.JSplitPane displacementSplitPane;
    private javax.swing.JTabbedPane flightPanelJTabbedPane;
    private javax.swing.JPanel gyroAccelPanel;
    private javax.swing.JPanel gyroPanel;
    private javax.swing.JScrollPane gyroScrollPane;
    private javax.swing.JToggleButton gyroXToggleButton;
    private javax.swing.JToggleButton gyroYToggleButton;
    private javax.swing.JToggleButton gyroZToggleButton;
    private javax.swing.JSplitPane gyroscopeSplitPane;
    private javax.swing.JToggleButton velXToggleButton;
    private javax.swing.JToggleButton velYToggleButton;
    private javax.swing.JToggleButton velZToggleButton;
    private javax.swing.JPanel velocityOptionsPanel;
    private javax.swing.JPanel velocityPanel;
    private javax.swing.JSplitPane velocitySplitPlane;
    private javax.swing.JScrollPane velocityscrollPane;
    // End of variables declaration//GEN-END:variables
}
