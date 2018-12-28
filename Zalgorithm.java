
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Zalgorithm {
	
	public static void main(String[] args) throws IOException {
		
		String pattern = "";
		String text ="";
		int idx = 0;
		ArrayList<Integer> locs = new ArrayList<Integer>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("inputfiles/rosalind_dna.txt"))) {
		     pattern = br.readLine();
		     text = "";
		    String line;
		    while ((line = br.readLine()) != null) {
		       text = text + line;
		    }
		}
		int[] z;
		
		String concatenatedText, output = "\nPattern found at indices = { ";
		
		
		concatenatedText = pattern + '$' + text;		
		
		System.out.println("Initializing array...");
		
		z = new int[concatenatedText.length()];		
		
		System.out.println("Generating values of the array...");
		
		generateZ(z, concatenatedText);		
		
		for (int i = 0; i < z.length; i++) {
			if (z[i] == pattern.length()) {		
				output += (i - pattern.length()) + ", ";		
			}
		}
		System.out.println(pattern);
		System.out.println(output);
		//output = output.substring(0, output.lastIndexOf(", ")) + " }";		
		System.out.println(output.lastIndexOf(", "));
		System.out.println(output);		
	}
	
	private static void generateZ(int[] z, String concatenatedText) {
		int l = 0, r = 0, k = 0;		

		for (int i = 1; i < concatenatedText.length(); i++) {
			if (i > r) {		
				l = r = i;

				while (r < concatenatedText.length() && concatenatedText.charAt(r - l) == concatenatedText.charAt(r)) {		// initially (r - l) = 0...
					r++;
				}

				z[i] = r - l;
				r--;
			}
			else {
				k = i - l;		
				if (z[k] < r - i + 1) {		
					z[i] = z[k];
				}
				else {		
					l = i;

					while (r < concatenatedText.length() && concatenatedText.charAt(r - l) == concatenatedText.charAt(r)) {
						r++;
					}

					z[i] = r - l;
					r--;
				}
			}
		}
	}
	
}