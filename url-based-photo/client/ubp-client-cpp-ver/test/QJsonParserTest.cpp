//
// Created by Lee on 2019-04-19.
//

#include <iostream>
#include "gtest/gtest.h"

int fact(int n) {
	if (n < 2) {
		return 1;
	}
	return n * fact(n - 1);
}

TEST(ubp_client_cpp_ver, ubp) {
	std::cout << "endl" << std::endl;
	EXPECT_EQ(6, fact(3));
}