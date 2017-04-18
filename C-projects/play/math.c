/*
 * math.c
 *
 *  Created on: Mar 28, 2017
 *      Author: mayi
 */
#include <stdint.h>
#include <stddef.h>
#include <assert.h>
#include <stdlib.h>
#include <sys/queue.h>


struct fc_list_ptr_t {
	int64_t sum;
	LIST_ENTRY(fc_list_ptr_t) entries;
} fc_list_ptr_t;

struct pair_i{
	int32_t st, end;
} pair_t;

struct fc_list_pair_t {
	struct pair_i pair;
	LIST_ENTRY(fc_list_ptr_t) entries;
} fc_list_pair_t;

typedef LIST_HEAD(fc_l_ptr_h, fc_list_ptr_t) fc_list_ptr_h;
typedef LIST_HEAD(fc_l_pair_h, fc_list_pair_t) fc_list_pair_h;

static float fclib_pow(float x, int32_t n){
	if(x == 0)
		return 0;
	if(x == 1)
		return 1;
	if(n == 0)
		return 1;
	if(n == 1)
		return x;

	float f = fclib_pow(x, n/2);
	return n&0x01 == 0 ? f * f: n > 0 ? f * f * x: f * f * 1/x;
}


static void testpow(){

	assert(fclib_pow(1, 3) == 1);
	assert(fclib_pow(0, 3) == 0);

	assert(fclib_pow(2, 3) == 8);
	assert(fclib_pow(-2, 3) == -8);
	assert(fclib_pow(-2, 4) == 16);

	assert(fclib_pow(0.5, 3) -  0.125 < 0.00001);
	assert(fclib_pow(0.5, -3) == 8);
}

int int_compare(const void * i1, const void * i2){
	return *(int32_t *) (i1) - *(int32_t *) (i2);
}

static int64_t smallestDifInArrays(int32_t * a1, int32_t * a2, int8_t sizeA1, int8_t sizeA2){
	int64_t res = 0x7fffffff;

	if(!(a1 && a2))
		return 0;

	qsort(a1, sizeA1, sizeof(int32_t), &int_compare);
	qsort(a2, sizeA2, sizeof(int32_t), &int_compare);

	int ind1 = 0, ind2 = 0;
	uint64_t dif = 0;
	for(; ind1 < sizeA1 && ind2 < sizeA2; ){
		dif = a1[ind1] > a2[ind2]? a1[ind1] - a2[ind2]: a2[ind2] - a1[ind1];
		res = res < dif ? res : dif;
		if(a1[ind1] > a2[ind2])
			++ind2;
		else
			++ind1;
	}
	return res;
}

static void testsmallestDifInArrays(){
	int32_t a1[] = {2, 4, 26, 118};
	int32_t a2[] = {-2, -4, 0, 3};

	assert(smallestDifInArrays(a1, a2, sizeof(a1)/sizeof(int32_t), sizeof(a2)/sizeof(int32_t))
			== 1);

	int32_t a11[] = {2, 4, 26, 118};
	int32_t a21[] = {-22, -424, 50, 99};

	assert(smallestDifInArrays(a11, a21, sizeof(a11)/sizeof(int32_t), sizeof(a21)/sizeof(int32_t))
			== 19);
}

static void sumIntervals(int32_t * arr, size_t a_size, struct pair_i * queries,
		size_t q_size, int64_t * res){
	if(!(arr && queries && res))
		return;

	int64_t sum [a_size];
	size_t i;
	sum[0] =  arr[0];
	for(i = 1; i< a_size; sum[i] = sum[i-1] + arr[i], i++);
	for(i = 0; i < q_size;
			res[i] = queries[i].st == 0 ? sum[queries[i].end] : sum[queries[i].end] - sum[queries[i].st - 1],
					i++);
}

static void sumIntervalsList(const int32_t * arr, size_t a_size, fc_list_pair_h * queries, fc_list_ptr_h * res){

	if(!(arr && queries && res))
		return;

	int64_t sum [a_size];
	size_t i;
	struct fc_list_ptr_t * resNode;
	struct fc_list_pair_t * n;

	sum[0] =  arr[0];

	for(i = 1; i< a_size; sum[i] = sum[i-1] + arr[i], i++);
	LIST_FOREACH(n, queries, entries){
		resNode = malloc(sizeof(struct fc_list_ptr_t));
		resNode->sum = n->pair.st == 0 ?
				sum[n->pair.end] : sum[n->pair.end] - sum[n->pair.st - 1];
		LIST_INSERT_HEAD(res, resNode, entries);
	}
}

static void testsumIntervals(){
	int32_t a1[] = {2, 10, 4, 26, 118};
	int64_t exp[] = {40, 42, 144};
	struct pair_i q[] = {{1,3}, {0, 3}, {3,4}};
	int64_t res[sizeof(q)/sizeof(struct pair_i)];

	sumIntervals(a1, sizeof(a1)/sizeof(int32_t), q, sizeof(q)/sizeof(struct pair_i), res);
	assert(memcmp(exp, res, sizeof(exp)) == 0);

}

