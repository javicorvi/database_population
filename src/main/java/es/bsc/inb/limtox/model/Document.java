package es.bsc.inb.limtox.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document implements LimtoxEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String sourceId;
	
	private String fulltext;
	
	private Double score;
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "document", orphanRemoval = true)
	private List<Sentence> sentences = new ArrayList<Sentence>();
	
	//private List<MeshChemicalCompound> meshChemicalCompounds = new ArrayList<MeshChemicalCompound>();
	
	public Document() {
		super();
	}
	
	public Document(String sourceId, String fulltext, Double score) {
		this.sourceId=sourceId;
		this.fulltext=fulltext;
		this.score=score;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
//	public List<MeshChemicalCompound> getMeshChemicalCompounds() {
//		return meshChemicalCompounds;
//	}
//	public void setMeshChemicalCompounds(List<MeshChemicalCompound> meshChemicalCompounds) {
//		this.meshChemicalCompounds = meshChemicalCompounds;
//	}
	
	public String getFulltext() {
		return fulltext;
	}
	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	
	
	
	
	
}
