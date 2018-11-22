package es.bsc.inb.limtox.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.DocumentSource;

@Repository
public interface DocumentSourceRepository extends CrudRepository<DocumentSource, Integer>{

	public DocumentSource findByName(String name) throws MoreThanOneEntityException;
	
}
