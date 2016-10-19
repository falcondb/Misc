#include <search.h>
#include <stdlib.h>
#include <talloc.h>

#define DATASIZE 1024

int main(int argc, char** argv){

	int arrInt[DATASIZE];
	char** arrChar;

	int i, rval = 0;

	ENTRY e, *ep;

	if(!(rval = hcreate((int)(DATASIZE * 1.25))))
		return rval;

	/* create an int array for search */
	for (i = 0 ; i< DATASIZE; arrInt[i++] = i);

	/* create an int-int hashtable for search */
	arrChar = talloc_array(NULL, char*, DATASIZE);
	for(i = 0; i < DATASIZE; arrChar[i++] = talloc_asprintf(arrChar, "%d", i));

	for(i = 0; i < DATASIZE; i++){
		e.key =  arrChar[i];
		e.data = (void *) i;
	    ep = hsearch(e, ENTER);
	    if (ep == NULL)
	    	goto cleanupAll;
	}

	/* search for random int in the hashtable */
	for (i = 0; i < 10; i++){
		e.key = arrChar[random()%DATASIZE];
		e.data = NULL;
		ep = hsearch(e, FIND);
#ifdef DEBUG
		if(ep)
			printf("%s->%d\n", ep->key, (int)ep->data);
		else
			fprintf(stderr, "%s is not found\n", e.key);
#endif
	}
	/* search for random int which may be not in the hashtable */
	for (i = 0; i < 10; i++){
		asprintf(&e.key, "%d", DATASIZE + i);
		e.data = NULL;
		ep = hsearch(e, FIND);
#ifdef DEBUG
		if(ep)
			printf("%s->%d\n", ep->key, (int)ep->data);
		else
			printf("%s is not found\n", e.key);
#endif
		free(e.key);
	}
/* Clean up */
cleanupAll:
	talloc_free(arrChar);
	hdestroy();
	return rval;
}
