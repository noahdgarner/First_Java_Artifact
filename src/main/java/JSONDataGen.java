import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//For my Web App & Stock Analyzer so I can have JSON to work with.
public class JSONDataGen {

    //create data for a company name, #'s soon to be randomly gen
    public static JSONObject jsonObj(String name){
        JSONObject innerObj = new JSONObject();
        innerObj.put("Company Name", name);
        innerObj.put("Company Score", UsefulFunctions.createRandomNumber(3));
        innerObj.put("High", UsefulFunctions.createRandomNumber(4));
        innerObj.put("Low", UsefulFunctions.createRandomNumber(4));
        innerObj.put("P per E", UsefulFunctions.createRandomNumber(2));
        return innerObj;
    }


    public static void main(String[] args) throws IOException{
        JSONObject obj = new JSONObject();
        //create a new JSONObject, add it to JSONArray instead of String
        JSONArray list = new JSONArray();
        //this can be created dynamically
        list.add(jsonObj("Facebook"));
        list.add(jsonObj("Amazon"));
        list.add(jsonObj("Netflix"));
        list.add(jsonObj("Google"));


        obj.put("Company Data", list);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("data/file1.json")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }


    }
}
