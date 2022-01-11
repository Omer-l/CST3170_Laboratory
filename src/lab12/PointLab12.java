package lab12;

public class PointLab12 {

    private double x;
    private double y;
    private char classification;

    public PointLab12(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public PointLab12(double x, double y, char classification) {
        this.x = x;
        this.y = y;
        this.classification = classification;
    }
    
    /**
     * 
     * @param 	point2 2nd point to get midpoint from
     * @return	midpoint of the 2 points
     */
    public PointLab12 getMidPointTo(PointLab12 point2) {
    	return new PointLab12( (this.x + point2.x) / 2, (this.y + point2.y) / 2);
    }
    
    /**
     * This function gets all points belonging to a specified class (i.e. 'A' or 'B')
     * @param allPoints			all the points in the file
     * @param classification	desired class
     * @return					an array of classified points
     */
    public static PointLab12[] getClassifiedPoints(PointLab12[] allPoints, char classification) {
    	PointLab12[] classifiedPoints = new PointLab12[getNumberOfClassification(allPoints, classification)];
    	int classifiedPointsIterator = 0;
    	
    	for(PointLab12 point : allPoints)
    		if(point.classification == classification) 
    			classifiedPoints[classifiedPointsIterator++] = point;
    			
    	
    	return classifiedPoints;
    }

    /**
     *
     * @param	A array of points, add up all their x's return avg x
     * @return 	average x
     */
    public static double getAverageX(PointLab12[] A) {
        double sum = 0;

        for(PointLab12 a : A)
            sum += a.getX();

        return sum / A.length;
    }

    /**
     *
     * @param A array of points, add up all their y's return avg x
     * @return  average x
     */
    public static double getAverageY(PointLab12[] A) {
        double sum = 0;

        for(PointLab12 a : A)
            sum += a.getY();

        return sum / A.length;
    }

    /**
     * this function returns the centre of a group of data points
     * @param A     array of classified points
     * @return      a Point in the centre of all the points.
     */
    public static PointLab12 getCentroid(PointLab12[] A) {
        return new PointLab12(PointLab12.getAverageX(A), PointLab12.getAverageY(A));
    }

    /**
     * This function counts how many of the specified classification (i.e. 'A' or 'B') points there are
     * @param allPoints			all the points in the file
     * @param classification	desired class
     * @return					total number of specified class
     */
    protected static int getNumberOfClassification(PointLab12[] allPoints, char classification) {
    	int counter = 0;
    	
    	for(PointLab12 point : allPoints)
    		if(point.classification == classification)
    			counter++;
    	
		return counter;
	}

	public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public char getClassification() {
		return classification;
	}

	public void setClassification(char classification) {
		this.classification = classification;
	}

    public static void printPoints(PointLab12[] points) {
        for (PointLab12 pointLab12 : points)
            System.out.println(pointLab12.toString());
    }

	@Override
    public String toString() {
    	return "(" + x + ", " + y + ((int)classification == 0? "" : ", " + classification) + ")";
    }
}
