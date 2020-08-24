
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        if(ch =='a' || ch == 'e' || ch =='i' || ch =='o' || ch =='u'){
          return true;  
        }
        return false;
    }
    
    public boolean isCharacter(char ch1,char ch2){
        ch1 = Character.toLowerCase(ch1);
        ch2 = Character.toLowerCase(ch2);
        if(ch1 ==ch2){
          return true;  
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch){
       StringBuilder newPhrase =new StringBuilder(phrase);
        for(int i=0; i< newPhrase.length(); i++){
            char currentCh = newPhrase.charAt(i);
            if(isVowel(currentCh) == true){
                newPhrase.setCharAt(i,ch);
            }
        }
        return newPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder newPhrase =new StringBuilder(phrase);
        int idx = 0;
        for(int i=0; i< newPhrase.length(); i++){
            char currentCh = newPhrase.charAt(i);
            idx = phrase.indexOf(currentCh,idx+1);
            if(isCharacter(currentCh,ch) == true){
                if(idx % 2 ==0){
                    newPhrase.setCharAt(i,'*');
                }else{
                    newPhrase.setCharAt(i,'+');
                }
            }
        }
        return newPhrase.toString();        
    }
    
    public void tester(){
        System.out.println(isVowel('F'));
        System.out.println(isVowel('o'));
        System.out.println(replaceVowels("Hello World",'*'));
        System.out.println(emphasize("dna ctgaaactga",'a'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }
}