static void testsumIntervalsList(){
	int32_t a1[] = {2, 10, 4, 26, 118};
	int64_t exp[] = {40, 42, 144};
	struct pair_i q[] = {{1,3}, {0, 3}, {3,4}};

	fc_list_pair_h quries;
	fc_list_ptr_h res;
	struct fc_list_pair_t * qn;

	LIST_INIT(&quries);
	LIST_INIT(&res);

	qn = malloc(sizeof(struct fc_list_ptr_t ));
	qn->pair.st = 1;
	qn->pair.end = 3;
	LIST_INSERT_HEAD(&quries, qn, entries);

	qn = malloc(sizeof(struct fc_list_ptr_t ));
	qn->pair.st = 0;
	qn->pair.end = 3;
	LIST_INSERT_HEAD(&quries, qn, entries);

	qn = malloc(sizeof(struct fc_list_ptr_t ));
	qn->pair.st = 3;
	qn->pair.end = 4;
	LIST_INSERT_HEAD(&quries, qn, entries);

	sumIntervalsList(a1, sizeof(a1)/sizeof(int32_t), &quries, & res);

	struct fc_list_ptr_t * rn;
	uint8_t ind = 0;
	LIST_FOREACH(rn, & res, entries){
		assert(rn->sum == exp[ind++]);
	}
}


static int32_t findSingleInSortedArray(const int32_t * const arr, size_t size){

	if(!arr || size % 2 == 0)
		return 0xffffffff;
	if(size == 1)
		return *arr;

	uint8_t st = 0, end = size, mid;

	while(st <= end){
		mid = st>>1 + end>>1;
		if(mid > 0 && mid < size -1 ){
			if(arr[mid - 1] != arr[mid] && arr[mid] != arr[mid+1])
				return arr[mid];
			else {
				if(arr[mid - 1] == arr[mid]){
					if((mid - st) & 0x01 == 0)
						end = mid - 2;
					else
						st = mid + 1;
			}
				else{
					if((mid - st) & 0x01 == 0)
						st = mid + 2;
					else
						end = mid - 1;
				}
			}
		}
		else {
			if(mid == 0 && arr[0] != arr[1])
				return *arr;
			else if(mid == size -1 && arr[mid - 1] != arr[mid])
				return arr[mid];
			else
				return 0xffffffff;
		}
	}

	return 0xffffffff;
}

static void testfindSingleInSortedArray(){
	int32_t d1[] = {1};
	int32_t d2[] = {1, 1, 2};
	int32_t d3[] = {1, 2, 2};
	int32_t d4[] = {1, 1, 2, 3, 3};
	int32_t d5[] = {1, 1, 2, 2, 3};
	int32_t d6[] = {1, 1, 2, 2, 3, 4, 4};

	assert(findSingleInSortedArray(d1, sizeof(d1)/sizeof(int32_t)) == 1);
	assert(findSingleInSortedArray(d2, sizeof(d2)/sizeof(int32_t)) == 2);
	assert(findSingleInSortedArray(d3, sizeof(d3)/sizeof(int32_t)) == 1);
	assert(findSingleInSortedArray(d4, sizeof(d4)/sizeof(int32_t)) == 2);
	assert(findSingleInSortedArray(d5, sizeof(d5)/sizeof(int32_t)) == 3);
	assert(findSingleInSortedArray(d6, sizeof(d6)/sizeof(int32_t)) == 3);

}

static void getProduction(const int16_t * data,  int32_t * res, size_t sz){
	if(! data && res)
		return;
	int32_t tmp = 1;
	size_t i;

	res[0] = data[0];
	for(i=1; i< sz - 1; res[i] = res[i-1] * data[i],  ++i);

	for(i=sz-1; i>0; res[i] = res[i-1] * tmp, tmp *= data[i], --i);
	res[0] = tmp;
}

static void testgetProduction(){
	int16_t d1[] = {2, 8, 3};
	int16_t d2[] = {3, 5, 2, 2, 3, 4, 4};

	int32_t r1[3], r2[7];
	int32_t e1[] = {24, 6, 16};
	int32_t e2[] = {960, 576, 1440, 1440, 960, 720, 720};

	getProduction(d1, r1, 3);
	getProduction(d2, r2, 7);
	assert(!memcmp(r1, e1, sizeof(r1)/sizeof(int32_t)));
	assert(!memcmp(r2, e2, sizeof(r2)/sizeof(int32_t)));
}

//static uint8_t findMissingNumber(const char * nums, size_t nsize, uint8_t n){
//	if(!nums)
//		return 0xff;
//	char flags[n];
//	memset(flags, 0, n);
//
//	uint8_t i;
//
//}
//static uint8_t findMissingNumberHelper(const char * nums, size_t nsize, uint8_t n, size_t ist, char * flags){
//	if(ist == nsize - 1)
//		return findMissingFlag(flags, n);
//	else{
//		flags[nums]
//		if(findMissingNumberHelper(nums, nsize, n, ist+1, flags) )
//	}
//}
//
//static uint8_t findMissingFlag(const char * const flags, size_t s){
//	char found = 0;
//	uint8_t value = 0xff;
//	size_t i = 0;
//	for(; i<s; ++i){
//		if(!flags[i]){
//			if(found){
//				found = !found;
//				value = i;
//			}
//			else
//				return 0xff;
//		}
//	}
//	return 0xff;
//}

void main(){
	testgetProduction();
	//testfindSingleInSortedArray();
	//testsumIntervalsList();
	//testsumIntervals();
	//testsmallestDifInArrays();
	//testpow();
}


