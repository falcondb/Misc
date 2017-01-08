/*
 * search_test.c
 *
 *  Created on: Jan 7, 2017
 *      Author: mayi
 */
#include <assert.h>
#include <fclib_util.h>
#include <signal.h>
#include <search.h>

struct wrapper {
	const char *name;
	const int val;
};

struct wrapper data[] = { { "Kermit", 1 }, { "Piggy", 2 }, {
		"Gonzo", 3 } , { "Kermit", 10 }, { "Kermit", 11 }, { "Kermit", 12 }, { "Kermit", 13 },
		{ "Kermit", 31 }, { "Kermit", 21 }, { "Kermit", 18 }, { "Kermit", 19 }};

void * root = 0;

/* This is the comparison function used for sorting and searching. */
int w_cmp(const void *v1, const void *v2) {
	return ((struct wrapper *) v1)->val - ((struct wrapper *) v2)->val;
}

void main(){
	test_tsearch();
}

int test_qsort(){
	struct wrapper key = {"Piggy", 3};
	size_t cnt = sizeof(data) / sizeof(struct wrapper);
	struct wrapper * rst;

	qsort(data, cnt, sizeof(struct wrapper), w_cmp);

	rst = bsearch(&key, data, cnt, sizeof(struct wrapper), w_cmp);

	puts(rst->name);
	return 0;
}

int test_lfind(){
	struct wrapper * rst = 0;
	struct wrapper key = {"Kermit", 2};
	size_t cnt = sizeof(data) / sizeof (struct wrapper);

	rst = (struct wrapper *) lfind(&key, &data, &cnt, sizeof(struct wrapper), &w_cmp);
	if(rst)
		puts(key.name);

	return 0;
}

void node_print (const void *v, VISIT value, int level){
	if(v && (value == postorder))
		printf( "I: %d %d %s\n", level, ((struct wrapper *) v)->val, ((struct wrapper *) v)->name);
	if(v && (value == leaf))
		printf( "L: %d %d %s\n", level, ((struct wrapper *) v)->val, ((struct wrapper *) v)->name);
}

void test_tsearch(){

	struct wrapper * rst;
	int i;
	size_t cnt = sizeof(data) / sizeof(struct wrapper);
	struct wrapper key = {"abc", 19};

	for(i=0; i< cnt; i++)
		tsearch(&data[i], &root, &w_cmp);

	if((rst = tfind(&key, &root, &w_cmp))){
		puts(rst->name);
	}
	//twalk(root, &node_print);
}
