//
// Created by Lee on 2019-04-23.
//

#include "ThumbnailMaker.h"

#include <src/image/ImagePool.h>

const std::pair<int, int> ThumbnailMaker::movePointer[4] = {
		std::pair<int, int>(25, 25),
		std::pair<int, int>(250, 25),
		std::pair<int, int>(25, 250),
		std::pair<int, int>(250, 250)
};

void ThumbnailMaker::makeThumbnailOfPhotoGroup(std::vector<Photo> photos, QWidget *parent) {
	unsigned long size = photos.size();

	for (int i = 0; i < 4 && i < size; i++) {
		Photo photo = photos[i];
		QPixmap image = ImagePool::getQPixmap(photo.getUrl());
		image = image.scaled(200, 200, Qt::KeepAspectRatio);
		QLabel * label = new QLabel(parent);
		QRect rect = parent->geometry();
		rect.moveTo(rect.x() + movePointer[i].first, rect.y() + movePointer[i].second);
		label->setGeometry(rect);
		label->resize(200, 200);
		label->setPixmap(image);
		label->show();
	}
}

void ThumbnailMaker::makeThumbnailOfPhotoGroup(PhotoGroup *group, QWidget *parent) {
	makeThumbnailOfPhotoGroup(group->getPhotoList(), parent);
}