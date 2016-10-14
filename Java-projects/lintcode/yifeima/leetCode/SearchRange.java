package yifeima.leetCode;

import java.util.Arrays;
import java.util.Random;

public class SearchRange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = new Random();
		int pSize = 20;
		int[] prices = new int[pSize];

		for (int index = 0; index < pSize; ++index) {
			prices[index] = r.nextInt(100);
			System.out.print(prices[index] + ",");
		}
		System.out.println();
		Arrays.sort(prices);
		// int [] re =new SearchRange().searchRange(prices, prices[3]);
		int[] p = { 1};

		//System.out.println(new SearchRange().binarySearch(p, 3));

		int[] re = new SearchRange().searchRange(p, 1);
		System.out.println(re[0] + "," + re[1]);
	}

	public int[] searchRange(int[] A, int target) {

		int[] res = new int[2];
		res[0] = -1;
		res[1] = -1;
		if (A == null || A.length < 1)
			return res;
		int head = 0, tail = A.length - 1;
		int tindex = -1;
		while (head <= tail){
			int mid = (head+tail)/2;
			if (A[mid] == target){
				tindex = mid;
				break;
			}else if (A[mid] > target)
				tail = mid -1;
			else
				head = mid+1;
				
		}
		if (tindex == -1)
			return res;
		head = 0; tail = tindex;
		while (head <= tail){
			int mid =  (head+tail)/2;
			if (A[mid] == target){
				tail = mid -1;
			}else{
				head = mid+1;
			}
		}
		res[0] = tail+1;
		head = tindex ; tail = A.length -1;
		while (head <= tail){
			int mid =  (head+tail)/2;
			if (A[mid] == target){
				head = mid+1;
			}else{
				tail = mid -1;
			}
		}
		res[1] = head-1;
		return res;
	}

	public int[] searchRange2(int[] A, int target) {

		int[] res = new int[2];
		res[0] = -1;
		res[1] = -1;
		if (A == null || A.length < 1)
			return res;
		int s = Arrays.binarySearch(A, target);
		if (s < 0 || target != A[s])
			return res;
		else {
			if (s == 0)
				res[0] = 0;
			for (int i = s; i > 0; --i)
				if ((A[i] == A[i - 1]))
					res[0] = i - 1;
				else {
					res[0] = i;
					break;
				}
		}
		s = Arrays.binarySearch(A, target + 1);
		if (s > 0) {
			res[1] = s - 1;
			for (int i = s; i > res[0]; --i) {
				if (!(A[i] == A[i - 1]))
					res[1] = i - 1;
			}
		} else
			res[1] = -s - 2;
		return res;

	}

	public int binarySearch(int[] v, int k) {
		if (v == null || v.length < 1)
			return -1;
		int head = 0, tail = v.length - 1;
		while (head <= tail) {
			if (v[(head + tail) / 2] == k)
				return (head + tail) / 2;
			if (v[(head + tail) / 2] < k)
				head = (head + tail) / 2 + 1;
			else
				tail = (head + tail) / 2 - 1;
		}
		return head;
	}
}

// class Solution {
// public:
// vector<int> searchRange(int A[], int n, int target) {
// // Start typing your C/C++ solution below
// // DO NOT write int main() function
// vector<int> result(2,-1);
// if(0 == n) return result;
// if(A[0] > target) return result;
// if(A[n-1] < target) return result;
// int i = 0,j = n-1;
// int ti = -1;
// while(i <= j) {
// int mid = (i+j) / 2;
// int midval = A[mid];
// if(target == midval) {
// ti = mid;
// break;
// } else if (target < midval)
// j = mid - 1;
// else
// i = mid + 1;
// }
// if(ti == -1) return result;//target not found
// int si = 0, sj = ti - 1;
// while(si <= sj) {
// int mid = (si + sj) / 2;
// int midval = A[mid];
// if(target == midval) {
// sj = mid - 1;
// } else {
// si = mid + 1;
// }
// }
// //at last si will equal to sj, mid == si == sj, if midval == target, sj will
// be 1 position before target, else sj is some time before the final test to be
// 1 postion before target. so, over all, sj will be 1 position before target
// after the loop break.
// result[0] = sj + 1;
// int ei = ti + 1, ej = n - 1;
// while(ei <= ej) {
// int mid = (ei + ej) / 2;
// int midval = A[mid];
// if(target == midval)
// ei = mid + 1;
// else
// ej = mid - 1;
// }
// result[1] = ei - 1;
// return result;
// }
// };