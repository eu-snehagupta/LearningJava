import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location city =  new Location(35.42, 139.43);

        Filter f1 = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f1); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        
        Filter f2 = new MagnitudeFilter(4.5,5.0); 
        ArrayList<QuakeEntry> m8  = filter(m7, f2); 
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        }
        
        Filter f3 = new DistanceFilter(city,10000.0); 
        ArrayList<QuakeEntry> m9  = filter(list, f3); 
        for (QuakeEntry qe: m9) { 
            System.out.println(qe);
        }
        
        Filter f4 = new DistanceMinMagFilter(city,10000.0,4.0); 
        ArrayList<QuakeEntry> m10  = filter(list, f4); 
        for (QuakeEntry qe: m10) { 
            System.out.println(qe);
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
