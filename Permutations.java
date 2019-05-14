package permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
	//Test cases
	public static void main(String[] args) {
		permutation(3,3); //returns "213"
		permutation (4,9); //returns "2314"
		System.out.println();
		int [] sumTest1 = {2,3,6,7};
		sumMultiple(sumTest1, 7); //Returns "7" and "2,2,3"
		int [] sumTest2 = {2,3,5};
		sumMultiple(sumTest2, 8); //Returns "2,2,2,2" and "2,3,3", and "3,5"
		System.out.println();
		int [] sumTest3 = {10,1,2,7,6,1,5};
		sumOnce(sumTest3, 8); //Returns "1,7" and "1,2,5" and "2,6" and "1,1,6"
		int [] sumTest4 = {2,5,2,1,2};
		sumOnce(sumTest4, 5); //Returns "1,2,2" and "5"
		System.out.println();
		combinations(3,7); //Returns "1,2,4"
		combinations(3,9); //Returns "1,2,6" and "1,3,5" and "2,3,4"
	}
	
	//Finds the kth permutation within a list from 1 to n
	public static void permutation (int n, int k) {
				//Create a list from 1 to n
				ArrayList<Integer> numbers = new ArrayList<Integer>();
				for (int i = 1; i <= n; i++) {
					numbers.add(i);
				}
				//Decrement k (because we start at 0 and not 1)
				k--;
				//Get factorial of n
				int mod = 1;
				for (int i = 1; i <= n; i++) {
					mod *= i;
				}
				//String for our result
				String res = "";
				//Get the sequence
				for (int i = 0; i < n; i++) {
					mod = mod / (n - i);
					int bufferent = k / mod;
					k = k % mod;
					res += numbers.get(bufferent);
					numbers.remove(bufferent);
				}
				//Print the result
				System.out.println (res.toString());
			}
	
	
	//Finds all combinations of numbers in n that add up to k
	//Numbers in n can be used multiple times
	public static void sumMultiple (int [] n, int k) {
		//Create a list of list of integers and a buffer
		List<List<Integer>> result = new ArrayList<>();
        List<Integer> buffer = new ArrayList<>();
        //Search for combinations
        searchMultiple(result, buffer, n, 0, k);
        //Print combinations
        for (List<Integer> list : result) {
        	   for (int a : list) {
        	       System.out.print(a + " "); 
        	   }
        	   System.out.println();
        	} 
        System.out.println();
    }

	//Method for sumMultiple
    private static void searchMultiple(List<List<Integer>> result, List<Integer> buffer, int[] n, int index, int k) {
        //If k < 0 we return
    		if (k < 0) {
            return;
        }
    		//If k is 0 we add the buffer to our results
        if (k == 0) {
            result.add(buffer);
        }
        //Recursive function to find all combinations
        for (int i = index; i < n.length; i++) {
            if (i > 0 && n[i] == n[i - 1]) { 
            	continue;
            }
            List<Integer> temp = new ArrayList<Integer>(buffer);
            temp.add(n[i]);
            searchMultiple(result, temp, n, i, k - n[i]);
        }
    }
	
	//Finds all combinations of numbers in n that add up to k
		//Numbers in n can be used only once
	public static void sumOnce (int [] n, int k) {
		//Create a list of list of integers and a buffer
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	    List<Integer> buffer = new ArrayList<Integer>();
	    //Sort our input list
	    Arrays.sort(n);
	    //Search for combinations
	    searchOnce(result, buffer, 0, k, n);
	    //Print combinations
	    for (List<Integer> list : result) {
     	   for (int a : list) {
     	       System.out.print(a + " "); 
     	   }
     	   System.out.println();
     	} 
     System.out.println();
	}
	//Method for sumOnce
	public static void searchOnce(List<List<Integer>> result, List<Integer> buffer, int start, int k, int[] n){
		//If k is 0 we add the buffer to our results
		if(k==0){
	        result.add(new ArrayList<Integer>(buffer));
	        return;
	    }
		 //If k < 0 we return
	    if(k<0){
	        return;
	    }
	  //Recursive function to find all combinations, use previous to make sure we don't use the same number twice
	    //Or add the same combination
	    int previous=-1;
	    for(int i=start; i<n.length; i++){
	        if(previous!=n[i]){ 
	            buffer.add(n[i]);
	            searchOnce(result, buffer, i+1, k-n[i], n); 
	            buffer.remove(buffer.size()-1);
	            previous=n[i];
	        }
	    }
	}
	
	//Find all combinations of k numbers that add up to a number n
	//Only 1-9 may be used and each combination should be a unique set of numbers
	public static void combinations (int k, int n) {
		//Create a list of list of integers and a buffer
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 List<Integer> buffer = new ArrayList<Integer>();
		 //Search for combinations
		 searchCombinations(result, buffer, k, 1, n);
		  //Print combinations
		    for (List<Integer> list : result) {
	     	   for (int a : list) {
	     	       System.out.print(a + " "); 
	     	   }
	     	   System.out.println();
	     	} 
	     System.out.println();
	}
	//Method for Combinations
	public static void searchCombinations(List<List<Integer>> result, List<Integer> buffer, int k, int start, int sum){
		//If sum < 0 we return
	    if(sum<0){
	        return;
	    }
	    //If sum is 0 and the buffer size is k we add the buffer to our results
	    if(sum==0 && buffer.size()==k){
	        result.add(new ArrayList<Integer>(buffer));
	        return;
	    }
	    //From start -> 9 we add i to the buffer and recursively call searchCombinations
	    for(int i=start; i<=9; i++){
	    	buffer.add(i);
	    	searchCombinations(result, buffer, k, i+1, sum-i);
	    buffer.remove(buffer.size()-1);
	    }
	}
}
