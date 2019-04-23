//
// Created by Lee on 2019-04-19.
//

#ifndef UBP_CLIENT_CPP_VER_ROOT_UNIT_H
#define UBP_CLIENT_CPP_VER_ROOT_UNIT_H

#include <QMainWindow>
#include <QPushButton>
#include <QStackedWidget>

#include <src/view/ViewerView.h>
#include <src/view/ThumbnailViewer.h>
#include <src/view/UploadView.h>

class RootUnit : public QMainWindow {
	Q_OBJECT
public:
	static const int VIEW_IMAGE = 2;
	explicit RootUnit(QWidget * parent = 0);
	QStackedWidget * getStackedWidget();
	ViewerView * getViewerView();

private slots:
	void handleViewButton();
	void handleUploadButton();

private:
	QPushButton *viewButton;
	QPushButton *uploadButton;
	ViewerView *viewerView;
	UploadView *uploadView;
	ThumbnailViewer *thumbnailViewer;
	QStackedWidget *stackedWidget;
};


#endif //UBP_CLIENT_CPP_VER_ROOT_UNIT_H
