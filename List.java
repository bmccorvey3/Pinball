public class List {

	// Class that adds each score to a list with a defined amount of entries

	private final int maxEntries = 10; 
	private int numEntries;
	private Score[] entries;
  
  
	public List() { 
		entries = new Score[maxEntries];
  		numEntries = 0;
  	}
	
	// Adds score to entry if array is not full and also orders entries
	// by number of bounces

  	public void add(Score entry) {
    	int newScore = entry.getBounces();
    	if (numEntries == maxEntries) { 
    		if (newScore <= entries[numEntries-1].getBounces()) {
    	  		return;  
			}
   	}
    	else {     	
			numEntries++;
		}
    	
    	int i = numEntries-1; 
    	for (; (i >= 1) && (newScore > entries[i-1].getBounces()); i--)
      	entries[i] = entries[i - 1]; 	  
    		entries[i] = entry; 			  
  	}
	
	// Formats entries in list and separates them by commas
  
  	public String toString() {
    	String s = "[";
    	for (int i = 0; i < numEntries; i++) {
      	if (i > 0) s  += ", "; 
      	s += entries[i];
    	}
    	return s + "]";
  	}
	
}