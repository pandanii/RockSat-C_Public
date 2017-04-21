package userinterface;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class FlightPanel extends javax.swing.JPanel
{

    private static final long serialVersionUID = 1L;
    public static final int LOAD_RAW = 0;
    public static final int LOAD_FLIGHT = 1;

    private File flightFile;

    //Stuff for Velocity
    private JFreeChart velocityLineChart;
    private XYSeries velocityXSeries;
    private XYSeries velocityYSeries;
    private XYSeries velocityZSeries;
    private XYSeriesCollection velocityDataset;
    private File velocityXFile;
    private File velocityYFile;
    private File velocityZFile;

    //Stuff for Accel
    private JFreeChart accelLineChart;
    private XYSeries accelXSeries;
    private XYSeries accelYSeries;
    private XYSeries accelZSeries;
    private XYSeriesCollection accelDataset;
    private File accelXFile;
    private File accelYFile;
    private File accelZFile;

    //Stuff for Accel
    private JFreeChart gyroLineChart;
    private XYSeries gyroXSeries;
    private XYSeries gyroYSeries;
    private XYSeries gyroZSeries;
    private XYSeriesCollection gyroDataset;
    private File gyroXFile;
    private File gyroYFile;
    private File gyroZFile;

    //Stuff for Displacement
    private JFreeChart dispLineChart;
    private XYSeries dispXSeries;
    private XYSeries dispYSeries;
    private XYSeries dispZSeries;
    private XYSeriesCollection dispDataset;
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

        velocityXSeries = new XYSeries("Data Point");
        velocityXSeries.add(1.0, 10.0);
        velocityXSeries.add(2.0, 8.0);
        velocityXSeries.add(3.0, 5.0);
        velocityXSeries.add(4.0, 24.0);
        velocityXSeries.add(5.0, 3.0);
        velocityXSeries.add(6.0, 2.0);
        velocityXSeries.add(7.0, 22.0);
        velocityXSeries.add(8.0, 100.0);
        velocityXSeries.add(9.0, 8.0);
        velocityXSeries.add(10.0, 2.0);
        velocityXSeries.add(11.0, 29.0);
        velocityXSeries.add(12.0, 275.0);
        // Add the series to your data set
        velocityDataset = new XYSeriesCollection();
        velocityDataset.addSeries(velocityXSeries);

        velocityLineChart = ChartFactory.createXYLineChart(
                "XY Chart", // Title
                "x-axis", // x-axis Label
                "y-axis", // y-axis Label
                velocityDataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        ChartPanel CP = new ChartPanel(velocityLineChart);

        velocityPanel.add(CP, BorderLayout.CENTER);
        velocityPanel.validate();

        // Setup Axis
        accelXSeries = new XYSeries("Data Point");
        accelXSeries.add(1, 23);
        accelXSeries.add(2, 14);
        accelXSeries.add(3, 15);
        accelXSeries.add(4, 24);
        accelXSeries.add(5, 34);
        accelXSeries.add(6, 36);
        accelXSeries.add(7, 22);
        accelXSeries.add(8, 45);
        accelXSeries.add(9, 43);
        accelXSeries.add(10, 17);
        accelXSeries.add(11, 29);
        accelXSeries.add(12, 25);
        accelDataset = new XYSeriesCollection();
        accelDataset.addSeries(accelXSeries);
        accelLineChart = ChartFactory.createXYLineChart(
                "XY Chart", // Title
                "x-axis", // x-axis Label
                "y-axis", // y-axis Label
                accelDataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        CP = new ChartPanel(accelLineChart);
        accelPanel.add(CP, BorderLayout.CENTER);
        accelPanel.validate();

        // Setup Axis
        gyroXSeries = new XYSeries("Data Point");
        gyroXSeries.add(1, 1);
        gyroXSeries.add(2, 2);
        gyroXSeries.add(3, 3);
        gyroXSeries.add(4, 4);
        gyroXSeries.add(5, 5);
        gyroXSeries.add(6, 7);
        gyroXSeries.add(7, 8);
        gyroXSeries.add(8, 0);
        gyroXSeries.add(9, 2);
        gyroXSeries.add(10, 5);
        gyroXSeries.add(11, 1);
        gyroXSeries.add(12, 27);
        gyroDataset = new XYSeriesCollection();
        gyroDataset.addSeries(gyroXSeries);
        gyroLineChart = ChartFactory.createXYLineChart(
                "XY Chart", // Title
                "x-axis", // x-axis Label
                "y-axis", // y-axis Label
                gyroDataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        CP = new ChartPanel(gyroLineChart);
        gyroPanel.add(CP, BorderLayout.CENTER);
        gyroPanel.validate();

        // Setup Axis
        dispXSeries = new XYSeries("Data Point");
        dispXSeries.add(1, 1);
        dispXSeries.add(2, 2);
        dispXSeries.add(3, 3);
        dispXSeries.add(4, 4);
        dispXSeries.add(5, 5);
        dispXSeries.add(6, 7);
        dispXSeries.add(7, 8);
        dispXSeries.add(8, 0);
        dispXSeries.add(9, 2);
        dispXSeries.add(10, 5);
        dispXSeries.add(11, 1);
        dispXSeries.add(12, 27);
        dispDataset = new XYSeriesCollection();
        dispDataset.addSeries(dispXSeries);
        dispLineChart = ChartFactory.createXYLineChart(
                "XY Chart", // Title
                "x-axis", // x-axis Label
                "y-axis", // y-axis Label
                dispDataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        CP = new ChartPanel(dispLineChart);
        displacementPanel.add(CP, BorderLayout.CENTER);
        displacementPanel.validate();

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
            Calculator calculator = new Calculator(file.toString());
            saveFlight(file);
        }
        else if (loadValue == LOAD_FLIGHT)
        {
            loadFlight(file);
        }

        // Setup Axis
        // Add the series to your data set
        velocityDataset = new XYSeriesCollection();
        velocityLineChart = ChartFactory.createXYLineChart(
                "Velocity XY Chart", // Title
                "Time", // x-axis Label
                "y-axis", // y-axis Label
                velocityDataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        ChartPanel CP = new ChartPanel(velocityLineChart);
        velocityPanel.add(CP, BorderLayout.CENTER);
        velocityPanel.validate();

        // Add the series to your data set
        accelDataset = new XYSeriesCollection();
        accelLineChart = ChartFactory.createXYLineChart(
                "Acceleration XY Chart", // Title
                "Time", // x-axis Label
                "y-axis", // y-axis Label
                accelDataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        CP = new ChartPanel(accelLineChart);
        accelPanel.add(CP, BorderLayout.CENTER);
        accelPanel.validate();

        // Add the series to your data set
        gyroDataset = new XYSeriesCollection();
        gyroLineChart = ChartFactory.createXYLineChart(
                "Gyroscope XY Chart", // Title
                "Time", // x-axis Label
                "y-axis", // y-axis Label
                gyroDataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        CP = new ChartPanel(gyroLineChart);
        gyroPanel.add(CP, BorderLayout.CENTER);
        gyroPanel.validate();

        // Add the series to your data set
        dispDataset = new XYSeriesCollection();
        dispLineChart = ChartFactory.createXYLineChart(
                "Displacement XY Chart", // Title
                "Time", // x-axis Label
                "y-axis", // y-axis Label
                dispDataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        CP = new ChartPanel(dispLineChart);
        displacementPanel.add(CP, BorderLayout.CENTER);
        displacementPanel.validate();

    }

    /*
     * This method is used for saving the flight once the calculator class has saved them all.
     * It is used to create the rsc file
     */
    private void saveFlight(File file)
    {
        String tempFileName = file.toString().substring(0, file.toString().indexOf("."));
        System.out.println("fileName after substring: " + tempFileName);
        flightFile = new File(tempFileName + ".rsc");
        System.out.println("SAVING FILE: " + flightFile);

        try (DataOutputStream tempDOS = new DataOutputStream(new FileOutputStream(flightFile));)
        {
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
            tempDOS.flush();
            tempDOS.close();

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
    private void loadFlight(File file)
    {
        flightFile = file;
        System.out.println("Loading FILE: " + flightFile);
        try (DataInputStream tempDIS = new DataInputStream(new FileInputStream(flightFile));)
        {
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
            tempDIS.close();
        }
        catch (IOException iOEException)
        {
            Logger.getLogger(FlightPanel.class.getName()).log(Level.SEVERE, null, iOEException);
            iOEException.printStackTrace();
        }
    }

    private void generateSeries(File dataFile, XYSeries series)
    {
        if (dataFile != null)
        {
            OrderedPair op;
            int count = 0;

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile)))
            {
                while (true)//for (int i = 0; i < 100; i++)// will stop once EOF has been reached.
                {
                    op = new OrderedPair();
                    op.readOrderedPair(ois);
                    System.out.println("OrderedPair xValue: " + op.xValue + " yValue: " + op.yValue);
                    series.add(op.xValue, op.yValue);
                    count++;
                }
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
            System.out.println("There were: " + count + " points read in and printed");
            System.out.println("series.getitemCount(): " + series.getItemCount());
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
        accelerationSplitPane = new javax.swing.JSplitPane();
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
        gyroAccelPanel = new javax.swing.JPanel();
        gyroXToggleButton = new javax.swing.JToggleButton();
        gyroYToggleButton = new javax.swing.JToggleButton();
        gyroZToggleButton = new javax.swing.JToggleButton();
        gyroScrollPane = new javax.swing.JScrollPane();
        gyroPanel = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(640, 480));
        setOpaque(false);

        flightPanelJTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        flightPanelJTabbedPane.setToolTipText("");
        flightPanelJTabbedPane.setMinimumSize(new java.awt.Dimension(640, 480));
        flightPanelJTabbedPane.setName(""); // NOI18N
        flightPanelJTabbedPane.setPreferredSize(new java.awt.Dimension(1280, 720));

        velocitySplitPlane.setToolTipText("");
        velocitySplitPlane.setMinimumSize(new java.awt.Dimension(1280, 720));
        velocitySplitPlane.setPreferredSize(new java.awt.Dimension(1280, 720));

        velocityOptionsPanel.setPreferredSize(new java.awt.Dimension(0, 0));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        velocitySplitPlane.setLeftComponent(velocityOptionsPanel);

        velocityscrollPane.setMinimumSize(flightPanelJTabbedPane.getMinimumSize());
        velocityscrollPane.setName(""); // NOI18N
        velocityscrollPane.setPreferredSize(flightPanelJTabbedPane.getPreferredSize());

        velocityPanel.setLayout(new java.awt.BorderLayout());
        velocityscrollPane.setViewportView(velocityPanel);

        velocitySplitPlane.setRightComponent(velocityscrollPane);

        flightPanelJTabbedPane.addTab("Velocity", null, velocitySplitPlane, "");

        accelerationSplitPane.setMinimumSize(new java.awt.Dimension(1280, 720));
        accelerationSplitPane.setPreferredSize(new java.awt.Dimension(1280, 720));

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
                .addContainerGap(572, Short.MAX_VALUE))
        );

        accelerationSplitPane.setLeftComponent(accelOptionPanel);

        accelScrollPane.setMinimumSize(new java.awt.Dimension(1280, 720));
        accelScrollPane.setPreferredSize(new java.awt.Dimension(1280, 720));

        accelPanel.setName(""); // NOI18N
        accelPanel.setLayout(new java.awt.BorderLayout());
        accelScrollPane.setViewportView(accelPanel);

        accelerationSplitPane.setRightComponent(accelScrollPane);

        flightPanelJTabbedPane.addTab("Acceleration", accelerationSplitPane);

        displacementSplitPane.setMinimumSize(new java.awt.Dimension(1280, 720));

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
                .addContainerGap(572, Short.MAX_VALUE))
        );

        displacementSplitPane.setLeftComponent(displacementOptionPanel);

        displacementScrollPane.setMinimumSize(new java.awt.Dimension(1280, 720));
        displacementScrollPane.setPreferredSize(new java.awt.Dimension(1280, 720));

        displacementPanel.setLayout(new java.awt.BorderLayout());
        displacementScrollPane.setViewportView(displacementPanel);

        displacementSplitPane.setRightComponent(displacementScrollPane);

        flightPanelJTabbedPane.addTab("Displacement", displacementSplitPane);

        gyroscopeSplitPane.setMinimumSize(new java.awt.Dimension(1280, 720));

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
                .addContainerGap(572, Short.MAX_VALUE))
        );

        gyroscopeSplitPane.setLeftComponent(gyroAccelPanel);

        gyroScrollPane.setMinimumSize(new java.awt.Dimension(1280, 720));
        gyroScrollPane.setPreferredSize(new java.awt.Dimension(1280, 720));

        gyroPanel.setLayout(new java.awt.BorderLayout());
        gyroScrollPane.setViewportView(gyroPanel);

        gyroscopeSplitPane.setRightComponent(gyroScrollPane);

        flightPanelJTabbedPane.addTab("GyroScope", gyroscopeSplitPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(flightPanelJTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(flightPanelJTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void gyroZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroZToggleButtonActionPerformed
        if (gyroZToggleButton.isSelected())
        {
            gyroZSeries = new XYSeries("Gyroscope Z");
            generateSeries(gyroZFile, gyroZSeries);
            gyroDataset.addSeries(gyroZSeries);
        }
        else
        {
            if (!gyroZSeries.isEmpty())
            {
                gyroZSeries.clear();
                gyroDataset.removeSeries(gyroZSeries);
            }
        }
    }//GEN-LAST:event_gyroZToggleButtonActionPerformed

    private void gyroYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroYToggleButtonActionPerformed
        if (gyroYToggleButton.isSelected())
        {
            gyroYSeries = new XYSeries("Gyroscope Y");
            generateSeries(gyroYFile, gyroYSeries);
            gyroDataset.addSeries(gyroYSeries);
        }
        else
        {
            if (!gyroYSeries.isEmpty())
            {
                gyroYSeries.clear();
                gyroDataset.removeSeries(gyroYSeries);
            }
        }
    }//GEN-LAST:event_gyroYToggleButtonActionPerformed

    private void gyroXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gyroXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_gyroXToggleButtonActionPerformed
        if (gyroXToggleButton.isSelected())
        {
            gyroXSeries = new XYSeries("Gyroscope X");
            generateSeries(gyroXFile, gyroXSeries);
            gyroDataset.addSeries(gyroXSeries);
        }
        else
        {
            if (!gyroXSeries.isEmpty())
            {
                gyroXSeries.clear();
                gyroDataset.removeSeries(gyroXSeries);
            }
        }
    }//GEN-LAST:event_gyroXToggleButtonActionPerformed

    private void dispZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispZToggleButtonActionPerformed
        if (dispZToggleButton.isSelected())
        {
            dispZSeries = new XYSeries("Displacement Z");
            generateSeries(dispZFile, dispZSeries);
            dispDataset.addSeries(dispZSeries);
        }
        else
        {
            if (!dispZSeries.isEmpty())
            {
                dispZSeries.clear();
                dispDataset.removeSeries(dispZSeries);
            }
        }
    }//GEN-LAST:event_dispZToggleButtonActionPerformed

    private void dispYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispYToggleButtonActionPerformed
        if (dispYToggleButton.isSelected())
        {
            dispYSeries = new XYSeries("Displacement Y");
            generateSeries(dispYFile, dispYSeries);
            dispDataset.addSeries(dispYSeries);
        }
        else
        {
            if (!dispYSeries.isEmpty())
            {
                dispYSeries.clear();
                dispDataset.removeSeries(dispYSeries);
            }
        }
    }//GEN-LAST:event_dispYToggleButtonActionPerformed

    private void dispXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dispXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_dispXToggleButtonActionPerformed
        if (dispXToggleButton.isSelected())
        {
            dispXSeries = new XYSeries("Displacement X");
            generateSeries(dispXFile, dispXSeries);
            dispDataset.addSeries(dispXSeries);
        }
        else
        {
            if (!dispXSeries.isEmpty())
            {
                dispXSeries.clear();
                dispDataset.removeSeries(dispXSeries);
            }
        }
    }//GEN-LAST:event_dispXToggleButtonActionPerformed

    private void accelZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelZToggleButtonActionPerformed
        if (accelZToggleButton.isSelected())
        {
            accelZSeries = new XYSeries("Acceleration Z");
            generateSeries(accelZFile, accelZSeries);
            accelDataset.addSeries(accelZSeries);
        }
        else
        {
            if (!accelZSeries.isEmpty())
            {
                accelZSeries.clear();
                accelDataset.removeSeries(accelZSeries);
            }
        }
    }//GEN-LAST:event_accelZToggleButtonActionPerformed

    private void accelYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelYToggleButtonActionPerformed
        if (accelYToggleButton.isSelected())
        {
            accelYSeries = new XYSeries("Acceleration Y");
            generateSeries(accelYFile, accelYSeries);
            accelDataset.addSeries(accelYSeries);
        }
        else
        {
            if (!accelYSeries.isEmpty())
            {
                accelYSeries.clear();
                accelDataset.removeSeries(accelYSeries);
            }
        }
    }//GEN-LAST:event_accelYToggleButtonActionPerformed

    private void accelXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accelXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_accelXToggleButtonActionPerformed
        if (accelXToggleButton.isSelected())
        {
            accelXSeries = new XYSeries("Acceleration X");
            generateSeries(accelXFile, accelXSeries);
            accelDataset.addSeries(accelXSeries);
        }
        else
        {
            if (!accelXSeries.isEmpty())
            {
                accelXSeries.clear();
                accelDataset.removeSeries(accelXSeries);
            }
        }
    }//GEN-LAST:event_accelXToggleButtonActionPerformed

    private void velZToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velZToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velZToggleButtonActionPerformed
        if (velZToggleButton.isSelected())
        {
            velocityZSeries = new XYSeries("Velocity Z");
            generateSeries(velocityZFile, velocityZSeries);
            velocityDataset.addSeries(velocityZSeries);
        }
        else
        {
            if (!velocityZSeries.isEmpty())
            {
                velocityZSeries.clear();
                velocityDataset.removeSeries(velocityZSeries);
            }
        }
    }//GEN-LAST:event_velZToggleButtonActionPerformed

    private void velYToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velYToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velYToggleButtonActionPerformed
        if (velYToggleButton.isSelected())
        {
            velocityYSeries = new XYSeries("Velocity Y");
            generateSeries(velocityYFile, velocityYSeries);
            velocityDataset.addSeries(velocityYSeries);
        }
        else
        {
            if (!velocityYSeries.isEmpty())
            {
                velocityYSeries.clear();
                velocityDataset.removeSeries(velocityYSeries);
            }
        }
    }//GEN-LAST:event_velYToggleButtonActionPerformed

    private void velXToggleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_velXToggleButtonActionPerformed
    {//GEN-HEADEREND:event_velXToggleButtonActionPerformed
        if (velXToggleButton.isSelected())
        {
            velocityXSeries = new XYSeries("Velocity X");
            generateSeries(velocityXFile, velocityXSeries);
            velocityDataset.addSeries(velocityXSeries);
        }
        else
        {
            if (!velocityXSeries.isEmpty())
            {
                velocityXSeries.clear();
                velocityDataset.removeSeries(velocityXSeries);
            }
        }
    }//GEN-LAST:event_velXToggleButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accelOptionPanel;
    private javax.swing.JPanel accelPanel;
    private javax.swing.JScrollPane accelScrollPane;
    private javax.swing.JToggleButton accelXToggleButton;
    private javax.swing.JToggleButton accelYToggleButton;
    private javax.swing.JToggleButton accelZToggleButton;
    private javax.swing.JSplitPane accelerationSplitPane;
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
