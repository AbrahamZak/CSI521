package stockMaxProfit;

public class StockMaxProfit {
	public static void main(String[] args) {
		//Run tests for one transaction, should return 5, 0
		System.out.println("One Transaction tests:");
		oneTransaction(new int[] {7,1,5,3,6,4});
		oneTransaction(new int[] {7,6,4,3,1});
		//Run tests for unlimited transaction, should return 7, 4, 0
		System.out.println("Unlimited Transaction tests:");
		unlimitedTransactions(new int[] {7,1,5,3,6,4});
		unlimitedTransactions(new int[] {1,2,3,4,5});
		unlimitedTransactions(new int[] {7,6,4,3,1});
		//Run tests for two transaction, should return 6, 4, 0
		System.out.println("Two Transaction tests:");
		twoTransactions(new int[] {3,3,5,0,0,3,1,4});
		twoTransactions(new int[] {1,2,3,4,5});
		twoTransactions(new int[] {7,6,4,3,1});
		//Run tests for k transaction, both k=2, should return 2,7
		System.out.println("k Transaction tests:");
		kTransactions(2, new int[] {2,4,1});
		kTransactions(2, new int[] {3,2,6,5,0,3});
	}
	public static void oneTransaction(int [] stock) {
		//records profit
		int profit = 0;
		//loop through days from day 0 to last day, then day 1 to last day and so on
		//if the profit is ever higher than the most recent highest value, update the profit
		for (int i=0; i<=stock.length-1; i++) {
			for (int j=i+1; j<=stock.length-1; j++) {
				if (stock[j]-stock[i]>profit) {
					profit = stock[j]-stock[i];
				}
			}
		}
		System.out.println(profit);
	}
	
	public static void unlimitedTransactions(int [] stock) {
		//records profit
		int profit = 0;
		//loop through days and compare day by day, if there exists a profit, record it
	    for(int i = 1; i < stock.length; i++){
	        int temp = stock[i]-stock[i-1];
	        if(temp > 0){
	        	profit += temp; 
	        }
	    }
	    System.out.println(profit);
	}
	
	public static void twoTransactions(int [] stock) {
		//records profit
		int profit = 0;
		//records locations of max profit
		int startDay = 0;
		int endDay = 0;
		//loop through days from day 0 to last day, then day 1 to last day and so on
		//if the profit is ever higher than the most recent highest value, update the profit
		for (int i=0; i<=stock.length-1; i++) {
			for (int j=i+1; j<=stock.length-1; j++) {
				if (stock[j]-stock[i]>profit) {
					profit = stock[j]-stock[i];
					 startDay = i;
					 endDay = j;
				}
				}
			}
		/*Now because we can't do multiple transactions across same days,
		we break up the array into before and after our first transaction
		We find the most profit in each and compare, and take the higher value and add
		it to our total profit*/
		int profitBefore = 0;
		int profitAfter = 0;
		for (int i=endDay+1; i<=stock.length-1; i++) {
			for (int j=i+1; j<=stock.length-1; j++) {
				if (stock[j]-stock[i]>profitAfter) {
					profitAfter = stock[j]-stock[i];
				}
				}
			}
		for (int i=0; i<=startDay-1; i++) {
			for (int j=i+1; j<=stock.length-1; j++) {
				if (stock[j]-stock[i]>profitBefore) {
					profitBefore = stock[j]-stock[i];
				}
				}
			}
		if (profitBefore>profitAfter) {
			profit = profit+profitBefore;
		}
		else {
			profit = profit+profitAfter;
		}
				System.out.println(profit);
	}
	
	public static void kTransactions(int k, int [] stock) {
		//Make an array for profit to test all possibilities
		int[][] profits = new int[k + 1][stock.length + 1];
		//Can't make a trade on the first day
		for (int i = 0; i <= k; i++) {
            profits[i][0] = 0; 
		}
		//Start all profits at 0
		for (int i = 0; i <= stock.length; i++) {
            profits[0][i] = 0;
		}
		//Loop for k amount
		for (int i = 1; i <= k; i++)  
        { 
			//Loop through days
            for (int j = 1; j < stock.length; j++) 
            { 
            	//Set current max profit to 0
                int profit = 0; 
                //Loop through profit possibilities and record max profits in table
                for (int l = 0; l < j; l++) 
                	profit = Math.max(profit, stock[j] - 
                		stock[l] + profits[i - 1][l]); 
                
                profits[i][j] = Math.max(profits[i] [j - 1],  
                		profit); 
            } 
        } 
		//Print the total profit
		System.out.println(profits[k][stock.length - 1]);
	}
	
}
