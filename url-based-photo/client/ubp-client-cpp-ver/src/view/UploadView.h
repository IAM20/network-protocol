//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_UPLOADVIEW_H
#define UBP_CLIENT_CPP_VER_UPLOADVIEW_H


#include "PhotoThumbnailForUpload.h"

#include <QWidget>

#include <src/api/PhotoManager.h>

class UploadView : public QWidget {
	Q_OBJECT
public:
	explicit UploadView(QWidget * parent = nullptr,
	                    PhotoManager * photoManager = nullptr);
	void updateThumbnails();

private slots:
	void handleHomeButton();
	void handleCreateGroupButton();
	void handleDeleteGroupButton();

private:
	std::vector<PhotoGroup> photoGroups;
	std::vector<PhotoThumbnailForUpload *> photoThumbnails;
	PhotoManager * photoManager;
	QPushButton * homeButton;
	QPushButton * createGroupButton;
	QPushButton * deleteGroupButton;
	void updateContents(PhotoGroup &group);
	void replaceTheContents();
};


#endif //UBP_CLIENT_CPP_VER_UPLOADVIEW_H
