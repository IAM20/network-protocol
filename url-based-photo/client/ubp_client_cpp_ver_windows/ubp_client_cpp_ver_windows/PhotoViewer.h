//
// Created by Lee on 2019-04-19.
//

#ifndef UBP_CLIENT_CPP_VER_PHOTOVIEWER_H
#define UBP_CLIENT_CPP_VER_PHOTOVIEWER_H


#include <QLabel>
#include <QPixmap>

#include "Photo.h"

class PhotoViewer : public QLabel {
    Q_OBJECT
private:
    Photo photo;
public:
    explicit PhotoViewer(QWidget * parent = nullptr, Photo * photo = nullptr);
    void updatePhoto(Photo * photo);
};


#endif //UBP_CLIENT_CPP_VER_PHOTOVIEWER_H
