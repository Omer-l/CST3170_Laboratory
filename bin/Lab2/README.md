# Linear Categorisers

## Scenario

Make a linear classifer that separates the A's and the B's from the two scenarios below.
- Line Class 1
<img src="README_Images/lineClass1.png" heigh=200 width=200>

- Line Class 2
<img src="README_Images/lineClass2.png" heigh=400 width=400>


## Approach 1: Hard-coded linear classifier

Equation of a line is made using 2 hard-coded points. Below is the result of hard-coding two points to determine the best linear classifer.

This method is shown below for both Line Class 1 and Line Class 2. The labelled points (i.e. '(4, 6)' ) are hard-coded points used to create the linear interpolation.

- Line Class 1
<img src="README_Images/Hard_Coded_lineClass1.png" heigh=200 width=200>

- Line Class 2
<img src="README_Images/Hard_Coded_lineClass2.png" heigh=400 width=400> 

## Approach 2: Centroid
A learning line approach.
1. The point at the centre of class A points and the point at the centre of class B points are the **centroids**.

2. A line is made connecting the two centroids.

3. A perpendicular line intersects the line connecting the two centroids. This line intersects at exactly the midpoint of the two centroids. 

4. The perpendicular line classifies A and B points.

This approach does not always produce optimal results. Be that as it may, this approach is sufficiently accurate for this dataset.

Below is the linear interpolation of this method for both Line Class 1 and Line Class 2.

- Line Class 1
<img src="README_Images/Centroid_Classification_lineClass1.png" heigh=300 width=300>

- Line Class 2
<img src="README_Images/Centroid_Classification_lineClass2.png" heigh=400 width=400> 
