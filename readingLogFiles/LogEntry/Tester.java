
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class Tester {
    public void LogEntryTester(){
        LogEntry log= new LogEntry("1.3.4.5",new Date(),"example request",300,400);
        System.out.println(log);
    }
}
