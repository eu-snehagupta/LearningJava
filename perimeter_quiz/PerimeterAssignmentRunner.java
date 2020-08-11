import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for ( Point i : s.getPoints()) {
        count ++;
    }
    return count;  
    }

    public double getAverageLength(Shape s) {
        double totalPerimeter= getPerimeter(s);
        int totalnNoPoints = getNumPoints(s);
        double avgLength = totalPerimeter/totalnNoPoints;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (largestSide < currDist) {
                largestSide = currDist;
                prevPt = currPt;
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if (largestX < currX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(largestPerimeter < perimeter) {
                largestPerimeter = perimeter;
            }
    }
return largestPerimeter;
}

    public String getFileWithLargestPerimeter() {
        File temp = null;
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(largestPerimeter < perimeter) {
                largestPerimeter = perimeter;
                temp = f;
            }
        }
    return temp.getName();
   }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double longestLength = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("No of Points = " + numPoints);
        System.out.println("Average of Lengths = " + avgLength);
        System.out.println("Longest Side = " + longestLength);
        System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String fileLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("File With Largest Perimeter = " + fileLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
