package es.bsc.inb.limtox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class EntityAssociationSentence {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	private String entityInstanceFoundId;
	
	private String entityInstanceName;
	
	@ManyToOne(optional=true)
	private EntityInstanceFound entityInstanceFoundOrigin;
	
	private String associationEntityInstanceFoundId;
	
	private String associationEntityInstanceFoundName;
	
	@ManyToOne(optional=true)
	private EntityInstanceFound entityInstanceFoundAssociated;
	
	private String keyword;
	
	private String patternName;
	
	@ManyToOne(optional=false)
	private Sentence sentence;
	
	
	
	public EntityAssociationSentence() {
		super();
	}

	public EntityAssociationSentence(String entityInstanceFoundId, String entityInstanceName, String associationEntityInstanceFoundId,  String associationEntityInstanceFoundName, String keyword, String patternName) {
		this.entityInstanceFoundId = entityInstanceFoundId;
		this.entityInstanceName = entityInstanceName;
		this.associationEntityInstanceFoundId = associationEntityInstanceFoundId;
		this.associationEntityInstanceFoundName = associationEntityInstanceFoundName;
		this.keyword = keyword;
		this.patternName = patternName;
	}

	public String getEntityInstanceFoundId() {
		return entityInstanceFoundId;
	}

	public void setEntityInstanceFoundId(String entityInstanceFoundId) {
		this.entityInstanceFoundId = entityInstanceFoundId;
	}

	public String getAssociationEntityInstanceFoundId() {
		return associationEntityInstanceFoundId;
	}

	public void setAssociationEntityInstanceFoundId(String associationEntityInstanceFoundId) {
		this.associationEntityInstanceFoundId = associationEntityInstanceFoundId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}



	public String getEntityInstanceName() {
		return entityInstanceName;
	}



	public void setEntityInstanceName(String entityInstanceName) {
		this.entityInstanceName = entityInstanceName;
	}



	public String getAssociationEntityInstanceFoundName() {
		return associationEntityInstanceFoundName;
	}



	public void setAssociationEntityInstanceFoundName(String associationEntityInstanceFoundName) {
		this.associationEntityInstanceFoundName = associationEntityInstanceFoundName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}

	public EntityInstanceFound getEntityInstanceFoundOrigin() {
		return entityInstanceFoundOrigin;
	}

	public void setEntityInstanceFoundOrigin(EntityInstanceFound entityInstanceFoundOrigin) {
		this.entityInstanceFoundOrigin = entityInstanceFoundOrigin;
	}

	public EntityInstanceFound getEntityInstanceFoundAssociated() {
		return entityInstanceFoundAssociated;
	}

	public void setEntityInstanceFoundAssociated(EntityInstanceFound entityInstanceFoundAssociated) {
		this.entityInstanceFoundAssociated = entityInstanceFoundAssociated;
	}
	
	/**
	 * Set the entity instance found in the association
	 * @param sentence
	 */
	public void setEntityInstancesFound(Sentence sentence) {
		if(this.sentence.getEntitiesInstanceFound()!=null) {
			for (EntityInstanceFound entitiesInstanceFound : this.sentence.getEntitiesInstanceFound()) {
				if(this.getEntityInstanceFoundId().equals(entitiesInstanceFound.getEntityInstanceId())) {
					this.setEntityInstanceFoundOrigin(entitiesInstanceFound);
				}else if(this.getAssociationEntityInstanceFoundId().equals(entitiesInstanceFound.getEntityInstanceId())){
					this.setEntityInstanceFoundAssociated(entitiesInstanceFound);
				}
			}
		}
	}
	

	
	
}
