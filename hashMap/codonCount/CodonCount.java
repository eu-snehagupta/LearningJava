
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        if(map!=null){
            map.clear();
        }
        for(int k=start; k+3<dna.length(); k+=3){
            String codon = dna.substring(k,k+3);
            if(!map.keySet().contains(codon)){
                map.put(codon,1);
            } else{
               map.put(codon,map.get(codon)+1);
            }
        }
    }
    
    public void printCodon(){
        for(String s: map.keySet()){
            System.out.println(s+ "\t" +map.get(s));
        }
    }
    
    public String getMostCommonCodon(){
        int max= 0;
        String maxCodon = "";
        for(String s: map.keySet()){
            int count = map.get(s);
            if(max<count){
                max=count;
                maxCodon=s;
            }
        }
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end){
        for(String s: map.keySet()){
            int count = map.get(s);
            if(start<=count && count<=end){
                System.out.println(s+ "\t" +map.get(s));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        buildCodonMap(0,dna);
        printCodon();
        System.out.println(getMostCommonCodon());
        printCodonCounts(1,2);
    }
}
