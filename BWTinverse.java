import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BWTinverse{
	
	
	public static void main(String[] arguments) throws IOException{
		
    BufferedReader br = new BufferedReader(new FileReader("inputfiles/bwt_inverse.txt"));
	String tform = br.readLine();
	int len = tform.length();
	char[] sorted_append = tform.toCharArray();
	
	Arrays.sort(sorted_append);
	
	String[] bwt_table = new String[tform.length()];
	
	for(int i = 0; i < len; i++){
		
		bwt_table[i] = "" + tform.charAt(i);
	}
	
	for(int idx = 0; idx < len; idx++){
		bwt_table[idx] += sorted_append[idx];
	}
	
	Arrays.sort(bwt_table);
	
	for(int x = 1; x < len - 1; x++){
		for(int y = 0; y < len; y++){
			bwt_table[y] = tform.charAt(y) + bwt_table[y];
		}
		Arrays.sort(bwt_table);
	}
	
	for(String s : bwt_table){
		if(s.charAt(len - 1) == '$'){
			System.out.println(s);
		}
	}
	
}
}