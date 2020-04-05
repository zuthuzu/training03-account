package ua.kpi.tef.zu.model.dao.impl;

import ua.kpi.tef.zu.model.dao.DaoFactory;
import ua.kpi.tef.zu.model.dao.RecordDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Anton Domin on 2020-04-04
 */
public class JDBCDaoFactory extends DaoFactory {
	private final static String PROPERTIES = "db.properties";
	private final static String DB_URL = "db.url";
	private final static String DB_USER = "db.user";
	private final static String DB_PWD = "db.password";

	private String url;
	private String user;
	private String password;

	@Override
	public RecordDao createRecordDao() {
		return new JDBCRecordDao(getConnection());
	}

	private void getProperties() throws IOException {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES)) {
			Properties prop = new Properties();
			prop.load(input);
			url = prop.getProperty(DB_URL);
			user = prop.getProperty(DB_USER);
			password = prop.getProperty(DB_PWD);
		}
	}

	private Connection getConnection() {
		try {
			getProperties();
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
