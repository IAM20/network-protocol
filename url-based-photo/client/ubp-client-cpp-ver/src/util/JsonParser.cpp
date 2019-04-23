//
// Created by Lee on 2019-04-22.
//

#include "JsonParser.h"

#include <QJsonArray>
#include <QCoreApplication>
#include <QDebug>

Photo JsonParser::jsonToPhoto(QJsonObject json) {
	long id = (long)(json.value("id")).toDouble();
	long groupId = (long)(json.value("photoGroupId")).toDouble();
	QString name = (json.value("name")).toString();
	QString uri = (json.value("url")).toString();
	QString mimeType = (json.value("mimeType")).toString();
	QString comment = (json.value("comment")).toString();

	return Photo(id, groupId, uri, mimeType, name, comment);
}

PhotoGroup JsonParser::jsonToPhotoGroup(QJsonObject json) {
	long id = (long)(json.value("id")).toDouble();
	QString name = (json.value("name")).toString();
	QJsonArray array = (json.value("photoList")).toArray();
	std::vector<Photo> photoList;
	foreach (const QJsonValue & value, array) {
		qDebug() << value;
		photoList.emplace_back(jsonToPhoto(value.toObject()));
	}

	return PhotoGroup(id, name, photoList);
}

std::vector<PhotoGroup> JsonParser::jsonToPhotoGroupVector(QJsonArray jsonArray) {
	std::vector<PhotoGroup> photoGroups;
	foreach(const QJsonValue & value, jsonArray) {
		photoGroups.emplace_back(jsonToPhotoGroup(value.toObject()));
	}

	return photoGroups;
}