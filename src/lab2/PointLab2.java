package lab2;

public class PointLab2 {

    private double x;
    private double y;
    private char classification;

    public PointLab2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public PointLab2(double x, double y, char classification) {
        this.x = x;
        this.y = y;
        this.classification = classification;
    }
    
    /**
     * 
     * @param 	point2 2nd point to get midpoint from
     * @return	midpoint of the 2 points
     */
    public PointLab2 getMidPointTo(PointLab2 point2) {
    	return new PointLab2( (this.x + point2.x) / 2, (this.y + point2.y) / 2);
    }
    
    /**
     * This function gets all points belonging to a specified class (i.e. 'A' or 'B')
     * @param allPoints			all the points in the file
     * @param classification	desired class
     * @return					an array of classified points
     */
    public static PointLab2[] getClassifiedPoints(PointLab2[] allPoints, char classification) {
    	PointLab2[] classifiedPoints = new PointLab2[getNumberOfClassification(allPoints, classification)];
    	int classifiedPointsIterator = 0;
    	
    	for(PointLab2 point : allPoints)
    		if(point.classification == classification) 
    			classifiedPoints[classifiedPointsIterator++] = point;
    			
    	
    	return classifiedPoints;
    }

    /**
     *
     * @param	A array of points, add up all their x's return avg x
     * @return 	average x
     */
    public static double getAverageX(PointLab2[] A) {
        double sum = 0;

        for(PointLab2 a : A)
            sum += a.getX();

        return sum / A.length;
    }

    /**
     *
     * @param A array of points, add up all their y's return avg x
     * @return  average x
     */
    public static double getAverageY(PointLab2[] A) {
        double sum = 0;

        for(PointLab2 a : A)
            sum += a.getY();

        return sum / A.length;
    }

    /**
     * this function returns the centre of a group of data points
     * @param A     array of classified points
     * @return      a Point in the centre of all the points.
     */
    public static PointLab2 getCentroid(PointLab2[] A) {
        return new PointLab2(PointLab2.getAverageX(A), PointLab2.getAverageY(A));
    }

    /**
     * This function counts how many of the specified classification (i.e. 'A' or 'B') points there are
     * @param allPoints			all the points in the file
     * @param classification	desired class
     * @return					total number of specified class
     */
    protected static int getNumberOfClassification(PointLab2[] allPoints, char classification) {
    	int counter = 0;
    	
    	for(PointLab2 point : allPoints)
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

    public static void printPoints(PointLab2[] points) {
        for (PointLab2 pointLab2 : points)
            System.out.println(pointLab2.toString());
    }

	@Override
    public String toString() {
    	return "(" + x + ", " + y + ((int)classification == 0? "" : ", " + classification) + ")";
    }
}
