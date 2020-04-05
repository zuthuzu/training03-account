package ua.kpi.tef.zu.model.dao.impl;

import ua.kpi.tef.zu.model.PersistanceException;
import ua.kpi.tef.zu.model.Record;
import ua.kpi.tef.zu.model.dao.RecordDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Anton Domin on 2020-04-04
 */
public class JDBCRecordDao implements RecordDao {
	private final static String TABLE = "records";
	private Connection connection;

	public JDBCRecordDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(Record entity) throws PersistanceException {
		try (PreparedStatement ps = connection.prepareStatement
				("INSERT INTO `" + TABLE + "` (`login`, `first_name`, `second_name`, `patronym`, `comment`, `group`, " +
						"`phone_mobile`, `phone_mobile2`, `phone_landline`, `email`, `skype`, " +
						"`address_zip`, `address_city`, `address_street`, `address_building`, `address_apt`, " +
						"`created_date`, `changed_date`)" +
						" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
			ps.setString(1 , entity.getLogin());
			ps.setString(2 , entity.getFirstName());
			ps.setString(3 , entity.getSecondName());
			ps.setString(4 , entity.getPatronym());
			ps.setString(5 , entity.getComment());
			ps.setString(6 , entity.getGroup());
			ps.setString(7 , entity.getPhoneMobile());
			ps.setString(8 , entity.getPhoneMobile2());
			ps.setString(9 , entity.getPhoneLandline());
			ps.setString(10 , entity.getEmail());
			ps.setString(11 , entity.getSkype());
			ps.setString(12 , entity.getAddressZip());
			ps.setString(13 , entity.getAddressCity());
			ps.setString(14 , entity.getAddressStreet());
			ps.setString(15 , entity.getAddressBuilding());
			ps.setString(16 , entity.getAddressApt());
			ps.setObject(17 , entity.getCreatedDate());
			ps.setObject(18 , entity.getChangedDate());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistanceException(entity, e);
		}
	}

	@Override
	public Optional<Record> findById(int id) {
		return Optional.empty();
	}

	@Override
	public List<Record> findAll() {
		return new ArrayList<>();
	}

	@Override
	public void update(Record entity) {

	}

	@Override
	public void delete(int id) {

	}

	@Override
	public void close() throws Exception {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}
}
