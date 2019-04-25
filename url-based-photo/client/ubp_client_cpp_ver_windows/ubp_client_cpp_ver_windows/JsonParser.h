//
// Created by Lee on 2019-04-22.
//

#ifndef UBP_CLIENT_CPP_VER_JSONPARSER_H
#define UBP_CLIENT_CPP_VER_JSONPARSER_H


#include <vector>

#include <QJsonObject>

#include "Photo.h"
#include "PhotoGroup.h"

class JsonParser {
public:
    static Photo jsonToPhoto(QJsonObject json);
    static PhotoGroup jsonToPhotoGroup(QJsonObject json);
    static std::vector<PhotoGroup> jsonToPhotoGroupVector(QJsonArray json);
};


#endif //UBP_CLIENT_CPP_VER_JSONPARSER_H
