//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_IMAGEDOWNLOADER_H
#define UBP_CLIENT_CPP_VER_IMAGEDOWNLOADER_H


#include <QPixmap>

class ImageDownloader {
public:
	static QPixmap getImageFromUri(QString uri);

};


#endif //UBP_CLIENT_CPP_VER_IMAGEDOWNLOADER_H
