
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
    
    public void testHowMany(){
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA","ATAAAA"));
    }
}

