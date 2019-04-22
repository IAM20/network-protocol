package com.github.iam20.photo.msg

class DbSqls {
public static final String CREATE_PHOTO_GROUP_TABLE = '''
CREATE TABLE IF NOT EXISTS photo_group (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	name VARCHAR(255)
)ENGINE INNODB;
'''

public static final String CREATE_PHOTO_TABLE = '''
CREATE TABLE IF NOT EXISTS photo (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	photo_group_id BIGINT,
	name VARCHAR(255),
	mime_type VARCHAR(255),
	url VARCHAR(255),
	comment VARCHAR(255),
	FOREIGN KEY fk_photo_group_id(photo_group_id) REFERENCES photo_group(id)
)ENGINE INNODB;
'''

public static final String INSERT_BASIC_PHOTO_GROUP = '''
INSERT INTO photo_group VALUES ("all");
'''

public static final String GET_CNT_OF_PHOTO_GROUP = '''
SELECT COUNT(*) FROM photo_group;
'''

public static final String GET_PHOTO_BY_GROUP = '''
SELECT
    p.id AS id ,
    p.name AS name ,
    p.photo_group_id AS photoGroupId ,
    p.mime_type AS mimeType ,
    p.url as url ,
    p.comment as comment
FROM photo p
JOIN photo_group pg
ON p.photo_group_id = :groupId;
'''

public static final String GET_PHOTO_GROUP_BY_ID = '''
SELECT * FROM photo_group WHERE id = :groupId
'''

public static final String GET_PHOTO_BY_ID = '''
SELECT * FROM photo WHERE id = :photoId
'''

public static final String GET_PHOTO_GROUP_BY_NAME = '''
SELECT * FROM photo_group WHERE name = :groupName
'''

public static final String INSERT_PHOTO_GROUP = '''
INSERT INTO photo_group (name) VALUES ( :groupName );
'''

public static final String INSERT_PHOTO_FULL_ARGS = '''
INSERT INTO photo (
	name,
	photo_group_id,
	mime_type,
	url,
	comment
) VALUES (
	:name ,
	:photoGroupId ,
	:mimeType ,
	:url ,
	:comment
);
'''

public static final String INSERT_PHOTO_FOUR_ARGS = '''
INSERT INTO photo (
	name,
	photo_group_id,
	mime_type,
	url
) VALUES ( 
	:name , 
	:photoGroupId , 
	:mimeType ,  
	:url 
);
'''

public static final String UPDATE_PHOTO_BY_ID = '''
UPDATE photo
SET 
	name = :name ,
	photo_group_id = :groupId ,
	mime_type = :mimeType ,
	url = :url ,
	comment = :comment
WHERE id = :photoId ;
'''

public static final String UPDATE_PHOTO_GROUP_BY_ID = '''
UPDATE photo_group
SET
	name = :name
WHERE id = :groupId ;
'''

public static final String DELETE_PHOTO_BY_ID = '''
DELETE FROM photo WHERE id = :photoId ;
'''

public static final String DELETE_PHOTO_GROUP_BY_ID = '''
DELETE FROM photo_group WHERE id = :groupId ;
'''
}
