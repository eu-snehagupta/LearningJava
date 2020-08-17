
/**
 * Write a description of MaxTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MaxTemp {
    
    public CSVRecord compareHottestTemp(CSVRecord largestSoFar, CSVRecord currentRow){
        if(largestSoFar== null){
                largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currentTemp > largestTemp){
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar= null;
        for(CSVRecord currentRow: parser){
            largestSoFar = compareHottestTemp(largestSoFar, currentRow);
        }
        return largestSoFar;
    }
    
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar= null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = compareHottestTemp(largestSoFar, currentRow);
        }
        return largestSoFar;
    }
    
    
    public void testHottestInDay(){
        FileResource fr = new FileResource("datafilles/nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord hottest= hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was "+hottest.get("TemperatureF") +" at " +hottest.get("TimeEST"));
    }
    
    public void testHottestInManyDays(){
        CSVRecord hottest= hottestInManyDays();
        System.out.println("hottest temperature was "+hottest.get("TemperatureF") +" at " +hottest.get("DateUTC"));
    }
}
