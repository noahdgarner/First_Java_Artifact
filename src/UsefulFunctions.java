import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

//This class contains several useful text file and csv file functions created
//specifically for taking hackerrank data, and using it in arraylists and arrays
//in my other projects
public class UsefulFunctions {
    //read in files and into an array @params file: the relative path to file isTexT: true or false
    //true; pulls out all text; false, pulls out only numbers.
    //good for parsing strings line by line OR
    //good for parsing integers one number by one number
    public <T> ArrayList<T> fileLineReader(String file, boolean type)  {
        ArrayList fileLines = new ArrayList<>();
        //put strings into array list
        if (type) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    fileLines.add(line);
                }
            }  catch (IOException e) {
                //simply print the chain of calls to see where our error
                //could be from. Note, we are calling this method on the
                //object 'e', e is the object created by the error in the try block
                e.printStackTrace();
            }
        }
        else {
            try {
                Scanner scanner = new Scanner(new File(file));

                while (scanner.hasNextInt()) {

                    fileLines.add(scanner.nextInt());
                }
            }
            catch (FileNotFoundException i) {
                i.printStackTrace();
            }
        }
        return fileLines;
    }

    //convert integer arraylist to integer array
    //remember, we are doing all this because we want to use an arraylist for scanning text file
    public int [] integerListToIntegerArray(ArrayList<Integer> intList) {
        int [] arr = new int [intList.size()];
        //convert our list into an array
        for (int i = 0; i < arr.length; i++){
            arr[i] = intList.get(i);
        }
        return arr;
    }

    //FOR INTEGERS ONLY
    //syntax to return a list of lists of Integers from parsing file
    //note the file you send this function should ONLY have INTEGERS NOT DOUBLES in it!!!! Remember this
    public ArrayList<ArrayList<Integer>> listOfIntegerLists (String file) throws IOException {
        //get the number of lines in the text file, this way we don't
        //need to specify a size parameter
        ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>();
        //get file path to call a line ocunting function
        Path path = Paths.get(file);
        //working!
        long lineCount = Files.lines(path).count();
        //ready the reader to read integers for conversion into the array
        BufferedReader reader = new BufferedReader(new FileReader(file));
        //I wrote this function, barely know what its doing
        //but i can tell you, its parsing integers in a file
        //into an arraylist line by line using regular expression
        //and pattern matching. holy shit lol
        for (int i = 0; i < lineCount; i++) {
            ArrayList<Integer> numbersInALine = new ArrayList<>();
            //we must first compile a pattern object
            Pattern tokSplitter = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
            String tokens = reader.readLine();
            //we then construct a matcher object by invoking
            //the matcher method on a pattern object
            Matcher m = tokSplitter.matcher(tokens); //match on the current line
            //this while loop will parse the matches into
            while (m.find()) {
                if (m.group(1) != null) {
                    //convert the string number into an integer
                    numbersInALine.add(Integer.parseInt(m.group(1)));
                }
                else {
                    numbersInALine.add(Integer.parseInt(m.group()));
                }
            }
            listOfLists.add(numbersInALine);
        }
        return listOfLists;
    }

    //convert a string to some arbitrary integer number
    public  int stringToInteger(String s) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException("s must be a string, program terminating.");
        }
        //we want to loop through each char in the string
        char [] charArray = s.toCharArray();
        int sum = 0;

        for(Character aChar : charArray) {
            sum += aChar - '0';  //easiest way to convert a char to an integer. remember this!!
        }

        return sum;
    }


    //write to a file, this is just a quick method before i got to the gym
    public void  writeToAFile(String toWrite){


            File file=new File("writeToMe.txt");

            try{

                PrintWriter output=new PrintWriter("writeToMe.txt");
                //oh so this is how you do it. GAY!
                //time to go tp the gym
                output.println(toWrite);

                output.close();

            } catch(IOException x){
                x.printStackTrace();
            }
    }
    //input length for a random number length n
    public final static long createRandomNumber(long len) {
        //all numbers in Java cap out at 18 digits
        if (len > 18)
            throw new IllegalStateException("To many digits");
        long tLen = (long) Math.pow(10, len - 1) * 9;

        long number = (long) (Math.random() * tLen) + (long) Math.pow(10, len - 1) * 1;
        //check if the #'s String equiv is of len specified
        String  tVal = number + "";
        if (tVal.length() != len) {
            throw new IllegalStateException("The random number '" + tVal + "' is not '" + len + "' digits");
        }
        //otherwise give the number
        return number;
    }
    //this method is merely for quick debugging because im fucking tired of
    //typing so many letts to print shit, make global somehow?
    public static void p(String printMeFast) {
        System.out.println(printMeFast);
    }
    //this is the program driver, we need to run this to have our code compile so that we can make a jar!!
    public static void main(String [] args) throws IOException {
        System.out.println("Making our first jar file");

        UsefulFunctions uf = new UsefulFunctions();
        int sum = uf.stringToInteger("convertThisTiInteger");


       ArrayList<String> stuff = uf.fileLineReader("data/runthis.txt",true);
       System.out.println(sum);


        uf.writeToAFile("Some stuff");


    }
}
