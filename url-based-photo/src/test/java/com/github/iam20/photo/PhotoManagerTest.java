package com.github.iam20.photo;


import com.github.iam20.photo.core.PhotoManager;
import org.junit.Before;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

public class PhotoManagerTest {
	private DataSource dataSource;
	private PhotoManager photoManager;

	@Before
	public void before() {
		DataSource dataSource = DataSourceBuilder.create()
				.url("jdbc:h2:~/url-based-photo/test")
				.driverClassName("org.h2.Driver")
				.build();
		photoManager = new PhotoManager(dataSource);

	}
}
