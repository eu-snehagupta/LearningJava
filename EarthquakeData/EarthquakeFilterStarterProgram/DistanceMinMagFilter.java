
/**
 * Write a description of DistanceMinMagFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceMinMagFilter implements Filter{
    private Location loc;
    private double maxDist;
    private double magMin; 
    
    public DistanceMinMagFilter(Location from, double distRange, double min){
        loc = from;
        maxDist = distRange;
        magMin = min;
    }
    
    public boolean satisfies(QuakeEntry qe){
        Location locationQe = qe.getLocation();
        return locationQe.distanceTo(loc) <= maxDist && qe.getMagnitude() >= magMin;
    }
}
