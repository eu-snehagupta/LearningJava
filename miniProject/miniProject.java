
/**
 * Write a description of miniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class miniProject {
    public void printName(){
        FileResource fr = new FileResource();
        for (CSVRecord record : fr.getCSVParser(false)){
            int noBorn = Integer.parseInt(record.get(2));
            if(noBorn <= 100){
            System.out.println("Name: " +record.get(0) + " Gender: " +record.get(1) + " No of borns: " +record.get(2));
           }
        } 
    }
    
    public void totalBirths(FileResource fr){
        int totals= 0;
        int totalgirls= 0;
        int totalboys= 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            int noBorn = Integer.parseInt(record.get(2));
            totals += noBorn;
            if(record.get(1).equals("F")){
               totalgirls += noBorn;
            } else{
                totalboys += noBorn;
            }
        }
        System.out.println("Total no of names: " +totals);
        System.out.println("Total no of Girls names: " +totalgirls);
        System.out.println("Total no of Boys names: " +totalboys);
    }
    
    public int totalnoOfGirlsNames(FileResource fr){
        int totalGirlsNames = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            if(record.get(1).equals("F")){
                totalGirlsNames++;
            }
        }
        return totalGirlsNames;
    }
    
    public int totalnoOfBoysNames(FileResource fr){
        int totalBoysNames = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            if(record.get(1).equals("M")){
                totalBoysNames++;
            }
        }
        return totalBoysNames;
    }
    
    public int totalnoOfNames(FileResource fr){
        int totalNames = totalnoOfGirlsNames(fr) + totalnoOfBoysNames(fr);
        return totalNames;
    }
    
    public int getRank(int year, String name, String gender){
        int rank= 1;
        FileResource fr = new FileResource("us_babynames_by_year/yob" +Integer.toString(year) +".csv");
        for (CSVRecord record : fr.getCSVParser(false)){
            if(gender.equals("F")){
                if((record.get(0).equals(name)) && (record.get(1).equals(gender))){
                    return rank;
                } else {
                    rank++;
                }
            } else{
                if((record.get(0).equals(name)) && (record.get(1).equals(gender))){
                    return rank - totalnoOfGirlsNames(fr);
                } else {
                    rank++;
                }
            }
        }
        return -1;
     }
    
    public String getName(int year, int rank, String gender){
        int i = 1;
        String name = "";
        FileResource fr = new FileResource("us_babynames_by_year/yob" +Integer.toString(year) +".csv");
        if(gender.equals("M")){
            rank = rank + totalnoOfGirlsNames(fr);
        }
        for (CSVRecord record : fr.getCSVParser(false)){
            if(i == rank){
                return record.get(0);
            }else{
                i++;
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int rank= getRank(year,name,gender);
        System.out.println(rank);
        String newName= getName(newYear,rank,gender);
        System.out.println(newName);
        System.out.println(name +" born in " +year +" would be "+newName +" if was born in " +newYear +".");
    }
    
    public int yearOfHighestRank(String name,String gender){
        int tempRank= 999999999;
        int highestYear= -1;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt((f.getName()).substring(3,7));
            int currentRank = getRank(year,name,gender);
            if(tempRank > currentRank && currentRank!= -1) {
                tempRank = currentRank;
                highestYear = year;
            }
        }
        return highestYear;
    }
    
    public double getAverageRank(String name,String gender){
        double avgRank= 0;
        int noOfFiles= 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt((f.getName()).substring(3,7));
            double currentRank = getRank(year,name,gender);
            if(currentRank!= -1.0) {
                avgRank += currentRank;
                noOfFiles++;
            } else{
                return -1.0;
            }
        }
        return avgRank/noOfFiles;
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){ 
        int highestNoOfBirths = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob" +Integer.toString(year) +".csv");
        for (CSVRecord record : fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                if(record.get(0).equals(name)){
                    return highestNoOfBirths;
                } else {
                    int noOfBirths = Integer.parseInt(record.get(2));
                    highestNoOfBirths += noOfBirths;
                }
            }
        }
        return highestNoOfBirths;
    }
    
    public void test(){
        // FileResource fr = new FileResource();
        //totalBirths(fr);
        // System.out.println(totalnoOfBoysNames(fr));
        // System.out.println(getRank(1971,"Frank","M"));
        // System.out.println(getName(1982,450,"M"));
        whatIsNameInYear("Owen",1974,2014,"M");
        // System.out.println(yearOfHighestRank("Mich","M"));
        // System.out.println(getAverageRank("Robert","M"));
        // System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
}
