
/**
 * Write a description of DecryptCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class DecryptCaesarCipher extends CaesarCipher {
    public int[] countLetters(String message){
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for(int k=0; k<message.length();k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if(dex!= -1){
                count[dex] +=1;
            }
        }
        return count;
    }
    
    public int maxIndex(int[] count){
        int maxCount = 0;
        for(int k = 0; k<count.length; k++){
            if(maxCount < count[k]){
                maxCount = count[k];
            }}
        return maxCount;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs= countLetters (encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex- 4;
        if(maxDex< 4){
            dkey= 26-(4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
    }
    
    public void testdecrypt(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "UGTT RPZT XC IWT RDCUTGTCRT GDDB!";
        System.out.println(decrypt(message));
    }
}

