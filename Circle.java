// Creates circle class with radius and coordinates

public class Circle {
	private double radius;
	private double[] coord;
	
	public Circle(double radius, double[] coord){
		this.radius = radius;
		this.coord = coord;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public double[] getCoord(){
		return coord;
	}
	
}
