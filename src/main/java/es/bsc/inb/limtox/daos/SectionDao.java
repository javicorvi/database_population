package es.bsc.inb.limtox.daos;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.Section;


public interface SectionDao extends GenericDao<Section>{

	public Section findByName(String name) throws MoreThanOneEntityException;
	
}
