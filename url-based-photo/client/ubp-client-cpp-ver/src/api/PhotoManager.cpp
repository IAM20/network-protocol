//
// Created by Lee on 2019-04-19.
//

#include "PhotoManager.h"

#include <QString>

#include <src/util/JsonParser.h>

PhotoManager::PhotoManager(QString serverAddr) {
	this->serverAddr = serverAddr;
}

std::vector<PhotoGroup> PhotoManager::getAllPhotoGroup() {
	QString uri = "http://" + serverAddr + "/group";
	QNetworkAccessManager networkManager;
	QNetworkRequest request = QNetworkRequest(QUrl(uri));
	QScopedPointer<QNetworkReply> reply(networkManager.get(request));

	QTime timeout = QTime::currentTime().addSecs(10);
	while( QTime::currentTime() < timeout && !reply->isFinished()){
		QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
	}

	if (reply->error() != QNetworkReply::NoError) {
		qDebug() << "Failure" <<reply->errorString();
	}

	QByteArray responseData = reply->readAll();
	QJsonArray jsonArray = QJsonDocument::fromJson(responseData).array();

	reply->deleteLater();
	return JsonParser::jsonToPhotoGroupVector(jsonArray);
}

Photo PhotoManager::getPhotoInfo(Photo &photo) {
	return getPhotoInfo(photo.getId());
}

Photo PhotoManager::getPhotoInfo(long id) {
	QNetworkAccessManager networkManager;
	QNetworkRequest request = QNetworkRequest(QUrl(uriMaker(TYPE_GET_PHOTO, QString::number(id))));
	qDebug() << request.url().toString();
	request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");
	QScopedPointer<QNetworkReply> reply(networkManager.get(request));

	QTime timeout= QTime::currentTime().addSecs(10);
	while( QTime::currentTime() < timeout && !reply->isFinished()){
		QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
	}

	if (reply->error() != QNetworkReply::NoError) {
		qDebug() << "Failure" <<reply->errorString();
	}

	QByteArray responseData = reply->readAll();
	QJsonObject jsonObject = QJsonDocument::fromJson(responseData).object();

	reply->deleteLater();
	return JsonParser::jsonToPhoto(jsonObject);
}

PhotoGroup PhotoManager::getPhotoGroupInfo(PhotoGroup &group) {
	return getPhotoGroupInfo(group.getName());
}

PhotoGroup PhotoManager::getPhotoGroupInfo(QString groupName) {
	QNetworkAccessManager networkManager;
	QNetworkRequest request = QNetworkRequest(QUrl(uriMaker(TYPE_GET_PHOTO_GROUP, groupName)));
	qDebug() << request.url().toString();
	request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");
	QScopedPointer<QNetworkReply> reply(networkManager.get(request));

	QTime timeout= QTime::currentTime().addSecs(10);
	while( QTime::currentTime() < timeout && !reply->isFinished()){
		QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
	}

	if (reply->error() != QNetworkReply::NoError) {
		qDebug() << "Failure" <<reply->errorString();
	}

	QByteArray responseData = reply->readAll();
	QJsonObject jsonObject = QJsonDocument::fromJson(responseData).object();
	reply->deleteLater();
	return JsonParser::jsonToPhotoGroup(jsonObject);
}

PhotoGroup PhotoManager::makeGroup(PhotoGroup &group) {
	return makeGroup(group.getName());
}

PhotoGroup PhotoManager::makeGroup(QString groupName) {
	QNetworkAccessManager networkManager;
	QNetworkRequest request = QNetworkRequest(QUrl(uriMaker(TYPE_POST_PHOTO_GROUP, groupName)));
	qDebug() << request.url().toString();
	request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");
	QJsonObject json;
	QScopedPointer<QNetworkReply> reply(networkManager.post(request, QJsonDocument(json).toJson()));

	QTime timeout= QTime::currentTime().addSecs(10);
	while( QTime::currentTime() < timeout && !reply->isFinished()){
		QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
	}

	if (reply->error() != QNetworkReply::NoError) {
		qDebug() << "Failure" <<reply->errorString();
	}

	QByteArray responseData = reply->readAll();
	QJsonObject jsonObject = QJsonDocument::fromJson(responseData).object();
	reply->deleteLater();
	return JsonParser::jsonToPhotoGroup(jsonObject);
}

Photo PhotoManager::updatePhoto(Photo &photo, PhotoGroup &group) {
	return updatePhoto(photo, group.getName());
}

Photo PhotoManager::updatePhoto(Photo &photo) {
	return updatePhoto(photo, "all");
}

Photo PhotoManager::updatePhoto(Photo &photo, QString groupName) {
	return (photo.getId() == 0) ?
			executePutPhoto(uriMaker(TYPE_POST_PHOTO, groupName), photo, TYPE_POST) :
			executePutPhoto(uriMaker(TYPE_PUT_PHOTO, QString::number(photo.getId())), photo, TYPE_PUT);
}

