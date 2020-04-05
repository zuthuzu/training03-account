package ua.kpi.tef.zu.model.dao;

import ua.kpi.tef.zu.model.PersistanceException;
import ua.kpi.tef.zu.model.Record;
import java.util.List;
import java.util.Optional;

/**
 * Created by Anton Domin on 2020-04-04
 */
public interface RecordDao extends AutoCloseable {
	void create(Record entity) throws PersistanceException;
	Optional<Record> findById(int id);
	List<Record> findAll();
	void update(Record entity);
	void delete(int id);
}
