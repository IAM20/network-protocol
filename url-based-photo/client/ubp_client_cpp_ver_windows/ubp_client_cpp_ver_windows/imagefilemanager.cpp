//
// Created by Lee on 2019-04-23.
//

#include "ImageFileManager.h"

#include <QFileInfo>
#include <QFileDialog>
#include <QDir>
#include <QDebug>
#include <QProcess>

Photo ImageFileManager::uploadPhotoFileToGithub(QWidget * parent) {
    QString fileName = QFileDialog::getOpenFileName(parent, ("Open File"),
                                                    QDir::homePath(),
                                                    ("All files (*.*)"));
    qDebug() << fileName;
    if (fileName == "") {
        Photo tmp;
        tmp.setId(-1);
        return tmp;
    }
    QFileInfo fileInfo(fileName);

    QString mimeType = fileInfo.suffix();
    QString name = fileInfo.fileName();

    QString node = "node";
    QStringList arguments;
    arguments << "../github-uploader-binary/lib/cli.js" << fileName;
    auto *process = new QProcess(parent);
    process->start(node, arguments);
    process->waitForFinished();
    QByteArray url = process->readAll();

    QString uri(url);
    Photo photo(0, uri, mimeType, name);
    return photo;
}
