//test class, this was a test function to convert a string and multiply its integer equivalents

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoreUsefulFunctions {



    //does not work for floating point, this is merely for educaitonal purposes
    public int stringToIntegerMultiplication(String s){
        int product = 1;

        if (s.equals("") || s == null) {
            throw new IllegalArgumentException("You must enter a string with content. Program terminating");
        }

        for(int i = 0; i < s.length(); i++) {
            //whats considered an integer? Note: Subtracting '0' from a char means you are subtracting 48 ('0' in ascii)
            //from the values of 0-9 (48-57) e.g. 57 - 48 is the literal characters 9-0 = 9
           int currCharVal = s.charAt(i) - '0';
           if (currCharVal <= 9 && currCharVal >= 0) {
               //System.out.println(currCharVal);
               product *= currCharVal;
            }
        }
        return product;
    }


    //RegEx Test Harness
    public static void RegexTestHarness() {
        while (true) {
            //remember, you need a scanner when working with user test input
            //in java
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your regular expression");
            String regex = scanner.nextLine();
            Pattern  pattern =
                            Pattern.compile(regex);
            System.out.println("Enter your String of text: ");
            Matcher matcher =
                    pattern.matcher(scanner.nextLine());

            boolean found = false;
            while (matcher.find()) {
                System.out.println("I found the text "+matcher.group()
                +"\nstarting at index "+matcher.start()+
                        " and ending at index "+matcher.end());
                found = true;
            }
            if(!found){
                System.out.println("Not found!");
            }
        }
    }









    public static void main(String [] args) {
        System.out.println("Test for second class in noahs library");

        MoreUsefulFunctions mUF = new MoreUsefulFunctions();

        System.out.println("The following line multiples 6 * 4");
        //only works for integers separated by white space
        System.out.println(mUF.stringToIntegerMultiplication("Convert should give 6 * 4 * 4"));

        RegexTestHarness();


    }
}
