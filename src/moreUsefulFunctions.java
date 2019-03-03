//test class, this was a test function to convert a string and multiply its integer equivalents

public class moreUsefulFunctions {



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









    public static void main(String [] args) {
        System.out.println("Test for second class in noahs library");

        moreUsefulFunctions mUF = new moreUsefulFunctions();

        System.out.println("The following line multiples 6 * 4");
        //only works for integers separated by white space
        System.out.println(mUF.stringToIntegerMultiplication("Convert should give 6 * 4 * 4"));



    }
}
