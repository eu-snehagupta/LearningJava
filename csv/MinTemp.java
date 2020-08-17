
/**
 * Write a description of MinTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MinTemp {
    public CSVRecord compareTemp(CSVRecord lowestSoFar, CSVRecord currentRow){
        if(lowestSoFar== null){
                lowestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            if((currentTemp != -9999) && (currentTemp < lowestTemp)){
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord compareHumidity(CSVRecord lowestSoFar, CSVRecord currentRow){
        if(lowestSoFar== null){
                lowestSoFar = currentRow;
        } else {
            int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
            int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
            if((currentHumidity < lowestHumidity)){
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestSoFar= null;
        for(CSVRecord currentRow: parser){
            lowestSoFar = compareTemp(lowestSoFar, currentRow);
        }
        return lowestSoFar;
    }
    
    public CSVRecord fileWithColdestTemperature(){
        CSVRecord lowestSoFar= null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            lowestSoFar = compareTemp(lowestSoFar, currentRow);
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord lowestSoFar= null;
        for(CSVRecord currentRow: parser){
            lowestSoFar = compareHumidity(lowestSoFar, currentRow);
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles (){
        CSVRecord lowestSoFar= null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = compareHumidity(lowestSoFar, currentRow);
        }
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double averageTemp = 0.0;
        for(CSVRecord currentRow: parser){
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            averageTemp = averageTemp + currentTemp;
        }
        return averageTemp/24;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int humidity){
        double averageTemp = 0.0;
        int count = 0;
        for(CSVRecord currentRow: parser){
            int currenthumidity = Integer.parseInt(currentRow.get("Humidity"));
            if(currenthumidity >= humidity){
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                averageTemp = averageTemp + currentTemp;
                count++;
            } 
        }
        return averageTemp/count;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord Coldest= coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was "+Coldest.get("TemperatureF") +" at " +Coldest.get("TimeEDT"));
    }
    
    public void testFileWithColdestTemperature(){
        CSVRecord Coldest= fileWithColdestTemperature();
        System.out.println("Coldest temperature was "+Coldest.get("TemperatureF") +" at " +Coldest.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord Lowest= lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was "+Lowest.get("Humidity") +" at " +Lowest.get("DateUTC") +" " +Lowest.get("TimeEDT"));
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord Lowest= lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+Lowest.get("Humidity") +" at " +Lowest.get("DateUTC") +" " +Lowest.get("TimeEDT"));
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double Average= averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is "+Average);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double Average= averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if(Average == 0.0){
            System.out.println("No temperatures with that humidity.");
        } else{
            System.out.println("Average temperature with high humidity in file is "+Average);
        }
    }
}
