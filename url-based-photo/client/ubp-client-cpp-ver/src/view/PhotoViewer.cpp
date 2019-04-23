//
// Created by Lee on 2019-04-19.
//

#include "PhotoViewer.h"

#include <src/image/ImagePool.h>
#include <src/util/ImageDownloader.h>

PhotoViewer::PhotoViewer(QWidget *parent, Photo *photo) : QLabel(parent) {
	this->resize(1000, 1000);
	updatePhoto(photo);
}

void PhotoViewer::updatePhoto(Photo *photo) {
	QPixmap pixmap = ImagePool::getQPixmap(photo->getUrl());
	this->setPixmap(pixmap.scaled(900, 900, Qt::KeepAspectRatio));
	this->update();
}