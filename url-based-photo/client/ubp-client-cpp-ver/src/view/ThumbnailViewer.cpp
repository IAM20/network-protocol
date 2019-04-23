//
// Created by Lee on 2019-04-23.
//

#include "ThumbnailViewer.h"

#include <QWidget>

#include <src/api/PhotoManager.h>
#include <src/RootUnit.h>

ThumbnailViewer::ThumbnailViewer(QWidget *parent,
	                             PhotoManager *photoManager) : QWidget(parent) {
	this->photoManager = photoManager;
	homeButton = new QPushButton("HOME", this);
	homeButton->setGeometry(QRect(QPoint(382, 1025), QSize(200, 50)));

	connect(homeButton, SIGNAL(released()), this, SLOT(handleHomeButton()));
}

void ThumbnailViewer::handleHomeButton() {
	auto rootUnit = (RootUnit *) (this->parent()->parent());
	rootUnit->getStackedWidget()->setCurrentIndex(0);
}

void ThumbnailViewer::updateThumbnails() {
	for (PhotoThumbnail * thumbnail : photoThumbnails) {
		delete thumbnail;
	}
	while(!photoThumbnails.empty()) {
		photoThumbnails.pop_back();
	}

	photoGroups = photoManager->getAllPhotoGroup();
	for (PhotoGroup group : photoGroups) {
		photoThumbnails.emplace_back(new PhotoThumbnail(this, &group));
	}

	int x = 0, y = 0, i = 0;
	for (PhotoThumbnail * thumbnail : photoThumbnails) {
		thumbnail->setGeometry(QRect(QPoint(x, y), QSize(500, 500)));
		if (++i % 2) {
			x += 500;
		} else {
			y += 500;
			x -= 500;
		}
		thumbnail->show();
	}
}