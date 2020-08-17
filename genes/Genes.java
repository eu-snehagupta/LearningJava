
/**
 * Write a description of Genes here.
 * 
 * @Sneha
 * @08_11_2020
 */


import edu.duke.*;
import java.io.File;

public class Genes {
    
    public int findStopCodon(String dna,int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        while (currIndex!= -1){
            if ((currIndex- startIndex)%3 == 0){
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon,currIndex);
            }
        }
        return -1;
    }
    
    public String findGene(String dna,int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if (taaIndex== -1 || (tgaIndex!= -1 && tgaIndex <taaIndex)){
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource genList = new StorageResource();
        int startIndex = 0;
        //int count = 0;
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            //System.out.println(currentGene);
            genList.add(currentGene);
            //count++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        //return count;
        return genList;
    }
    
    public void testOn(String dna){
        System.out.println("Our dna string: " + dna);
        //System.out.println("Our dna count: " +printAllGenes(dna));
        StorageResource genes = getAllGenes(dna);
        for( String g: genes.data()){
            System.out.println("Our dnagenes: " +g);
        }
        System.out.println("Our gene count: " +genes.size());
    }
    
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGAATGTAA");
        
    }
}
