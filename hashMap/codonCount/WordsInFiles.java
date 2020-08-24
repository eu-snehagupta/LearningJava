
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordInFilesMap;

    public WordsInFiles() {
        wordInFilesMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File file) {
        FileResource fileResource = new FileResource(file);
        String fileName = file.getName();
        for (String word : fileResource.words()) {
            if (!wordInFilesMap.containsKey(word)) {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(fileName);
                wordInFilesMap.put(word, newList);
            } else if (wordInFilesMap.containsKey(word)
                    && !wordInFilesMap.get(word).contains(fileName)) {
                ArrayList<String> currentList = wordInFilesMap.get(word);
                currentList.add(fileName);
                wordInFilesMap.put(word, currentList);
            }
        }
    }

    private void buildWordFileMap() {
        wordInFilesMap.clear();
        DirectoryResource dirResource = new DirectoryResource();
        for (File file : dirResource.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    private int maxNumber() {
        int highest = 0;
        for (String word : wordInFilesMap.keySet()) {
            ArrayList<String> currentFileList = wordInFilesMap.get(word);
            int currentNum = currentFileList.size();
            if (currentNum > highest) {
                highest = currentNum;
            }
        }
        return highest;
    }

    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        for (String word : wordInFilesMap.keySet()) {
            ArrayList<String> currentList = wordInFilesMap.get(word);
            int currentFileCount = currentList.size();
            if (currentFileCount == number) {
                wordList.add(word);
            }
        }
        return wordList;
    }

    private void printFilesIn(String word) {
        ArrayList<String> fileNames = wordInFilesMap.get(word);
        for (int index = 0; index < fileNames.size(); index++) {
            System.out.println(fileNames.get(index));
        }
    }

    public void tester() {
        buildWordFileMap();
        int fileNum = maxNumber();
        System.out.println("Max number files any word is in: " + fileNum);

        ArrayList<String> wordsInFiles = wordsInNumFiles(fileNum);
        System.out.println("Total words in all files: " + wordsInFiles.size());
        wordsInFiles = wordsInNumFiles(4);
        System.out.println("Total words in four files: " + wordsInFiles.size());
        printFilesIn("birds");
        System.out.println("\n");
        printFilesIn("and");
    }
}