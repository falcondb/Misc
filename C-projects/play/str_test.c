/*
 * str_test.c

 *
 *  Created on: Jan 7, 2017
 *      Author: mayi
 */
#define _GNU_SOURCE
#include <string.h>

void main(){
	test_token();
}

void test_token(){

	char d[] = "abc sd, ads. aoidsf ";

	char delim[] = " ,.";
	char * data = strdupa (d);
	char * token =  strtok(data, delim);

	for(; token != 0; puts(token), token = strtok(0, delim));

	data = strdupa (d);

	for(token = strsep(&data, delim); token != 0; puts(token), token = strsep(&data, delim));

}

void test_cmp(){
	char * str1, *str2, *str3;
	asprintf(&str1, "abcd");
	asprintf(&str2, "aBC");
	asprintf(&str3, "albkcdabcsidfs");

	//if(strcasecmp(str1, str2) ==  0)
	if(strncasecmp(str1, str2, 4) ==  0)
		puts("yes");
	else
		puts("no");

	puts(strchr(str1, 'c'));

	puts(strcasestr(str3, str2));
}

