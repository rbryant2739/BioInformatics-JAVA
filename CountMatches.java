import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CountMatches{
	
	public static void main(String[] arguments) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("inputfiles/rosalind_dna.txt"))) {
		    String pattern = br.readLine();
		    String text = "";
		    String line;
		    int idx = 0;
		    ArrayList<Integer> locs = new ArrayList<Integer>();
		    while ((line = br.readLine()) != null) {
		       text = text + line;
		    }
		    
		    while(idx >= 0) {
		    	
		    	idx = text.indexOf(pattern, idx);
		    	
		    	if(idx >= 0){
		    		locs.add(idx++);
		    	}
		    	
		     }
		    
		    
		  for (int i : locs){
			  System.out.print(i + " ");
		  }
		}
	}
	
	
}