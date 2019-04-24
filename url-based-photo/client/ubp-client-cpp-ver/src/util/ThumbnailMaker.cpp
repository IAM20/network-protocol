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

void ThumbnailMaker::makeThumbnailOfPhotoGroup(std::vector<Photo> photos, QWidget *parent, QLabel * thumbnails[4]) {
	unsigned long size = photos.size();

	for(int i = 0; i < 4; i++) {
		if (thumbnails[i] != nullptr) {
			delete thumbnails[i];
			thumbnails[i] = nullptr;
		}
	}

	for (int i = 0; i < 4 && i < size; i++) {
		Photo photo = photos[i];
		QPixmap image = ImagePool::getQPixmap(photo.getUrl());
		image = image.scaled(200, 200, Qt::KeepAspectRatio);
		thumbnails[i] = new QLabel(parent);
		QRect rect = parent->geometry();
		rect.moveTo(rect.x() + movePointer[i].first, rect.y() + movePointer[i].second);
		thumbnails[i]->setGeometry(rect);
		thumbnails[i]->resize(200, 200);
		thumbnails[i]->setPixmap(image);
		thumbnails[i]->show();
	}
}

void ThumbnailMaker::makeThumbnailOfPhotoGroup(PhotoGroup *group, QWidget *parent, QLabel * thumbnails[4]) {
	makeThumbnailOfPhotoGroup(group->getPhotoList(), parent, thumbnails);
}