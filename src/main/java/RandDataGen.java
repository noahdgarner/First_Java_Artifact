import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class RandDataGen implements Serializable {

    //default serialVersion id
    private static final long serialVersionUID = 1l;
    //create a POJO
    String fName;
    String lName;
    String pass;
    String Address;
    String location;
    long id;
    long ip;

    @Override
    public String toString() {
        return "This object has key id: "+id+" \nand ip: "+ip+"\n";
    }

    public static void main(String[] args) throws IOException {
        //store by id# as i (because its easier to store people as #'s
        HashMap<Integer, RandDataGen> dataDict = new HashMap<>();
        //for rand num gen
        UsefulFunctions uf = new UsefulFunctions();
        //for testing
        int numDatas = 100;
        String filepath = "data/writeToMe.txt";
        //init data creation
        for(int i=0;i<numDatas;i++) {
            //new data object
            RandDataGen aData = new RandDataGen();
            //create a random 10 digit long number, cannot use double
            long randNum = uf.createRandomNumber(10);
            aData.id = i;
            aData.ip = randNum;
            aData.fName = "John";
            aData.lName = "Doe";
            aData.Address = "130 Corner Street";
            aData.location = "somewhere";
            aData.pass = "123456";
            dataDict.put(i,aData);
            //not really working as intended, weird. overwritting. not good
            p("This object before serial: "+aData.id+ " "+aData.ip);
            WriteObjectToFile(aData, filepath);
            RandDataGen dataObject = (RandDataGen) readObjectFromFile(filepath);
            p("After serial: "+dataObject.toString());
        }
    }
    //notice in main we must cast the object. Because it doesn't know
    //what kind of object is being returned
    public static Object  readObjectFromFile(String filepath) {
        try {
            // read object from file
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            RandDataGen result = (RandDataGen) ois.readObject();
            ois.close();
            return result;
        }
        //some bullshit catch shit, don't worry only req for above
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    //TODO: make so this appends instead of overwrites
    public static void WriteObjectToFile(Object serObj, String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void p(String printMeFast) {
        System.out.println(printMeFast);
    }
}
