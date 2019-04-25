//
// Created by Lee on 2019-04-23.
//

#include "ViewerView.h"

#include <QStackedWidget>
#include <QFileDialog>

#include "RootUnit.h"
#include "ImageFileManager.h"

ViewerView::ViewerView(QWidget *parent,
                       std::vector<Photo> *photoList,
                       PhotoManager *photoManager) : QWidget(parent) {
    this->photoManager = photoManager;
    this->photoList = photoList;
    if (photoList == nullptr) {
        photoList = new std::vector<Photo>();
        Photo photo;
        photo.setUrl(
                "https://user-images.githubusercontent.com/35682872/56488787-60da8700-651a-11e9-9b1e-f4e1e40a1c00.jpeg");
        photo.setMimeType("jpg");
        photoList->emplace_back(photo);
    }

    prevButton = new QPushButton("PREV", this);
    nextButton = new QPushButton("NEXT", this);
    homeButton = new QPushButton("HOME", this);
    modifyButton = new QPushButton("MODIFY", this);
    deleteButton = new QPushButton("DELETE", this);

    prevButton->setGeometry(QRect(QPoint(0, 925), QSize(200, 50)));
    modifyButton->setGeometry(QRect(QPoint(200, 925), QSize(200, 50)));
    homeButton->setGeometry(QRect(QPoint(400, 925), QSize(200, 50)));
    deleteButton->setGeometry(QRect(QPoint(600, 925), QSize(200, 50)));
    nextButton->setGeometry(QRect(QPoint(800, 925), QSize(200, 50)));

    photoViewer = new PhotoViewer(this, &(photoList->at(now % photoList->size())));
    photoViewer->setGeometry(QRect(QPoint(25, 10), QSize(900, 900)));

    connect(nextButton, SIGNAL(released()), this, SLOT(handleNextButton()));
    connect(homeButton, SIGNAL(released()), this, SLOT(handleHomeButton()));
    connect(prevButton, SIGNAL(released()), this, SLOT(handlePrevButton()));
    connect(modifyButton, SIGNAL(released()), this, SLOT(handleModifyButton()));
    connect(deleteButton, SIGNAL(released()), this, SLOT(handleDeleteButton()));
}

void ViewerView::handleDeleteButton() {
    photoManager->deletePhoto(nowPhoto);
    photoList->erase(photoList->begin() + (now % photoList->size()));
    photoViewer->updatePhoto(&(photoList->at(now % photoList->size())));
}

void ViewerView::handleModifyButton() {
    Photo tmp = ImageFileManager::uploadPhotoFileToGithub(this);

    nowPhoto.setUrl(tmp.getUrl());
    Photo photo = photoManager->updatePhoto(nowPhoto);
    photoList->at(now % photoList->size()).setUrl(tmp.getUrl());
    photoViewer->updatePhoto(&(photoList->at(now % photoList->size())));
}

void ViewerView::updatePhotoList(std::vector<Photo> *photoList) {
    if (photoList == nullptr || photoList->empty()) {
        photoViewer->setPixmap(QPixmap());
        photoViewer->setText("NO PICTURE");
        return;
    }
    this->photoList = photoList;
    now = 0;
    photoViewer->updatePhoto(&photoList->at(now % photoList->size()));
    nowPhoto = photoList->at(now % photoList->size());
}

void ViewerView::handleHomeButton() {
    auto rootUnit = (RootUnit *) (this->parent()->parent());
    rootUnit->getStackedWidget()->setCurrentIndex(0);
}

void ViewerView::handleNextButton() {
    if (photoList == nullptr || photoList->empty()) return;
    photoViewer->updatePhoto(&(photoList->at(++now % photoList->size())));
    nowPhoto = photoList->at(now % photoList->size());
}

void ViewerView::handlePrevButton() {
    if (photoList == nullptr || photoList->empty()) return;
    if (--now < 0) {
        now += photoList->size() - 1;
    }
    photoViewer->updatePhoto(&photoList->at(now % photoList->size()));
    nowPhoto = photoList->at(now % photoList->size());
}
