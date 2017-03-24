//package userinterface;
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

float xAxis;
float yAxis;


    //=====================================================
    /*
    Constructor accepts the values to be written to file.
    */
    //=====================================================
    public OrderedPair(float xAxis, float yAxis)                    //Constructor
    {

    this.xAxis = xAxis;
    this.yAxis = yAxis;

    }
    //=====================================================
    /*
    Constructor initializes data members to zero as the values
    will be read from file.
    */
    //=====================================================
    public OrderedPair()                                        //Constructor
    {

    this.xAxis = 0;
    this.yAxis = 0;

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

    xAxis = graphFileObjectInputStream.readFloat();
    yAxis = graphFileObjectInputStream.readFloat();

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

    graphFileObjectOutputStream.writeFloat(xAxis);
    graphFileObjectOutputStream.writeFloat(yAxis);

    }
    //=====================================================

}
//=========================================================