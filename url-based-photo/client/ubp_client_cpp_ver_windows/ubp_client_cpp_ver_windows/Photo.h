//
// Created by Lee on 2019-04-19.
//

#ifndef UBP_CLIENT_CPP_VER_PHOTO_H
#define UBP_CLIENT_CPP_VER_PHOTO_H

#include <QString>

class Photo {
private:
    long id;
    long photoGroupId;
    QString name;
    QString mimeType;
    QString url;
    QString comment;
    void init(long id, long photoGroupId, QString url, QString mimeType, QString name, QString comment);

public:
    Photo();
    Photo(long photoGroupId, QString &url, QString &mimeType);
    Photo(long photoGroupId, QString &url, QString &mimeType, QString &name);
    Photo(long photoGroupId, QString &url, QString &mimeType, QString &name, QString &comment);
    Photo(long id, long photoGroupId, QString &url, QString &mimeType, QString &name, QString &comment);

    long getId();
    long getPhotoGroupId();
    QString getName();
    QString getMimeType();
    QString getUrl();
    QString getComment();

    void setId(long id);
    void setPhotoGroupId(long id);
    void setName(QString name);
    void setMimeType(QString mimeType);
    void setUrl(QString url);
    void setComment(QString comment);
};


#endif //UBP_CLIENT_CPP_VER_PHOTO_H
