
/**
 * Write a description of Dna here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class Dna {
    
    public int howMany(String stringa,String stringb){
        int count= 0;
        int fromIndex= 0;
        while(stringb.indexOf(stringa, fromIndex) != -1){
            count++;
            fromIndex= stringb.indexOf(stringa, fromIndex) +  stringa.length();
        }
        return count;  
    }
    
    public double cgRatio(String dna){
        return (double)((double)dna.length()/(howMany("C",dna)+howMany("G",dna)));
    }
    
    
    
    public void test(){
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA","ATAAAA"));
        System.out.println(cgRatio("ATGCCATAG"));
        System.out.println(howMany("CTG","ATGCCATAG"));
        System.out.println(howMany("CTG","ATGCTGCTGATAGCATG"));
    }
}

