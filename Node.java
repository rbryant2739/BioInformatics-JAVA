import java.util.ArrayList;
import java.util.Collections;

public class Node{
		
		private String id;
		private ArrayList<String> edges;
		
		
		public Node(String id, String edge){
			this.id = id;
			this.edges = new ArrayList<String>();
			this.edges.add(edge);
		}
		public Node(String id, ArrayList<String> outgoingEdges){
			this.id = id;
			this.edges = outgoingEdges;
		}
		
		public void addEdge(String edge){
			edges.add(edge);
		}
		
		public String getID(){
			return this.id;
		}
		
		public ArrayList<String> getEdges(){
			return this.edges;
		}
		
		public String toString(){
			String retVal = "";
			int size = this.edges.size();
			
		    Collections.sort(this.edges);
		    
		    retVal += this.id + " -> ";
		    
		    for(int i = 0; i < size; i++){
		    	if(i == size - 1){
		    		retVal += this.edges.get(i);
		    	} else {
		    		retVal += this.edges.get(i) + ", ";
		    	}
		    }
			
			return retVal;
		}
		
	}