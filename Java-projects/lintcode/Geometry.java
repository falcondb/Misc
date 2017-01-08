import java.util.ArrayList;

public class Geometry {

	/*
	 * Container With Most Water
	 * 
	 * Given n non-negative integers a1, a2, ..., an, where each represents a
	 * point at coordinate (i, ai). n vertical lines are drawn such that the two
	 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which
	 * together with x-axis forms a container, such that the container contains
	 * the most water.
	 */
	public int maxArea(int[] height) {
		int i, j, lh, rh, area, tmp, len = height.length;
		lh = height[0];
		rh = height[len - 1];
		area = 0;
		i = 0;
		j = len - 1;

		while (i < j) {
			tmp = Math.min(lh, rh) * (j - i);

			if (tmp > area) {
				area = tmp;
			}

			if (lh < rh) {
				while (i < j && height[i] <= lh) {
					i++;
				}
				if (i < j) {
					lh = height[i];
				}
			} else {
				while (i < j && height[j] <= rh) {
					j--;
				}
				if (i < j) {
					rh = height[j];
				}
			}
		}
		return area;
	}

	/*
	 * Trapping Rain Water 
	 * 
	 * Given n non-negative integers representing an elevation map where the
	 * width of each bar is 1, compute how much water it is able to trap after
	 * raining.
	 */
	public int trap(int A[]) {
		if (A == null)
			return 0;
		int n = A.length;
		if (n < 2) {
			return 0;
		}

		int[] l = new int[n];
		int[] r = new int[n];

		int water = 0;

		l[0] = 0;
		for (int i = 1; i < n; i++) {
			l[i] = Math.max(l[i - 1], A[i - 1]);
		}

		r[n - 1] = 0;
		for (int i = n - 2; i >= 0; i--) {
			r[i] = Math.max(r[i + 1], A[i + 1]);
		}

		for (int i = 0; i < n; i++) {
			if (Math.max(l[i], r[i]) - A[i] > 0) {
				water += Math.min(l[i], r[i]) - A[i];
			}
		}
		return water;

	}

	// There are N gas stations along a circular route, where the amount of gas
	// at station i is gas[i].
	//
	// You have a car with an unlimited gas tank and it costs cost[i] of gas to
	// travel from station i to its next station (i+1). You begin the journey
	// with an empty tank at one of the gas stations.
	//
	// Return the starting gas station's index if you can travel around the
	// circuit once, otherwise return -1.
	//
	// Note:
	// The solution is guaranteed to be unique.

	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (gas == null || cost == null || gas.length != cost.length)
			return -1;
		int i = 0, j = 0;
		int sum = 0;
		int total = 0;
		while (j < gas.length) {
			int diff = gas[j] - cost[j];
			if (sum + diff < 0) {
				i = j + 1;
				sum = 0;
			} else {
				sum += diff;
			}
			j++;
			total += diff;
		}
		return total >= 0 ? i : -1;
	}

	/*
	 * 
	 * 
	 * Given a triangle, find the minimum path sum from top to bottom. Each step
	 * you may move to adjacent numbers on the row below.
	 * 
	 * For example, given the following triangle
	 * 
	 * [ [2], [3,4], [6,5,7], [4,1,8,3] ] The minimum path sum from top to
	 * bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
	 */

	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
		int[] total = new int[triangle.size()];
		int l = triangle.size() - 1;

		for (int i = 0; i < triangle.get(l).size(); i++) {
			total[i] = triangle.get(l).get(i);
		}

		// iterate from last second row
		for (int i = triangle.size() - 2; i >= 0; i--) {
			for (int j = 0; j < triangle.get(i + 1).size() - 1; j++) {
				total[j] = triangle.get(i).get(j)
						+ Math.min(total[j], total[j + 1]);
			}
		}

		return total[0];
	}
	
	/*
	 * Sort colors 
	 * 
	 * Given an array with n objects colored red, white or blue,
	 * sort them so that objects of the same color are adjacent, with the colors
	 * in the order red, white and blue. Here, we will use the integers 0, 1,
	 * and 2 to represent the color red, white, and blue respectively.
	 */
	public void sortColors(int[] A) {
		if (A == null || A.length == 0 || A.length == 1)
			return;

		// one-pass solution
		int red = 0, blue = A.length - 1, tmp, i = 0;
		// stop looping when current >= blue
		while (i <= blue) {
			// if color is red, move to the front
			if (A[i] == 0) {
				// when cur > red, switch
				if (i > red) {
					tmp = A[red];
					A[red] = A[i];
					A[i] = tmp;
					red++;
				}
				// when cur <= red, no need to switch, just move both to next
				else {
					i++;
					red++;
				}
			}
			// if color is blue, move to the end
			else if (A[i] == 2) {
				// when cur < blue, switch
				if (i < blue) {
					tmp = A[blue];
					A[blue] = A[i];
					A[i] = tmp;
					blue--;
				}
				// when cur >= blue, end the loop
				else {
					return;
				}
			}
			// if color is white, skip
			else {
				i++;
			}
		}
	}
	
	/*
	 * Jump Game
	 * 
	 * Given an array of non-negative integers, you are initially positioned at
	 * the first index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that
	 * position.
	 * 
	 * Determine if you are able to reach the last index.
	 * 
	 * For example: A = [2,3,1,1,4], return true.
	 * 
	 * A = [3,2,1,0,4], return false.
	 */
	public boolean canJump(int[] A) {

	    if(A.length <= 1)
			return true;
		if(A[0] >= (A.length - 1))
			return true;
		int maxlength = A[0];
		if(maxlength == 0)
		    return false;
		for(int i = 1; i < A.length - 1; i++)
		{
			if(maxlength >= i && (i + A[i]) >= A.length - 1)
				return true;
			if(maxlength <= i && A[i] == 0)
				return false;
			if((i + A[i]) > maxlength)
				maxlength = i + A[i];
		}
		return false;
	}
}


// public int canCompleteCircuit(int[] gas, int[] cost) {
// if(gas == null || cost == null || gas.length != cost.length)
// return -1;
// int sum = 0, total =0;
// int max = -1;
// int mi = -1;
// int cs = -1;
// for (int i=0; i<gas.length; ++i){
// total += gas[i] - cost[i];
// if(total > max){
// max = total;
// mi = i;
// }
// }
// System.out.println("total: "+ total);
//
// if(total >= 0){
// for(int i = mi; i != i%gas.length; ++i){
// if(max - (gas[i] - cost[i]) == 0 )
// return i;
// if(max - (gas[i] - cost[i]) < 0)
// return (i+1)/gas.length;
// }
// return 0;
// }
// else
// return -1;
// }