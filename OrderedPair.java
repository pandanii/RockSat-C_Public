/*
OrderedPair is a class used to write and read integer
pairs to and from file with ObjectOutputStreams and
ObjectInputStreams respectively. This class might
just be useless overhead and if the software runs slowly
this could be done by parent classes, but for now
it keeps things object oriented and uniform.
*/

import java.io.*;


//=========================================================
public class OrderedPair
{

double xValue;                                                       //Graph point in the xAxis
double yValue;                                                       //Graph point in the yAxis


    //=====================================================
    /*
    Constructor accepts the values to be written to file.
    */
    //=====================================================
    public OrderedPair(double xValue, double yValue)                  //Constructor
    {

    this.xValue = xValue;
    this.yValue = yValue;

    }
    //=====================================================
    /*
    Constructor initializes data members to zero as the values
    will be read from file.
    */
    //=====================================================
    public OrderedPair()                                        //Constructor
    {

    this.xValue = 0;
    this.yValue = 0;

    }
    //=====================================================
    /*
    Method readOrderedPair accepts an ObjectInputStream that
    has been provided with a FileInputStream already to read
    in a pair of integers from a file. It has no return value
    and throws IOExceptions.
    */
    //=====================================================
    public void readOrderedPair(ObjectInputStream graphFileObjectInputStream)
                                                     throws IOException
    {

    xValue = graphFileObjectInputStream.readDouble();
    yValue = graphFileObjectInputStream.readDouble();

    }
    //=====================================================
    /*
    Method writeOrderedPair accepts an ObjectOutputStream that
    has been provided with a FileOutputStream already to write
    a pair of integers to the file. It has no return value
    and throws IOExceptions.
    */
    //=====================================================
    public void writeOrderedPair(ObjectOutputStream graphFileObjectOutputStream)
                                                     throws IOException
    {

    graphFileObjectOutputStream.writeDouble(xValue);
    graphFileObjectOutputStream.writeDouble(yValue);

    }
    //=====================================================

}
//=========================================================