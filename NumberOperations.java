package numberOperations;

public class NumberOperations {

	public static void main(String[] args) {
		//tests
		primeFactorization(20); //2,2,5
		System.out.println();
		primeFactorization(100); //2,2,5,5
		System.out.println();
		primeFactorization(35);  //5,7
		System.out.println();
		System.out.println("--------------------");
		//will return 5 and print the speeds 
		long startTime = System.nanoTime();
		System.out.print(gcdEuclidean(25, 30));
		long endTime = System.nanoTime();
		long finalTime = endTime-startTime;
		System.out.println("\nEuclidean GCD Speed in nanoseconds: " + finalTime);
		startTime = System.nanoTime();
		System.out.print(gcdPrimeFactorization(25,30));
		endTime = System.nanoTime();
		finalTime = endTime-startTime;
		System.out.println("\nPrime Factorization GCD Speed in nanoseconds: " + finalTime);
		System.out.println("--------------------");
		mod(16,3,15); //1
		mod(24,4,26); //16
		System.out.println("--------------------");
		primeBits(6,10); //4
		primeBits(10,15); //5
	}
	
	
	//Function to find the prime factorization of an integer
	public static void primeFactorization(int a) {
		//Start by dividing by 2 as many times as possible 
        while (a % 2==0) { 
            System.out.print(2 + " "); 
            a = a / 2;
        } 
  
        //Starting with 3, we attempt to divide when the modulo is 0,
        //if we can't we raise i by 2 (because we eliminated evens at this point),
        //and we keep going until we reach the square root of the number
        for (int i = 3; i <= Math.sqrt(a); i+=2) { 
            while (a % i == 0) { 
                System.out.print(i + " "); 
                a = a / i; 
            } 
        } 
  
        	//If a is prime and greater than 2 we print it
        if (a > 2) 
            System.out.print(a); 
	}
	
	/*Functions to find the gcd of two integers using prime 
	factorization and the Euclidean algorithm,comparing the corresponding running time
	*/
	public static int gcdEuclidean(int m, int n) {
		//If second number is 0 we return the first
		if (n == 0) {
			return m;
		}
		//If not recursively call the function with Euclidean algorithm
		else {
			return gcdEuclidean(n, m%n);
		}
	}
	
	public static int gcdPrimeFactorization(int m, int n) {
		//Start at 1
		int gcd = 1;
		//Check if i is a factor of both 
		for (int i = 1; i <= m && i <= n; ++i)
        {
            if(m % i==0 && n % i==0)
                gcd = i;
        }
		return gcd;
	}
	
	//Calculates a^b mod m using modular exponention algorithm,
	public static void mod(int a, int b, int m) {
		//Start with result = 1
		int finalResult = 1;
		//Loop from 0 to the power and use the algorithm on each iteration
		for (int i = 0; i<b; i++) {
			finalResult = ((finalResult*a) % m);
		}
		System.out.println(finalResult);
	}
	
	//Find the count of numbers in the range [L,R] having a prime number of set bits (1s) in their binary 
    public static void primeBits(int L, int R) {
    		//Start with count 0, loop from L->R
    		int count = 0;
		for (int i=L; i<=R; i++) {
			//Get the bitcount of our current i
			int cur = Integer.bitCount(i);
			//Now we check if the bitcount is prime
			if (cur<=1) {
				continue;
			}
			//Assume the number is prime, and check if it isn't then change the bool value
			boolean isPrime = true;
			for (int j=2; j<cur; j++) {
				if (cur % j == 0) {
					isPrime=false;
					break;
				}
			}
			//If the bool is true after the loop, increment the count
			if (isPrime==true) {
				count++;
			}
			
		}
		System.out.println(count);
	}
}
