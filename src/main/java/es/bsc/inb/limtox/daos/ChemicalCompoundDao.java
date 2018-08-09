package es.bsc.inb.limtox.daos;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.ChemicalCompound;


public interface ChemicalCompoundDao extends GenericDao<ChemicalCompound>{

	public ChemicalCompound findByName(String name) throws MoreThanOneEntityException;

	
}
