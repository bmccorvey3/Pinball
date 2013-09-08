import java.util.LinkedList;

public class Score {
	
	private double angle;
   private int bounces;	
   private LinkedList<String> cList;
    
	public Score(double a, int b, LinkedList<String> cList) {
      this.angle = a;
      this.bounces = b;
      this.cList = cList;
    }
	 
	// Formats each score in entry 
    
	public String toString() { 
    	String result = "(" + angle + " radians, " + bounces + " bounces";
    	for (int i=0; i < cList.size(); i++){
    		result += ", ";
    		result += cList.get(i);
    	}
    	result += ")";
    	return result; 
    }

	public double getAngle() { 
		return angle; 
	}
 
	public int getBounces() { 
		return bounces; 
	}

	public void setBounces(int i) {
    	bounces = i;
   }
	     
}
