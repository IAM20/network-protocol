//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_PHOTOTHUMBNAILFORUPLOAD_H
#define UBP_CLIENT_CPP_VER_PHOTOTHUMBNAILFORUPLOAD_H


#include <QWidget>
#include <QPushButton>

#include <src/model/PhotoGroup.h>
#include <src/api/PhotoManager.h>

class PhotoThumbnailForUpload : public QWidget {
	Q_OBJECT
public:
	explicit PhotoThumbnailForUpload(QWidget * parent = nullptr,
	                                 PhotoGroup * group = nullptr,
	                                 PhotoManager * photoManager = nullptr);
	QString getGroupName();

private slots:
	void handleButton();
private:
	const std::pair<int, int> movePointer[4] = {
			std::pair<int, int>(25, 25),
			std::pair<int, int>(250, 25),
			std::pair<int, int>(25, 250),
			std::pair<int, int>(250, 250)
	};
	std::vector<Photo> photos;
	PhotoGroup * photoGroup;
	QPushButton * pushButton;
	long groupId;
	QString groupName;
	PhotoManager * photoManager;
	void updateThumbnail();
};


#endif //UBP_CLIENT_CPP_VER_PHOTOTHUMBNAILFORUPLOAD_H
