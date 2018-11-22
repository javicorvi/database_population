package es.bsc.inb.limtox.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.EntityInstance;

@Repository
public interface EntityInstanceRepository extends CrudRepository<EntityInstance, Integer>{

	public EntityInstance findByValue(String value) throws MoreThanOneEntityException;
	
}
