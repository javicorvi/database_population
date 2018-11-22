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
public class Section implements LimtoxEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 250)
	private String name;
	
	@Column(name = "internalname", nullable = true, length = 100)
	private String internalName;
	
	@Column(name = "text", nullable = false, length = 10000)
	private String text;
	
	@Column(nullable = false)
	private Integer speciesQuantity;
	
	@Column(nullable = false)
	private Integer diseasesQuantity;
	
	@Column(nullable = false)
	private Integer genesQuantity;
	
	@Column(nullable = false)
	private Integer chemicalCompoundsQuantity;
	
	@ManyToOne(optional=false)
	private Document document;
	
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "section", orphanRemoval = true)
	private List<RelevantSectionTopicInformation> relevantTopicsInformation = new ArrayList<RelevantSectionTopicInformation>();
	
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "section", orphanRemoval = true)
	private List<EntityInstanceFound> entitiesInstanceFound = new  ArrayList<EntityInstanceFound>();
	
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "section", orphanRemoval = true)
	private List<Sentence> sentences = new ArrayList<Sentence>();
	
	
	
	public Section() {
		super();
	}

	public Section(String name, String text) {
		super();
		this.name = name;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	
	public List<RelevantSectionTopicInformation> getRelevantTopicsInformation() {
		return relevantTopicsInformation;
	}

	public void setRelevantTopicsInformation(List<RelevantSectionTopicInformation> relevantTopicsInformation) {
		this.relevantTopicsInformation = relevantTopicsInformation;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public void addRelevantTopicInformation(RelevantSectionTopicInformation relevantSectionTopicInformation) {
		relevantTopicsInformation.add(relevantSectionTopicInformation);
		
	}

	public void addSentence(Sentence sentence) {
		sentences.add(sentence);
		
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
		for (RelevantTopicInformation relevantTopicInformation : relevantTopicsInformation) {
			if(relevantTopicInformation.getTopicName().equals(topicName)) {
				return relevantTopicInformation;
			}
		}
		return null;
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

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	
	
	
}
