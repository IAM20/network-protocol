#! /bin/bash

if ! [ -x "$(command -v npm)" ]; then
	echo "node must be installed... T_T"
	exit 1
fi

unameOut="$(uname -s)"

case "${unameOut}" in
	Linux*)		machine=Linux;;
	Darwin*)	machine=Mac;;
	CYGWIN*)	machine=Windows;;
	MINGW*)		machine=Windows;;
	*)		machine="Unknown:${unameOut}";;
esac

npm install --savedev github-uploader
git clone https://github.com/2013008264/github-uploader-binary
mv node_modules github-uploader-binary/
rm package-lock.json

#Build with cmake

if [ -x "$(command -v cmake)" ]; then
  mkdir build
  cd build
  cmake ..
	make
  cd ..
  exit 0
fi
