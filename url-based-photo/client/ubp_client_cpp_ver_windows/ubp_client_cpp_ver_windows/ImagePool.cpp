//
// Created by Lee on 2019-04-23.
//

#include "ImagePool.h"

#include "ImageDownloader.h"

std::unordered_map<std::string, QPixmap> ImagePool::pixmapPool;

bool ImagePool::doesExist(QString uri) {
    auto iterator = pixmapPool.find(uri.toStdString());
    return (iterator != pixmapPool.end());
}

void ImagePool::merge(QString uri, QPixmap pixmap) {
    if (doesExist(uri)) {
        return;
    }

    pixmapPool.insert(std::pair<std::string, QPixmap>(uri.toStdString(), pixmap));
}

QPixmap ImagePool::getQPixmap(QString uri) {
    if(doesExist(uri)) {
        auto iterator = pixmapPool.find(uri.toStdString());
        return iterator->second;
    }
    QPixmap pixmap = ImageDownloader::getImageFromUri(uri);
    merge(uri, pixmap);
    return pixmap;
}
