//
// Created by Lee on 2019-04-19.
//

#include "PhotoThumbnail.h"

#include <QLabel>
#include <QDebug>

#include <src/image/ImagePool.h>
#include <src/util/ThumbnailMaker.h>
#include <src/util/ImageDownloader.h>
#include <src/RootUnit.h>


PhotoThumbnail::PhotoThumbnail(QWidget *parent,
	                           PhotoGroup * group) : QWidget(parent) {
	pushButton = new QPushButton(this);
	pushButton->resize(475, 475);
	QLabel * textLabel = new QLabel(pushButton);
	textLabel->setText(group->getName());
	QRect tmpRect = pushButton->geometry();
	tmpRect.moveTo(tmpRect.x() + 232, tmpRect.y());
	tmpRect.setSize(QSize(475, 25));
	textLabel->setGeometry(tmpRect);
	photos = group->getPhotoList();

	this->resize(475, 475);
	ThumbnailMaker::makeThumbnailOfPhotoGroup(group, pushButton, thumbnails);

	connect(pushButton, SIGNAL(released()), this, SLOT(handleButton()));
}

void PhotoThumbnail::handleButton() {
	auto rootUnit = (RootUnit *)(this->parent()->parent()->parent());
	ViewerView *viewerView = rootUnit->getViewerView();

	viewerView->updatePhotoList(&photos);
	rootUnit->getStackedWidget()->setCurrentIndex(RootUnit::VIEW_IMAGE);
}
