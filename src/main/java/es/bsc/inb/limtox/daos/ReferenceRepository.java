package es.bsc.inb.limtox.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.EntityType;
import es.bsc.inb.limtox.model.Reference;

@Repository
public interface ReferenceRepository extends CrudRepository<Reference, Integer>{

	public Reference findByName(String value) throws MoreThanOneEntityException;
	
}
