package com.github.iam20.photo;

import static org.junit.Assert.*;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import com.github.iam20.photo.core.PhotoManager;
import com.github.iam20.photo.model.Photo;
import com.github.iam20.photo.model.PhotoGroup;
import com.github.iam20.photo.msg.H2Sqls;

@Slf4j
public class PhotoManagerTest {
	private final String TEST_H2_DB_PATH = "~/url-based-photo/test";

	private PhotoManager photoManager;
	private Date date = new Date();
	private final String GROUP_TEST_NAME = date.toString() + "TEST";
	private final String MODIFY_TEST_NAME = date.toString() + "PUT_TEST";

	@Before
	public void setUpH2Db() {
		DataSource dataSource = DataSourceBuilder.create()
				.url("jdbc:h2:" + TEST_H2_DB_PATH + "?database_to_upper=false")
				.driverClassName("org.h2.Driver")
				.build();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute(H2Sqls.PHOTO_GROUP_TABLE_INIT);
		jdbcTemplate.execute(H2Sqls.PHOTO_TABLE_INIT);

		photoManager = new PhotoManager(dataSource);
	}

	@Test
	public void photoGroupCRUDTest() {
		log.info("Photo group insert test");
		PhotoGroup group = PhotoGroup.builder()
				.name(GROUP_TEST_NAME)
				.build();
		photoManager.insertPhotoGroup(group);
		group = photoManager.getPhotoByGroupName(GROUP_TEST_NAME);

		assertNotNull(group);
		assertEquals(GROUP_TEST_NAME, group.getName());
		log.info("PASS : Photo group insert test");

		group.setName(MODIFY_TEST_NAME);
		photoManager.updatePhotoGroup(group);
		group = photoManager.getPhotoByGroupName(MODIFY_TEST_NAME);
		log.info(group.getName());

		assertNotNull(group);
		assertEquals(MODIFY_TEST_NAME, group.getName());
		log.info("PASS : Photo group modify test");

		log.info("Photo group delete test");
		int rowNumber = photoManager.deletePhotoGroup(group);
		assertEquals(1, rowNumber);
		log.info("PASS : Photo group delete test");
	}

	@Test
	public void photoCRUDTest() {
		log.info("Photo group insert for photo CRUD test");
		PhotoGroup group = PhotoGroup.builder()
				.name(GROUP_TEST_NAME)
				.build();
		photoManager.insertPhotoGroup(group);
		group = photoManager.getPhotoByGroupName(GROUP_TEST_NAME);
		assertNotNull(group);

		log.info("Photo insert test");
		Photo photo = Photo.builder()
				.name(GROUP_TEST_NAME)
				.comment("HELLO COMMENT")
				.mimeType("jpg")
				.url("http://www.naver.com")
				.build();
		photoManager.insertPhoto(group, photo);
		List<Photo> photos = photoManager.getPhotoByGroupId(group.getId());

		long photoId = -1;
		for (Photo p : photos) {
			if (GROUP_TEST_NAME.equals(p.getName())) {
				photoId = p.getId();
				photo = p;
				break;
			}
		}
		assertNotEquals(-1, photoId);
		log.info("PASS : Photo insert test");

		log.info("Photo modify test");
		photo.setName(MODIFY_TEST_NAME);
		photoManager.updatePhoto(photo);
		photo = photoManager.getPhotoById(photoId);
		assertNotNull(photo);
		assertEquals(MODIFY_TEST_NAME, photo.getName());
		log.info("PASS : Photo modify test");

		log.info("Photo delete test");
		int rowNum = photoManager.deletePhoto(photo);
		assertEquals(1, rowNum);
		log.info("PASS : Photo delete test");
	}

	@After
	public void deleteH2Db() {
		File file = new File(TEST_H2_DB_PATH);
		fileDelete(file);
	}

	private void fileDelete(@NotNull File file) {
		if (!file.delete()) {
			log.error("Failed to remove" + file.getName());
		}
	}
}
