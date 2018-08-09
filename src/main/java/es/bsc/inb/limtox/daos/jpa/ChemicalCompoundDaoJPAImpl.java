package es.bsc.inb.limtox.daos.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.ChemicalCompoundDao;
import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.ChemicalCompound;
@Repository(value="chemicalCompoundDaoJPAImpl")
public class ChemicalCompoundDaoJPAImpl extends GenericDaoJPAImpl<ChemicalCompound> implements ChemicalCompoundDao {

	public ChemicalCompound findByName(String name) throws MoreThanOneEntityException {
		Query query =  em.createQuery("from ChemicalCompound d where d.name=:name");
		query.setParameter("name", name);
		List<ChemicalCompound> chemicalCompounds = query.getResultList();
		if(chemicalCompounds!=null && chemicalCompounds.size()==1) {
			return chemicalCompounds.get(0);
		}else if(chemicalCompounds!=null && chemicalCompounds.size()>1) {
			throw new MoreThanOneEntityException("More than one entity with name " + name + " exist in database ");
		}
		return null;
	}
}