Photo PhotoManager::executePutPhoto(QString uri, Photo photo, int type) {
	QNetworkAccessManager networkManager;
	QNetworkRequest request = QNetworkRequest(QUrl(uri));
	request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");
	QJsonObject json;
	double tmp;
	QString tmpString;
	if ((tmp = photo.getPhotoGroupId()) != 0) {
		json.insert("photoGroupId", tmp);
	}
	if ((tmpString = photo.getComment()) != nullptr) {
		json.insert("comment", tmpString);
	}
	if ((tmpString = photo.getName()) != nullptr) {
		json.insert("comment", tmpString);
	}
	json.insert("mimeType", photo.getMimeType());
	json.insert("url", photo.getUrl());

	QNetworkReply * reply = (TYPE_POST == type) ?
			networkManager.post(request, QJsonDocument(json).toJson()) :
			networkManager.put(request, QJsonDocument(json).toJson());

	QTime timeout= QTime::currentTime().addSecs(10);
	while( QTime::currentTime() < timeout && !reply->isFinished()){
		QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
	}

	if (reply->error() != QNetworkReply::NoError) {
		qDebug() << "Failure" <<reply->errorString();
	}

	QByteArray responseData = reply->readAll();
	QJsonObject jsonObject = QJsonDocument::fromJson(responseData).object();
	reply->deleteLater();

	return JsonParser::jsonToPhoto(jsonObject);
}

PhotoGroup PhotoManager::updateGroup(PhotoGroup &group, QString newName) {
	return updateGroup(group.getName(), newName);
}

PhotoGroup PhotoManager::updateGroup(QString oldGroupName, QString newName) {
	QNetworkAccessManager networkManager;
	QNetworkRequest request = QNetworkRequest(QUrl(uriMaker(TYPE_PUT_PHOTO_GROUP, oldGroupName)));
	qDebug() << request.url().toString();
	request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");

	QJsonObject json;
	json.insert("name", newName);

	QScopedPointer<QNetworkReply> reply(networkManager.put(request, QJsonDocument(json).toJson()));

	QTime timeout= QTime::currentTime().addSecs(10);
	while( QTime::currentTime() < timeout && !reply->isFinished()){
		QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
	}

	if (reply->error() != QNetworkReply::NoError) {
		qDebug() << "Failure" <<reply->errorString();
	}

	QByteArray responseData = reply->readAll();
	QJsonObject jsonObject = QJsonDocument::fromJson(responseData).object();
	reply->deleteLater();
	return JsonParser::jsonToPhotoGroup(jsonObject);
}

void PhotoManager::deletePhoto(Photo &photo) {
	QNetworkAccessManager networkManager;
	QNetworkRequest request = QNetworkRequest(QUrl(uriMaker(TYPE_DELETE_PHOTO, QString::number(photo.getId()))));
	qDebug() << request.url().toString();
	QScopedPointer<QNetworkReply> reply(networkManager.deleteResource(request));

	QTime timeout= QTime::currentTime().addSecs(10);
	while( QTime::currentTime() < timeout && !reply->isFinished()){
		QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
	}

	if (reply->error() != QNetworkReply::NoError) {
		qDebug() << "Failure" <<reply->errorString();
	}

	QByteArray responseData = reply->readAll();
	QJsonObject jsonObject = QJsonDocument::fromJson(responseData).object();
	reply->deleteLater();
}

void PhotoManager::deletePhotoGroup(QString groupName) {
	QNetworkAccessManager networkManager;
	QNetworkRequest request = QNetworkRequest(QUrl(uriMaker(TYPE_DELETE_PHOTO_GROUP, groupName)));
	qDebug() << request.url().toString();
	QScopedPointer<QNetworkReply> reply(networkManager.deleteResource(request));

	QTime timeout= QTime::currentTime().addSecs(10);
	while( QTime::currentTime() < timeout && !reply->isFinished()){
		QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
	}

	if (reply->error() != QNetworkReply::NoError) {
		qDebug() << "Failure" <<reply->errorString();
	}

	QByteArray responseData = reply->readAll();
	QJsonObject jsonObject = QJsonDocument::fromJson(responseData).object();
	reply->deleteLater();
}

void PhotoManager::deletePhotoGroup(PhotoGroup &group) {
	deletePhotoGroup(group.getName());
}

QString PhotoManager::uriMaker(RequestType type, QString str) {
	switch(type) {
		case TYPE_GET_PHOTO:
		case TYPE_PUT_PHOTO:
		case TYPE_DELETE_PHOTO:
			return ("http://" + serverAddr + "/group/all/photo/") + str;
		case TYPE_POST_PHOTO:
			return "http://" + serverAddr + "/group/" + str + "/photo";
		case TYPE_GET_PHOTO_GROUP:
		case TYPE_POST_PHOTO_GROUP:
		case TYPE_PUT_PHOTO_GROUP:
		case TYPE_DELETE_PHOTO_GROUP:
			return "http://" + serverAddr + "/group/" + str;
	}
}