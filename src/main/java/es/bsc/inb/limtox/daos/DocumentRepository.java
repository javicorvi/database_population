package es.bsc.inb.limtox.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.Document;
import es.bsc.inb.limtox.model.DocumentSource;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer>{

	//public DocumentSource findByName(String name) throws MoreThanOneEntityException;
	
}
