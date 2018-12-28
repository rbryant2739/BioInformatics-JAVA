import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CyclopeptideSequencing1 {
    public static int[] AMINO_ACIDS = {57,71,87,97,99,101,103,113,114,115,128,129,131,137,147,156,163,186};

    public static void main(String[] args) throws IOException {
    	
    	try (BufferedReader br = new BufferedReader(new FileReader("inputfiles/ideal_spectrum.txt"))){
    		String str_spectrum = br.readLine();
    		String[] pepts = str_spectrum.split(" ");
    		ArrayList<Integer> spectrum =  new ArrayList<Integer>();
    		
    		for(String pept : pepts){
    			spectrum.add(Integer.parseInt(pept));
    		}
    		
    		
    		
    	int n = 5;
        

        ArrayList<Integer> peptide = leaderBoard(spectrum, n);
           
        List<List<Integer>> myLists = permutations(peptide);
        
        Collections.sort(myLists, new Comparator<List<Integer>>() {
            
        	public int compare(List<Integer> o1, List<Integer> o2) {
                
        		if(o1.size() != o2.size()){
        			System.out.println("lists are two different sizes!");
        		} else {
        			int terminate = 0;
        			for(int i = 0; i < o1.size() - 1; i++){
        				terminate = -(o1.get(i).compareTo(o2.get(i)));
        				if(terminate != 0){
        					return terminate;
        				}
        			}
        		} return 0;
            
        	}
        });
        for (List<Integer> al : myLists) {
            String appender = "";
            for (Integer i : al) {
                System.out.print(appender + i);
                appender = "-";
            }
            System.out.print(" ");
        }

      
    }
    }


    private static ArrayList<Integer> leaderBoard(ArrayList<Integer> spectrum, int n) {
       
    	
    	ArrayList<ArrayList<Integer>> leaderBoard = new ArrayList<>();
        
    	ArrayList<Integer> leaderPeptide = new ArrayList<>();
        
    	int parentMass = spectrum.get(spectrum.size() - 1);

        leaderBoard.add(new ArrayList<Integer>());
        
        
        while (!leaderBoard.isEmpty()) {
        
        	leaderBoard = expand(leaderBoard);
            
        	ArrayList<ArrayList<Integer>> tempLeaderBoard = new ArrayList<>(leaderBoard);
            
        	for (ArrayList<Integer> peptide : leaderBoard) {
            
        		int mass = total(peptide);
                
        		if(mass == parentMass) {
                   
        			if(score(peptide, spectrum) > score(leaderPeptide, spectrum)) {
                    
        				leaderPeptide = peptide;
                    
        			}
                } 
        		else if(mass > parentMass){
                
        			tempLeaderBoard.remove(peptide);
                }
            }
            
        	leaderBoard = cut(tempLeaderBoard, n, spectrum);
        
        }

        return leaderPeptide;
    }
    //Expand by adding Amino Acids 1 by 1, as done in textbook
    private static ArrayList<ArrayList<Integer>> expand(ArrayList<ArrayList<Integer>> leaderBoard) {
       
    	ArrayList<ArrayList<Integer>> expandedLeaders = new ArrayList<>();
        
    	for (ArrayList<Integer> leader : leaderBoard) {
        
    		for (int i = 0; i < AMINO_ACIDS.length; i++) {
            
    			ArrayList<Integer> expandedLeader = new ArrayList<>(leader);
                
    			expandedLeader.add(AMINO_ACIDS[i]);
                
    			expandedLeaders.add(expandedLeader);
            }
        }
        return expandedLeaders;
    }

    private static int total(ArrayList<Integer> peptide) {
       
    	int sum = 0;
        
    	for (Integer integer : peptide) {
        
    		sum += integer;
        
    	}
        
    	return sum;
    
    }
    //gives score based off how many of theoretical spectrum masses are in the experimental spectrum
    //to see how similar they are
    private static Integer score(ArrayList<Integer> peptide, ArrayList<Integer> spectrum) {
       
    	ArrayList<Integer> eSpec = new ArrayList<>(spectrum);
        
    	ArrayList<Integer> tSpec = getSpectrum(peptide);
        
    	int score = 0;
        
    	for (Integer integer : tSpec) {
        
    		if(eSpec.contains(integer)) {
            
    			score++;
                
    			eSpec.remove(integer);
            }
        }
        return score;
    }

    private static ArrayList<Integer> getSpectrum(ArrayList<Integer> peptide) {
        
    	ArrayList<ArrayList<Integer>> subtides = subPeptides(peptide);
        
    	ArrayList<Integer> spectrum = new ArrayList<>();
        
    	spectrum.add(0);
        
    	for (ArrayList<Integer> sub : subtides) {
        
    		int total = 0;
            
    		for (Integer mass : sub)
            
    			total += mass;
            
    		spectrum.add(total);
        }
       
    	Collections.sort(spectrum);
        
    	return spectrum;
    }

    private static ArrayList<ArrayList<Integer>> subPeptides(ArrayList<Integer> peptide) {

    	ArrayList<ArrayList<Integer>> subtides = new ArrayList<>();
        
    	subtides.add(peptide);
        
    	ArrayList<Integer> extendedPeptide = new ArrayList<>(peptide);
        
    	extendedPeptide.addAll(peptide.subList(0, peptide.size() > 2 ? peptide.size() - 2 : 0));
        
    	for (int i = 0; i < peptide.size(); i++) {
           
    		for (int j = 0; j < peptide.size() - 1; j++) {
            
    			subtides.add(new ArrayList<>(extendedPeptide.subList(i, i + j + 1)));
            
    		}
        
    	}
        
    	return subtides;
    }
    
    
    //Takes leaderboard and cuts accordingly, based on the golf logic keeping the top n (including ties)
    //Deleting takes too much time and makes program terminate, adding appropriate to new leaderboard is what
    //ended up working in the end
    
    private static ArrayList<ArrayList<Integer>> cut(ArrayList<ArrayList<Integer>> leaderBoard, int n, final ArrayList<Integer> spectrum){
        
    	if(n > leaderBoard.size())
        
    		return leaderBoard;

        Collections.sort(leaderBoard, new Comparator<ArrayList<Integer>>() {
            
        	public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            
        		return -(score(o1, spectrum)).compareTo(score(o2, spectrum));
            
        	}
        });

        ArrayList<ArrayList<Integer>> newLeaderBoard = new ArrayList<>();
        
        Integer lastScore = score(leaderBoard.get(n), spectrum);
        
        for (int i = 0; i < leaderBoard.size(); i++) {
          
        		if(i < n) {
                
        			newLeaderBoard.add(leaderBoard.get(i));
            
        		} else if(score(leaderBoard.get(i), spectrum) >= lastScore) {
                
        			newLeaderBoard.add(leaderBoard.get(i)); 
            }
        }
        return newLeaderBoard;
    }
    
    //returns a list of integer lists, each integer list being a permutation of the peptide
    
    public static List<List<Integer>> permutations(List<Integer> list) {

        if (list.size() == 0) {
           
        	List<List<Integer>> result = new ArrayList<List<Integer>>();
            
        	result.add(new ArrayList<Integer>());
            
        	return result;

}

        List<List<Integer>> permutations = new ArrayList<List<Integer>>();

        Integer lead = list.remove(0);

        List<List<Integer>> allPerms = permutations(list);
        
        for (List<Integer> perm : allPerms) {

            for (int idx = 0; idx <= perm.size(); idx++) {
               
            	List<Integer> curr = new ArrayList<Integer>(perm);
                
            	curr.add(idx, lead);
                
            	permutations.add(curr);
            }

        }
        return permutations;
    }
}