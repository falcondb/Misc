set -x -e
#### Generate configure.ac ####
autoreconf -i

### Configure and make ###
#### Another way to set the target path is setting DESTDIR before running make install ####
./configure --prefix=$PWD
make
make install

### Run a test ###
bin/helloWorld

