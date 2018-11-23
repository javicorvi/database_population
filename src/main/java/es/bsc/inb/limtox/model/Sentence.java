package es.bsc.inb.limtox.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Sentence implements LimtoxEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "sentenceId", nullable = false, length = 50)
	private String sentenceId;
	
	@Column(name = "n_order", nullable = false)
	private Integer order;
	
	@Column(name = "text", nullable = false, length = 2000)
	private String text;
	
	private Integer speciesQuantity;
	
	private Integer diseasesQuantity;
	
	private Integer genesQuantity;
	
	private Integer chemicalCompoundsQuantity;
	
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "sentence", orphanRemoval = true)
	private List<RelevantSentenceTopicInformation> relevantTopicsInformation = new ArrayList<RelevantSentenceTopicInformation>(); 
	
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "sentence", orphanRemoval = true)
	private List<EntityInstanceFound> entitiesInstanceFound = new  ArrayList<EntityInstanceFound>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sentence", orphanRemoval = true)
	private List<EntityAssociationSentence> entitiesAssociationsInstanceFound = new  ArrayList<EntityAssociationSentence>();
	
	@ManyToOne(optional=false)
	private Section section;
	
	public Sentence() {
		super();
	}

	public Sentence(String sentenceId, Integer order, String text) {
		this.sentenceId = sentenceId;
		this.order = order;
		this.text = text;
	}

	
	public List<EntityInstanceFound> findEntitiesInstanceFoundByType(String type) {
		List<EntityInstanceFound> entitiesInstanceFound = new ArrayList<EntityInstanceFound>();
		for (EntityInstanceFound entityInstanceFound : this.entitiesInstanceFound) {
			if(entityInstanceFound.getEntityInstance().getEntityTypeName().equals(type)){
				entitiesInstanceFound.add(entityInstanceFound);
			}
		}
		return entitiesInstanceFound;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getSentenceId() {
		return sentenceId;
	}

	public void setSentenceId(String sentenceId) {
		this.sentenceId = sentenceId;
	}

	
	public List<RelevantSentenceTopicInformation> getRelevantTopicsInformation() {
		return relevantTopicsInformation;
	}

	public void setRelevantTopicsInformation(List<RelevantSentenceTopicInformation> relevantTopicsInformation) {
		this.relevantTopicsInformation = relevantTopicsInformation;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public void addRelevantTopicInformation(RelevantSentenceTopicInformation relevantSentenceTopicInformation) {
		this.relevantTopicsInformation.add(relevantSentenceTopicInformation);
	}

	public List<EntityInstanceFound> getEntitiesInstanceFound() {
		return entitiesInstanceFound;
	}

	public void setEntitiesInstanceFound(List<EntityInstanceFound> entitiesInstanceFound) {
		this.entitiesInstanceFound = entitiesInstanceFound;
	}

	public void addEntityInstanceFound(EntityInstanceFound entityInstanceFound) {
		entitiesInstanceFound.add(entityInstanceFound);
		
	}

	public RelevantTopicInformation getRelevantTopicsInformationByName(String topicName) {
		for (RelevantSentenceTopicInformation relevantTopicInformation : relevantTopicsInformation) {
			if(relevantTopicInformation.getTopicName().equals(topicName)) {
				return relevantTopicInformation;
			}
		}
		return null;
	}

	public List<EntityAssociationSentence> getEntitiesAssociationsInstanceFound() {
		return entitiesAssociationsInstanceFound;
	}

	public void setEntitiesAssociationsInstanceFound(List<EntityAssociationSentence> entitiesAssociationsInstanceFound) {
		this.entitiesAssociationsInstanceFound = entitiesAssociationsInstanceFound;
	}

	public void addEntityAssociationInstanceFound(EntityAssociationSentence entityAssociationSentence) {
		entitiesAssociationsInstanceFound.add(entityAssociationSentence);
		
	}

	public Integer getSpeciesQuantity() {
		return speciesQuantity;
	}

	public void setSpeciesQuantity(Integer speciesQuantity) {
		this.speciesQuantity = speciesQuantity;
	}

	public Integer getDiseasesQuantity() {
		return diseasesQuantity;
	}

	public void setDiseasesQuantity(Integer diseasesQuantity) {
		this.diseasesQuantity = diseasesQuantity;
	}

	public Integer getGenesQuantity() {
		return genesQuantity;
	}

	public void setGenesQuantity(Integer genesQuantity) {
		this.genesQuantity = genesQuantity;
	}

	public Integer getChemicalCompoundsQuantity() {
		return chemicalCompoundsQuantity;
	}

	public void setChemicalCompoundsQuantity(Integer chemicalCompoundsQuantity) {
		this.chemicalCompoundsQuantity = chemicalCompoundsQuantity;
	}

	

	
	
	
	
	
	
	
}
