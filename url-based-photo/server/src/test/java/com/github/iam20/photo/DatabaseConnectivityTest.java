package com.github.iam20.photo;

import static org.junit.Assert.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseConnectivityTest {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void before() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void connectValidTest() {
		List list = jdbcTemplate.queryForList("SELECT 1");
		log.info("SELECT 1 executed into database");
		assertEquals(1, list.size());
		log.info("TEST PASSED");
	}

	@Test
	public void tableValidTest() {
		/*If bad sql grammar exception is thrown , the table does not exist.*/
		jdbcTemplate.queryForList("SELECT * FROM photo");
		jdbcTemplate.queryForList("SELECT * FROM photo_group");
		log.info("'photo' and 'photo_group' table does exists");
		log.info("TEST PASSED");
	}
}
