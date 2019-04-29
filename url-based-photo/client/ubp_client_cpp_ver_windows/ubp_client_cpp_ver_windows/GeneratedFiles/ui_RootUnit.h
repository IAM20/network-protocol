/********************************************************************************
** Form generated from reading UI file 'RootUnit.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ROOTUNIT_H
#define UI_ROOTUNIT_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_RootUnitClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *RootUnitClass)
    {
        if (RootUnitClass->objectName().isEmpty())
            RootUnitClass->setObjectName(QStringLiteral("RootUnitClass"));
        RootUnitClass->resize(600, 400);
        menuBar = new QMenuBar(RootUnitClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        RootUnitClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(RootUnitClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        RootUnitClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(RootUnitClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        RootUnitClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(RootUnitClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        RootUnitClass->setStatusBar(statusBar);

        retranslateUi(RootUnitClass);

        QMetaObject::connectSlotsByName(RootUnitClass);
    } // setupUi

    void retranslateUi(QMainWindow *RootUnitClass)
    {
        RootUnitClass->setWindowTitle(QApplication::translate("RootUnitClass", "RootUnit", 0));
    } // retranslateUi

};

namespace Ui {
    class RootUnitClass: public Ui_RootUnitClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ROOTUNIT_H
