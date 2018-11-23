package es.bsc.inb.limtox.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.EntityType;

@Repository
public interface EntityTypeRepository extends CrudRepository<EntityType, Integer>{

	public EntityType findByName(String value) throws MoreThanOneEntityException;
	
}
