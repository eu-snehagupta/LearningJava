
/**
 * Write a description of FoodCsv here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class FoodCsv {
    public void readfood(){
        FileResource fr = new FileResource();
        CSVParser parser =fr.getCSVParser();
        for(CSVRecord record: parser){
            System.out.print(record.get("Name")+ " ");
            System.out.println(record.get("Food"));
        }
    }
    
}

