package com.github.iam20.proxy.config;

import com.github.iam20.proxy.model.CoreInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ApplicationConfig {
	private static CoreInformation coreInformation = new CoreInformation();
	private static NamedParameterJdbcTemplate jdbcTemplate;
	private static String restServerIp;
	private static String restServerPort;

	public static void init() {
		try {
			InputStream inputStream = ApplicationConfig.class
					.getClassLoader()
					.getResourceAsStream("application.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			restServerIp = (String)properties.get("restServerIp");
			restServerPort = (String)properties.get("restServerPort");
			DataSource dataSource = DataSourceBuilder.create()
					.driverClassName((String)properties.get("datasource.driverClassName"))
					.url((String)properties.get("datasource.jdbcUrl"))
					.username((String)properties.get("datasource.username"))
					.password((String)properties.get("datasource.password"))
					.build();
			jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			log.debug("REST Server IP : {}", restServerIp);
			log.debug("REST Server port : {}", restServerPort);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static String getHttpRestServerURI() {
		return "http://" + restServerIp + ":" + restServerPort + "/info";
	}

	public static void setCoreInformation(CoreInformation coreInformation) {
		ApplicationConfig.coreInformation = coreInformation;
	}

	public static NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public static CoreInformation getCoreInformation() {
		return coreInformation;
	}
}
