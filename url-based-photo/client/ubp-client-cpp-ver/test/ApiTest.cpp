//
// Created by Lee on 2019-04-19.
//

#include <src/api/PhotoManager.h>
#include "gtest/gtest.h"

const static QString testAddr = "106.10.54.51:8080";

TEST(ubp_client_cpp_ver, api_photo_get_test) {
	int argc = 0;
	char * argv = 0;
	QCoreApplication testApp(argc, &argv);
	PhotoManager manager(testAddr);
	Photo photo = manager.getPhotoInfo(1);

	qDebug() << "id : " << photo.getId();
	qDebug() << "group id : " << photo.getPhotoGroupId();
	qDebug() << "url : " << photo.getUrl();
	qDebug() << "mime type : " << photo.getMimeType();
	qDebug() << "name : " << photo.getName();
	qDebug() << "comment : " << photo.getComment();
}

TEST(ubp_client_cpp_ver, api_group_get_test) {
	int argc = 0;
	char * argv = 0;
	QCoreApplication testApp(argc, &argv);
	PhotoManager manager(testAddr);
	PhotoGroup group = manager.getPhotoGroupInfo("all");

	qDebug() << "id : " << group.getId();
	qDebug() << "name : " << group.getName();
	std::vector<Photo> photoList = group.getPhotoList();

	int i = 1;
	for (Photo p : photoList) {
		qDebug() << "photo " << i++;
		qDebug() << "   id : " << p.getId();
		qDebug() << "   group id : " << p.getPhotoGroupId();
		qDebug() << "   url : " << p.getUrl();
		qDebug() << "   mime type : " << p.getMimeType();
		qDebug() << "   name : " << p.getName();
		qDebug() << "   comment : " << p.getComment();
	}
}

TEST(ubp_client_cpp_ver, api_all_read_test) {
	int argc = 0;
	char * argv = 0;
	QCoreApplication testApp(argc, &argv);
	PhotoManager manager(testAddr);
	std::vector<PhotoGroup> groupVector = manager.getAllPhotoGroup();

	for(PhotoGroup group : groupVector) {
		qDebug() << "id : " << group.getId();
		qDebug() << "name : " << group.getName();
		std::vector<Photo> photoList = group.getPhotoList();

		int i = 1;
		for (Photo p : photoList) {
			qDebug() << "photo " << i++;
			qDebug() << "   id : " << p.getId();
			qDebug() << "   group id : " << p.getPhotoGroupId();
			qDebug() << "   url : " << p.getUrl();
			qDebug() << "   mime type : " << p.getMimeType();
			qDebug() << "   name : " << p.getName();
			qDebug() << "   comment : " << p.getComment();
		}
	}
}

/**
 * CRUD test sequence
 * Make group 'test-for-client-940329'
 * upload photo in this group
 * read group
 * read photo
 * delete photo
 * delete group
 */
TEST(ubp_client_cpp_ver, api_all_crud_test) {
	int argc = 0;
	char * argv = 0;
	QString testGroupName = "test-for-client-940329";
	QCoreApplication testApp(argc, &argv);
	PhotoManager manager(testAddr);
	qDebug() << "####################################";
	qDebug() << "MAKE GROUP TEST";
	PhotoGroup group = manager.makeGroup("test-for-client-940329");

	qDebug() << "id : " << group.getId();
	qDebug() << "name : " << group.getName();

	qDebug() << "####################################";
	qDebug() << "UPLOAD PHOTO TEST";
	Photo photo;
	photo.setUrl("https://user-images.githubusercontent.com/35682872/56488786-5fa95a00-651a-11e9-8f1f-d14df7b4a96a.jpeg");
	photo.setMimeType("jpeg");
	photo = manager.updatePhoto(photo, group.getName());

	qDebug() << "id : " << photo.getId();
	qDebug() << "group id : " << photo.getPhotoGroupId();
	qDebug() << "name : " << photo.getName();
	qDebug() << "mime type" << photo.getMimeType();
	qDebug() << "comment" << photo.getComment();
	qDebug() << "url" << photo.getUrl();

	qDebug() << "####################################";
	qDebug() << "READ GROUP TEST";
	group = manager.getPhotoGroupInfo(group.getName());

	qDebug() << "id : " << group.getId();
	qDebug() << "name : " << group.getName();
	std::vector<Photo> photoList = group.getPhotoList();

	int i = 1;
	for (Photo p : photoList) {
		qDebug() << "photo " << i++;
		qDebug() << "   id : " << p.getId();
		qDebug() << "   group id : " << p.getPhotoGroupId();
		qDebug() << "   url : " << p.getUrl();
		qDebug() << "   mime type : " << p.getMimeType();
		qDebug() << "   name : " << p.getName();
		qDebug() << "   comment : " << p.getComment();
	}

	qDebug() << "####################################";
	qDebug() << "READ JUST ONE PHOTO TEST";
	photo = photoList[0];
	photo = manager.getPhotoInfo(photo);

	qDebug() << "id : " << photo.getId();
	qDebug() << "group id : " << photo.getPhotoGroupId();
	qDebug() << "name : " << photo.getName();
	qDebug() << "mime type" << photo.getMimeType();
	qDebug() << "comment" << photo.getComment();
	qDebug() << "url" << photo.getUrl();

	qDebug() << "####################################";
	qDebug() << "DELETE PHOTO TEST";
	manager.deletePhoto(photo);
	qDebug() << "DELETED SUCCESSFULLY";

	qDebug() << "####################################";
	qDebug() << "DELETE PHOTO GROUP TEST";
	manager.deletePhotoGroup(group);
	qDebug() << "DELETED SUCCESSFULLY";
}