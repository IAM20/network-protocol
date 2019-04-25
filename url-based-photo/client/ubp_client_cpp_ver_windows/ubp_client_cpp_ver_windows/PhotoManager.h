//
// Created by Lee on 2019-04-19.
//

#ifndef UBP_CLIENT_CPP_VER_PHOTOMANAGER_H
#define UBP_CLIENT_CPP_VER_PHOTOMANAGER_H


#include <vector>

#include <QtNetwork>
#include <QJsonArray>
#include <QJsonObject>

#include "Photo.h"
#include "PhotoGroup.h"

class PhotoManager {
public:
    explicit PhotoManager(QString serverAddr);
    std::vector<PhotoGroup> getAllPhotoGroup();
    Photo getPhotoInfo(Photo &photo);
    Photo getPhotoInfo(long id);
    PhotoGroup getPhotoGroupInfo(PhotoGroup &group);
    PhotoGroup getPhotoGroupInfo(QString groupName);
    PhotoGroup makeGroup(PhotoGroup &group);
    PhotoGroup makeGroup(QString groupName);
    PhotoGroup updateGroup(PhotoGroup &group, QString newName);
    PhotoGroup updateGroup(QString oldGroupName, QString newName);
    Photo updatePhoto(Photo &photo);
    Photo updatePhoto(Photo &photo, PhotoGroup &group);
    Photo updatePhoto(Photo &photo, QString groupName);
    void deletePhoto(Photo &photo);
    void deletePhotoGroup(QString groupName);
    void deletePhotoGroup(PhotoGroup &group);

private:
    const int TYPE_POST = 0;
    const int TYPE_PUT = 1;

    typedef enum {
        TYPE_GET_PHOTO = 0,
        TYPE_GET_PHOTO_GROUP,
        TYPE_POST_PHOTO,
        TYPE_POST_PHOTO_GROUP,
        TYPE_PUT_PHOTO,
        TYPE_PUT_PHOTO_GROUP,
        TYPE_DELETE_PHOTO,
        TYPE_DELETE_PHOTO_GROUP
    } RequestType;

    QString serverAddr;
    QJsonObject jsonBody;
    Photo executePutPhoto(QString uri, Photo photo, int type);
    QString uriMaker(RequestType type, QString str);
};


#endif //UBP_CLIENT_CPP_VER_PHOTOMANAGER_H
