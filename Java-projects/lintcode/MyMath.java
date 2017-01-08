import java.util.*;
import java.util.LinkedList;

public class MyMath {
	/*
	 * Merge Intervals
	 * 
	 * Given a collection of intervals, merge all overlapping intervals.
	 * 
	 * For example, Given [1,3],[2,6],[8,10],[15,18], return
	 * [1,6],[8,10],[15,18].
	 */

	public class MergeIntervals {
		public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
			if (intervals == null || intervals.size() < 2) {
				return intervals;
			}
			ArrayList<Interval> result = new ArrayList<Interval>();
			Comparator<Interval> intervalComperator = new Comparator<Interval>() {
				public int compare(Interval i1, Interval i2) {
					Integer i1St = i1.start;
					Integer i2St = i2.start;
					return i1St.compareTo(i2St);

				}
			};
			Collections.sort(intervals, intervalComperator);
			Interval current = intervals.get(0);
			for (Interval currentToCompare : intervals) {
				if (current.end < currentToCompare.start) {
					result.add(current);
					current = currentToCompare;
				} else
					current.end = Math.max(current.end, currentToCompare.end);
			}
			result.add(current);
			return result;
		}

		/*
		 * permutations
		 * 
		 * Given a collection of numbers, return all possible permutations.
		 * 
		 * For example, [1,2,3] have the following permutations: [1,2,3],
		 * [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
		 */
		public ArrayList<ArrayList<Integer>> permute(int[] num) {
			// Start typing your Java solution below
			// DO NOT write main() function
			ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
			if (num.length == 0)
				return res;
			Arrays.sort(num);
			boolean[] visit = new boolean[num.length];
			dfs(res, new ArrayList<Integer>(), num, visit);
			return res;
		}

		public void dfs(ArrayList<ArrayList<Integer>> res,
				ArrayList<Integer> buff, int[] num, boolean[] visit) {
			if (buff.size() == num.length) {
				res.add(buff);
				return;
			}
			for (int i = 0; i < num.length; i++) {
				if (!visit[i]) {
					visit[i] = true;
					ArrayList<Integer> nbuff = new ArrayList<Integer>(buff);
					nbuff.add(num[i]);
					dfs(res, nbuff, num, visit);
					visit[i] = false;
				}
			}
		}

		/*
		 * 
		 * Permutations II
		 * 
		 * Given a collection of numbers that might contain duplicates, return
		 * all possible unique permutations. For example, [1,1,2] have the
		 * following unique permutations: [1,1,2], [1,2,1], and [2,1,1].
		 */

