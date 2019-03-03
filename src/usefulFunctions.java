import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This ckass contains several useful text file and csv file functions created
//specificalyl for taking hackerrank data, and using it in arraylists and arrays
//in my other projects
public class usefulFunctions {
    //read in files and into an array @params file: the relative path to file isTexT: true or false
    //true; pulls out all text; false, pulls out only numbers.
    //good for parsing strings line by line OR
    //good for parsing integers one number by one number
    public <T> ArrayList<T> fileLineReader(String file, boolean type) throws FileNotFoundException {
        ArrayList fileLines = new ArrayList<>();
        //put strings into array list
        if (type) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    fileLines.add(line);
                }
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Scanner scanner = new Scanner(new File(file));
            while(scanner.hasNextInt()) {
                fileLines.add(scanner.nextInt());
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
            //ignores spaces, slashes, etc
            Pattern tokSplitter = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
            String tokens = reader.readLine();
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






    //this is the program driver, we need to run this to have our code compile so that we can make a jar!!
    public static void main(String [] args) throws IOException {
        System.out.println("Making our first jar file");

        /*usefulFunctions uf = new usefulFunctions();
        int sum = uf.stringToInteger("convertThisTiInteger");

       // String stuff = uf.fileLineReader("runthis.txt",true);
        System.out.println(sum);
        */

        usefulFunctions uf = new usefulFunctions();



    }


}
