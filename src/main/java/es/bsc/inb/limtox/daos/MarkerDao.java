package es.bsc.inb.limtox.daos;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.Marker;


public interface MarkerDao extends GenericDao<Marker>{

	public Marker findByIdentifier(String identifier) throws MoreThanOneEntityException;
	
}
