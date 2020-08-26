import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder str = new StringBuilder();
        for(int k=whichSlice; k<message.length();k+=totalSlices){
            str.append(message.charAt(k));
        }
        return str.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker obj = new CaesarCracker(mostCommon);
        for(int k=0;k<klength;k++){
            String ekey= sliceString(encrypted,k,klength);
            key[k]=obj.getKey(ekey);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for (String line: fr.lines()){
            line= line.toLowerCase();
            dictionary.add(line);
        }
        return dictionary;
    }
    
    public int countWords(String message,HashSet<String> dictionary){
        int count = 0;
        String words[]= message.split("\\W+");
        for(int k=0;k<words.length;k++){
            String s = words[k];
            if(dictionary.contains(s.toLowerCase())){
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
            int arr[] = tryKeyLength(encrypted,38,'e');
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
    
    public void breakVigenereWithKeyLength () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        int arr[] = tryKeyLength(input,4,'e');
        VigenereCipher obj = new VigenereCipher(arr);
        System.out.println(obj.decrypt(input));
    }
    
    public void breakVigenereWithoutKeyLength () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        FileResource dr = new FileResource("dictionaries\\English");
        HashSet<String> dict = readDictionary(dr);
        System.out.println(breakForLanguage(input,dict));
    }
    
    public void test(){
        //System.out.println(sliceString("abcdefghijklm",4,5));
        FileResource fr = new FileResource();
        String input = fr.asString();
        int arr[] = tryKeyLength(input,4,'e');
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
