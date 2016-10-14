import java.util.Random;
public class PainterPartition {

	
	public static void main(String [] args){
		
		int k = 3; // partitions
		int size = 8;
		int[] time = new int[size];
		Random r = new Random();
		for (int i = 0; i< size ; ++i){
			time[i] = r.nextInt(100);
			System.out.print(time[i]+ ", ");
		}
		System.out.println();
		System.out.println(partition(time, size -1, k));
	}
	
	public static int partition(int[] t, int as, int p){
		
		int min =Integer.MAX_VALUE;
		if (p == 0){
			return sum(t, 0, as);
		}
		if (as == p){
			return max(t, 0, as);
		}
		if(as < p){
			return Integer.MAX_VALUE;
		}
		for (int i = 1; i < as; ++i){
			int s1 = partition(t, i, p-1);
			int s2 = sum(t, i+1, as);
			if (s1 < s2 && s2 < min){
				min = s2;
			}else if(s1 >= s2 && s1< min){
				min = s1;
			}
			System.out.println("min " + min);
		}
		return min;
	}
	
	private static int sum(int[] t, int s, int e){
		int sum=0;
		for (int i = s ; i<=e; ++i){
			sum+=t[i];
		}
		return sum;
	}
	private static int max (int[] t, int s, int e){
		
		int max = 0;
		for (int i = s; i<= e; ++i){
			if (max < t[i])
				max = t[i];
		}
		return max;
	}
}
