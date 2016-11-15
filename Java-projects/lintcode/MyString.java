import java.util.*;

public class MyString {

	/*
	 * Edit distance
	 */
	public int minDistanceWithRecurse(String word1, String word2) {
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return minDistance(word1.substring(1), word2.substring(1));
		} else {
			int t1 = minDistance(word1.substring(1), word2);
			int t2 = minDistance(word1, word2.substring(1));
			int t3 = minDistance(word1.substring(1), word2.substring(1));
			return Math.min(t1, Math.min(t2, t3)) + 1;
		}
	}

	public int minDistance(String word1, String word2) {
		int length1 = word1.length();
		int length2 = word2.length();
		if (length1 == 0 || length2 == 0) {
			return length1 == 0 ? length2 : length1;
		}
		int[][] distance = new int[length1 + 1][length2 + 1];
		distance[0][0] = 0;
		for (int i = 1; i <= length1; i++) {
			distance[i][0] = i;
		}
		for (int i = 1; i <= length2; i++) {
			distance[0][i] = i;
		}
		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					distance[i][j] = distance[i - 1][j - 1];
				} else {
					distance[i][j] = Math.min(distance[i - 1][j - 1],
							Math.min(distance[i][j - 1], distance[i - 1][j])) + 1;
				}
			}
		}
		return distance[length1][length2];
	}

	/*
	 * Simplify path
	 * 
	 * Given an absolute path for a file (Unix-style), simplify it.
	 */

	public String simplifyPath(String path) {
		Stack<String> stack = new Stack<String>();
		String[] strs = path.split("/");
		for (String str : strs) {
			if (str.equals("") || str.equals(".")) {
				continue;
			} else if (str.equals("..")) {
				if (!stack.empty()) {
					stack.pop();
				}
			} else {
				stack.push(str);
			}
		}
		StringBuffer sb = new StringBuffer();
		if (stack.empty())
			return "/";
		while (!stack.empty()) {
			sb.insert(0, "/" + stack.pop());
		}
		return sb.toString();
	}

	/*
	 * Length of the last word
	 * 
	 * Given a string s consists of upper/lower-case alphabets and empty space
	 * characters ' ', return the length of last word in the string.
	 * 
	 * If the last word does not exist, return 0.
	 */
	public int lengthOfLastWord(String s) {
		int p, len = 0;
		for (p = s.length() - 1; p >= 0 && s.charAt(p) == ' '; p--)
			;
		for (len = 0; p >= 0 && s.charAt(p) != ' '; len++, p--)
			;
		return len;
	}

	/*
	 * Anagrams
	 * 
	 * Given an array of strings, return all groups of strings that are
	 * anagrams.
	 */
	public ArrayList<String> anagrams(String[] strs) {
		ArrayList<String> ret = new ArrayList<String>();
		Hashtable<String, ArrayList<String>> ht = new Hashtable<String, ArrayList<String>>();
		for (int i = 0; i < strs.length; i++) {
			String sorted = sorted(strs[i]);
			ArrayList<String> val = ht.get(sorted);
			if (val != null) {
				val.add(strs[i]);
			} else {
				val = new ArrayList<String>();
				val.add(strs[i]);
				ht.put(sorted, val);
			}
		}
		Set<String> set = ht.keySet();
		for (String s : set) {
			ArrayList<String> val = ht.get(s);
			if (val.size() > 1) {
				ret.addAll(val);
			}
		}
		return ret;
	}

	public String sorted(String a) {
		char[] achar = a.toCharArray();
		Arrays.sort(achar);
		return new String(achar);
	}

	/*
	 * 
	 * Longest Common Prefix
	 * 
	 * Write a function to find the longest common prefix string amongst an
	 * array of strings.
	 */
	public String longestCommonPrefix(String[] strs) {
		String result = "";
		int len = strs.length;
		int i, j;
		if (len == 0)
			return "";
		for (j = 0;; ++j) {
			for (i = 0; i < len; ++i)
				if (j >= strs[i].length()
						|| strs[i].charAt(j) != strs[0].charAt(j))
					break;
			if (i < len)
				break;
			result += strs[0].charAt(j);
		}
		return result;
	}

	// End of Regular Expression Matching

	/*
	 * /* String to integer
	 */
	public int atoi(String str) {
		int len = str.length();
		if (len < 1)
			return 0;
		int sign = 1;
		int index = 0;
		while (str.charAt(index) == ' ') {
			index++;
		}
		if (str.charAt(index) == '+') {
			index++;
		} else if (str.charAt(index) == '-') {
			sign = -1;
			index++;
		}
		long num = 0;
		for (; index < len; index++) {
			if (str.charAt(index) < '0' || str.charAt(index) > '9')
				break;
			num = num * 10 + (str.charAt(index) - '0');
			if (num > Integer.MAX_VALUE && sign == 1)
				break;
		}
		if (num * sign > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if (num * sign < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return (int) num * sign;
	}

	/*
	 * ZigZag Conversion
	 * 
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given
	 * number of rows like this: (you may want to display this pattern in a
	 * fixed font for better legibility) P A H N A P L S I I G Y I R And then
	 * read line by line: "PAHNAPLSIIGYIR"
	 * 
	 * (i,j)= (j+1 )*n +i (j+1)*n -i
	 */

	public String convert(String s, int nRows) {
		if (nRows <= 1)
			return s;
		String ret = "";
		int zigsize = 2 * nRows - 2;
		for (int i = 0; i < nRows; ++i) {
			for (int base = i;; base += zigsize) {
				if (base >= s.length())
					break;
				ret += s.charAt(base);
				if (i > 0 && i < nRows - 1) {
					// middle need add ziggggging char
					int ti = base + zigsize - 2 * i;
					if (ti < s.length())
						ret += s.charAt(ti);
				}
			}
		}
		return ret;
	}

	/*
	 * Longest Palindromic Substring
	 * 
	 * Given a string S, find the longest palindromic substring in S. You may
	 * assume that the maximum length of S is 1000, and there exists one unique
	 * longest palindromic substring.
	 */
	public String longestPalindrome(String s) {
		if (s == null)
			return null;
		int n = s.length();
		String res = "";
		int left, right;
		for (int i = 0; i < 2 * n - 1; i++) {
			right = i / 2 + 1;
			left = (i % 2 == 0 ? i / 2 - 1 : i / 2);
			for (; left >= 0 && right < n && s.charAt(left) == s.charAt(right); left--, right++)
				if (right - left - 1 > res.length())
					res = s.substring(left + 1, right);
		}
		return res;
	}

	/*
	 * Longest Substring Without Repeating Characters Given a string, find the
	 * length of the longest substring without repeating characters. For
	 * example, the longest substring without repeating letters for "abcabcbb"
	 * is "abc", which the length is 3. For "bbbbb" the longest substring is
	 * "b", with the length of 1.
	 */
	public int lengthOfLongestSubstring(String s) {
		int length = s.length();
		if (length == 0) {
			return 0;
		}
		int[] countTable = new int[256];
		Arrays.fill(countTable, -1);
		int max = 1;
		int start = 0;
		int end = 1;

		countTable[s.charAt(0)] = 0;
		while (end < length) {
			// Has not reached a duplicate char
			if (countTable[s.charAt(end)] >= start) {
				start = countTable[s.charAt(end)] + 1;
			}
			max = Math.max(max, end - start + 1);
			countTable[s.charAt(end)] = end;
			end++;
		}
		return max;
	}

	/*
	 * Minimum Window Substring
	 * 
	 * Given a string S and a string T, find the minimum window in S which will
	 * contain all the characters in T in complexity O(n). For example, S =
	 * "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
	 */
	public String minWindow(String S, String T) {
		int[] srcHash = new int[100];
		for (int i = 0; i < T.length(); i++) {
			srcHash[T.charAt(i)]++;
		}
		int start = 0, i = 0;
		int[] destHash = new int[100];
		int found = 0;
		int begin = -1, end = S.length(), minLength = 1 + S.length();
		for (start = i = 0; i < S.length(); i++) {
			if (srcHash[S.charAt(i)] != 0) {
				destHash[S.charAt(i)]++;
				if (destHash[S.charAt(i)] <= srcHash[S.charAt(i)])
					found++;
				if (found == T.length()) {
					// find the first window that satisfied this condition
					// next step : shrink the window size
					while (start < i) {
						if (srcHash[S.charAt(start)] == 0
								|| (srcHash[S.charAt(start)] != 0 && (--destHash[S
										.charAt(start)]) >= srcHash[S
										.charAt(start)])) {
							start++;
						} else {
							break;
						}
					}
					if (i - start + 1 < minLength) {
						minLength = i - start + 1;
						begin = start;
						end = i;
					}
					found--;
					start++;
				}
			}
		}
		return begin == -1 ? "" : S.substring(begin, end + 1);
	}

	/*
	 * Longest Valid Parentheses We can do it better without using a stack,
	 * right? Try it!
	 */
	/*
	 * Given a string containing just the characters '(' and ')', find the
	 * length of the longest valid (well-formed) parentheses substring.
	 * 
	 * For "(()", the longest valid parentheses substring is "()", which has
	 * length = 2.
	 * 
	 * Another example is ")()())", where the longest valid parentheses
	 * substring is "()()", which has length = 4.
	 * 
	 * Algorithm time complexity is O(n), and space complexity is O(n) Basic
	 * algrithm is to keep track of the longest matched paramthesis. But we need
	 * to separate the valid substring, which have 2 special cases: ())()() and
	 * ()()(((), and others(whole string is valid) are easy.
	 * 
	 * case 1: use the last variable to store the last ) pos when stack is empty
	 * case 2: compare the max with the cur pos - the top position of (
	 */
	public int longestValidParentheses(String s) {
		// Using stack to store the position of '('
		Stack<Integer> stack = new Stack<Integer>();
		int i = 0;
		int last = -1;
		int max = 0;

		while (i < s.length()) {
			if (s.charAt(i) == '(') {
				// push it to the stack
				stack.push(i);
			} else {
				if (stack.empty()) {
					// last will save the pos of last ')' when stack is empty
					last = i;
				} else {
					stack.pop();
					if (stack.empty()) {
						// deal with cases when s is ())()()
						max = Math.max(max, i - last);
					} else {
						// deal with cases when s is ()()(((()
						max = Math.max(max, i - stack.peek());
					}
				}
			}
			i++;
		}
		return max;
	}

	/*
	 * Next Permutation Implement next permutation, which rearranges numbers
	 * into the lexicographically next greater permutation of numbers.
	 * 
	 * If such arrangement is not possible, it must rearrange it as the lowest
	 * possible order (ie, sorted in ascending order).
	 * 
	 * The replacement must be in-place, do not allocate extra memory.
	 * 
	 * Here are some examples. Inputs are in the left-hand column and its
	 * corresponding outputs are in the right-hand column. 1,2,3 => 1,3,2 3,2,1
	 * 1,2,3 1,1,5 1,5,1
	 */
	boolean nextPermutation(int[] array) {
		// Find longest non-increasing suffix
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;
		// Now i is the head index of the suffix

		// Are we at the last permutation already?
		if (i <= 0)
			return false;

		// Let array[i - 1] be the pivot
		// Find rightmost element that exceeds the pivot
		int j = array.length - 1;
		while (array[j] <= array[i - 1])
			j--;
		// Now the value array[j] will become the new pivot
		// Assertion: j >= i

		// Swap the pivot with j
		int temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;

		// Reverse the suffix
		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}

		// Successfully computed the next permutation
		return true;
	}

	/*
	 * Substring with Concatenation of All Words
	 * 
	 * Substring with Concatenation of All Words You are given a string, S, and
	 * a list of words, L, that are all of the same length. Find all starting
	 * indices of substring(s) in S that is a concatenation of each word in L
	 * exactly once and without any intervening characters.
	 * 
	 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
	 * 
	 * You should return the indices: [0,9]. (order does not matter).
	 */
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (S == null || S.length() == 0 || L == null || L.length == 0)
			return res;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < L.length; i++) {
			if (map.containsKey(L[i])) {
				map.put(L[i], map.get(L[i]) + 1);
			} else {
				map.put(L[i], 1);
			}
		}
		for (int i = 0; i < L[0].length(); i++) {
			HashMap<String, Integer> curMap = new HashMap<String, Integer>();
			int count = 0;
			int left = i;
			for (int j = i; j <= S.length() - L[0].length(); j += L[0].length()) {
				String str = S.substring(j, j + L[0].length());
				if (map.containsKey(str)) {
					if (curMap.containsKey(str))
						curMap.put(str, curMap.get(str) + 1);
					else
						curMap.put(str, 1);
					if (curMap.get(str) <= map.get(str))
						count++;
					else {
						while (curMap.get(str) > map.get(str)) {
							String temp = S.substring(left,
									left + L[0].length());
							curMap.put(temp, curMap.get(temp) - 1);
							left += L[0].length();
						}
					}
					if (count == L.length) {
						res.add(left);
						String temp = S.substring(left, left + L[0].length());
						curMap.put(temp, curMap.get(temp) - 1);
						count--;
						left += L[0].length();
					}
				} else {
					curMap.clear();
					count = 0;
					left = j + L[0].length();
				}
			}
		}
		return res;
	}

	/*
	 * subString()
	 * 
	 * Returns a pointer to the first occurrence of needle in haystack, or null
	 * if needle is not part of haystack.
	 */
	public String strStr(String haystack, String needle) {
		int needleLen = needle.length();
		int haystackLen = haystack.length();
		if (needleLen == haystackLen && needleLen == 0)
			return "";
		if (needleLen == 0)
			return haystack;
		for (int i = 0; i < haystackLen; i++) {
			// make sure in boundary of needle
			if (haystackLen - i + 1 < needleLen)
				return null;
			int k = i;
			int j = 0;
			while (j < needleLen && k < haystackLen
					&& needle.charAt(j) == haystack.charAt(k)) {
				j++;
				k++;
				if (j == needleLen)
					return haystack.substring(i);
			}
		}
		return null;
	}

	/*
	 * Valid Parentheses
	 */
	public static boolean isValid(String s) {
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

	public String reverseWords(String s) {
		/*
		 * from the start to the end if the current char is a white space if we
		 * found a char before, we found a word: 1 save the word, reset found
		 * else ignore this white space else not a white space and not found
		 * flag, reset the found flag, save the starting index after walking
		 * through the string, if the found flag is set, then save the last
		 * word.
		 */
		if (s == null || s.length() == 0)
			return "";
		int ol = s.length();
		int start = 0;
		boolean foundWord = false;
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < ol; ++i) {
			if (s.charAt(i) == ' ') {
				if (foundWord) {
					foundWord = false;
					res.insert(0, s.substring(start, i) + ' ');
				}
			}
			// not a white space
			else if (!foundWord) {
				foundWord = true;
				start = i;
			}
		}// end of for loop
		if (foundWord)
			res.insert(0, s.substring(start, ol) + ' ');
		return res.toString().trim();
	}

	public static String cleanSpaces(String input) {
		if (input == null || input.length() == 0)
			return input;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length(); ++i)
			if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z'
					|| input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')
				sb.append(input.charAt(i));
		return sb.toString().toLowerCase();
	}

	public static boolean isPalindrome(String input) {
		if (input == null)
			return true;
		input = cleanSpaces(input);
		if (input.length() == 0)
			return true;
		if (input.length() % 2 == 0) {
			for (int i = 0; i < input.length() / 2; ++i)
				if (input.charAt(i) != input.charAt(input.length() - 1 - i))
					return false;
			return true;
		}
		if (input.length() % 2 == 1) {
			for (int i = 0; i < input.length() / 2; ++i)
				if (input.charAt(i) != input.charAt(input.length() - 1 - i))
					return false;
			return true;
		}
		return false;
	}

	/*
	 * Given a string s and a dictionary of words dict, determine if s can be
	 * segmented into a space-separated sequence of one or more dictionary
	 * words.
	 * 
	 * For example, given s = "leetcode", dict = ["leet", "code"].
	 * 
	 * Return true because "leetcode" can be segmented as "leet code".
	 */
	public boolean wordBreak(String s, Set<String> dict) {
		String sstr;
		if (dict.contains(s))
			return true;
		else {
			for (int i = 0; i < s.length(); i++) {
				sstr = s.substring(0, i);
				if (dict.contains(sstr))
					return wordBreak(s.substring(i), dict);
			}
			return false;
		}
	}

	public boolean wordBreakDP(String s, Set<String> dict) {
		boolean[] t = new boolean[s.length() + 1];
		t[0] = true; // set first to be true, why?
		// Because we need initial state
		for (int i = 0; i < s.length(); i++) {
			// should continue from match position
			if (!t[i])
				continue;
			for (String a : dict) {
				int len = a.length();
				int end = i + len;
				if (end > s.length())
					continue;

				if (t[end])
					continue;

				if (s.substring(i, end).equals(a)) {
					t[end] = true;
				}
			}
		}
		return t[s.length()];
	}

	/*
	 * Distinct Subsequences
	 * 
	 * 
	 * Given a string S and a string T, count the number of distinct
	 * subsequences of T in S.
	 * 
	 * A subsequence of a string is a new string which is formed from the
	 * original string by deleting some (can be none) of the characters without
	 * disturbing the relative positions of the remaining characters. (ie, "ACE"
	 * is a subsequence of "ABCDE" while "AEC" is not).
	 * 
	 * Here is an example: S = "rabbbit", T = "rabbit"
	 * 
	 * Return 3.
	 */

	public int numDistinctRec(String S, String T) {
		if (S.length() == 0) {
			return T.length() == 0 ? 1 : 0;
		}
		if (T.length() == 0) {
			return 1;
		}
		int cnt = 0;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == T.charAt(0)) {
				cnt += numDistinct(S.substring(i + 1), T.substring(1));
			}
		}
		return cnt;
	}

	int numDistinct(String S, String T) {
		int record[] = new int[200];
		for (int i = 1; i < 200; i++)
			record[i] = 0;

		record[0] = 1;
		for (int i = 1; i <= S.length(); i++)
			for (int j = T.length(); j >= 1; j--) {
				if (S.charAt(i - 1) == T.charAt(j - 1))
					record[j] += record[j - 1];
			}

		return record[T.length() - 1];
	}

	/*
	 * 
	 * Regular Expression Matching
	 * 
	 * Implement regular expression matching with support for '.' and '*'.
	 * 
	 * '.' Matches any single character. '*' Matches zero or more of the
	 * preceding element.
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * The function prototype should be: bool isMatch(const char *s, const char
	 * *p)
	 * 
	 * Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true
	 * isMatch("aaa","aa") → false isMatch("aa", "a*") → true isMatch("aa",
	 * ".*") → true isMatch("ab", ".*") → true isMatch("aab", "c*a*b") → true
	 */
	public static boolean isMatch(String s, String p) {
		return isM(s, p, 0, 0);
	}

	public static boolean isM(String s, String p, int i, int j) {
		if (j >= p.length()) { // pattern已经用光
			return i >= s.length(); // 如果s已经走完则匹配，否则不匹配
		}
		if (j == p.length() - 1) { // 如果pattern的j已经走到最后一个字符，如果想匹配，则s的i也必须在最后一个，且相等
			return (i == s.length() - 1)
					&& (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
		}

		// 如果pattern的下一个字符(j+1)不是*
		if (j + 1 < p.length() && p.charAt(j + 1) != '*') {
			if (i == s.length()) { // 如果s已经走完，则说明不匹配
				return false;
			}
			if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') { // 如果当前字符匹配
				return isM(s, p, i + 1, j + 1); // 继续下一个字符判断
			} else { // 如果当前字符不匹配，直接返回false
				return false;
			}
		}

		// 如果下一个字符(j+1)是* 且 当前字符匹配，则进行搜索：
		while (i < s.length() && j < p.length()
				&& (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
			// 因为*可以取0,1,2,...所以i=i,i+1,i+2,...对所有可能进行测试
			// 最后能否匹配取决于剩下的匹配是否成功
			if (isM(s, p, i, j + 2)) { // 只要找到一个匹配成功即可
				return true;
			}
			i++;
		}

		// 如果下一个字符(j+1)是* 且 当前字符不匹配，则pattern跳过两个，继续比较
		// 还有一种情况到这里是上面的最后一次尝试（i==s.length()）
		return isM(s, p, i, j + 2);
	}

	public boolean isMatch2(String s, String p) {
		int s_cur = 0, p_cur = 0, match = 0, star = -1;
		while (s_cur < s.length()) {
			if (p_cur < p.length()
					&& (s.charAt(s_cur) == p.charAt(p_cur) || p.charAt(p_cur) == '?')) {
				s_cur++;
				p_cur++;
			} else if (p_cur < p.length() && p.charAt(p_cur) == '*') {
				match = s_cur;
				star = p_cur;
				p_cur++;
			} else if (star != -1) {
				p_cur = star + 1;
				match = match + 1;
				s_cur = match;
			} else
				return false;
		}
		while (p_cur < p.length() && p.charAt(p_cur) == '*')
			p_cur++;

		if (p_cur == p.length())
			return true;
		else
			return false;
	}

	/*
	 * Interleaving String
	 * 
	 * 
	 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and
	 * s2.
	 * 
	 * For example, Given: s1 = "aabcc", s2 = "dbbca",
	 * 
	 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return
	 * false.
	 */

	public boolean isInterleave(String s1, String s2, String s3) {
		// Note: The Solution object is instantiated only once and is reused by
		// each test case.
		if (s1 == null || s2 == null || s3 == null)
			return false;
		if (s1.length() + s2.length() != s3.length())
			return false;
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true;
		for (int i = 1; i < s1.length() + 1; i++) {
			if (s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0]) {
				dp[i][0] = true;
			}
		}
		for (int j = 1; j < s2.length() + 1; j++) {
			if (s2.charAt(j - 1) == s3.charAt(j - 1) && dp[0][j - 1]) {
				dp[0][j] = true;
			}
		}
		for (int i = 1; i < s1.length() + 1; i++) {
			for (int j = 1; j < s2.length() + 1; j++) {
				if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) {
					dp[i][j] = true;
				}
				if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]) {
					dp[i][j] = true;
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	/*
	 * Scramble String
	 * 
	 * Given a string s1, we may represent it as a binary tree by partitioning
	 * it to two non-empty substrings recursively.
	 * 
	 * Below is one possible representation of s1 = "great":
	 * 
	 * great / \ gr eat / \ / \ g r e at / \ a t To scramble the string, we may
	 * choose any non-leaf node and swap its two children.
	 */

	public boolean isScramble(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}

		if (s1.length() == 0) {
			return s2.length() == 0;
		}
		if (s1.length() != s2.length()) {
			return false;
		}
		if (s1.equals(s2)) {
			return true;
		}

		int value1 = 0;
		int value2 = 0;

		// check is s1 and s2 has same chars
		for (int i = 0; i < s1.length(); i++) {
			value1 += s1.charAt(i) - '0';
			value2 += s2.charAt(i) - '0';
		}

		if (value1 != value2) {
			return false;
		}

		for (int i = 1; i < s1.length(); i++) {
			String s1Left = s1.substring(0, i);
			String s1Right = s1.substring(i);

			String s2Left = s2.substring(0, i);
			String s2Right = s2.substring(i);

			if (isScramble(s1Left, s2Left) && isScramble(s1Right, s2Right)) {
				return true;
			}
			s2Left = s2.substring(s2.length() - i);
			s2Right = s2.substring(0, s2.length() - i);

			if (isScramble(s1Left, s2Left) && isScramble(s1Right, s2Right)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Remove Adjacent Pairs String
	 */
	public static String removeAdjacentPairs(String s) {
		int len = s.length();
		if (len == 0) {
			return s;
		}

		int cur = 0;
		char[] ss = s.toCharArray();
		for (int probe = 1; probe < len; probe++) {
			while (cur >= 0 && probe < len && ss[probe] == ss[cur]) {
				probe++;
				cur--;
			}
			if (cur + 1 < len && probe >= 0 && probe < len) {
				cur++;
				ss[cur] = ss[probe];
			}
		}

		return new String(ss, 0, cur + 1);
	}

	/*
	 * Isomorphic Strings
	 * 
	 * Given two (dictionary) words as Strings, determine if they are
	 * isomorphic.
	 * 
	 * Two words are called isomorphic if the letters in one word can be
	 * remapped to get the second word.
	 * 
	 * Remapping a letter means replacing all occurrences of it with another
	 * letter while the ordering of the letters remains unchanged.
	 * 
	 * No two letters may map to the same letter, but a letter may map to
	 * itself.
	 * 
	 * Example:
	 * 
	 * given "foo", "app"; returns true we can map f -> a and o->p given "bar",
	 * "foo"; returns false we can't map both 'a' and 'r' to 'o' given "ab",
	 * "ca"; returns true we can map 'a' -> 'b' and 'c' -> 'a'
	 */

	public static boolean check(String s, String t) {
		if (s.length() != t.length())
			return false;
		HashMap<Character, Character> map1 = new HashMap<Character, Character>();
		HashMap<Character, Character> map2 = new HashMap<Character, Character>();

		for (int i = 0; i < s.length(); i++) {
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);
			if (map1.containsKey(c1)) {
				if (map1.get(c1) != c2)
					return false;
			}
			if (map2.containsKey(c2)) {
				if (map2.get(c2) != c1)
					return false;
			}

			map1.put(c1, c2);
			map2.put(c2, c1);
		}
		return true;
	}
}
