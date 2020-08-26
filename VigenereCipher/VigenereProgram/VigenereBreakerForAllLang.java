import java.util.*;
import edu.duke.*;
import java.io.File;
import java.io.*;

public class VigenereBreakerForAllLang {
    private char CommonChar;
    
    public VigenereBreakerForAllLang(){
        CommonChar = 'e';
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alph= "abcdefghijklmnopqrstuvwxyz";
        int[] alphcount= new int[26];
        int max = 0;
        for(String s:dictionary){
            int wordLength = s.length();
            for(int k=0; k<wordLength;k++){
                char ch= s.charAt(k);
                int letter = alph.indexOf(ch);
                alphcount[letter]=+1;
            }
        }
        for(int i=0;i<alphcount.length;i++){
            if(max<alphcount[i]){
                max=alphcount[i];
            }
        }
        return alph.charAt(max);
    }
    
    public HashMap<String,HashSet<String>> readDictionary(FileResource fr){
        HashMap<String,HashSet<String>> langdict = new HashMap<String,HashSet<String>>();
        HashSet<String> dict = new HashSet<String>();
        //String lang = fr.getName();
        for (String line: fr.lines()){
            line= line.toLowerCase();
            dict.add(line);
        }
        CommonChar = mostCommonCharIn(dict);
        langdict.put(lang,dict);
        return langdict;
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder str = new StringBuilder();
        for(int k=whichSlice; k<message.length();k+=totalSlices){
            str.append(message.charAt(k));
        }
        return str.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength) {
        int[] key = new int[klength];
        CaesarCracker obj = new CaesarCracker(CommonChar);
        for(int k=0;k<klength;k++){
            String ekey= sliceString(encrypted,k,klength);
            key[k]=obj.getKey(ekey);
        }
        return key;
    }
    
    public int countWords(String message,HashMap<String,HashSet<String>> langdict){
        int count = 0;
        String words[]= message.split("\\W+");
        for(int k=0;k<words.length;k++){
            String s = words[k];
            if(langdict.contains(s.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int max = 0;
        String message = "";
        int dkeylength = 0;
        for(int i=1; i<=100;i++){
            int arr[] = tryKeyLength(encrypted,i);
            VigenereCipher obj = new VigenereCipher(arr);
            String decreption = obj.decrypt(encrypted);
            int count = countWords(decreption,dictionary);
            if(max<count){
                max = count;
                message = decreption;
                dkeylength =arr.length;
            }
        }
        System.out.println("The valid words in the decrypted string: "+max);
        System.out.println("The key length used to encrypt the file: "+dkeylength);
        return message;
        // System.out.println("The valid words in the decrypted string: "+count);
        // return decreption;
    }
    
    public String breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int maxCount = 0;
        String decreptmsg = "";
        String bestlanguage = "";
        for (String s: languages.keySet()){
            String decreptionMessage = breakForLanguage(encrypted, languages.get(s));
            int count = countWords(decreptionMessage, languages.get(s));
            if(maxCount<count){
                maxCount = count;
                decreptmsg = decreptionMessage;
                bestlanguage = s;
            }
        }
        System.out.println("The valid words in the decrypted string: "+maxCount);
        System.out.println("Language used: "+bestlanguage);
        return decreptmsg;
    }
 
    public void breakVigenereWithoutKeyLengthLanguage () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        FileResource dr = new FileResource("dictionaries\\English");
        HashSet<String> dict = readDictionary(dr);
        System.out.println(breakForAllLangs(input,dict));
    }
}
