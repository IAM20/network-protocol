#include "RootUnit.h"

#include <QDebug>
#include <QApplication>
#include <QStackedWidget>
#include <QVBoxLayout>

int main(int argc, char * argv[]) {
	QApplication application(argc, argv);
	RootUnit unit;
	unit.resize(975, 1100);
	unit.show();

	return application.exec();
}
