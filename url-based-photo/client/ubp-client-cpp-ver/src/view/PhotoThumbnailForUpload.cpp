//
// Created by Lee on 2019-04-23.
//

#include "PhotoThumbnailForUpload.h"

#include <QLabel>
#include <QDebug>
#include <QUrl>
#include <QStandardPaths>
#include <QFileDialog>
#include <QProcess>

#include <src/image/ImagePool.h>
#include <src/util/ThumbnailMaker.h>
#include <src/util/ImageFileManager.h>
#include <src/util/ImageDownloader.h>

PhotoThumbnailForUpload::PhotoThumbnailForUpload(QWidget *parent,
	                                             PhotoGroup *group,
	                                             PhotoManager *photoManager) : QWidget(parent) {
	groupId = group->getId();
	groupName = group->getName();
	this->photoManager = photoManager;
	photoGroup = group;
	pushButton = new QPushButton(this);
	pushButton->resize(475, 475);
	QLabel * textLabel = new QLabel(pushButton);
	textLabel->setText(group->getName());
	QRect tmpRect = pushButton->geometry();
	tmpRect.moveTo(tmpRect.x() + 232, tmpRect.y());
	tmpRect.setSize(QSize(475, 25));
	textLabel->setGeometry(tmpRect);

	this->resize(475, 475);
	photos = group->getPhotoList();
	updateThumbnail();

	connect(pushButton, SIGNAL(released()), this, SLOT(handleButton()));
}

QString PhotoThumbnailForUpload::getGroupName() {
	return groupName;
}

void PhotoThumbnailForUpload::updateThumbnail() {
	ThumbnailMaker::makeThumbnailOfPhotoGroup(photos, pushButton);
	this->update();
}

void PhotoThumbnailForUpload::handleButton() {
	Photo photo = ImageFileManager::uploadPhotoFileToGithub(this);
	photo = photoManager->updatePhoto(photo, groupName);

	photos.insert(photos.begin(), photo);
	updateThumbnail();
	qDebug() << photo.getUrl();
}