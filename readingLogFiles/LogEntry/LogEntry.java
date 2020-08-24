
/**
 * Write a description of LogEntry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LogEntry {
    private String ipAdress;
    private Date acessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;
    
    public LogEntry(String ip,Date time,String req,int status,int bytes){
        ipAdress=ip;
        acessTime=time;
        request=req;
        statusCode=status;
        bytesReturned=bytes;
    }
    
    public String getIpAdress(){
        return ipAdress;
    }
    
    public Date getacessTime(){
        return acessTime;
    }
    
    public String getrequest(){
        return request;
    }
    
    public int getstatusCode(){
        return statusCode;
    }
    
    public int getbytesReturned(){
        return bytesReturned;
    }
    
    public String toString(){
        return ipAdress+" "+acessTime+" "+request+" "+statusCode+" "+bytesReturned; 
    }
}
