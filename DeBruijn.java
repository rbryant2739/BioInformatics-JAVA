import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DeBruijn{
	
	public static void main(String[] arguments) throws NumberFormatException, IOException{
		
		
		BufferedReader br = new BufferedReader(new FileReader("inputfiles/DeBruijn.txt"));
		
		int k = Integer.parseInt(br.readLine());
		int kmer = k - 1;
		String sequence = br.readLine();
		int size = sequence.length() - kmer;
		ArrayList<Node> graph = new ArrayList<Node>();
		
		for(int i = 0; i < size; i++){
			
			String id = sequence.substring(i, i + kmer);
			String edge = sequence.substring(i + 1, i + kmer + 1);
			
			if(graph.isEmpty()){
				graph.add(new Node(id,edge));
			} else if(hasNode(graph, id)){
				getNode(graph, id).addEdge(edge);
			} else {
				graph.add(new Node(id, edge));
			}
		}
		
		Collections.sort(graph, new Comparator<Node>(){

			@Override
			public int compare(Node arg0, Node arg1) {
				
				return arg0.getID().compareTo(arg1.getID());
			}
			
		});
		
		for(Node n : graph){
			System.out.println(n.toString());
		}
		
	}
	
	public static boolean hasNode(ArrayList<Node> graph, String id){
		
		if(graph.isEmpty()){
			return false;
		} else {
			for(Node n : graph){
				if(n.getID().compareTo(id) == 0){
					return true;
				} 
			} return false;
		}
		
	}
    
	public static Node getNode(ArrayList<Node> graph, String id){
		for(Node n : graph){
			if(n.getID().compareTo(id) == 0){
				return n;
			}
		} return null;
	}
	
	
}
