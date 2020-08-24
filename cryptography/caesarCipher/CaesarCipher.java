
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypt = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedalphabet = alphabet.substring(key) + alphabet.substring(0,key);
        for(int i=0; i<encrypt.length(); i++){
            char currentChar = input.charAt(i);
            if(Character.isUpperCase(currentChar)==true){
                int idx= alphabet.indexOf(currentChar);
                if(idx!= -1){
                char newChar = shiftedalphabet.charAt(idx);
                encrypt.setCharAt(i,newChar);
                }
            } else{
                currentChar = Character.toUpperCase(currentChar);
                int idx= alphabet.indexOf(currentChar);
                if(idx!= -1){
                char newChar = Character.toLowerCase(shiftedalphabet.charAt(idx));
                encrypt.setCharAt(i,newChar);
                }
            }
        }
        return encrypt.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypt = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String evenalphabet = alphabet.substring(key1) + alphabet.substring(0,key1); //XYZABCDEFGHIJKLMNOPQRSTUVW
        String oddalphabet = alphabet.substring(key2) + alphabet.substring(0,key2); //RSTUVWXYZABCDEFGHIJKLMNOPQ
        for(int i=0; i<encrypt.length(); i++){
            char currentChar = input.charAt(i);
            if(Character.isUpperCase(currentChar)==true){
                int idx= alphabet.indexOf(currentChar);
                if(idx != -1){
                    if(i%2 == 0){
                        char newChar= evenalphabet.charAt(idx);
                        encrypt.setCharAt(i,newChar);
                    } else{
                        char newChar= oddalphabet.charAt(idx);
                        encrypt.setCharAt(i,newChar);
                    }
                }
            } else{
                currentChar = Character.toUpperCase(currentChar);
                int idx= alphabet.indexOf(currentChar);
                if(idx!= -1){
                    if(i%2 == 0){
                        char newChar= Character.toLowerCase(evenalphabet.charAt(idx));
                        encrypt.setCharAt(i,newChar);
                    } else{
                        char newChar= Character.toLowerCase(oddalphabet.charAt(idx));
                        encrypt.setCharAt(i,newChar);
                    }
                }
            }
            
        }
        return encrypt.toString();
    }
    
    public void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted1 = encrypt(message, 15);
        System.out.println("key is  15: " + encrypted1);
        System.out.println(encrypt(encrypted1, 11));
        
        //String encrypted2 = encryptTwoKeys(message,8,21);
        //System.out.println("\n" + encrypted2);
    }
}
