package userinterface;

/*
The Calculator class is responsible for taking in RawDataLines,
using mathematics to convert the raw data into OrderedPairs,
and holding the streams open on each file that the
OrderedPairs will be written to.
 */
import java.io.*;
import javax.swing.DefaultListModel;

//=========================================================
public class Calculator
{

//FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;

//ObjectInputStream objectInputStream;
    ObjectOutputStream oos_TxA_x;
    ObjectOutputStream oos_TxA_y;
    ObjectOutputStream oos_TxA_z;
    ObjectOutputStream oos_TxV_x;
    ObjectOutputStream oos_TxV_y;
    ObjectOutputStream oos_TxV_z;
    ObjectOutputStream oos_TxD_x;
    ObjectOutputStream oos_TxD_y;
    ObjectOutputStream oos_TxD_z;
    ObjectOutputStream oos_TxGyro_x;
    ObjectOutputStream oos_TxGyro_y;
    ObjectOutputStream oos_TxGyro_z;

    FileReader fileReader;
    BufferedReader bufferedReader;

    DefaultListModel<RawDataLine> dataLineList;

    String rawFileName;

    double accelerationAverage_x;
    double accelerationAverage_y;
    double accelerationAverage_z;

    //=====================================================
    /*
    The constructor for Calculator automates the raw data
    gathering and calculating. It opens a bufferedReader
    on the raw data file and calls methods to begin reading
    and calculating data. It accepts a raw file name, but
    this should be turned into a file path later so that
    the code can exist anywhere and read a raw file anywhere
    else. It has no return, as it is a constructor, and it
    catches FileNotFoundExceptions, EOFExceptions, and
    IOExceptions.
     */
    //=====================================================
    public Calculator(String rawFileName)
    {

        try
        {

            this.rawFileName = rawFileName.trim();

            fileReader = new FileReader(this.rawFileName);
            bufferedReader = new BufferedReader(fileReader);

            dataLineList = new DefaultListModel<RawDataLine>();

            preLoadSomeLines();

            calculateValues();

        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();

            System.out.println("FileNotFoundException in Calculator constructor");
        }
        catch (EOFException eofe)
        {
            eofe.printStackTrace();

            System.out.println("EndOfFileException in Calculator constructor");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();

            System.out.println("IOException in Calculator constructor");
        }

    }

    //=====================================================
    /*
    The method preLoadSomeLines is used to gather the first
    several lines from the raw data file. It uses the already
    opened bufferedReader to do this, but accepts no arguments.
    It returns nothing, throws IOExceptions and EOFExceptions.
    It is private and can only be called by Calculator.
    Currently it pulls the first ten properly formatted lines
    from the raw data file.
     */
    //=====================================================
    private void preLoadSomeLines() throws IOException,
            EOFException
    {
        RawDataLine rawDataLine;
        int counter;

        counter = 0;
        while (counter < 10)
        {

            rawDataLine = new RawDataLine();

            rawDataLine.readFileLine(bufferedReader);

            if (rawDataLine.timeInMicroSeconds != -1)
            {
                dataLineList.addElement(rawDataLine);
                counter = counter + 1;
            }

        }

    }

