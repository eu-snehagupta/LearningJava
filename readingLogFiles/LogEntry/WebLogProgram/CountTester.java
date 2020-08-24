package WebLogProgram;


/**
 * Write a description of CountTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class CountTester {
    public void testcount(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
}
