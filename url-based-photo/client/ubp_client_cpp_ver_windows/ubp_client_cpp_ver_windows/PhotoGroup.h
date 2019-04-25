//
// Created by Lee on 2019-04-19.
//

#ifndef UBP_CLIENT_CPP_VER_PHOTOGROUP_H
#define UBP_CLIENT_CPP_VER_PHOTOGROUP_H


#include "Photo.h"

#include <vector>

#include <QString>

class PhotoGroup {
private:
    long id;
    QString name;
    std::vector<Photo> photoList;
    void init(long id, QString name);
    void init(long id, QString name, std::vector<Photo> &photoList);

public:
    PhotoGroup();
    PhotoGroup(QString &name);
    PhotoGroup(long id, QString &name);
    PhotoGroup(long id, QString &name, std::vector<Photo> &photoList);

    long getId();
    QString getName();
    std::vector<Photo> getPhotoList();

    void setId(long id);
    void setName(QString &name);
    void setPhotoList(std::vector<Photo> &photoList);
};


#endif //UBP_CLIENT_CPP_VER_PHOTOGROUP_H
