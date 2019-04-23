//
// Created by Lee on 2019-04-23.
//

#include "UploadView.h"

#include <QInputDialog>
#include <QLineEdit>

#include <src/RootUnit.h>

UploadView::UploadView(QWidget *parent, PhotoManager *photoManager) : QWidget(parent) {
	this->photoManager = photoManager;

	deleteGroupButton = new QPushButton("DELETE", this);
	homeButton = new QPushButton("HOME", this);
	createGroupButton = new QPushButton("CREATE GROUP", this);

	deleteGroupButton->setGeometry(QRect(QPoint(0, 1025), QSize(200, 50)));
	homeButton->setGeometry(QRect(QPoint(382, 1025), QSize(200, 50)));
	createGroupButton->setGeometry(QRect(QPoint(775, 1025), QSize(200, 50)));

	connect(deleteGroupButton, SIGNAL(released()), this, SLOT(handleDeleteGroupButton()));
	connect(homeButton, SIGNAL(released()), this, SLOT(handleHomeButton()));
	connect(createGroupButton, SIGNAL(released()), this, SLOT(handleCreateGroupButton()));
}

void UploadView::updateThumbnails() {
	for (PhotoThumbnailForUpload *tmp : photoThumbnails) {
		delete tmp;
	}

	while(!photoThumbnails.empty()) {
		photoThumbnails.pop_back();
	}

	photoGroups = this->photoManager->getAllPhotoGroup();
	for (PhotoGroup group : photoGroups) {
		photoThumbnails.emplace_back(new PhotoThumbnailForUpload(this, &group, photoManager));
	}

	replaceTheContents();
	this->update();
}

void UploadView::handleDeleteGroupButton() {
	bool ok;
	QString text = QInputDialog::getText(this, tr("Delete group"),
	                                     tr("Input group name : "), QLineEdit::Normal,
	                                     QDir::home().dirName(), &ok);
	try {
		photoManager->deletePhotoGroup(text);
	} catch (QUnhandledException &e) {
		return;
	}
	updateThumbnails();
}

void UploadView::handleHomeButton() {
	auto rootUnit = (RootUnit *) (this->parent()->parent());
	rootUnit->getStackedWidget()->setCurrentIndex(0);
	rootUnit->update();
}

void UploadView::replaceTheContents() {
	int x = 0, y = 0, i = 0;
	for (PhotoThumbnailForUpload * thumbnail : photoThumbnails) {
		qDebug() << x << " " << y;
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

void UploadView::handleCreateGroupButton() {
	bool ok;
	QString text = QInputDialog::getText(this, tr("Update group"),
	                                     tr("group name : "), QLineEdit::Normal,
	                                     QDir::home().dirName(), &ok);
	qDebug() << text;
	PhotoGroup group = photoManager->makeGroup(text);
	updateContents(group);
}

void UploadView::updateContents(PhotoGroup &group) {
	QString groupName = group.getName();
	PhotoGroup updateGroup = photoManager->getPhotoGroupInfo(groupName);
	photoThumbnails.emplace_back(new PhotoThumbnailForUpload(this, &updateGroup, photoManager));
	replaceTheContents();
	this->update();
}