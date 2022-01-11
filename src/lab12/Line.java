package lab12;

public class Line {
	// give two points to create a line.
	private PointLab12 A;
	private PointLab12 B;

	public Line(PointLab12 a, PointLab12 b) {
		A = a;
		B = b;
	}

	/**
	 * @param C new point to sub into the equation of this Line
	 * @return returns false if Point C is below or on the Line.
	 */
	public boolean aboveLine(PointLab12 C) { // C.y > (m)(C.x) + c, then it is above the line
		if (C.getY() > (getGradient() * C.getX()) + getYIntercept())
			return true;
		else
			return false;
	}

	public double getGradient() {
		return (B.getY() - A.getY()) / (B.getX() - A.getX());
	}

	// sub in x and y, solve for y-intercept. eq: y = mx + c -> c = y - mx
	public double getYIntercept() {
		return A.getY() - (getGradient() * A.getX());
	}

	//This function returns the perpendicular line's gradient to this line. -1/m
	public double getPerpendicularGradient() {
		return - (1 / getGradient());
	}

	/**
	 * This function calculates the gradient and y-intercept of the line perpendicular at the point given and returns the perpendicular line.
	 * @param pointOnLine   a point at which to intersect
	 * @return              a perpendicular line that intersects at the given 'pointOnLine'
	 */
	public Line getPerpendicularLine( PointLab12 pointOnLine) {
		//get gradient of perpendicular line
		double perpendicularGradient = getPerpendicularGradient();

		//calculate the Y intercept. c = y - mx
		double mx = (perpendicularGradient * pointOnLine.getX());
		double y = pointOnLine.getY();
		double perpendicularLineYIntercept = y - mx;

		//(0, y-intercept) counts as a point on the perpendicular line, use this point with the pointOnLine to create the line.
		PointLab12 pointToCreatePerpendicularLine = new PointLab12(0, perpendicularLineYIntercept);

		//create perpendicular line to AtoBLine to classify points.
		Line perpendicularLine = new Line(pointToCreatePerpendicularLine, pointOnLine);

		return perpendicularLine;
	}

	public PointLab12 getA() {
		return A;
	}

	public void setA(PointLab12 a) {
		A = a;
	}

	public PointLab12 getB() {
		return B;
	}

	public void setB(PointLab12 b) {
		B = b;
	}

	@Override
	public String toString() {
		return "Linear classifier equation -> y = " + getGradient() + "x + "
				+ getYIntercept();
	}
}
