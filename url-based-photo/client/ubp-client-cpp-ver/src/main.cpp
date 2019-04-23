#include "RootUnit.h"

#include <QDebug>
#include <QApplication>
#include <QStackedWidget>
#include <QVBoxLayout>
#include <gtest/gtest.h>

int main(int argc, char * argv[]) {
	//::testing::InitGoogleTest(&argc, argv);
	//int result = RUN_ALL_TESTS();
	//std::cout << result << std::endl;

	QApplication application(argc, argv);
	RootUnit unit;
	unit.resize(975, 1100);
	unit.show();

	return application.exec();
}