import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KMP {
	
	
    
    public static void main(String args[]) throws FileNotFoundException, IOException{
    	
    	
       
    	
    	try (BufferedReader br = new BufferedReader(new FileReader("inputfiles/rosalind_dna.txt"))) {
		    String pattern = br.readLine();
		    String text = "";
		    String line;
		   
		    while ((line = br.readLine()) != null) {
		       text = text + line;
		    }
  
    	
   
    
        int M = pattern.length();
        int N = text.length();
 
       
        int lps[] = new int[M];
        int j = 0;  
 
        int i = 0;  
        while (i < N)
        {
            if (pattern.charAt(j) == text.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                System.out.println("Found pattern "+
                              "at index " + (i-j));
                j = lps[j-1];
            }
 
          
            else if (i < N && pattern.charAt(j) != text.charAt(i))
            {
                
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
 
        int size = 0;
        int idx = 1;
        lps[0] = 0; 
 
        
        while (idx < M) {
            if (pattern.charAt(idx) == pattern.charAt(size))   {
                size++;
                lps[idx] = size;
                idx++;
            }
            else {
               
                if (size != 0){
                    size = lps[size-1];
 
                
                }
                else 
                {
                    lps[idx] = size;
                    idx++;
                }
            }
        }
    }

    }
}

