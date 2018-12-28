public class MultiDimPoint{
	


    private	double[] coords;
	private int dim;
	
	public MultiDimPoint(double[] coordinates, int dimension){
		this.coords = coordinates;
		this.dim = dimension;
	}
	
	public double[] getCoords(){
		return this.coords;
	}
	
	public int getDimension(){
		return this.dim;
	}
	
	public double getDistance(MultiDimPoint otherP){
		double sum = 0;
		double[] otherCoords = otherP.getCoords();
		
		for(int i = 0; i < this.dim; i++){
			sum += Math.pow(this.coords[i] - otherCoords[i], 2);
		}
		
		return Math.sqrt(sum);
	}
	
	public boolean equals(MultiDimPoint otherP){
		double[] otherPcoords = otherP.getCoords();
		
		for(int idx = 0; idx < this.dim; idx++){
			
			if(this.coords[idx] != otherPcoords[idx]){
				return false; 
			}
		}
		return true;
		
	}
	public String toString(){
		String retVal = "";
		
		for(int i = 0; i < this.dim; i++){
			retVal += " " + this.coords[i];
		}
		
		return retVal;
	}
}