package prep;

public class Row {

    private double [] inputs;
    private int classification;

    public Row(double[] inputs, int classification) {
        this.inputs = inputs;
        this.classification = classification;
    }

    //calculates euclidean distance to another data point/row
    public double getEuclideanDistanceTo(Row otherDataPoint) {
        double sum = 0;

        //get expression inside square root
        for(int inputIndex = 0; inputIndex < inputs.length; inputIndex++)
            sum += Math.pow(this.inputs[inputIndex] - otherDataPoint.inputs[inputIndex], 2);

        return Math.sqrt(sum);
    }

    public double[] getInputs() {
        return inputs;
    }

    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    public int getClassification() {
		return classification;
	}

	public void setClassification(int classification) {
		this.classification = classification;
	}

    public static void printPoints(Row[] points) {
        for (Row row : points)
            System.out.println(row.toString());
    }

	@Override
    public String toString() {
        String output = "";

        for(int inputNumber = 0; inputNumber < inputs.length; inputNumber++)
            output += inputs[inputNumber] + " ";

        output += classification;

        return output;
    }
}
