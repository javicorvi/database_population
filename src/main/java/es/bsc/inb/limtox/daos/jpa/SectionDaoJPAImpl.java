package es.bsc.inb.limtox.daos.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.SectionDao;
import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.Section;
@Repository(value="sectionDaoJPAImpl")
public class SectionDaoJPAImpl extends GenericDaoJPAImpl<Section> implements SectionDao{

	public Section findByName(String name) throws MoreThanOneEntityException {
		Query query =  em.createQuery("from Section d where d.name=:name");
		query.setParameter("name", name);
		List<Section> sections = query.getResultList();
		if(sections!=null && sections.size()==1) {
			return sections.get(0);
		}else if(sections!=null && sections.size()>1) {
			throw new MoreThanOneEntityException("More than one entity with sourceId " + name + " exist in database ");
		}
		return null;
	}
	
}
