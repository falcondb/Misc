/*
 * helloWorld.h
 *
 *  Created on: Oct 14, 2016
 *      Author: mayi
 */

#ifndef SRC_HELLOWORLD_H_
#define SRC_HELLOWORLD_H_

#ifdef FALCONPRINT
	#define PRINTCOLOR
	#define FALCONBOLD
	#define FALCONBLINK
#endif

#ifdef PRINTCOLOR
	#define PCRED  "\x1B[31m"
	#define PCGRN  "\x1B[32m"
	#define PCYEL  "\x1B[33m"
#endif

#ifdef FALCONBOLD
	#define PBOLD "\x1B[51m"
#endif

#ifdef FALCONBLINK
	#define BLINK "\x1B[5m"
#endif

#if defined (PRINTCOLOR) || defined (FALCONBOLD) || defined(BLINK)
	#define PNRM "\x1B[0m"
#endif

#endif /* SRC_HELLOWORLD_H_ */
