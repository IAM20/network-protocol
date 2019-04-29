//
// Created by Lee on 2019-04-19.
//

#include "RootUnit.h"

RootUnit::RootUnit(QWidget *parent) : QMainWindow(parent) {
	stackedWidget = new QStackedWidget(this);
	this->setCentralWidget(stackedWidget);
	QWidget *widget = new QWidget();

	uploadButton = new QPushButton("UPLOAD", widget);
	uploadButton->setGeometry(QRect(QPoint(25, 275), QSize(450, 450)));

	viewButton = new QPushButton("VIEW", widget);
	viewButton->setGeometry(QRect(QPoint(500, 275), QSize(450, 450)));

	connect(uploadButton, SIGNAL(released()), this, SLOT(handleUploadButton()));
	connect(viewButton, SIGNAL(released()), this, SLOT(handleViewButton()));

	PhotoManager * manager = new PhotoManager("106.10.54.51:8080");
	thumbnailViewer = new ThumbnailViewer(this, manager);
	uploadView = new UploadView(this, manager);
	viewerView = new ViewerView(this, nullptr, manager);

	stackedWidget->addWidget(widget);
	stackedWidget->addWidget(thumbnailViewer);
	stackedWidget->addWidget(viewerView);
	stackedWidget->addWidget(uploadView);
	stackedWidget->setCurrentIndex(0);
}

QStackedWidget* RootUnit::getStackedWidget() {
	return stackedWidget;
}

ViewerView* RootUnit::getViewerView() {
	return viewerView;
}

void RootUnit::handleUploadButton() {
	uploadView->updateThumbnails();
	stackedWidget->setCurrentIndex(3);
	this->update();
}

void RootUnit::handleViewButton() {
	thumbnailViewer->updateThumbnails();
	stackedWidget->setCurrentIndex(1);
	this->update();
}
