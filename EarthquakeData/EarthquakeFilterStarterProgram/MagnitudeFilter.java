
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    
    public MagnitudeFilter(double magMin, double magMax){
        minMag = magMin;
        maxMag = magMax;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag);
    }
}
