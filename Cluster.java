import java.util.ArrayList;

public class Cluster {
 
	private ArrayList<MultiDimPoint> points;
	
   
	
	public Cluster(){
		this.points = new ArrayList<MultiDimPoint>();
		
	}
	
 	public Cluster(ArrayList<MultiDimPoint> points, int dimension){
 		this.points = points;
 		
 	}
 	
 	public void addPoint(MultiDimPoint point){
 		this.points.add(point);
 	}
 	
 	public MultiDimPoint getCenter(){
 		int size = this.points.size();
 		int dimension = this.points.get(0).getDimension();
 		double[] sum = new double[dimension];
 		double numPoints = (double) size;
 		
 		for(MultiDimPoint p : points){
 			double[] coords = p.getCoords();
 			for(int i = 0; i < dimension; i++){
 				sum[i] += coords[i];
 			}
 		}
 		
 		double[] centerCoords = new double[dimension];
 		//System.out.println(dimension);
 		for(int idx = 0; idx < dimension; idx++){
 			centerCoords[idx] = sum[idx] / numPoints;
 		}
 		/*for(int p = 0; p < centerCoords.length; p++){
 		System.out.println(centerCoords[p]);
 		}*/
 		return new MultiDimPoint(centerCoords, dimension);
 		
 	}
 	
 	public void clearCluster(){
 		for(MultiDimPoint p : points){
 			points.remove(p);
 		}
 	}
 	
 	public String toString(){
 		String retVal = "";
 		
 		for(MultiDimPoint p : this.points){
 			retVal += p.toString() + "\n";
 		}
 		
 		return retVal;
 	}
 
}
