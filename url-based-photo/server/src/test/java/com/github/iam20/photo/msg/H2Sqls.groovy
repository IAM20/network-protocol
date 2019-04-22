package com.github.iam20.photo.msg

class H2Sqls {
	public static final String PHOTO_TABLE_INIT = '''
	CREATE TABLE IF NOT EXISTS photo (
		id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
		photo_group_id BIGINT,
		name VARCHAR(255),
		mime_type VARCHAR(255),
		url VARCHAR(255),
		comment VARCHAR(255),
		FOREIGN KEY (photo_group_id) REFERENCES photo_group(id)
	);
	'''

	public static final String PHOTO_GROUP_TABLE_INIT = '''
	CREATE TABLE IF NOT EXISTS photo_group (
		id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
		name VARCHAR(255)
	);
	'''

}
