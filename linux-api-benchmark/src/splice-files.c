/*
 * splice-files.c
 *
 *  Created on: Oct 19, 2016
 *      Author: mayi
 */
#define _GNU_SOURCE
#include <fcntl.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>

#define BUFSIZE 64

static int readFile(int fd,size_t len);

int main(int argc, char** argv){

	int fd1, fd2;
	ssize_t total_bytes_sent = -1, count = 16 , bytes_sent = -1, offset = 0;
	if((fd1 = open ("./a.txt", O_RDONLY) >= 0) && (fd2 = open ("./b.txt", O_WRONLY))){
		readFile(fd1, 64);
		readFile(fd2, 64);
//	    while (total_bytes_sent < count) {
//	        if ((bytes_sent = sendfile(fd2, fd1, &offset, count - total_bytes_sent)) < 0) {
//	            if (errno == EINTR || errno == EAGAIN) {
//	            	printf("%d\n", errno);
//	                continue;
//	            }
//	            perror("sendfile");
//	            return -1;
//	        }
//	        total_bytes_sent += bytes_sent;
//	    }
	}

	close(fd1);
	close(fd2);
	return 0;
}

static int readFile(int fd,size_t len){

	int readbytes = 0, rv = 0;
	char buf[BUFSIZE];

	if(fd < 0){
		perror("fd is incorrect");
		return -1;
	}
	memset(buf, 0, BUFSIZE);

//	fseek(fd, offset, SEEK_SET);

	while(readbytes < len){
		rv = read(fd, buf, BUFSIZE);
		if(rv > 0 ){
			readbytes+=rv;
			printf("%s", buf);
		}
	}
	return 0;
}
