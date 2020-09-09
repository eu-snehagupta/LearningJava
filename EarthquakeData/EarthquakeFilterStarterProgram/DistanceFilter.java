
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location loc;
    private double maxDist;
    
    public DistanceFilter(Location from, double distRange){
        loc = from;
        maxDist = distRange;
    }
    
    public boolean satisfies(QuakeEntry qe){
        Location locationQe = qe.getLocation();
        return locationQe.distanceTo(loc) <= maxDist;
    }
}
