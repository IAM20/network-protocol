//
// Created by Lee on 2019-0409.
//

#include "Photo.h"

void Photo::init(long id,
             long photoGroupId,
             QString url,
             QString mimeType,
             QString name,
             QString comment) {
    this->id = id;
    this->photoGroupId = photoGroupId;
    this->url = url;
    this->mimeType = mimeType;
    this->name = name;
    this->comment = comment;
}

Photo::Photo() {
    init(0, 0, nullptr, nullptr, nullptr, nullptr);
}

Photo::Photo(long photoGroupId, QString &url, QString &mimeType) {
    init(0, photoGroupId, url, mimeType, nullptr, nullptr);
}

Photo::Photo(long photoGroupId, QString &url, QString &mimeType, QString &name) {
    init(0, photoGroupId, url, mimeType, name, nullptr);
}

Photo::Photo(long photoGroupId, QString &url, QString &mimeType, QString &name, QString &comment) {
    init(0, photoGroupId, url, mimeType, name, comment);
}

Photo::Photo(long id, long photoGroupId, QString &url, QString &mimeType, QString &name, QString &comment) {
    init(id, photoGroupId, url, mimeType, name, comment);
}

long Photo::getId() {
    return id;
}

long Photo::getPhotoGroupId() {
    return photoGroupId;
}

QString Photo::getName() {
    return name;
}

QString Photo::getMimeType() {
    return mimeType;
}

QString Photo::getUrl() {
    return url;
}

QString Photo::getComment() {
    return comment;
}

void Photo::setId(long id) {
    this->id = id;
}

void Photo::setPhotoGroupId(long id) {
    this->photoGroupId = id;
}

void Photo::setName(QString name) {
    this->name = name;
}

void Photo::setMimeType(QString mimeType) {
    this->mimeType = mimeType;
}

void Photo::setUrl(QString url) {
    this->url = url;
}

void Photo::setComment(QString comment) {
    this->comment = comment;
}
