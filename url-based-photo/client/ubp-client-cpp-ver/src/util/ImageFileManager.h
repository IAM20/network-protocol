//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_IMAGEFILEMANAGER_H
#define UBP_CLIENT_CPP_VER_IMAGEFILEMANAGER_H

#include <QWidget>

#include <src/model/Photo.h>

class ImageFileManager {
public:
	static Photo uploadPhotoFileToGithub(QWidget * parent);
};


#endif //UBP_CLIENT_CPP_VER_IMAGEFILEMANAGER_H
