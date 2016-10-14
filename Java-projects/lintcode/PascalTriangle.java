
public class PascalTriangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PascalTriangle().printPascal(6);

	}

	public void printPascal(int n){
		  for (int line = 0; line < n; line++)
		  {
		    
		    for (int i = 0; i <= line; i++)
		    	System.out.print( pascal(line, i) + " ");
		    System.out.println();
		  }
	}
	public int pascal(int n, int k)
	{
	    int res = 1;
	    if (k > n - k)
	       k = n - k;
	    for (int i = 0; i < k; ++i)
	    {
	        res *= (n - i);
	        res /= (i + 1);
	    }
	    return res;
	}
}



