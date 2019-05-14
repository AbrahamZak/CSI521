package probabilities;

import java.util.LinkedList;
import java.util.List;

public class Probabilities {

	public static void main(String[] args) {
		//Test for possible parenthesis, should print "2 0"
		List<Integer> testParenthesis = possibleParenthesis("2-1-1");
		for (int i : testParenthesis) {
			System.out.print(i + " ");
		}
		System.out.println();
		//Test for possible parenthesis, should print "-34, -10, -14, -10, 10"
				List<Integer> testParenthesis2 = possibleParenthesis("2*3-4*5");
				for (int i : testParenthesis2) {
					System.out.print(i + " ");
				}	
		System.out.println();
		
		//Test for knight probability, should print 0.0625
		System.out.println(knightProbability(3,2,0,0));
	}
	
	//Given a string of numbers and operators, return all possible results from computing all the
	//different possible ways to group numbers and operators. The valid operators are +, − and ?.
	public static List<Integer> possibleParenthesis(String input) {
		List<Integer> result = new LinkedList<Integer>();
        for (int i=0; i<input.length(); i++) {
        	//Check which operation
            if (input.charAt(i) == '-' ||
                input.charAt(i) == '*' ||
                input.charAt(i) == '+' ) {
            		//Split the input
                String split1 = input.substring(0, i);
                String split2 = input.substring(i+1);
                List<Integer> result1 = possibleParenthesis(split1);
                List<Integer> result2 = possibleParenthesis(split2);
                //Do the operations and store in countc
                for (Integer a :   result1) {
                    for (Integer b :   result2) {
                        int countc = 0;
                        switch (input.charAt(i)) {
                            case '+': countc = a+b;
                                break;
                            case '-': countc = a-b;
                                break;
                            case '*': countc = a*b;
                                break;
                        }
                        //Add countc to our results
                        result.add(countc);
                    }
                }
            }
        }
        //Add the result as integer
        if (result.size() == 0) {
            result.add(Integer.valueOf(input));
        }
        //Return the results
        return result;
    }
	
	//Knight probability to stay on chess board
	public static double knightProbability(int N, int K, int r, int c) {
		//Direct for rows and columns
        int[] directr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] directc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
			double[][] grid = new double[N][N];
	        grid[r][c] = 1;
	        //For K moves
	        for (; K > 0; K--) {
	        	//Create a second grid
	            double[][] gridTwo = new double[N][N];
	            //For rows columns and 8 (size of board)
	            for (int countr = 0; countr < N; countr++) {
	                for (int countc = 0; countc < N; countc++) {
	                    for (int k = 0; k < 8; k++) {
	                    	//Get all possible directions
	                        int cr = countr + directr[k];
	                        int cc = countc + directc[k];
	                        if (0 <= cr && cr < N && 0 <= cc && cc < N) {
	                            gridTwo[cr][cc] += grid[countr][countc] / 8.0;
	                        }
	                    }
	                }
	            }
	            grid = gridTwo;
	        }
	        //Result double
	        double res = 0.0;
	        //For the grid and each row increment result by each chance
	        for (double[] row: grid) {
	            for (double x: row) {
	            	res += x;
	            }
	        }
	        return res;
	    }
    }
    


