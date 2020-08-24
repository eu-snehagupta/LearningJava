
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource file = new FileResource();
        for(String word : file.words()){
            word= word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
            } else{
                int count= myFreqs.get(index);
                myFreqs.set(index,count+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int maxFreq = 0;
        int maxIdx = 0;
        for(int k=0; k<myFreqs.size(); k++){
            if(maxFreq < myFreqs.get(k)){
                maxIdx= k;
            }
        }
        return maxIdx;
    }
    
    public void tester(){
        findUnique();
        System.out.println("#Unique Words: " +myWords.size());
        for(int k=0; k<myWords.size(); k++){
           System.out.println(myWords.get(k) +" : " +myFreqs.get(k)); 
        }
        System.out.println("The word that occurs most often and its count are: "+myWords.get(findIndexOfMax()) +" " +findIndexOfMax());
    }
}
