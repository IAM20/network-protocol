//
// Created by Lee on 2019-04-19.
//

#ifndef UBP_CLIENT_CPP_VER_PHOTOTHUMBNAIL_H
#define UBP_CLIENT_CPP_VER_PHOTOTHUMBNAIL_H

#include <vector>

#include <QPushButton>
#include <QStackedWidget>

#include <src/model/PhotoGroup.h>

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
};


#endif //UBP_CLIENT_CPP_VER_PHOTOTHUMBNAIL_H
