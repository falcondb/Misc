/*
 * t_str.c
 *
 *  Created on: Jan 8, 2017
 *      Author: mayi
 */
#include <string.h>

static unsigned int lengthOfLongestSubstring(const char * s){
	unsigned int rst = 0;
	int sl = strlen(s);
	short fpos[26];
	int ci, st, li;
	if(!s)
		return 0;

	memset(fpos, -1, sizeof(short) * 26);

	for(ci = 0, st = 0, li = 0; ci < sl; ++ci){
		if( fpos[ s[ci] - 'a'] == -1){
			fpos[ s[ci] - 'a'] = ci;
		}else{ // dup element
			rst =  rst > (ci - st)? rst : ci - st;
			for(li = st; li < fpos[ s[ci] - 'a']; fpos[li++] = -1 );
			st = fpos[ s[ci] - 'a'] + 1;
			fpos[ s[ci] - 'a'] = ci;
		}
	}

	rst =  rst > (sl - st)? rst : sl - st;
	return rst;
}

static unsigned int minDistanceWithRecurse(const char * w1, const char * w2,
		unsigned int wi1, unsigned int wi2) {
	unsigned int dis = 0;
	unsigned int i1, i2, i3;

	if (!(w1 && w2))
		return -1; //error

	if (wi1 == strlen(w1))
		return strlen(w2) - wi2;

	if (wi2 == strlen(w2))
		return strlen(w1) - wi1;

	if (w1[wi1] == w2[wi2]) {
		return minDistanceWithRecurse(w1, w2, wi1 + 1, wi2 + 1);
	}
	i1 = minDistanceWithRecurse(w1, w2, wi1, wi2 + 1);
	i2 = minDistanceWithRecurse(w1, w2, wi1 + 1, wi2);
	i3 = minDistanceWithRecurse(w1, w2, wi1 + 1, wi2 + 1);

	return (i1 < i2 ? i1 < i3 ? i1 : i3 : i2 < i3 ? i2 : i3) + 1;
}

static void longestCommonPrefix(const char **strs, unsigned short num) {

	int idx, si;
	char common;
	short found = 0;
	char rs[128];

	memset( &rs, 0, 128);
	if(!strs && num == 0)
		return;

	for(si = 0; si < num && strs[si]; ++si);
	if(si != num)
		return;

	for (idx = 0; !found; ++idx) {
		common = strs[0][idx];
		for (si = 1; si < num && !found; ++si) {
			if(common != strs[si][idx]){
				found = 1;
				break;
			}
		}
		if(!found)
			rs[idx]=common;
		else
			break;
	}
	puts(rs);
}

static unsigned int  longestValidParentheses(const char * s) {
	unsigned int rst = 0;
	unsigned short nl, idx, st;

	if(!s)
		return 0;

	for(nl = 0, idx = 0, st = 0; s[idx] ; ++idx){
		switch(s[idx]){
		case '(':
			++nl;
			break;
		case ')':
			--nl;
			if(nl == 0){
				rst = rst > (idx - st + 1) ? rst : (idx - st + 1);
			}else if(nl < 0){
				nl = 0;
				st = idx + 1;
			}
			break;
		default:
			break;
		}
	}

	return rst;
}

void main() {
	const char s[] = "badcdefcgdv";

	printf("%4u\n", lengthOfLongestSubstring(s));
}

//void main(){
//
//	printf("%d", longestValidParentheses(")(())("));
//}
//void main(){
//	const char * strs[] = { "abcd", "abcde", "abcde", "a" };
//
//	longestCommonPrefix(strs, sizeof(strs)/sizeof(char *));
//}

//void main() {
//	const char s1[] = "x";
//	const char s2[] = "badcd";
//
//	printf("%4u\n", minDistanceWithRecurse(s1, s2, 0, 0));
//}
