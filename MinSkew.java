import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MinSkew{
	
	public static void main(String[] arguments) throws FileNotFoundException{
		
		String dna;
		
		ArrayList<Integer> idxs = new ArrayList<Integer>();
		ArrayList<Integer> minSkews = new ArrayList<Integer>();
		
		int skew = 0;
		
		Scanner in = new Scanner(new FileReader("inputfiles/rosalind_dna.txt"));
		
		StringBuilder sb = new StringBuilder();
		while(in.hasNext()) {
		    sb.append(in.next());
		}
		in.close();
		dna = sb.toString();
		int dnalen = dna.length();
		
		for(int idx = 0; idx < dnalen - 1; idx++){
			idxs.add(skew);
			if(dna.charAt(idx) == 'G'){
				skew++;
			}else if(dna.charAt(idx) == 'C'){
				skew--;
			}
				
		}
		
		int min = Collections.min(idxs);
		int end = idxs.size() - 1;
		
		for(int idx = 0; idx < end; idx++){
			
			if(idxs.get(idx) == min){
				minSkews.add(idx);
			}
		}
				
		for(int i : minSkews){
			System.out.print(i+" ");
		}
		
	}	
	
}