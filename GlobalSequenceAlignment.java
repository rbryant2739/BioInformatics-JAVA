import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GlobalSequenceAlignment{
	 
	
	
	
	public static void main(String[] arguments) throws IOException{
	
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("inputfiles/sequence_align.txt"));
		BufferedReader blsm = new BufferedReader(new FileReader("inputfiles/BLOSUM62.txt"));
		    String sequence1 = br.readLine();
		    String sequence2 = br.readLine();
		
		String[][] tempS = new String[20][20];
		int [][] tempI = new int[20][20];
		
			for(int idx = 0; idx < 20; idx++){
				String line = blsm.readLine();
				tempS[idx] = line.split("\\s+");
			}
			
			for(int i = 0; i < 20; i++){
				for(int j = 0; j < 20; j++){
					tempI[i][j] = Integer.parseInt(tempS[i][j]);
				}
			}
			
		
		int sigma = -5;
		int dim1 = sequence1.length() + 1;
		int dim2 = sequence2.length() + 1;
		
		int[][] blosum = new int[26][26];
		int[][] scoreMatrix = new int[dim1][dim2];
		String[][] optimalPath = new String[dim1][dim2];
		
		
		
		blosum = createBlosum(tempI);
		buildScoreMatrix(blosum, scoreMatrix, optimalPath, dim1, dim2, sigma, sequence1, sequence2);
		
		String result = getOutput(optimalPath, sequence1, sequence2);
		
		result = scoreMatrix[dim1 - 1][dim2 -1] + "\n" + result; 
		
		System.out.println(result);
		   // prettyPrint(optimalPath, dim1, dim2);
		   // prettyPrint(scoreMatrix, dim1, dim2);
		//prettyPrint(blosum, 26, 26);
		//for(int i = 0; i < 26; i++){
			//System.out.println(blosum[i].length);
		//}
		//System.out.println(sequence1);
		//System.out.println(sequence2);
		
			
	}
	
	public static String getOutput(String[][] path, String seq1, String seq2){
		int ival = path.length - 1;
		int jval = path[0].length - 1;
		int seq1_iter = seq1.length() - 1;
		int seq2_iter = seq2.length() - 1;
		String new_seq1 = "";
		String new_seq2 = "";
		
		while(jval > 0 || ival > 0){
				if(path[ival][jval].compareTo("up") == 0){
					new_seq2 = "-" + new_seq2;
					new_seq1 = seq1.charAt(seq1_iter--) + new_seq1;
					ival--;
				} else if(path[ival][jval].compareTo("left") == 0){
					new_seq1 = "-" + new_seq1;
					new_seq2 = seq2.charAt(seq2_iter--) + new_seq2;
					jval--;
				} else if(path[ival][jval].compareTo("diag") == 0){
					new_seq1 = seq1.charAt(seq1_iter--) + new_seq1;
					new_seq2 = seq2.charAt(seq2_iter--) + new_seq2;	
					ival--;
					jval--;
				}
		}
		
		
		return new_seq1 + "\n" + new_seq2;
	}
	
	public static void buildScoreMatrix(int[][] blosum, int[][] score, String[][] path, int dim1, int dim2, int sigma,
											String seq1, String seq2)
	{
		
		int up, left, diag;
		int b1, b2;
		
		for(int i = 1; i < dim1; i++ ){
			score[i][0] = i * sigma;
			path[i][0] = "up";
		}
		
		for(int j = 1; j < dim2; j++){
			score[0][j] = j * sigma;
			path[0][j] = "left";
		}
	    
		for(int x = 1; x < dim1; x++){
			for(int y = 1; y < dim2; y++){
				
				b1 = (int) seq1.charAt(x - 1) - 65;
				b2 = (int) seq2.charAt(y - 1) - 65;
				
				up = score[x - 1][y] + sigma;
				left = score[x][y - 1] + sigma;
				diag = score[x - 1][y - 1] + 
						blosum[b1][b2];
				

				//System.out.println(b1);
				
				if(up >= left && up >= diag){
					score[x][y] = up;
					path[x][y] = "up";
				} else if(left >= up && left >= diag){
					score[x][y] = left;
					path[x][y] = "left";
				} else {
					score[x][y] = diag;
					path[x][y] = "diag";
				}
			}
		}
	    		
	}
	
	
	// methods to print matrix for debug purposes
	
	public static void prettyPrint(int[][] scoreMatrix, int dim1, int dim2){
		
		for(int i = 0; i < dim1; i++){
			for(int j = 0; j < dim2; j++){
				System.out.print(scoreMatrix[i][j] + " ");
			}
			System.out.println("");
		}
		
	}
	public static void prettyPrint(String[][] scoreMatrix, int dim1, int dim2){
		
		for(int i = 0; i < dim1; i++){
			for(int j = 0; j < dim2; j++){
				System.out.print(scoreMatrix[i][j] + " ");
			}
			System.out.println("");
		}
		
	}
	
	public static int[][] createBlosum(int[][] temp){

		int[][] blosum = new int[26][26];
		int ival = 0, jval = 0;
		
		
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				if(i > 18){
					ival = i + 5;
				} else if(i > 16){
					ival = i + 4;
				} else if (i > 11){
					ival = i + 3;
				} else if (i > 7){
					ival = i + 2;
				} else if (i > 0){
					ival = i + 1;
				} else {
					ival = i;
				}
				if(j > 18){
					jval = j + 5;
				} else if(j > 16){
					jval = j + 4;
				} else if (j > 11){
					jval = j + 3;
				} else if (j > 7){
					jval = j + 2;
				} else if (j > 0){
					jval = j + 1;
				} else {
					jval = j;
				}
				
				blosum[ival][jval] = temp[i][j];
				
			}
		}
		
		
		
		return blosum;
	}
	
	
	
}