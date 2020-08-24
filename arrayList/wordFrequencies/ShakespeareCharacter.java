
/**
 * Write a description of ShakespeareCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.ArrayList;

public class ShakespeareCharacter {
    private ArrayList<String> allCharacter;
    private ArrayList<Integer> allFreqs;
    
    public ShakespeareCharacter() {
        allCharacter = new ArrayList<String>();
        allFreqs = new ArrayList<Integer>();;    
    }
    
    public void update(String person){
        int index = allCharacter.indexOf(person);
        if(index == -1){
            allCharacter.add(person);
            allFreqs.add(1);
        } else{
            int count= allFreqs.get(index);
            allFreqs.set(index,count+1);
        }
    }
    
    public void findAllCharacters(){
        allCharacter.clear();
        allFreqs.clear();
        FileResource file = new FileResource();
        for(String line : file.lines()){
            int characterline = line.indexOf(". ");
            if(characterline != -1){
                String charactername= line.substring(0,characterline);
                char check = line.charAt(characterline-1);
                if(Character.isUpperCase(check)){
                    update(charactername);    
                }
            }
        }
    }
    
    public void charactersWithNumParts(int num1,int num2){
        for (int k=0; k<allFreqs.size(); k++){
            int num= allFreqs.get(k);
            if (num1<=num && num<=num2){
                System.out.println(allCharacter.get(k));
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        int max=0;
        String maxSpeaker="";
        for (int k=0; k<allCharacter.size(); k++){
            System.out.println(allCharacter.get(k) +" spoke for" + allFreqs.get(k)+" times ");
            if (max<allFreqs.get(k)){
                max = allFreqs.get(k);
                maxSpeaker = allCharacter.get(k);
            }
        }
        System.out.println(maxSpeaker +" spoke max for " + max +" times ");
        charactersWithNumParts(2,3);
    }
}