    //=====================================================
    /*
    This method calculates values for velocity and displacement
    based on ten data points with the following equasions.

    Vi = 1/10 (ai-10 +...+ ai) * (ti-1 - ti) + Vi-1
    Si = 1/2 (vi-1 +...+ vi) * (ti-1 - ti) + Si-1

    Averaging 10 points of acceleration instead of 2 to smooth out
    the outlying points. This can be changed later on based on what
    we need. The formulas are somewhat modified because in the code,
    the average acceleration is calculated before hand.

    Averaging 2 points of velocity instead of 10 because we have
    already smoothed the curve, but some averaging is required for
    accuracy. See Trapezoid rule.

    For the change in time, since there is some variation in the
    hardware's read rate, we will average a delta time as follows.

    deltaTime = (time0 + time2) / 2 - (time1 + time3) / 2

    This method also calls rotateAccelerationsAboutXIn3D, rotateAccelerationsAboutYIn3D,
    and rotateAccelerationsAboutZIn3D to re-orthogonalize the acceleration
    magnitudes based on calculated rotations that come from the Gyroscope.
    The rotations are done with rotational matrices. See project
    notes for more info.

    All raw data values are modified by an offset before every calculation
    involving them. This is calculated by taking the first ten rawDataLines
    and averaging them to find an offset as it is thought that in most
    cases, the first values of a file are at rest.

    It holds an OrderedPair for every point pair it writes
    after calculation from the raw data file. It has a
    while(true) loop in it that runs through the lines of
    the raw data file to create OrderedPairs and it only
    gets kicked out of this loop once a EOFException is thrown.
    After this point, it flushes and closes all streams.

    It is private called by the constructor only, returns nothing,
    accepts no arguements, and catches IOExceptions and EOFExceptions.
     */
    //=====================================================
    private void calculateValues()
    {
        OrderedPair orderedPair_TxA_x;                              //Time x Acceleration in rockets xAxis
        OrderedPair orderedPair_TxV_x;                              //Time x Velocity in rockets xAxis
        OrderedPair orderedPair_TxD_x;                              //Time x Displacement in rockets xAxis

        OrderedPair orderedPair_TxA_y;                              //Time x Acceleration in rockets yAxis
        OrderedPair orderedPair_TxV_y;                              //Time x Velocity in rockets yAxis
        OrderedPair orderedPair_TxD_y;                              //Time x Displacement in rockets yAxis

        OrderedPair orderedPair_TxA_z;                              //Time x Acceleration in rockets zAxis
        OrderedPair orderedPair_TxV_z;                              //Time x Velocity in rockets zAxis
        OrderedPair orderedPair_TxD_z;                              //Time x Displacement in rockets zAxis

        OrderedPair orderedPair_TxGyro_x;                           //Time x Gyroscope reading in xAxis
        OrderedPair orderedPair_TxGyro_y;                           //Time x Gyroscope reading in yAxis
        OrderedPair orderedPair_TxGyro_z;                           //Time x Gyroscope reading in zAxis

        double distanceTotal_x;
        double distanceTotal_y;
        double distanceTotal_z;

        double time0;
        double time1;
        double time2;
        double time3;
        double deltaTime;

        double timeOffset;

        double radiansFromX;                                         //These are values derived from Gyroscope data
        double radiansFromY;
        double radiansFromZ;

        double averageRadiansPerSecondInGyroXAxis;
        double averageRadiansPerSecondInGyroYAxis;
        double averageRadiansPerSecondInGyroZAxis;

        double averageRadiansPerSecondInGyroXAxisOffset;
        double averageRadiansPerSecondInGyroYAxisOffset;
        double averageRadiansPerSecondInGyroZAxisOffset;

        double accelerationAverage_xOffset;
        double accelerationAverage_yOffset;
        double accelerationAverage_zOffset;

        double currentVelocity_x;
        double currentVelocity_y;
        double currentVelocity_z;

        double velocityAverage_x;
        double velocityAverage_y;
        double velocityAverage_z;

        double previousVelocity_x;
        double previousVelocity_y;
        double previousVelocity_z;

        double currentDisplacement_x;
        double currentDisplacement_y;
        double currentDisplacement_z;

        double previousDisplacement_x;
        double previousDisplacement_y;
        double previousDisplacement_z;

        int counter;

        try
        {

            openStreams();
            //Initialize values
            distanceTotal_x = 0;
            distanceTotal_y = 0;
            distanceTotal_z = 0;

            radiansFromX = 0;
            radiansFromY = 0;
            radiansFromZ = 0;

            averageRadiansPerSecondInGyroXAxis = (dataLineList.elementAt(0).xAxisGyro_MPU9250
                    + dataLineList.elementAt(1).xAxisGyro_MPU9250
                    + dataLineList.elementAt(2).xAxisGyro_MPU9250
                    + dataLineList.elementAt(3).xAxisGyro_MPU9250
                    + dataLineList.elementAt(4).xAxisGyro_MPU9250
                    + dataLineList.elementAt(5).xAxisGyro_MPU9250
                    + dataLineList.elementAt(6).xAxisGyro_MPU9250
                    + dataLineList.elementAt(7).xAxisGyro_MPU9250
                    + dataLineList.elementAt(8).xAxisGyro_MPU9250
                    + dataLineList.elementAt(9).xAxisGyro_MPU9250) * (3.1416 / 180 / 10);
            averageRadiansPerSecondInGyroXAxisOffset = averageRadiansPerSecondInGyroXAxis;

            averageRadiansPerSecondInGyroYAxis = (dataLineList.elementAt(0).yAxisGyro_MPU9250
                    + dataLineList.elementAt(1).yAxisGyro_MPU9250
                    + dataLineList.elementAt(2).yAxisGyro_MPU9250
                    + dataLineList.elementAt(3).yAxisGyro_MPU9250
                    + dataLineList.elementAt(4).yAxisGyro_MPU9250
                    + dataLineList.elementAt(5).yAxisGyro_MPU9250
                    + dataLineList.elementAt(6).yAxisGyro_MPU9250
                    + dataLineList.elementAt(7).yAxisGyro_MPU9250
                    + dataLineList.elementAt(8).yAxisGyro_MPU9250
                    + dataLineList.elementAt(9).yAxisGyro_MPU9250) * (3.1416 / 180 / 10);
            averageRadiansPerSecondInGyroYAxisOffset = averageRadiansPerSecondInGyroYAxis;

            averageRadiansPerSecondInGyroZAxis = (dataLineList.elementAt(0).zAxisGyro_MPU9250
                    + dataLineList.elementAt(1).zAxisGyro_MPU9250
                    + dataLineList.elementAt(2).zAxisGyro_MPU9250
                    + dataLineList.elementAt(3).zAxisGyro_MPU9250
                    + dataLineList.elementAt(4).zAxisGyro_MPU9250
                    + dataLineList.elementAt(5).zAxisGyro_MPU9250
                    + dataLineList.elementAt(6).zAxisGyro_MPU9250
                    + dataLineList.elementAt(7).zAxisGyro_MPU9250
                    + dataLineList.elementAt(8).zAxisGyro_MPU9250
                    + dataLineList.elementAt(9).zAxisGyro_MPU9250) * (3.1416 / 180 / 10);
            averageRadiansPerSecondInGyroZAxisOffset = averageRadiansPerSecondInGyroZAxis;

            accelerationAverage_x = (dataLineList.elementAt(0).xAxisAccel_MPU9250
                    + dataLineList.elementAt(1).xAxisAccel_MPU9250
                    + dataLineList.elementAt(2).xAxisAccel_MPU9250
                    + dataLineList.elementAt(3).xAxisAccel_MPU9250
                    + dataLineList.elementAt(4).xAxisAccel_MPU9250
                    + dataLineList.elementAt(5).xAxisAccel_MPU9250
                    + dataLineList.elementAt(6).xAxisAccel_MPU9250
                    + dataLineList.elementAt(7).xAxisAccel_MPU9250
                    + dataLineList.elementAt(8).xAxisAccel_MPU9250
                    + dataLineList.elementAt(9).xAxisAccel_MPU9250) / 10;
            accelerationAverage_xOffset = accelerationAverage_x;

            accelerationAverage_y = (dataLineList.elementAt(0).yAxisAccel_MPU9250
                    + dataLineList.elementAt(1).yAxisAccel_MPU9250
                    + dataLineList.elementAt(2).yAxisAccel_MPU9250
                    + dataLineList.elementAt(3).yAxisAccel_MPU9250
                    + dataLineList.elementAt(4).yAxisAccel_MPU9250
                    + dataLineList.elementAt(5).yAxisAccel_MPU9250
                    + dataLineList.elementAt(6).yAxisAccel_MPU9250
                    + dataLineList.elementAt(7).yAxisAccel_MPU9250
                    + dataLineList.elementAt(8).yAxisAccel_MPU9250
                    + dataLineList.elementAt(9).yAxisAccel_MPU9250) / 10;
            accelerationAverage_yOffset = accelerationAverage_y;

            accelerationAverage_z = (dataLineList.elementAt(0).zAxisAccel_MPU9250
                    + dataLineList.elementAt(1).zAxisAccel_MPU9250
                    + dataLineList.elementAt(2).zAxisAccel_MPU9250
                    + dataLineList.elementAt(3).zAxisAccel_MPU9250
                    + dataLineList.elementAt(4).zAxisAccel_MPU9250
                    + dataLineList.elementAt(5).zAxisAccel_MPU9250
                    + dataLineList.elementAt(6).zAxisAccel_MPU9250
                    + dataLineList.elementAt(7).zAxisAccel_MPU9250
                    + dataLineList.elementAt(8).zAxisAccel_MPU9250
                    + dataLineList.elementAt(9).zAxisAccel_MPU9250) / 10;
            accelerationAverage_zOffset = accelerationAverage_z;

//System.out.println("accerlerationAverages: " + accelerationAverage_x + " " + accelerationAverage_y + " " + accelerationAverage_z);
            currentVelocity_x = 0;
            currentVelocity_y = 0;
            currentVelocity_z = 0;

            velocityAverage_x = 0;
            velocityAverage_y = 0;
            velocityAverage_z = 0;

            previousVelocity_x = 0;
            previousVelocity_y = 0;
            previousVelocity_z = 0;

            currentDisplacement_x = 0;
            currentDisplacement_y = 0;
            currentDisplacement_z = 0;

            previousDisplacement_x = 0;
            previousDisplacement_y = 0;
            previousDisplacement_z = 0;

            timeOffset = dataLineList.elementAt(0).timeInMicroSeconds * 0.000001;

            counter = 0;
            while (true)                                                //end of file exception will kick out of the loop.
            {
                //The initial averages for acceleration have been
                //calculated using every point, but now for computational
                //efficiency we will multiply by ten, subtract oldest
                //acceleration, then add newest, then divide by ten again.
                //Is one addition, one subtraction, one multiply, and one
                //divide actually faster than 9 additions and one dividsion?
                //If we scale up to more points per average, then obviously
                //this method gets faster. Additionally, multiplying and dividing by
                //10 should just be a shift operation.

                //Delta Time calculations
                time0 = dataLineList.elementAt(0).timeInMicroSeconds;
                time1 = dataLineList.elementAt(1).timeInMicroSeconds;
                time2 = dataLineList.elementAt(2).timeInMicroSeconds;
                time3 = dataLineList.elementAt(3).timeInMicroSeconds;

                time0 = time0 * 0.000001;
                time1 = time1 * 0.000001;
                time2 = time2 * 0.000001;
                time3 = time3 * 0.000001;
                //Converting time in microseconds to seconds by multiplying
                //them by 1*10^-6
                deltaTime = (time1 + time3) / 2 - (time0 + time2) / 2;

//System.out.println("deltaTime:             " + deltaTime);
                //Angle calculations
                radiansFromX = (averageRadiansPerSecondInGyroXAxis
                        - averageRadiansPerSecondInGyroXAxisOffset)
                        * deltaTime + radiansFromX;

                radiansFromY = (averageRadiansPerSecondInGyroYAxis
                        - averageRadiansPerSecondInGyroYAxisOffset)
                        * deltaTime + radiansFromY;

                radiansFromZ = (averageRadiansPerSecondInGyroZAxis
                        - averageRadiansPerSecondInGyroZAxisOffset)
                        * deltaTime + radiansFromZ;

//System.out.println("radiansFrom X: " + radiansFromX + " Y: " + radiansFromY + " Z: " + radiansFromZ);
                //Correct for drift from original Axes
                //Re-orthogonalize with rotational matrices
                rotateAccelerationsAboutXIn3D(accelerationAverage_x, accelerationAverage_y,
                        accelerationAverage_z, radiansFromX);

                rotateAccelerationsAboutYIn3D(accelerationAverage_x, accelerationAverage_y,
                        accelerationAverage_z, radiansFromY);

                rotateAccelerationsAboutZIn3D(accelerationAverage_x, accelerationAverage_y,
                        accelerationAverage_z, radiansFromZ);

                //Calculate each pair of points
                //Acceleration
                orderedPair_TxA_x = new OrderedPair(time0 - timeOffset,
                        accelerationAverage_x - accelerationAverage_xOffset);

                orderedPair_TxA_y = new OrderedPair(time0 - timeOffset,
                        accelerationAverage_y - accelerationAverage_yOffset);

                orderedPair_TxA_z = new OrderedPair(time0 - timeOffset,
                        accelerationAverage_z - accelerationAverage_zOffset);

//System.out.println("accelerationAverage_x: " + (accelerationAverage_x - accelerationAverage_xOffset));
//System.out.println("accelerationAverage_y: " + (accelerationAverage_y - accelerationAverage_yOffset));
//System.out.println("accelerationAverage_xOffset: " + accelerationAverage_xOffset);
//System.out.println("accelerationAverage_yOffset: " + accelerationAverage_yOffset);
                //Velocity
                currentVelocity_x = (accelerationAverage_x - accelerationAverage_xOffset)
                        * deltaTime + previousVelocity_x;

                currentVelocity_y = (accelerationAverage_y - accelerationAverage_yOffset)
                        * deltaTime + previousVelocity_y;

                currentVelocity_z = (accelerationAverage_z - accelerationAverage_zOffset)
                        * deltaTime + previousVelocity_z;

                orderedPair_TxV_x = new OrderedPair(time0 - timeOffset, currentVelocity_x);
                orderedPair_TxV_y = new OrderedPair(time0 - timeOffset, currentVelocity_y);
                orderedPair_TxV_z = new OrderedPair(time0 - timeOffset, currentVelocity_z);

//System.out.println("currentVelocity_x:     " + currentVelocity_x);
                //Displacement
                if (counter == 0)
                {
                    velocityAverage_x = currentVelocity_x;
                    velocityAverage_y = currentVelocity_y;
                    velocityAverage_z = currentVelocity_z;
                }                                                   //previousVelocity = 0 first run
                //which cuts actual velocity in half
                //the first run leading to inaccuracy.
                else
                {
                    velocityAverage_x = (previousVelocity_x + currentVelocity_x) / 2;
                    velocityAverage_y = (previousVelocity_y + currentVelocity_y) / 2;
                    velocityAverage_z = (previousVelocity_z + currentVelocity_z) / 2;
                }

                currentDisplacement_x = velocityAverage_x * deltaTime + previousDisplacement_x;
                currentDisplacement_y = velocityAverage_y * deltaTime + previousDisplacement_y;
                currentDisplacement_z = velocityAverage_z * deltaTime + previousDisplacement_z;

                orderedPair_TxD_x = new OrderedPair(time0 - timeOffset, currentDisplacement_x);
                orderedPair_TxD_y = new OrderedPair(time0 - timeOffset, currentDisplacement_y);
                orderedPair_TxD_z = new OrderedPair(time0 - timeOffset, currentDisplacement_z);

//System.out.println("currentDisplacement_x: " + currentDisplacement_x);
                //Gyroscope
                orderedPair_TxGyro_x = new OrderedPair(time0 - timeOffset,
                        averageRadiansPerSecondInGyroXAxis
                        - averageRadiansPerSecondInGyroXAxisOffset);

                orderedPair_TxGyro_y = new OrderedPair(time0 - timeOffset,
                        averageRadiansPerSecondInGyroYAxis
                        - averageRadiansPerSecondInGyroYAxisOffset);

                orderedPair_TxGyro_z = new OrderedPair(time0 - timeOffset,
                        averageRadiansPerSecondInGyroZAxis
                        - averageRadiansPerSecondInGyroZAxisOffset);

//System.out.println("averageRadiansPerSecondInGyroXAxis: " + (averageRadiansPerSecondInGyroXAxis - averageRadiansPerSecondInGyroXAxisOffset));
                //have each pair write themselves
                orderedPair_TxA_x.writeOrderedPair(oos_TxA_x);
                orderedPair_TxA_y.writeOrderedPair(oos_TxA_y);
                orderedPair_TxA_z.writeOrderedPair(oos_TxA_z);

                orderedPair_TxV_x.writeOrderedPair(oos_TxV_x);
                orderedPair_TxV_y.writeOrderedPair(oos_TxV_y);
                orderedPair_TxV_z.writeOrderedPair(oos_TxV_z);

                orderedPair_TxD_x.writeOrderedPair(oos_TxD_x);
                orderedPair_TxD_y.writeOrderedPair(oos_TxD_y);
                orderedPair_TxD_z.writeOrderedPair(oos_TxD_z);

                orderedPair_TxGyro_x.writeOrderedPair(oos_TxGyro_x);
                orderedPair_TxGyro_y.writeOrderedPair(oos_TxGyro_y);
                orderedPair_TxGyro_z.writeOrderedPair(oos_TxGyro_z);

                //remove first element from averages
                accelerationAverage_x = accelerationAverage_x * 10
                        - dataLineList.firstElement().xAxisAccel_MPU9250;

                accelerationAverage_y = accelerationAverage_y * 10
                        - dataLineList.firstElement().yAxisAccel_MPU9250;

                accelerationAverage_z = accelerationAverage_z * 10
                        - dataLineList.firstElement().zAxisAccel_MPU9250;

                averageRadiansPerSecondInGyroXAxis = averageRadiansPerSecondInGyroXAxis * 10 * 180 / 3.1416
                        - dataLineList.firstElement().xAxisGyro_MPU9250;

                averageRadiansPerSecondInGyroYAxis = averageRadiansPerSecondInGyroYAxis * 10 * 180 / 3.1416
                        - dataLineList.firstElement().yAxisGyro_MPU9250;

                averageRadiansPerSecondInGyroZAxis = averageRadiansPerSecondInGyroZAxis * 10 * 180 / 3.1416
                        - dataLineList.firstElement().zAxisGyro_MPU9250;

                //remove the first row from the listmodel
                dataLineList.remove(dataLineList.indexOf(dataLineList.firstElement()));

                //Get the new line for the list
                getNewRawDataLine();

                //recalculate averages with new element
                accelerationAverage_x = (accelerationAverage_x + dataLineList.lastElement().xAxisAccel_MPU9250) / 10;
                accelerationAverage_y = (accelerationAverage_y + dataLineList.lastElement().yAxisAccel_MPU9250) / 10;
                accelerationAverage_z = (accelerationAverage_z + dataLineList.lastElement().zAxisAccel_MPU9250) / 10;

                averageRadiansPerSecondInGyroXAxis = (averageRadiansPerSecondInGyroXAxis
                        + dataLineList.lastElement().xAxisGyro_MPU9250)
                        * 3.1416 / 180 / 10;

                averageRadiansPerSecondInGyroYAxis = (averageRadiansPerSecondInGyroYAxis
                        + dataLineList.lastElement().yAxisGyro_MPU9250)
                        * 3.1416 / 180 / 10;

                averageRadiansPerSecondInGyroZAxis = (averageRadiansPerSecondInGyroZAxis
                        + dataLineList.lastElement().zAxisGyro_MPU9250)
                        * 3.1416 / 180 / 10;

                //Set previous values to current
                previousVelocity_x = currentVelocity_x;
                previousVelocity_y = currentVelocity_y;
                previousVelocity_z = currentVelocity_z;

                previousDisplacement_x = currentDisplacement_x;
                previousDisplacement_y = currentDisplacement_y;
                previousDisplacement_z = currentDisplacement_z;
//System.out.println("accerlerationAverages: " + accelerationAverage_x + " " + accelerationAverage_y + " " + accelerationAverage_z);

                counter = counter + 1;
            }

        }
        catch (EOFException eofe)
        {
            System.out.println("End of raw file reached.");

            try
            {
                //now flush and close all streams
                oos_TxA_x.flush();
                oos_TxA_x.close();
                oos_TxA_y.flush();
                oos_TxA_y.close();
                oos_TxA_z.flush();
                oos_TxA_z.close();

                oos_TxV_x.flush();
                oos_TxV_x.close();
                oos_TxV_y.flush();
                oos_TxV_y.close();
                oos_TxV_z.flush();
                oos_TxV_z.close();

                oos_TxD_x.flush();
                oos_TxD_x.close();
                oos_TxD_y.flush();
                oos_TxD_y.close();
                oos_TxD_z.flush();
                oos_TxD_z.close();

                oos_TxGyro_x.flush();
                oos_TxGyro_x.close();
                oos_TxGyro_y.flush();
                oos_TxGyro_y.close();
                oos_TxGyro_z.flush();
                oos_TxGyro_z.close();
            }
            catch (IOException ioe1)
            {
                ioe1.printStackTrace();

                System.out.println("IOException in Calculator calculateValues try catch(EOFException)");
            }

        }
        catch (IOException ioe2)
        {
            ioe2.printStackTrace();

            System.out.println("IOException in Calculator calculateValues");
        }

    }

