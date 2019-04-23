//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_THUMBNAILMAKER_H
#define UBP_CLIENT_CPP_VER_THUMBNAILMAKER_H


#include <QLabel>

#include <src/model/PhotoGroup.h>

class ThumbnailMaker {
public:
	static void makeThumbnailOfPhotoGroup(std::vector<Photo> photos, QWidget * parent);
	static void makeThumbnailOfPhotoGroup(PhotoGroup *group, QWidget *parent);
	static const std::pair<int, int> movePointer[4];
};


#endif //UBP_CLIENT_CPP_VER_THUMBNAILMAKER_H
