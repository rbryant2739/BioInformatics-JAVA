import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LloydsAlgorithm{
	
	public static void main(String arguments[]) throws IOException{
		
		ArrayList<MultiDimPoint> points = new ArrayList<MultiDimPoint>();
		ArrayList<MultiDimPoint> centers= new ArrayList<MultiDimPoint>();
		BufferedReader br = new BufferedReader(new FileReader("inputfiles/LloydInput.txt"));
	    String[] knm = br.readLine().split(" ");
	    int[] km = new int[2];
	    for(int i = 0; i < 2; i++){
	    	km[i] = Integer.parseInt(knm[i]);
	    }
	    boolean converges = false;
	    int numCtrs = km[0];
	    int dim = km[1];
	    Cluster[] clusters = new Cluster[numCtrs];
	    String line = br.readLine();
	    
	    while(line != null){
	    	double[] ptCoords = new double[dim];
	    	String[] coords = line.split(" ");
	    	for(int i = 0; i < dim; i++){
	    		ptCoords[i] = Double.parseDouble(coords[i]);
	    	}
	    	points.add(new MultiDimPoint(ptCoords, dim));	
	    	line = br.readLine();
	    }
	    for(int idx = 0; idx < numCtrs; idx++){
	    	centers.add(idx, points.get(idx));
	    }
	    
	   do{ 
	    	ArrayList<MultiDimPoint> newCenters = new ArrayList<MultiDimPoint>();
	    	clusters = createClusters(points, centers, dim);
	    	MultiDimPoint tempCenter;
	    	for(int i = 0; i < numCtrs; i++){
	    		newCenters.add(i, clusters[i].getCenter());
	    	}
	    	
	    	//System.out.println(clusters[0].getCenter());
	    
	    	//System.out.print(newCenters);
	    	//System.out.print(centers);
	    	converges = true;
	    	for(int idx = 0; idx < numCtrs; idx++){
	    		if(!newCenters.get(idx).equals(centers.get(idx))){
	    			converges = false;
	    		}
	    		//System.out.println(converges);
	    	} if(!converges){
	    		centers = newCenters;
	    	}
	    
	   } while(!converges);
	   
	   
	   for(MultiDimPoint p : centers){
		
		   double temp;
		   
		   double[] coords = p.getCoords();
		   
		   int length = coords.length;
		   
		   for(int i = 0; i < length; i++){
			   
			   coords[i] = (double)Math.round(coords[i] * 1000d) / 1000d;
			   System.out.print(String.format("%.3f", coords[i]) + " ");
			   
		   } System.out.println();
	   }
	   
	   
	}
	
	public static Cluster[] createClusters(ArrayList<MultiDimPoint> points, ArrayList<MultiDimPoint> centers, int dimension){
		
		int numCtrs = centers.size();
		Cluster[] clusters = new Cluster[numCtrs];
		double minDistance = 1000000000000000000.00;
		int clusterID = -1;
		
		for(int i = 0; i < numCtrs; i++){
			clusters[i] = new Cluster();
		}
		
		for(MultiDimPoint p : points) {
		for(int idx = 0; idx < numCtrs; idx++){
			double distance = p.getDistance(centers.get(idx));
			if(distance < minDistance){
				minDistance = distance;
				clusterID = idx;
			}
		}
		//System.out.println(clusterID);
		//System.out.println(minDistance);
		clusters[clusterID].addPoint(p);
		minDistance = 10000000000000000.00;
		}
		
		/*for(int p = 0; p < numCtrs; p++){
			System.out.println(clusters[p].toString());
			System.out.print("\n\n\n\n\n");
		}*/
		return clusters;
		
	}
	
	
}