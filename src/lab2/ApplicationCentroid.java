package lab2;

public class ApplicationCentroid {

    public static void main(String[] args) {
        FileReaderLab2 fileReader = new FileReaderLab2("lineClass2.txt");
        PointLab2[] allPoints = fileReader.getData();
    	PointLab2[] A = PointLab2.getClassifiedPoints(allPoints, 'A');
    	PointLab2[] B = PointLab2.getClassifiedPoints(allPoints, 'B');

        System.out.println("A POINTS:");
        PointLab2.printPoints(A);
        System.out.println("B POINTS:");
        PointLab2.printPoints(B);

        Line classifyingLine = getClassifyingLine(A, B);
        System.out.println(classifyingLine);

    }

    /**
     * This function gets two centroid points from two groups of points and then calculates the midpoint.
     * @param 	A	array A of Points
     * @param 	B	array B of Points
     * @return	    a line that is perpendicular to the `line connecting the two centroids` (specifically passing the midpoint of that line)
     */
    public static Line getClassifyingLine(PointLab2[] A, PointLab2[] B) {
        //get average X and Y for A
        PointLab2 centroidOfA = new PointLab2(PointLab2.getAverageX(A), PointLab2.getAverageY(A));
        //get average X and Y for B
        PointLab2 centroidOfB = new PointLab2(PointLab2.getAverageX(B), PointLab2.getAverageY(B));

        //create line connecting the two centroids
        Line AtoBLine = new Line(centroidOfA, centroidOfB);

        //get midpoint between centroids.
        PointLab2 midPointOfCentroids = centroidOfA.getMidPointTo(centroidOfB);

        //get perpendicular line to centroid, this is the classifer.
        Line perpendicularLine = AtoBLine.getPerpendicularLine(midPointOfCentroids);

        return perpendicularLine;
    }
}
