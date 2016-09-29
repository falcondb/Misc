set -x -e
./configure --prefix=$PWD
make
make install

bin/helloWorld