    //=====================================================
    /*
    This method gets called by CalculateValues to open
    ObjectOutputStreams on every OrderedPair to be written.
    It gets called once, is private, has no return, accepts
    no arguments, and throws IOExceptions.
     */
    //=====================================================
    private void openStreams() throws IOException
    {
        File timeAccelerationFile_x;
        File timeAccelerationFile_y;
        File timeAccelerationFile_z;
        File timeVelocityFile_x;
        File timeVelocityFile_y;
        File timeVelocityFile_z;
        File timeDisplacementFile_x;
        File timeDisplacementFile_y;
        File timeDisplacementFile_z;
        File timeGyroFile_x;
        File timeGyroFile_y;
        File timeGyroFile_z;

        rawFileName = rawFileName.substring(0, rawFileName.indexOf("."));
        System.out.println("fileName after substring: " + rawFileName);

        timeAccelerationFile_x = new File(rawFileName + "_MPU9250_TxA_x.dat");
        timeAccelerationFile_y = new File(rawFileName + "_MPU9250_TxA_y.dat");
        timeAccelerationFile_z = new File(rawFileName + "_MPU9250_TxA_z.dat");

        timeVelocityFile_x = new File(rawFileName + "_MPU9250_TxV_x.dat");
        timeVelocityFile_y = new File(rawFileName + "_MPU9250_TxV_y.dat");
        timeVelocityFile_z = new File(rawFileName + "_MPU9250_TxV_z.dat");

        timeDisplacementFile_x = new File(rawFileName + "_MPU9250_TxD_x.dat");
        timeDisplacementFile_y = new File(rawFileName + "_MPU9250_TxD_y.dat");
        timeDisplacementFile_z = new File(rawFileName + "_MPU9250_TxD_z.dat");

        timeGyroFile_x = new File(rawFileName + "_MPU9250_TxGyro_x.dat");
        timeGyroFile_y = new File(rawFileName + "_MPU9250_TxGyro_y.dat");
        timeGyroFile_z = new File(rawFileName + "_MPU9250_TxGyro_z.dat");

        fileOutputStream = new FileOutputStream(timeAccelerationFile_x);
        oos_TxA_x = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeAccelerationFile_y);
        oos_TxA_y = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeAccelerationFile_z);
        oos_TxA_z = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeVelocityFile_x);
        oos_TxV_x = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeVelocityFile_y);
        oos_TxV_y = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeVelocityFile_z);
        oos_TxV_z = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeDisplacementFile_x);
        oos_TxD_x = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeDisplacementFile_y);
        oos_TxD_y = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeDisplacementFile_z);
        oos_TxD_z = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeGyroFile_x);
        oos_TxGyro_x = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeGyroFile_y);
        oos_TxGyro_y = new ObjectOutputStream(fileOutputStream);

        fileOutputStream = new FileOutputStream(timeGyroFile_z);
        oos_TxGyro_z = new ObjectOutputStream(fileOutputStream);

    }

    //=====================================================
    /*
    This method gets called every time the while(true) loop
    completes so that a new line from the raw data file gets
    read in to replace the last that was deleted. It is private,
    returns nothing, accepts no arguments, and throws IOExceptions
    and EOFExceptions.
     */
    //=====================================================
    private void getNewRawDataLine() throws IOException,
            EOFException
    {
        RawDataLine rawDataLine;
        int counter;

        counter = 0;
        while (counter < 1)
        {

            rawDataLine = new RawDataLine();

            rawDataLine.readFileLine(bufferedReader);

            if (rawDataLine.timeInMicroSeconds != -1)
            {
                dataLineList.addElement(rawDataLine);
                counter = counter + 1;
            }

        }

    }

    //=====================================================
    /*
    rotateAccelerationsAboutXIn3D accepts three accelerations
    to transform and an angle from the X axis to transform them
    about. It returns nothing as it modifies the accelerations
    given to it. No exceptions are caught.
     */
    //=====================================================
    void rotateAccelerationsAboutXIn3D(double accelerationAverage_x,
            double accelerationAverage_y,
            double accelerationAverage_z,
            double radiansFromX)
    {
        //Matrix multiplications, see project notes.
        this.accelerationAverage_x = 1 * accelerationAverage_x
                + 0 * accelerationAverage_y
                + 0 * accelerationAverage_z;

        this.accelerationAverage_y = 0 * accelerationAverage_x
                + Math.cos(radiansFromX) * accelerationAverage_y
                + (-1) * Math.sin(radiansFromX) * accelerationAverage_z;

        this.accelerationAverage_z = 0 * accelerationAverage_x
                + Math.sin(radiansFromX) * accelerationAverage_y
                + Math.cos(radiansFromX) * accelerationAverage_z;
    }

    //=====================================================
    /*
    rotateAccelerationsAboutYIn3D accepts three accelerations
    to transform and an angle from the Y axis to transform them
    about. It returns nothing as it modifies the accelerations
    given to it. No exceptions are caught.
     */
    //=====================================================
    void rotateAccelerationsAboutYIn3D(double accelerationAverage_x,
            double accelerationAverage_y,
            double accelerationAverage_z,
            double radiansFromY)
    {
        //Matrix multiplications, see project notes.
        this.accelerationAverage_x = Math.cos(radiansFromY) * accelerationAverage_x
                + 0 * accelerationAverage_y
                + Math.sin(radiansFromY) * accelerationAverage_z;

        this.accelerationAverage_y = 0 * accelerationAverage_x
                + 1 * accelerationAverage_y
                + 0 * accelerationAverage_z;

        this.accelerationAverage_z = (-1) * Math.sin(radiansFromY) * accelerationAverage_x
                + 0 * accelerationAverage_y
                + Math.cos(radiansFromY) * accelerationAverage_z;
    }

    //=====================================================
    /*
    rotateAccelerationsAboutZIn3D accepts three accelerations
    to transform and an angle from the Z axis to transform them
    about. It returns nothing as it modifies the accelerations
    given to it. No exceptions are caught.
     */
    //=====================================================
    void rotateAccelerationsAboutZIn3D(double accelerationAverage_x,
            double accelerationAverage_y,
            double accelerationAverage_z,
            double radiansFromZ)
    {
        //Matrix multiplications, see project notes.
        this.accelerationAverage_x = Math.cos(radiansFromZ) * accelerationAverage_x
                + (-1) * Math.sin(radiansFromZ) * accelerationAverage_y
                + 0 * accelerationAverage_z;

        this.accelerationAverage_y = Math.sin(radiansFromZ) * accelerationAverage_x
                + Math.cos(radiansFromZ) * accelerationAverage_y
                + 0 * accelerationAverage_z;

        this.accelerationAverage_z = 0 * accelerationAverage_x
                + 0 * accelerationAverage_y
                + 1 * accelerationAverage_z;
    }
    //=====================================================

}
//=========================================================
