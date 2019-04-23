//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_IMAGEPOOL_H
#define UBP_CLIENT_CPP_VER_IMAGEPOOL_H


#include <unordered_map>

#include <QPixmap>

class ImagePool {
public:
	static std::unordered_map<std::string, QPixmap> pixmapPool;
	static bool doesExist(QString);
	static void merge(QString, QPixmap);
	static QPixmap getQPixmap(QString);
};


#endif //UBP_CLIENT_CPP_VER_IMAGEPOOL_H
