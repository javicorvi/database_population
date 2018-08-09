package es.bsc.inb.limtox.daos.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.MarkerDao;
import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.Marker;
@Repository(value="markerDaoJPAImpl")
public class MarkerDaoJPAImpl extends GenericDaoJPAImpl<Marker> implements MarkerDao{

	public Marker findByIdentifier(String identifier) throws MoreThanOneEntityException {
		Query query =  em.createQuery("from Marker d where d.marker_identifier=:identifier");
		query.setParameter("identifier", identifier);
		List<Marker> sections = query.getResultList();
		if(sections!=null && sections.size()==1) {
			return sections.get(0);
		}else if(sections!=null && sections.size()>1) {
			throw new MoreThanOneEntityException("More than one entity with identifier " + identifier + " exist in database ");
		}
		return null;
	}
	
}
