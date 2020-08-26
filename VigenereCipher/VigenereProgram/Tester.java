
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testCaesarCipher(){
        CaesarCipher obj = new CaesarCipher(4);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypt = obj.encrypt(input);
        String decrypt = obj.decrypt(encrypt);
        System.out.println("Encryted message: "+encrypt);
        System.out.println("Decryted message: "+decrypt);
    }
    
    public void testCaesarCracker(){
        // CaesarCracker obj1 = new CaesarCracker();
        // FileResource fr1 = new FileResource();
        // String input1 = fr1.asString();
        // System.out.println(obj1.decrypt(input1));
        
        CaesarCracker obj2 = new CaesarCracker('a');
        FileResource fr2 = new FileResource();
        String input2 = fr2.asString();
        System.out.println(obj2.decrypt(input2));
    }
    
    public void testVigenereCipher(){
        int key[]= {17,14,12,4};
        VigenereCipher obj = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypt = obj.encrypt(input);
        String decrypt = obj.decrypt(encrypt);
        System.out.println("Encryted message: "+encrypt);
        System.out.println("Decryted message: "+decrypt);
    }
}
