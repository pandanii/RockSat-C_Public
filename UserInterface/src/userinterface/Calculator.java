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

            fileReader = new FileReader(rawFileName);
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
        /*
    for (int j=0; j < dataLineList.size(); j++)
        {
        System.out.println(dataLineList.elementAt(j).timeInMicroSeconds);
        }
         */

    }

    //=====================================================
    /*
    This method calculates values for velocity and displacement
    based on ten data points with an expanded Trapizoid Rule
    of the Riemann Sum approach.

    Example of Trapizoid rule with velocity and displacement(S)
    Vi = (((ai + a(i + 1) ) / 2) * ti + Vi - 1) * (t(i+1) - ti)
    Si = (((ai + a(i + 1) ) / 2) * t2 / 2 + Vi - 1 * ti  + S(i - 1)) * (t(i+1) - ti)

    Averaging ten points of acceleration instead of 2 to smooth out
    the outlying points.

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

        Double distanceTotal_x;
        Double distanceTotal_y;
        Double distanceTotal_z;

        int time1;
        int time2;

        float accelerationAverage_x;
        float accelerationAverage_y;
        float accelerationAverage_z;

        float previousVelocity_x;
        float previousVelocity_y;
        float previousVelocity_z;

        float previousDisplacement_x;
        float previousDisplacement_y;
        float previousDisplacement_z;

        try
        {

            openStreams();

            distanceTotal_x = 0.0;
            distanceTotal_y = 0.0;
            distanceTotal_z = 0.0;

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

//System.out.println("accerlerationAverages: " + accelerationAverage_x + " " + accelerationAverage_y + " " + accelerationAverage_z);
            previousVelocity_x = 0;
            previousVelocity_y = 0;
            previousVelocity_z = 0;

            previousDisplacement_x = 0;
            previousDisplacement_y = 0;
            previousDisplacement_z = 0;

            while (true)                                                //end of file exception will kick us out of the loop.
            {
                //The initial averages for acceleration have been
                //calculated using every point, but now for computational
                //efficiency we will multiply by ten, subtract oldest
                //acceleration, then add newest, then divide by ten again.
                //Is one addition, one subtraction, one multiply, and one
                //divide actually faster than 9 additions and one dividsion?
                //If we scale up to more points per average, then obviously
                //this method gets faster.
                time1 = dataLineList.elementAt(0).timeInMicroSeconds;
                time2 = dataLineList.elementAt(1).timeInMicroSeconds;

                //Calculate each pair of points
                orderedPair_TxA_x = new OrderedPair(time1, dataLineList.firstElement().xAxisAccel_MPU9250);
                orderedPair_TxA_y = new OrderedPair(time1, dataLineList.firstElement().yAxisAccel_MPU9250);
                orderedPair_TxA_z = new OrderedPair(time1, dataLineList.firstElement().zAxisAccel_MPU9250);

                orderedPair_TxV_x = new OrderedPair(time1, (accelerationAverage_x * time1 + previousVelocity_x) * (time2 - time1));
                orderedPair_TxV_y = new OrderedPair(time1, (accelerationAverage_y * time1 + previousVelocity_y) * (time2 - time1));
                orderedPair_TxV_z = new OrderedPair(time1, (accelerationAverage_z * time1 + previousVelocity_z) * (time2 - time1));

                orderedPair_TxD_x = new OrderedPair(time1, (accelerationAverage_x * time1 * time1 / 2 + previousVelocity_x * time1 + previousDisplacement_x) * (time2 - time1));
                orderedPair_TxD_y = new OrderedPair(time1, (accelerationAverage_y * time1 * time1 / 2 + previousVelocity_y * time1 + previousDisplacement_y) * (time2 - time1));
                orderedPair_TxD_z = new OrderedPair(time1, (accelerationAverage_z * time1 * time1 / 2 + previousVelocity_z * time1 + previousDisplacement_z) * (time2 - time1));

                orderedPair_TxGyro_x = new OrderedPair(time1, dataLineList.firstElement().xAxisGyro_MPU9250);
                orderedPair_TxGyro_y = new OrderedPair(time1, dataLineList.firstElement().yAxisGyro_MPU9250);
                orderedPair_TxGyro_z = new OrderedPair(time1, dataLineList.firstElement().zAxisGyro_MPU9250);

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

                //remove first acceleration from averages
                accelerationAverage_x = accelerationAverage_x * 10 - dataLineList.firstElement().xAxisAccel_MPU9250;
                accelerationAverage_y = accelerationAverage_y * 10 - dataLineList.firstElement().yAxisAccel_MPU9250;
                accelerationAverage_z = accelerationAverage_z * 10 - dataLineList.firstElement().zAxisAccel_MPU9250;

                //remove the first row from the listmodel
                dataLineList.remove(dataLineList.indexOf(dataLineList.firstElement()));

                //Get the new line for the list
                getNewRawDataLine();

                //recalculate averages with new acceleration
                accelerationAverage_x = (accelerationAverage_x + dataLineList.lastElement().xAxisAccel_MPU9250) / 10;
                accelerationAverage_y = (accelerationAverage_y + dataLineList.lastElement().yAxisAccel_MPU9250) / 10;
                accelerationAverage_z = (accelerationAverage_z + dataLineList.lastElement().zAxisAccel_MPU9250) / 10;

//System.out.println("accerlerationAverages: " + accelerationAverage_x + " " + accelerationAverage_y + " " + accelerationAverage_z);
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

        timeAccelerationFile_x = new File(rawFileName + "_TxA_x.dat");
        timeAccelerationFile_y = new File(rawFileName + "_TxA_y.dat");
        timeAccelerationFile_z = new File(rawFileName + "_TxA_z.dat");

        timeVelocityFile_x = new File(rawFileName + "_TxV_x.dat");
        timeVelocityFile_y = new File(rawFileName + "_TxV_y.dat");
        timeVelocityFile_z = new File(rawFileName + "_TxV_z.dat");

        timeDisplacementFile_x = new File(rawFileName + "_TxD_x.dat");
        timeDisplacementFile_y = new File(rawFileName + "_TxD_y.dat");
        timeDisplacementFile_z = new File(rawFileName + "_TxD_z.dat");

        timeGyroFile_x = new File(rawFileName + "_TxGyro_x.dat");
        timeGyroFile_y = new File(rawFileName + "_TxGyro_y.dat");
        timeGyroFile_z = new File(rawFileName + "_TxGyro_z.dat");

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

}
//=========================================================
