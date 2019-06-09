import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//For my Web App & Stock Analyzer so I can have JSON to work with.
public class JSONDataGen {

    //create data for a company name, #'s soon to be randomly gen
    public static JSONObject jsonSectorObj(String name,String picName, String shortName){
        JSONObject innerObj = new JSONObject();
        //order is not retained... why?
        innerObj.put("sectorName", name);
        innerObj.put("picName", picName);
        innerObj.put("shortName",shortName);
        return innerObj;
    }

    //create data for a company name, #'s soon to be randomly gen
    public static JSONObject jsonCompanyObject(String name, String picName, String shortName){
        JSONObject innerObj = new JSONObject();
        //order is not retained... why?
        innerObj.put("companyName", name);
        innerObj.put("picName", picName);
        innerObj.put("shortName",shortName);
        return innerObj;
    }
    //you see, this is the beginning.. of the art of programming in my eyes
    public static void main(String[] args) throws IOException{
        //this is the mega object to hold innerObjects
        JSONObject sectorObj = new JSONObject();
        //list object will be created in jsonObj function, then added to list
        JSONArray list = new JSONArray();
        //the number of unique key inner objects we will create
        int n = 1;
        for(int i=0; i< n ;i++) {
            //gen JSON obj, put it into mega object
            list.add(jsonSectorObj("Financial", "financialSector","fs"));
            list.add(jsonSectorObj("Utility", "utilitySector", "us"));
            list.add(jsonSectorObj("Consumer Discretionary", "discretionarySector","ds"));
            list.add(jsonSectorObj("Consumer Staples", "stapleSector","ss"));
            list.add(jsonSectorObj("Healthcare", "healthSector","hs"));
            list.add(jsonSectorObj("Industrials", "industrialSector","is"));
            list.add(jsonSectorObj("Technology", "techSector","ts"));
            list.add(jsonSectorObj("Real Estate", "estateSector","es"));
        }
        sectorObj.put("Sector Data", list);
        //write data list to main object., overwrites, we dont need to appen for now
        try (FileWriter file = new FileWriter("data/sectorData.json")) {
            file.write(sectorObj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + sectorObj);
        }

        //this is the mega object to hold innerObjects
        JSONObject companyObj = new JSONObject();
        //list object will be created in jsonObj function, then added to list
        JSONArray list2 = new JSONArray();
        //the number of unique key inner objects we will create
        int j = 1;
        for(int i=0; i< j ;i++) {
            //gen JSON obj, put it into mega object
            list2.add(jsonCompanyObject("Nvidia", "nvidia","cn"));
            list2.add(jsonCompanyObject("McDonalds", "mcdonalds", "cm"));
            list2.add(jsonCompanyObject("Intel", "intel","ci"));
            list2.add(jsonCompanyObject("Books", "books","cb"));
            list2.add(jsonCompanyObject("Coffee", "coffee","cc"));
            list2.add(jsonCompanyObject("Trees", "trees","ct"));
            list2.add(jsonCompanyObject("Lamps", "lamps","cl"));
            list2.add(jsonCompanyObject("Desks", "desks","cd"));
        }
        companyObj.put("Company Data", list2);
        //write data list to main object., overwrites, we dont need to appen for now
        try (FileWriter file = new FileWriter("data/companyData.json")) {
            file.write(companyObj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + companyObj);
        }
    }
}
