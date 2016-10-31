/*
 * sendFileTest.c
 *
 *  Created on: Oct 30, 2016
 *      Author: mayi
 */
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/sendfile.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>

int main (int argc, char ** argv){
	int read_fd;
	int write_fd;
	off_t offset;
	struct stat stat_buf;

	if(argc < 3){
		fprintf(stdout, "Two parameters, first source file, second target file\n");
		return 0;
	}

	if((read_fd = open(argv[1], O_RDONLY) == -1))
			goto ErrorHandler;

	fstat (read_fd, &stat_buf);

	if((write_fd = open (argv[2], O_WRONLY | O_CREAT, stat_buf.st_mode) == -1))
			goto ErrorHandler;

	// To Do: check the bytes copied, repeat it if not done.
	if((sendfile(read_fd, write_fd, &offset, stat_buf.st_size) == -1))
		goto ErrorHandler;

	close(read_fd);
	close(read_fd);

	return 0;
ErrorHandler:
	perror("sendFile");
	return errno;
}
