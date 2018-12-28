import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class CharCount{


	public static void main(String[] arguments) throws FileNotFoundException{
	
	String dna = new String();
	HashMap<Character, Integer> counter = new HashMap<Character, Integer>();
	int strlen, a, c, g, t;
	
	Scanner in = new Scanner(new FileReader("inputfiles/rosalind_dna.txt"));
	
	StringBuilder sb = new StringBuilder();
	while(in.hasNext()) {
	    sb.append(in.next());
	}
	in.close();
	dna = sb.toString();
	
	strlen = dna.length();
	
	for(int i = 0; i < strlen; i++){
		char temp = dna.charAt(i);
		if( counter.containsKey( temp ) ){
		counter.put(temp, counter.get(temp) + 1);
		} else {
			counter.put(temp, 1);
		}
	}
	
	if(counter.containsKey('A'))
		a = counter.get('A');
	else 
		a = 0;
	if(counter.containsKey('C'))
		c = counter.get('C');
	else
		c = 0;
	if(counter.containsKey('G'))
		g = counter.get('G');
	else
		g = 0;
	if(counter.containsKey('T'))
		t = counter.get('T');
	else
		t = 0;
	
	System.out.print(a + "    "+ c +"    "+ g +"   " + t);
	
}

}