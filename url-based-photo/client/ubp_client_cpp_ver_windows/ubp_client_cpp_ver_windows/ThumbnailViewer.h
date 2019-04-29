//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_THUMBNAILVIEWER_H
#define UBP_CLIENT_CPP_VER_THUMBNAILVIEWER_H


#include "PhotoThumbnail.h"

#include <vector>

#include <QWidget>
#include <QStackedWidget>

#include "PhotoManager.h"
#include "PhotoGroup.h"

class ThumbnailViewer : public QWidget {
Q_OBJECT
public:
    explicit ThumbnailViewer(QWidget * parent = nullptr,
                             PhotoManager * photoManager = nullptr);
    void updateThumbnails();

private slots:
    void handleHomeButton();

private:
    std::vector<PhotoGroup> photoGroups;
    std::vector<PhotoThumbnail *> photoThumbnails;
    PhotoManager *photoManager;
    QPushButton *homeButton;
};

#endif //UBP_CLIENT_CPP_VER_THUMBNAILVIEWER_H
