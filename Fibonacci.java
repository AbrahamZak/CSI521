package fibonacci;

public class Fibonacci {

	public static void main(String[] args) {
		//Print out the Fibonacci number with the three different methods
		long sTime = System.nanoTime();
		System.out.println(fibFormula(10));
		long fTime   = System.nanoTime();
		long fTotalTime = fTime - sTime;
		System.out.println("Time needed by formula: " + fTotalTime);
		System.out.println(fibIteration(10));
		long iTime   = System.nanoTime();
		long iTotalTime = iTime - fTime;
		System.out.println("Time needed by iteration: " + iTotalTime);
		System.out.println(fibRecursion(10));
		long rTime   = System.nanoTime();
		long rTotalTime = rTime - iTime;
		System.out.println("Time needed by recursion: " + rTotalTime);
	}
	
	//Finding the Fibonacci number n using the formula
	public static int fibFormula(int n) {
		//Held square root of 5 in a double to make formula easier to read below
		double sqrtFive = Math.sqrt(5);
		return (int)((1/sqrtFive) * (Math.pow((1+sqrtFive)/2, n)- Math.pow((1-sqrtFive)/2, n)));
	}
	
	//Finding the Fibonacci number n using iteration
	public static int fibIteration(int n) {
		int first = 0;
		int second = 1; 
		int added; 
        if (n == 0) 
            return n; 
        for (int i = 2; i <= n; i++) 
        { 
            added = first + second; 
            first = second; 
            second = added; 
        } 
        return second; 
	}

	//Finding the Fibonacci number n using recursion
	public static int fibRecursion(int n) {
		if (n <= 1) 
		       return n; 
		    return fibRecursion(n-1) + fibRecursion(n-2); 
		    } 
	}

