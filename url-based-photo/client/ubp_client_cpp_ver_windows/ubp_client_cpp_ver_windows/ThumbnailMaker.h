//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_THUMBNAILMAKER_H
#define UBP_CLIENT_CPP_VER_THUMBNAILMAKER_H


#include <QLabel>

#include "PhotoGroup.h"

class ThumbnailMaker {
public:
    static void makeThumbnailOfPhotoGroup(std::vector<Photo> photos, QWidget * parent, QLabel * thumbnails[4]);
    static void makeThumbnailOfPhotoGroup(PhotoGroup *group, QWidget *parent, QLabel * thumbnails[4]);
    static const std::pair<int, int> movePointer[4];
};


#endif //UBP_CLIENT_CPP_VER_THUMBNAILMAKER_H
