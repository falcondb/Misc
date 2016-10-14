import java.util.Random;

public class VectorRotation {

	public static void main(String[] args) {
		Random r = new Random();
		int pSize = 20;
		int[] prices = new int[pSize];

		for (int index = 0; index < pSize; ++index) {
			prices[index] = r.nextInt(100);
			System.out.print(prices[index] + ",");
		}
		System.out.println();

		new VectorRotation().rotation(prices, 5, 20);
		
		for (int index = 0; index < pSize; ++index) {
			System.out.print(prices[index] + ",");
		}

	}

	public void rotation(int[] ar, int d , int l){
		reverse(ar, 0, d-1);
		reverse(ar, d, l-1);
		reverse (ar, 0 , l-1);
	}
	
	public void reverse(int[] ar, int s, int e){
		if (s< 0 || e< 0 || e<=s)
			return;
		for (int i = 0; i < (s+e)/2 ; ++i){
			int t = ar[s+i];
			ar[s+i] = ar[e-i];
			ar[e-i] = t;
		}
	}
}
