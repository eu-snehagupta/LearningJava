package WebLogProgram;


/**
 * Write a description of UniqueIPsTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueIPsTester {
    public void testUniqueIPs() {
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();  
        System.out.println("There are "+uniqueIPs +" unique IPs.");
    }
}
