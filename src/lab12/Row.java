package lab12;

public class Row {

    private double [] inputs;
    private int classification;

    public Row(double[] inputs, int classification) {
        this.inputs = inputs;
        this.classification = classification;
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
