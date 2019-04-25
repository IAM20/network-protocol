//
// Created by Lee on 2019-04-23.
//

#include "ImageDownloader.h"

#include <QUrl>
#include <QNetworkRequest>
#include <QNetworkReply>
#include <QNetworkAccessManager>
#include <QEventLoop>
#include <QCoreApplication>

QPixmap ImageDownloader::getImageFromUri(QString uri) {
    QPixmap result;
    QUrl url(uri);
    QNetworkAccessManager networkManager;
    QNetworkRequest request(url);
    QNetworkReply * reply = networkManager.get(request);

    QTime timeout= QTime::currentTime().addSecs(20);
    while( QTime::currentTime() < timeout && !reply->isFinished()){
        QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
    }

    if (reply->error() != QNetworkReply::NoError) {
        qDebug() << "Failure" <<reply->errorString();
    }

    result.loadFromData(reply->readAll());
    return result;
}
