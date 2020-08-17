
/**
 * Write a description of CountryExportCsv here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExportCsv {
    public void listExpoters(CSVParser parser, String exportItem){
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                String country= record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public String countryInfo(CSVParser parser, String countryName){
        for(CSVRecord record: parser){
            String country= record.get("Country");
            if((country.toLowerCase()).equals(countryName.toLowerCase())){
                String export= record.get("Exports");
                String value= record.get("Value (dollars)");
                return country+ ": "+export+ ": "+value;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,String exportItem2){
        for(CSVRecord record: parser){
            String export= record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                String country= record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count= 0;
        for(CSVRecord record: parser){
            String export= record.get("Exports");
            if(export.contains(exportItem)){
                String country= record.get("Country");
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String valueAmount){
        for(CSVRecord record: parser){
            String value= record.get("Value (dollars)");
            if(valueAmount.length()<value.length()){
                String country= record.get("Country");
                System.out.println(country+ " " +value);
            }
        }
    }
    
    public void test(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExpoters(parser,"coffee");
        //parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser,"Nauru"));
        //parser = fr.getCSVParser();
        //listExportersTwoProducts(parser,"cotton","flowers");
        // parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"cocoa"));
        // parser = fr.getCSVParser();
        //bigExporters(parser,"$999,999,999,999"); 
    }
}

