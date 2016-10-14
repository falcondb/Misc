import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DistanceMaximizing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = new Random();
		int pSize = 8;
		int[] values = { 9, 7, 0, 7, 2, 4, 2, 0 };
//		for (int index = 0; index < pSize; ++index) {
//			values[index] = r.nextInt(10);
//			System.out.print(values[index] + ",");
//		}
		System.out.println();

		ArrayList<Integer> endingCandicates = new ArrayList<Integer>();
		ArrayList<Integer> startingCandicates = new ArrayList<Integer>();

		findEndingCandicates(values, endingCandicates);
		findStartingCandicates(values, startingCandicates);

		// for (Integer v: startingCandicates){
		// System.out.print(v + ", ");
		// }
		// System.out.println();
		// for (Integer v: endingCandicates){
		// System.out.print(v + ", ");
		// }
		// System.out.println();

		boolean found = false;
		int s = 0;
		int e = 0;
		while (!found) {
			int result = isMD(values, endingCandicates, startingCandicates, s, e);
			if (result >= 0) {
				System.out.println("MD:" + result);
				found = true;
			} else if (result == -1) {
				int d1 = isMD(values, endingCandicates, startingCandicates, s + 1, e);
				int d2 = isMD(values, endingCandicates, startingCandicates, s, e + 1);
				if (d1 >= d2 && d2 >= 0) {
					System.out.println("MD :" + d1);
					found = true;
				} else if (d2 >= d1 && d1 >= 0) {
					System.out.println("MD :" + d2);
					found = true;
				} else {
					s++;
					e++;
				}
			}
		}
		System.out.println(MDNaitive(values));
	}

	public static void findStartingCandicates(int[] v, List<Integer> al) {
		int minSoFar = Integer.MAX_VALUE;
		for (int index = 0; index < v.length; ++index) {
			if (v[index] < minSoFar) {
				minSoFar = v[index];
				al.add(index);
			}
		}
	}

	public static void findEndingCandicates(int[] v, List<Integer> al) {
		int maxSoFar = Integer.MIN_VALUE;
		for (int index = v.length - 1; index >= 0; index--) {

			if (v[index] > maxSoFar) {
				maxSoFar = v[index];
				al.add(index);
			}
		}
	}

	public static int isMD(int[] values, ArrayList<Integer> endingCandicates, ArrayList<Integer> startingCandicates, int ep, int sp) {
		if (sp <= startingCandicates.size() && ep <= endingCandicates.size()) {
			if (values[startingCandicates.get(sp)] < values[endingCandicates.get(ep)]) {
				return endingCandicates.get(ep) - startingCandicates.get(sp);
			}
			return -1;
		}
		return -2;
	}
	
	public static int MDNaitive(int[] values){
		int max = 0;
		for (int index = 0; index < values.length; ++index){
			for (int j = index +1; j < values.length; ++j){
				if (values[j] > values[index] && (j - index) > max){
					max = j - index;
				}
			}
			
		}
		return max;
	}
}
