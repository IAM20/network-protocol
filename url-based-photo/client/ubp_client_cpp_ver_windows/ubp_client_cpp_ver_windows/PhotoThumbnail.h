//
// Created by Lee on 2019-04-19.
//

#ifndef UBP_CLIENT_CPP_VER_PHOTOTHUMBNAIL_H
#define UBP_CLIENT_CPP_VER_PHOTOTHUMBNAIL_H

#include <vector>

#include <QLabel>
#include <QPushButton>
#include <QStackedWidget>

#include "PhotoGroup.h"

class PhotoThumbnail : public QWidget {
    Q_OBJECT
public:
    explicit PhotoThumbnail(QWidget * parent = nullptr,
                            PhotoGroup* group = nullptr);

private slots:
    void handleButton();

private:
    std::vector<Photo> photos;
    QPushButton * pushButton;
    QLabel * thumbnails[4] = {
            nullptr,
            nullptr,
            nullptr,
            nullptr
    };
};


#endif //UBP_CLIENT_CPP_VER_PHOTOTHUMBNAIL_H
