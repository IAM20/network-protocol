//
// Created by Lee on 2019-04-19.
//

#include "PhotoGroup.h"

void PhotoGroup::init(long id, QString name) {
    this->id = id;
    this->name = name;
}

void PhotoGroup::init(long id, QString name, std::vector<Photo> &photoList) {
    this->id = id;
    this->name = name;
    this->photoList = photoList;
}

PhotoGroup::PhotoGroup() {
    init(-1, nullptr);
}

PhotoGroup::PhotoGroup(QString &name) {
    init(-1, name);
}

PhotoGroup::PhotoGroup(long id, QString &name) {
    init(id, name);
}

PhotoGroup::PhotoGroup(long id, QString &name, std::vector<Photo> &photoList) {
    init(id, name, photoList);
}

long PhotoGroup::getId() {
    return id;
}

QString PhotoGroup::getName() {
    return name;
}

std::vector<Photo> PhotoGroup::getPhotoList() {
    return photoList;
}

void PhotoGroup::setId(long id) {
    this->id = id;
}

void PhotoGroup::setName(QString &name) {
    this->name = name;
}

void PhotoGroup::setPhotoList(std::vector<Photo> &photoList) {
    this->photoList = photoList;
}
