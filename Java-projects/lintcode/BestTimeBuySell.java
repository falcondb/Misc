import java.util.ArrayList;
import java.util.Random;

public class BestTimeBuySell {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Random r = new Random();
		int pSize = 20;
		int[] prices = new int[pSize];
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int index = 0; index < pSize; ++index) {
			prices[index] = r.nextInt(100);
			al.add(prices[index]);
			System.out.print(prices[index] + ",");
		}
		System.out.println();

		int maxDif = 0;
		int minPriceSoFar = prices[0];
		for (int index = 0; index < pSize; ++index) {
			if (prices[index] < minPriceSoFar) {
				minPriceSoFar = prices[index];
			} else {
				if (prices[index] - minPriceSoFar > maxDif)
					maxDif = prices[index] - minPriceSoFar;
			}
		}
		System.out.println(maxDif + ", " + minPriceSoFar);
	}

}
