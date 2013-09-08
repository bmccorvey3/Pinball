import java.util.LinkedList;

public class Pinball {
	
	private static Circle circle, circle2, circle3;
	private static double s, r;

	// Adds two vectors
	public static double[] addVect(double[] v1, double[] v2) {
		double[] sum = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			sum[i] = v1[i] + v2[i];
		}
		return sum;
	}
	
	// Subtracts two vectors
	public static double[] subtractVect(double[] v1, double[] v2) {
		double[] diff = new double[v1.length];
		for (int i = 0; i < v1.length ; i++) {
			diff[i] = v1[i] - v2[i];
		}
		return diff;
	}
	
	// Multiply vector by a scalar
	public static double[] scalarMultiple(double scalar, double[] v1) {
		double[] multiple = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			multiple[i] = v1[i] * scalar;
		}
		return multiple;
	}
	
	// Dot product of two vectors
	public static double dotProduct(double[] v1, double[] v2) {
		double sum = 0;
		for (int i = 0; i < v1.length; i++) {
			sum += v1[i] * v2[i];
		}
		return sum;
	}
	
	// Returns the value w as given in the instructions
	private static double[] reflect(double[] c, double[] x1, double[] vec) {
		double[] cMinX = subtractVect(c, x1);
		double numer = dotProduct(scalarMultiple(2,vec),cMinX);
		double denom = dotProduct(cMinX, cMinX);
		double[] temp = scalarMultiple(numer / denom , cMinX);
		return (subtractVect(vec, temp));
	}
	
	// Returns the time of the intersection as given in the instructions
	public static double intersect(double[] c, double r, double[] x, double[] vec) {
		double[] diff = subtractVect(c,x);
		double dotP = dotProduct(vec, diff);
		if (dotP <= 0) {
			return -1;
		}
		double D = dotP*dotP - dotProduct(diff,diff) + r*r;
		if (D <= 0) {
			return -1;
		}
		return (dotP - Math.sqrt(D));
	}
	
	// Sees if an intercept occurs. If none return false.
	public static boolean isIntercept(double[] pos, double[] vec) {
		if ((intersect(circle.getCoord(), circle.getRadius(), pos, vec) == -1) &&
			(intersect(circle2.getCoord(), circle2.getRadius(), pos, vec) == -1) &&
			(intersect(circle3.getCoord(), circle3.getRadius(), pos, vec) == -1) ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//Finds circle intercept and returns circle
	public static Circle firstIntercept(double[] x, double[] v) {
		double val1 = intersect(circle.getCoord(), circle.getRadius(), x, v);
		double val2 = intersect(circle2.getCoord(), circle2.getRadius(), x, v);
		double val3 = intersect(circle3.getCoord(), circle3.getRadius(), x, v);
		
		// The next three if statements are very important
		// If the value is small, then each statement sets the value to a value that is larger than the other 
		// values. This makes sure that the bounces return do not stop at low values and continue on until 
		// all the bounces are reached.
		if (val1 == -1) {
			val1 = 100000.00;
			}
		if (val2 == -1) {
			val2 = 100000.00;
		}
		if (val3 == -1) {
			val3 = 100000.00;
		}
			
		if (val1 < val2 && val1 < val3) {
			return circle;
		}
		else if (val2 < val3) {
			return circle2;
		}
		else {
			return circle3;
		}
		
	}

	public static void main (String[] args) {
	   List list = new List();
		final int trials = 1000000;
		int[] freq = new int[trials];
		
		// For loops that goes through all the trials
		
		for (int i = 0; i < trials; i++) {
		
			// Uncomment the next two statements to make random, else method is systematic
			
			//double rand = Math.random();
			//double angle = 2.0 * Math.PI * rand;
			
			double angle = 2.0*Math.PI*i/trials;
			s = 6.0;
			r = 1.0;
			
			LinkedList<String> cList = new LinkedList<String>();
		
			double[] x = new double[2];
			x[0] = 0;
			x[1] = 0;
			double[] v = new double[2];
			v[0] = Math.cos(angle);
			v[1] = Math.sin(angle);
			
			// Initial coordinates of each circle
			
			double[] cir1 = {s/2, (-s * Math.sqrt(3) / 6) };
			circle = new Circle(r, cir1);
			
			double[] cir2 = {-s/2 , (-s * Math.sqrt(3) /6) };
			circle2 = new Circle(r, cir2);
			
			double[] cir3 = {0, (s * Math.sqrt(3) / 3) };
			circle3 = new Circle(r, cir3);
			
			// Add circle name to list 
			
			int count = 0;
			
			// While an intercept occurs 
			
			while (isIntercept(x,v)) {
				Circle temp = firstIntercept(x,v);
				if(temp == circle){
					cList.addLast("Circle 1");
				}
				else if(temp == circle2){
					cList.addLast("Circle 2");
				}
				else{
					cList.addLast("Circle 3");
				}
				double t = intersect(temp.getCoord(), temp.getRadius(), x, v);
				x = addVect(x, scalarMultiple(t,v));
				v = reflect(temp.getCoord(), x, v);
				count++;
			}
			
			freq[count]++;
			Score score = new Score(angle, count, cList);
			list.add(score);
		}
		
		// Amount of bounces 
		
		for (int i = 0; i < 11; i++) {
			System.out.println(i + "\t" + freq[i]);
		}

		System.out.println(list);
	}		
}