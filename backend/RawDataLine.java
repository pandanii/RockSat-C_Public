/*
RawDataLine is intended to store one line of a Raw Flight
File(comma delimited) in its individual components. This
class has data members to store Time; two sets of
x,y,z Accelerometer readings;and x,y,z Gyroscope readings.
It has a method that if given an open stream to a file can
read in each of these data members. While a BufferedInputStream
over a FileInputStream might be faster, to begin with this
class uses a BufferedReader over a FileReader for convenience.
The order of the datamembers is hard coded, so the file lines
should be ordered appropriately.
*/

import java.io.*;
import java.util.zip.DataFormatException;
import java.lang.reflect.*;



//=========================================================
class RawDataLine
{

float timeInMicroSeconds;
float xAxisAccel_MPU9250;
float yAxisAccel_MPU9250;
float zAxisAccel_MPU9250;
float xAxisGyro_MPU9250;
float yAxisGyro_MPU9250;
float zAxisGyro_MPU9250;
float xAxisAccel_ADXL377;
float yAxisAccel_ADXL377;
float zAxisAccel_ADXL377;


    //=====================================================
    RawDataLine()                                               //Constructor
    {

    timeInMicroSeconds = 0;
    xAxisAccel_MPU9250 = 0;
    yAxisAccel_MPU9250 = 0;
    zAxisAccel_MPU9250 = 0;
    xAxisGyro_MPU9250 = 0;
    yAxisGyro_MPU9250 = 0;
    zAxisGyro_MPU9250 = 0;
    xAxisAccel_ADXL377 = 0;
    yAxisAccel_ADXL377 = 0;
    zAxisAccel_ADXL377 = 0;

    }
    //=====================================================
    /*
    The method readFileLine accepts a Buffered Reader, has no
    return value, and throws IOException and EOFException.
    This method is called by Calculator to fill an instance
    of RawDataLine with values from a line of the file.
    It does so by calling readLine() of BufferedReader and
    then passing the results to parseLine of RawDataLine.
    If a problem occurs reading the line that isn't because
    the end of the file was reached or an IOException was
    thrown, then this instance of the class will be given
    a time of '-1' to test against so this data point can
    be thrown out.
    */
    //=====================================================
    void readFileLine(BufferedReader rawFileBufferedReader)
                                                     throws IOException,
                                                            EOFException
    {
    String fileLine;

    try
        {

        fileLine = rawFileBufferedReader.readLine();

        if (fileLine == null)
            {
            throw new EOFException();
            }
        else
            {
            parseLine(fileLine.trim());
            }

        }
    catch (DataFormatException dfe)
        {
        System.out.println("File line doesn't match regular expression.");

        timeInMicroSeconds = -1;
        }

    }
    //=====================================================
    /*
    The method parseLine() of class RawDataLine is private
    and gets called internally by the method readFileLine().
    It is sent a String, returns nothing, and throws
    DataFormatExceptions for various reasons.
    The method uses a regular expression to for the string
    holding the file line to match it before it starts
    parsing it. The method makes no attempt to decipher
    the order of the file's contents, instead the order
    is hard coded and the file should be ordered appropriately.
    */
    //=====================================================
    private void parseLine(String fileLine) throws DataFormatException
    {

//    System.out.println(fileLine);

    String regularExpressionForFileLine;
    String[] stringArray;

    regularExpressionForFileLine = new String("[0-9]+[,][-]?[0-9]+[.]?[0-9]*[,][-]?[0-9]+[.]?[0-9]*[,][-]?[0-9]+[.]?[0-9]*[,][-]?[0-9]+[.]?[0-9]*[,][-]?[0-9]+[.]?[0-9]*[,][-]?[0-9]+[.]?[0-9]*[,][-]?[0-9]+[.]?[0-9]*[,][-]?[0-9]+[.]?[0-9]*[,][-]?[0-9]+[.]?[0-9]*");

    if (fileLine.matches(regularExpressionForFileLine))
        {
//        System.out.println("File line good");

        stringArray = fileLine.split(",");                      //when properly formatted, this should always
                                                                //create as many strings are there are numbers
                                                                //separated by commas as there are in a single
                                                                //file line.

        try
            {
            timeInMicroSeconds = Float.parseFloat(
                           (String)Array.get(stringArray, 0));
                                                                //parseInt() and get() are static methods,
                                                                //so they can be called in this way.
                                                                //Array doesn't know it is full of strings,
                                                                //so a typecast was used.
            xAxisAccel_MPU9250 = Float.parseFloat(
                           (String)Array.get(stringArray, 1));

            yAxisAccel_MPU9250 = Float.parseFloat(
                           (String)Array.get(stringArray, 2));

            zAxisAccel_MPU9250 = Float.parseFloat(
                           (String)Array.get(stringArray, 3));

            xAxisGyro_MPU9250 = Float.parseFloat(
                           (String)Array.get(stringArray, 4));

            yAxisGyro_MPU9250 = Float.parseFloat(
                           (String)Array.get(stringArray, 5));

            zAxisGyro_MPU9250 = Float.parseFloat(
                           (String)Array.get(stringArray, 6));

            xAxisAccel_ADXL377 = Float.parseFloat(
                           (String)Array.get(stringArray, 7));

            yAxisAccel_ADXL377 = Float.parseFloat(
                           (String)Array.get(stringArray, 8));

            zAxisAccel_ADXL377 = Float.parseFloat(
                           (String)Array.get(stringArray, 9));

            }
        catch (NumberFormatException nfe)
            {
            nfe.printStackTrace();

            System.out.println("NumberFormatException thrown in RawDataLine.parseLine");

            throw new DataFormatException();
            }
        catch (NullPointerException npe)
            {
            npe.printStackTrace();

            System.out.println("NullPointerException thrown in RawDataLine.parseLine");

            throw new DataFormatException();
            }
        catch (IllegalArgumentException iae)
            {
            iae.printStackTrace();

            System.out.println("IllegalArgumentException thrown in RawDataLine.parseLine");

            throw new DataFormatException();
            }
        catch (ArrayIndexOutOfBoundsException aioobe)
            {
            aioobe.printStackTrace();

            System.out.println("ArrayIndexOutOfBoundsException thrown in RawDataLine.parseLine");

            throw new DataFormatException();
            }
        catch (Exception ex)
            {
            ex.printStackTrace();

            System.out.println("Exception thrown in RawDataLine.parseLine");

            throw new DataFormatException();
            }
        }
    else
        {
        throw new DataFormatException("File line doesn't match regular expression.");
        }

    }
    //=====================================================

}
//=========================================================