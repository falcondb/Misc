import java.util.ArrayList;
import java.util.Random;

public class MergeTwoSortedArray {

	public static void main(String[] args) {

	}

	public int[] merge(int[] a, int as, int[] b, int bs) {

		int ai = as - 1;
		int bi = bs - 1;
		int ti = as + bs - 1;

		while (ai >= 0 && bi >= 0) {
			if (a[ai] > b[bi]) {
				b[ti] = a[ai--];
			} else {
				b[ti] = b[bi--];
			}
			ti--;
		}
		while (ai >= 0) {
			b[ti--] = a[ai--];
		}
		return b;
	}

	public ArrayList<Integer> createSortedArray(int size, int range) {
		Random r = new Random();
		ArrayList<Integer> al = new ArrayList<Integer>();
		if (size <= 0)
			return null;
		for (int index = 0; index < size; ++index) {
			al.add(r.nextInt(range));
		}
		return al;
	}

}
