import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MostProbKmer {
	
	
	 @SuppressWarnings("null")
	public static void main(String args[]) throws FileNotFoundException, IOException{
	    	
	    	
	       
	    	
	    	try (BufferedReader br = new BufferedReader(new FileReader("inputfiles/prob_Kmer.txt"))) {
			    String strand = br.readLine();
			    int len = strand.length();
			    int size = Integer.parseInt(br.readLine());
			    String line;
			    String dnaProb[][] = new String[4][size];
			    double probs[][] = new double[4][size];
			    String temp_kmer = "";
			    double max_kmer = 0.0;
			    double temp = 1;
			    String str_kmer = "";
				    
			   
			   for(int i=0; i < 4; i++){
			    	line = br.readLine();
			    	dnaProb[i] = line.split(" ");
			    } 
			    
			   for(int x=0; x < 4; x++){
				   for(int y=0; y<size; y++){					   
					   probs[x][y] =  Double.parseDouble(dnaProb[x][y]);
				   }
			   }
			   
			   
			   
			   for(int j = 0; j < len - size; j++ ){
				   for(int k = j; k < j + size; k++){
					   switch(strand.charAt(k)) {
					   
					   case 'A' : temp *= probs[0][k - j];
					   			  temp_kmer  += 'A';
					   			  break;
					   
					   case 'C' : temp *= probs[1][k - j];
					   			  temp_kmer += 'C';
					   			  break;
					   
					   case 'G' : temp *= probs[2][k - j];
					   			  temp_kmer += 'G';
					   			  break;
					  
					   case 'T' : temp *= probs[3][k - j];
					   		      temp_kmer += 'T';
					              break;
					   }
				   } if(temp > max_kmer){
					   max_kmer = temp;
					   str_kmer = temp_kmer;
					   
					   //System.out.println(max_kmer);
					   //System.out.println(temp_kmer);
					   
				   } temp = 1;
				     temp_kmer = "";
			   }
			   
			  /* for(int j=0; j < 4; j++){
		    		for(int k =0; k< size; k++){
		    			System.out.print(probs[j][k] + " ");
		    			
		    		} System.out.print("\n");
		    	} */   //debugging print
			    
			   System.out.println(str_kmer);
			    
	    	}//end try
	    	
	    	
	    		}	//end main()
}//end class