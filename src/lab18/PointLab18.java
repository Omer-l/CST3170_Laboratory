package lab18;

public class PointLab18 {

    private final double[] features;
    private double classification;

    public PointLab18(double[] features, double classification) {
        this.features = features;
        this.classification = classification;
    }

    /**
     *
     * @param 	point2 2nd point to get midpoint from
     * @return	midpoint of the 2 points
     */
    public PointLab18 getMidPointTo(PointLab18 point2) {
        int featureSize = features.length;
        double[] midpoint = new double[featureSize];
        for(int featureNumber = 0; featureNumber < featureSize; featureNumber++) {
            double featureOfPoint1 = features[featureNumber];
            double featureOfPoint2 = point2.features[featureNumber];
            double midpointOfFeatures = (featureOfPoint1 + featureOfPoint2) / 2;
            midpoint[featureNumber] = midpointOfFeatures;
        }
        double midpointClassification = (this.classification + point2.classification) / 2;
    	return new PointLab18( midpoint, midpointClassification);
    }

    /**
     *
     * @return 	average feature
     */
    public static double[] getAverageOfFeatures(PointLab18[] A) {
        int numberOfFeatures = A[0].features.length;
        int numberOfPoints = A.length;
        double[] averageOfFeatures = new double[numberOfFeatures];

        for(int featureNumber = 0; featureNumber < numberOfPoints; featureNumber++) {
            double sum = 0;

            for(int pointNumber = 0; pointNumber < numberOfPoints; pointNumber++) {
                double featureOfPoint = A[pointNumber].features[featureNumber];
                sum += featureNumber;
            }

            double averageOfFeature = sum / numberOfPoints;

            averageOfFeatures[featureNumber] = averageOfFeature;
        }

        return averageOfFeatures;
    }
    /**
     *
     * @return 	average classification
     */
    public static double getMeanClassification(PointLab18[] A) {
        double sum = 0;
        int numberOfPoints = A.length;

        for(PointLab18 point : A)
            sum += point.classification;

        return sum / numberOfPoints;
    }

    /**
     * this function returns the centre of a group of data points
     * @param A     array of classified points
     * @return      a Point in the centre of all the points.
     */
    public static PointLab18 getCentroid(PointLab18[] A) {
        return new PointLab18(PointLab18.getAverageOfFeatures(A), getMeanClassification(A));
    }


    /**
     * This function gets all points belonging to a specified class (i.e. 'A' or 'B')
     * @param allPoints			all the points in the file
     * @param classification	desired class
     * @return					an array of classified points
     */
    public static PointLab18[] getClassifiedPoints(PointLab18[] allPoints, double classification) {
        PointLab18[] classifiedPoints = new PointLab18[getNumberOfClassification(allPoints, classification)];
        int classifiedPointsIterator = 0;

        for(PointLab18 point : allPoints)
            if(point.classification == classification)
                classifiedPoints[classifiedPointsIterator++] = point;


        return classifiedPoints;
    }

    /**
     * This function counts how many of the specified classification (i.e. 'A' or 'B') points there are
     * @param allPoints			all the points in the file
     * @param classification	desired class
     * @return					total number of specified class
     */
    protected static int getNumberOfClassification(PointLab18[] allPoints, double classification) {
    	int counter = 0;

    	for(PointLab18 point : allPoints)
    		if(point.classification == classification)
    			counter++;

		return counter;
	}

    /**
     * Calculates the dot product of two vectors/points
     * @param point2    is the other vector/point
     * @return          dot product of this point and the given point
     */
    public double getDotProduct(PointLab18 point2) {
        double dotProduct = 0;

        for(int featureNumber = 0; featureNumber < features.length; featureNumber++) {
            double featurePoint1 = features[featureNumber];
            double featurePoint2 = point2.features[featureNumber];

            dotProduct += (featurePoint1 * featurePoint2);
        }

        return dotProduct;
    }

    public double getClassification() {
		return classification;
	}

	public void setClassification(double classification) {
		this.classification = classification;
	}

    public static void printPoints(PointLab18[] points) {
        for (PointLab18 pointLab18 : points)
            System.out.println(pointLab18.toString());
    }

	@Override
    public String toString() {
    	return "INPUTS: ( " + featuresToString() + ")" +
                "\nCLASSIFICATION: " + ((int)classification == 0? "" : + classification);
    }

    public String featuresToString() {
        String featuresToString = "";

        for(double feature : features)
           featuresToString += feature + ", ";
        return featuresToString;
    }
}
