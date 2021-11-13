package Lab6;

public class Garden {

	private double height;
	private double width;
	private Plants plant;
	
	
	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public Plants getPlant() {
		return plant;
	}


	public void setPlant(Plants plant) {
		this.plant = plant;
	}


	public Garden(double height, double width) {
		this.height = height;
		this.width = width;
	}
	
	@Override
	public String toString() {
		return "GARDEN WIDTH: " + width + ",\t HEIGHT: " + height + ",\t PLANT: " + plant.toString();
	}
}
