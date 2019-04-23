//
// Created by Lee on 2019-04-23.
//

#ifndef UBP_CLIENT_CPP_VER_VIEWERVIEW_H
#define UBP_CLIENT_CPP_VER_VIEWERVIEW_H


#include "PhotoViewer.h"

#include <QWidget>
#include <QPushButton>

#include <src/model/Photo.h>
#include <src/api/PhotoManager.h>

class ViewerView : public QWidget {
	Q_OBJECT
public:
	explicit ViewerView(QWidget * parent = nullptr,
	                    std::vector<Photo> *photoList = nullptr,
	                    PhotoManager * photoManager = nullptr);
	void updatePhotoList(std::vector<Photo> *photoList);

private slots:
	void handlePrevButton();
	void handleNextButton();
	void handleHomeButton();
	void handleModifyButton();
	void handleDeleteButton();

private:
	int now = 0;
	Photo nowPhoto;
	std::vector<Photo> * photoList;
	QPushButton * prevButton;
	QPushButton * nextButton;
	QPushButton * homeButton;
	QPushButton * modifyButton;
	QPushButton * deleteButton;
	PhotoViewer * photoViewer;
	PhotoManager * photoManager;
};


#endif //UBP_CLIENT_CPP_VER_VIEWERVIEW_H