		public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
			Arrays.sort(num);
			ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			boolean[] visited = new boolean[num.length];
			permuteUniqueHelper(result, list, num, visited);
			return result;
		}

		public void permuteUniqueHelper(ArrayList<ArrayList<Integer>> result,
				ArrayList<Integer> list, int[] num, boolean[] visited) {

			if (list.size() == num.length) {
				result.add(new ArrayList<Integer>(list));
				return;
			}

			for (int i = 0; i < num.length; i++) {
				if (visited[i] == true
						|| (i != 0 && num[i] == num[i - 1] && visited[i - 1] == false)) {
					continue;
				}
				visited[i] = true;
				list.add(num[i]);
				permuteUniqueHelper(result, list, num, visited);
				list.remove(list.size() - 1);
				visited[i] = false;
			}
		}

		/*
		 * Multiply strings
		 * 
		 * Given two numbers represented as strings, return multiplication of
		 * the numbers as a string.
		 * 
		 * Note: The numbers can be arbitrarily large and are non-negative.
		 */
		public String multiply(String num1, String num2) {
			num1 = new StringBuilder(num1).reverse().toString();
			num2 = new StringBuilder(num2).reverse().toString();
			// even 99 * 99 is < 10000, so maximaly 4 digits
			int[] d = new int[num1.length() + num2.length()];
			for (int i = 0; i < num1.length(); i++) {
				int a = num1.charAt(i) - '0';
				for (int j = 0; j < num2.length(); j++) {
					int b = num2.charAt(j) - '0';
					d[i + j] += a * b;
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < d.length; i++) {
				int digit = d[i] % 10;
				int carry = d[i] / 10;
				sb.insert(0, digit);
				if (i < d.length - 1)
					d[i + 1] += carry;
			}
			// trim starting zeros
			while (sb.length() > 0 && sb.charAt(0) == '0') {
				sb.deleteCharAt(0);
			}
			return sb.length() == 0 ? "0" : sb.toString();
		}

		/*
		 * Next Permutation
		 * 
		 * Implement next permutation, which rearranges numbers into the
		 * lexicographically next greater permutation of numbers.
		 * 
		 * If such arrangement is not possible, it must rearrange it as the
		 * lowest possible order (ie, sorted in ascending order).
		 * 
		 * The replacement must be in-place, do not allocate extra memory.
		 * 
		 * Here are some examples. Inputs are in the left-hand column and its
		 * corresponding outputs are in the right-hand column. 1,2,3 -> 1,3,2
		 * 3,2,1-> 1,2,3 1,1,5 -> 1,5,1
		 */
		public void nextPermutation(int[] num) {
			int length = num.length - 1;
			int tmp = 0;
			while (length > 0) {
				if (num[length - 1] < num[length])
					break;
				length--;
			}
			if (length == 0) {
				for (int i = 0, j = num.length; i < j; i++, j--) {
					tmp = num[i];
					num[i] = num[j];
					num[j] = tmp;
				}

			}

			int k = num.length - 1;
			for (; k >= length; k--)
				if (num[k] > num[length - 1])
					break;
			tmp = num[k];
			num[k] = num[length - 1];
			num[length - 1] = tmp;
			java.util.Arrays.sort(num);
		}

		/*
		 * Palindrome Number
		 * 
		 * if it is a palindrome number, then the reversed number equals the
		 * orginial number
		 */
		public boolean isPalindrome(int x) {
			int reversed = 0, n = x;
			while (n > 0) {
				reversed = reversed * 10 + n % 10;
				n /= 10;
			}
			return x == reversed;
		}

		/*
		 * Divide two integers without using multiplication, division and mod
		 * operator.
		 */
		public int divide(int dividend, int divisor) {
			if (dividend == 0)
				return 0;
			if (divisor == 1)
				return dividend;
			boolean positive = (dividend > 0 && divisor > 0)
					|| (dividend < 0 && divisor < 0);
			long absDividend = dividend < 0 ? 0 - (long) dividend
					: (long) dividend;
			long absDivisor = divisor < 0 ? 0 - (long) divisor : (long) divisor;
			int result = dividePositive(absDividend, absDivisor, absDivisor);
			return positive ? result : 0 - result;
		}

		private int dividePositive(long p, long q, long originalDivisor) { // p
																			// /
																			// q
			if (p < q)
				return 0;
			int result = 0;
			int e = 0;
			while (p >= q) {
				result += 1 << e;
				p -= q;
				q = q << 1;
				e++;
			}
			return p > 0 ? result
					+ dividePositive(p, originalDivisor, originalDivisor)
					: result;
		}

		/*
		 * Two Sum
		 * 
		 * Given an array of integers, find two numbers such that they add up to
		 * a specific target number.
		 * 
		 * The function twoSum should return indices of the two numbers such
		 * that they add up to the target, where index1 must be less than
		 * index2. Please note that your returned answers (both index1 and
		 * index2) are not zero-based.
		 * 
		 * You may assume that each input would have exactly one solution.
		 * 
		 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
		 */
		public int[] twoSum(int[] numbers, int target) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			int[] result = new int[2];

			for (int i = 0; i < numbers.length; i++) {
				if (map.containsKey(numbers[i])) {
					int index = map.get(numbers[i]);
					result[0] = index + 1;
					result[1] = i + 1;
					break;
				} else {
					map.put(target - numbers[i], i);
				}
			}

			return result;
		}

		public int[] twoSum2(int[] numbers, int target) {
			Element[] e = new Element[numbers.length];
			for (int i = 0; i < numbers.length; i++) {
				e[i] = new Element(numbers[i], i);
			}
			Arrays.sort(e, new Comparator<Element>() {
				public int compare(Element a, Element b) {
					return a.val > b.val ? 1 : (a.val == b.val ? 0 : -1);
				}
			});

			int i = 0, j = numbers.length - 1;
			int[] res = new int[2];
			while (i < j) {
				int temp = e[i].val + e[j].val;
				if (temp == target) {
					res[0] = Math.min(e[i].index, e[j].index) + 1;
					res[1] = Math.max(e[i].index, e[j].index) + 1;
					return res;
				} else if (temp > target)
					j--;
				else
					i++;
			}
			return res;
		}

		public class Element {
			int val;
			int index;

			Element(int val, int index) {
				this.val = val;
				this.index = index;
			}

		}

		public int threeSumClosest(int[] num, int target) {
			int min = Integer.MAX_VALUE;
			int result = 0;

			Arrays.sort(num);

			for (int i = 0; i < num.length; i++) {
				int j = i + 1;
				int k = num.length - 1;
				while (j < k) {
					int sum = num[i] + num[j] + num[k];
					int diff = Math.abs(sum - target);
					if (diff < min) {
						min = diff;
						result = sum;
					}
					if (sum <= target) {
						j++;
					} else {
						k--;
					}
				}
			}

			return result;
		}

		public double pow(double x, int n) {
			if (x == 0)
				return 0;
			return power(x, n);
		}

		private double power(double x, int n) {
			if (n == 0)
				return 1;
			double left = power(x, n / 2);
			if (n % 2 == 0) {
				return left * left;
			} else if (n < 0) {
				return left * left / x;
			} else {
				return left * left * x;
			}
		}

		/*
		 * Reverse digits of an integer.
		 * 
		 * Example1: x = 123, return 321 Example2: x = -123, return -321
		 */
		public int reverse(int x) {
			int ret = 0;
			boolean zero = false;
			while (!zero) {
				ret = ret * 10 + (x % 10);
				x /= 10;
				if (x == 0) {
					zero = true;
				}
			}
			return ret;
		}

		/*
		 * Given two integers n and k, return all possible combinations of k
		 * numbers out of 1 ... n
		 */
		public ArrayList<ArrayList<Integer>> combine(int n, int k) {
			ArrayList<ArrayList<Integer>> combSet = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> comb = new ArrayList<Integer>();

			if (n < k)
				return combSet;
			helper(n, k, combSet, comb, 1);
			return combSet;
		}

		public void helper(int n, int k, ArrayList<ArrayList<Integer>> combSet,
				ArrayList<Integer> comb, int start) {
			// one possible combination constructed
			if (comb.size() == k) {
				combSet.add(new ArrayList<Integer>(comb));
				return;
			}

			else {
				for (int i = start; i <= n; i++) {// try each possibility number
													// in
													// current position
					comb.add(i);
					helper(n, k, combSet, comb, i + 1); // after selecting
														// number
														// for current position,
														// process next position
					comb.remove(comb.size() - 1); // clear the current position
													// to
													// try next possible number
				}
			}
		}

		/*
		 * Combination Sum
		 * 
		 * Given a set of candidate numbers (C) and a target number (T), find
		 * all unique combinations in C where the candidate numbers sums to T.
		 * 
		 * The same repeated number may be chosen from C unlimited number of
		 * times.
		 * 
		 * Note: All numbers (including target) will be positive integers.
		 * Elements in a combination (a1, a2, … , ak) must be in non-descending
		 * order. (ie, a1 <= a2 <= … <= ak). The solution set must not contain
		 * duplicate combinations. For example, given candidate set 2,3,6,7 and
		 * target 7, A solution set is: [7] [2, 2, 3]
		 */
		public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
				int target) {
			ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
			if (candidates == null || candidates.length == 0)
				return result;
			Arrays.sort(candidates); // Sort the candidate in non-descending
										// order
			ArrayList<Integer> current = new ArrayList<Integer>();
			recursiveAppend(candidates, target, 0, current, result);
			return result;
		}

		public void recursiveAppend(int[] candidates, int target,
				int startIndex, ArrayList<Integer> current,
				ArrayList<ArrayList<Integer>> result) {
			if (target < 0)
				return;
			if (target == 0) { // The current array is an solution
				result.add(new ArrayList<Integer>(current));
				return;
			}
			/*
			 * No duplicates if (i > startIndex && num[i] == num[i-1]) // The
			 * same number has been added earlier within the loop continue;
			 */
			for (int i = startIndex; i < candidates.length; i++) {
				if (candidates[i] > target) // No need to try the remaining
											// candidates
					break;
				// Add candidate[i] to the current array
				current.add(candidates[i]);
				// Recursively append the current array to compose a solution
				recursiveAppend(candidates, target - candidates[i], i, current,
						result);
				// Remove candidate[i] from the current array, and try next
				// candidate in the next loop
				current.remove(current.size() - 1);
			}
		}

		/*
		 * Climbing Stairs You are climbing a stair case. It takes n steps to
		 * reach to the top.
		 * 
		 * Each time you can either climb 1 or 2 steps. In how many distinct
		 * ways can you climb to the top?
		 */
		int climbStairs(int n) {
			if (n < 4)
				return n;
			int a = 2, b = 3, c = 5;
			for (int i = 5; i <= n; i++) {
				a = c;
				c = b + c;
				b = a;
			}
			return c;
		}

		int climbStairsRecur(int n) {
			if (n == 1)
				return 1;
			if (n == 2)
				return 2;
			return climbStairsRecur(n - 1) + climbStairsRecur(n - 2);
		}

		/*
		 * Sqrt(x) Newton's method
		 */
		public int sqrt(int x) {
			double x0 = x / 2.0;
			double x1 = (x0 + x / x0) / 2.0;
			while (Math.abs(x1 - x0) > 0.00001) {
				x0 = x1;
				x1 = (x0 + x / x0) / 2.0;
			}
			return (int) x1;
		}

		/*
		 * Given a number represented as an array of digits, plus one to the
		 * number. Example: Input:{9, 9, 9, 9, 9}. Output:{1, 0, 0, 0, 0, 0}.
		 */

		public int[] plusOne(int[] digits) {
			int carry = 1;
			for (int i = digits.length - 1; i >= 0; i--) {
				if (carry == 0)
					break;
				if (digits[i] == 9) {
					digits[i] = 0;
				} else {
					digits[i]++;
					carry = 0;
				}
			}
			if (carry == 0)
				return digits;
			int[] array = new int[digits.length + 1];
			array[0] = 1;
			for (int i = 0; i < digits.length; i++)
				array[i + 1] = digits[i];
			return array;
		}

		/*
		 * Validate if a given string is numeric.
		 * 
		 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" =>
		 * false "2e10" => true Note: It is intended for the problem statement
		 * to be ambiguous. You should gather all requirements up front before
		 * implementing one.
		 */

		public boolean isNumber(String s) {
			if (s == null) {
				return false;
			}
			// trim off head and tail zeros which not affect result depend on
			// question
			s = s.trim();
			if (s.length() == 0) {
				return false;
			}
			boolean hasNum = false, canSign = true, canDot = true, canE = false, hasE = false;

			int i = 0;
			while (i < s.length()) {
				char c = s.charAt(i++);
				if (c == ' ') {
					return false;
				}
				if (c == '+' || c == '-') {
					if (!canSign) {
						return false;
					}
					canSign = false;
					continue;
				}
				if (c == '.') {
					if (!canDot) {
						return false;
					}
					canDot = false;
					canSign = false;
					continue;
				}
				if (c == 'e') {
					if (!canE || hasE) {
						return false;
					}
					canE = false;
					hasE = true;
					hasNum = false;
					canDot = false;
					canSign = true;
					continue;
				}
				if (c >= '0' && c <= '9') {
					hasNum = true;
					if (!canE && !hasE) {
						canE = true;
					}
					canSign = false;
				} else {
					return false;
				}
			}
			return hasNum;
		}

		/*
		 * Given two binary strings, return their sum (also a binary string).
		 * 
		 * For example, a = "11" b = "1" Return "100".
		 */
		public String addBinary(String a, String b) {
			if (a == null || a.length() == 0)
				return b;
			if (b == null || b.length() == 0)
				return a;

			StringBuilder sb = new StringBuilder();
			int lastA = a.length() - 1;
			int lastB = b.length() - 1;
			int carry = 0;

			while (lastA >= 0 || lastB >= 0 || carry > 0) {
				int num1 = lastA >= 0 ? a.charAt(lastA--) - '0' : 0;
				int num2 = lastB >= 0 ? b.charAt(lastB--) - '0' : 0;
				int current = (num1 + num2 + carry) % 2;
				carry = (num1 + num2 + carry) / 2;
				sb.insert(0, current);
			}
			return sb.toString();
		}

		public int evalRPN(String[] tokens) {

			if (tokens == null)
				// invalid input
				return -1;
			if (tokens.length < 3)
				return new Integer(tokens[0]).intValue();
			// create a stack to hold the unsolved operands

			// walk through the string array
			// check the type of the token, an operand or an operator, or a
			// wrong
			// token
			// if an operand, push it to the stack
			// if an operator, if the stack has >=2 operands, pop them out,
			// calc,
			// and push the result in the stack.
			// end of the walking loop, if the stack size == 1, it is the
			// result,
			// otherwise error
			java.util.Stack<String> opds = new java.util.Stack<String>();

			for (String t : tokens) {
				if (checkType(t) == 1) {
					// an operand
					opds.push(t);
				} else if (checkType(t) == 0) {
					if (opds.size() < 2) {
						// ops, operands and operators do not match!
						return -1;
					}
					String so = opds.pop();
					opds.push(new Integer(calc(opds.pop(), so, t)).toString());
				}
			}// end of for loop
			if (opds.size() == 1)
				return new Integer(opds.pop()).intValue();
			// wrong input
			return -1;
		}

		/* return 1 for an number, 0 for an operator, -1 otherwise */
		private int checkType(String t) {
			if (t == null || t.length() == 0)
				return -1;

			if (t.trim().equals("+") || t.trim().equals("-")
					|| t.trim().equals("*") || t.trim().equals("/"))
				return 0;

			try {
				Integer.parseInt(t);
				return 1;
			} catch (NumberFormatException e) {
				return -1;
			}
		}

		private int calc(String first, String second, String operator)
				throws NumberFormatException {
			/*
			 * assume all the input are in the correct type, no more sanity
			 * check here
			 */
			switch (operator.trim().charAt(0)) {
			case '+':
				return Integer.parseInt(first) + Integer.parseInt(second);
			case '-':
				return Integer.parseInt(first) - Integer.parseInt(second);
			case '*':
				return Integer.parseInt(first) * Integer.parseInt(second);
			case '/':
				return Integer.parseInt(first) / Integer.parseInt(second);
			}
			return -1;
		}

		/*
		 * Generate Parentheses Given n pairs of parentheses, write a function
		 * to generate all combinations of well-formed parentheses.
		 * 
		 * For example, given n = 3, a solution set is:
		 * 
		 * "((()))", "(()())", "(())()", "()(())", "()()()"
		 */

		public List<String> generateParenthesis(int n) {
			ArrayList<String> result = new ArrayList<String>();
			ArrayList<Integer> diff = new ArrayList<Integer>();

			result.add("");
			diff.add(0);

			for (int i = 0; i < 2 * n; i++) {
				ArrayList<String> temp1 = new ArrayList<String>();
				ArrayList<Integer> temp2 = new ArrayList<Integer>();
				for (int j = 0; j < result.size(); j++) {
					String s = result.get(j);
					int k = diff.get(j);

					if (i < 2 * n - 1) {
						temp1.add(s + "(");
						temp2.add(k + 1);
					}
					if (k > 0 && i < 2 * n - 1 || k == 1 && i == 2 * n - 1) {
						temp1.add(s + ")");
						temp2.add(k - 1);
					}
				}
				result = new ArrayList<String>(temp1);
				diff = new ArrayList<Integer>(temp2);
			}
			return result;
		}

		/*
		 * 1: void CombinationPar(vector<string>& result, string& sample, int
		 * deep, 2: int n, int leftNum, int rightNum) 3: { 4: if(deep == 2*n) 5:
		 * { 6: result.push_back(sample); 7: return; 8: } 9: if(leftNum<n) 10: {
		 * 11: sample.push_back('('); 12: CombinationPar(result, sample, deep+1,
		 * n, leftNum+1, rightNum); 13: sample.resize(sample.size()-1); 14: }
		 * 15: if(rightNum<leftNum) 16: { 17: sample.push_back(')'); 18:
		 * CombinationPar(result, sample, deep+1, n, leftNum, rightNum+1); 19:
		 * sample.resize(sample.size()-1); 20: } 21: } 22: vector<string>
		 * generateParenthesis(int n) { 23: // Start typing your C/C++ solution
		 * below 24: // DO NOT write int main() function 25: vector<string>
		 * result; 26: string sample; 27: if(n!= 0) 28: CombinationPar(result,
		 * sample, 0, n, 0, 0); 29: return result; 30: }
		 */

		/**
		 * LeetCode - Letter Combinations of a Phone Number
		 */
		public String[] letters = { "", "", "abc", "def", "ghi", "jkl", "mno",
				"pqrs", "tuv", "wxyz" };

		public ArrayList<String> letterCombinations(String digits) {
			ArrayList<String> result = new ArrayList<String>();
			if (digits.isEmpty()) { // no digit
				result.add("");
				return result;
			}
			int n = digits.length();
			Queue<String> queue = new LinkedList<String>();
			for (int i = 0; i < n; i++) {
				String seq = letters[digits.charAt(i) - '0']; // the letter
																// sequence
																// related to
																// the
																// given digit
				int m = seq.length();
				if (queue.peek() == null) { // Empty queue; no digit has been
											// processed before
					for (int j = 0; j < m; j++)
						// Put separate letters into the queue
						queue.offer("" + seq.charAt(j));
				} else {
					Queue<String> temp = new LinkedList<String>();
					String s;
					// For each string in the queue, concatenate it with each
					// letter
					// in the sequence,
					// and put the concatenated string into a new queue
					while ((s = queue.poll()) != null) {
						for (int j = 0; j < m; j++)
							temp.offer(s + seq.charAt(j));
					}
					queue = temp;
				}
			}
			result = new ArrayList<String>(queue); // Make result an ArrayList
													// from
													// the queue
			return result;
		}

		public int charToInt(char c) {
			int data = 0;

			switch (c) {
			case 'I':
				data = 1;
				break;
			case 'V':
				data = 5;
				break;
			case 'X':
				data = 10;
				break;
			case 'L':
				data = 50;
				break;
			case 'C':
				data = 100;
				break;
			case 'D':
				data = 500;
				break;
			case 'M':
				data = 1000;
				break;
			}

			return data;
		}

		public int romanToInt(String s) {
			int i, total, pre, cur;

			total = charToInt(s.charAt(0));

			for (i = 1; i < s.length(); i++) {
				pre = charToInt(s.charAt(i - 1));
				cur = charToInt(s.charAt(i));

				if (cur <= pre) {
					total += cur;
				} else {
					total = total - pre * 2 + cur;
				}
			}

			return total;
		}

		public String IntegerToRomanNumeral(int input) {
			if (input < 1 || input > 3999)
				return "Invalid Roman Number Value";
			String s = "";
			while (input >= 1000) {
				s += "M";
				input -= 1000;
			}
			while (input >= 900) {
				s += "CM";
				input -= 900;
			}
			while (input >= 500) {
				s += "D";
				input -= 500;
			}
			while (input >= 400) {
				s += "CD";
				input -= 400;
			}
			while (input >= 100) {
				s += "C";
				input -= 100;
			}
			while (input >= 90) {
				s += "XC";
				input -= 90;
			}
			while (input >= 50) {
				s += "L";
				input -= 50;
			}
			while (input >= 40) {
				s += "XL";
				input -= 40;
			}
			while (input >= 10) {
				s += "X";
				input -= 10;
			}
			while (input >= 9) {
				s += "IX";
				input -= 9;
			}
			while (input >= 5) {
				s += "V";
				input -= 5;
			}
			while (input >= 4) {
				s += "IV";
				input -= 4;
			}
			while (input >= 1) {
				s += "I";
				input -= 1;
			}
			return s;
		}

		/*
		 * Valid Parentheses
		 */
		public boolean isValid(String s) {
			char[] charArray = s.toCharArray();

			HashMap<Character, Character> map = new HashMap<Character, Character>();
			map.put('(', ')');
			map.put('[', ']');
			map.put('{', '}');

			Stack<Character> stack = new Stack<Character>();

			for (Character c : charArray) {
				if (map.keySet().contains(c)) {
					stack.push(c);
				} else if (map.values().contains(c)) {
					if (!stack.isEmpty() && map.get(stack.peek()) == c) {
						stack.pop();
					} else {
						return false;
					}
				}
			}
			return stack.isEmpty();
		}

		/*
		 * Single Number
		 * 
		 * Given an array of integers, every element appears three times except
		 * for one. Find that single one.
		 */
		public int singleNumber(int[] A) {
			int x = 0;
			for (int a : A)
				x = x ^ a;
			return x;
		}

		int singleNumber(int A[], int n) {
			int[] sum = new int[32];
			int i, j, temp, result;
			result = 0;
			for (i = 0; i < n; i++) {
				for (j = 0; j < 32; j++) {
					sum[j] += (A[i] >> j) & 0x01;
				}
			}
			for (j = 0; j < 32; ++j) {
				if (sum[j] % 3 == 0)
					temp = 0;
				else
					temp = 1;
				result += (temp << j);

			}
			return result;
		}

		/* subset with duplications */
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] S) {
			// if(S==null||S.length==0) return result;
			// Arrays.sort(S);
			// result.add(list);
			// subsetsWithDup(S,0);
			// return result;
			return null;
		}

		public void subsetsWithDup(int[] S, int index) {
			// for(int i=index;i<S.length;i++){
			// list.add(S[i]);
			// result.add(new ArrayList<Integer>(list));
			// subsetsWithDup(S,i+1);
			// list.remove(list.size()-1);
			// while(i<S.length-1&&S[i]==S[i+1]) i++; // the only one difference
			// }
		}
	}
}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}
}