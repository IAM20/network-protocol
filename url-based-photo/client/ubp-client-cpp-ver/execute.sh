unameOut="$(uname -s)"

case "${unameOut}" in
	Linux*)		machine=Linux;;
	Darwin*)	machine=Mac;;
	CYGWIN*)	machine=Windows;;
	MINGW*)		machine=Windows;;
	*)		machine="Unknown:${unameOut}";;
esac

if 	[ "${machine}" == "Linux" ] 
then	
  ./build/ubp_client_cpp_ver
elif 	[ "${machine}" == "Mac" ] 
then	
  ./build/ubp_client_cpp_ver
elif 	[ "${machine}" == "Windows" ] 
then 	
  ./build/ubp_client_cpp_ver.exe
fi
