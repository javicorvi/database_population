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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="sentence")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sentence implements LimtoxEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String sentenceId="1";
	
	private String text;
	
	private Double score;
	
	@Column(name="n_order")
	private Integer order;
	
	@ManyToOne
	private Document document;
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "sentence", orphanRemoval = true)
	private List<ChemicalCompoundSentence> chemicalCompoundSentences = new ArrayList<ChemicalCompoundSentence>();
			
			
//	@OneToMany(cascade = CascadeType.ALL, 
//	mappedBy = "sentence", orphanRemoval = true)
	@Transient
	private List<HepatotoxicityTermSentence> hepatotoxicityTermSentences = new ArrayList<HepatotoxicityTermSentence>();
			
			
//	@OneToMany(cascade = CascadeType.ALL, 
//	mappedBy = "sentence", orphanRemoval = true)
//	private List<CytochromeSentence> cytochromeSentences = new ArrayList<CytochromeSentence>();
//			
//			
//	@OneToMany(cascade = CascadeType.ALL, 
//	mappedBy = "sentence", orphanRemoval = true)
//	private List<MarkerSentence> markerSentences = new ArrayList<MarkerSentence>();
//		
//	@OneToMany(cascade = CascadeType.ALL, 
//	mappedBy = "sentence", orphanRemoval = true)
//	private List<ChemicalCompoundCytochromeSentence> chemicalCompoundCytochromeSentences = new ArrayList<ChemicalCompoundCytochromeSentence>();
//
//	@OneToMany(cascade = CascadeType.ALL, 
//	mappedBy = "sentence", orphanRemoval = true)
//	private List<HepatotoxicityTermChemicalCompoundSentence> hepatotoxicityTermChemicalCompoundSentences = new ArrayList<HepatotoxicityTermChemicalCompoundSentence>();
//			
//	@OneToMany(cascade = CascadeType.ALL, 
//	mappedBy = "sentence", orphanRemoval = true)
//	private List<MarkerChemicalCompoundSentence> markerChemicalCompoundSentences = new ArrayList<MarkerChemicalCompoundSentence>();
//
//	@OneToMany(cascade = CascadeType.ALL, 
//	mappedBy = "sentence", orphanRemoval = true)
//	private List<TaxonomySentence> taxonomySentences = new ArrayList<TaxonomySentence>();
	
	
	public Sentence() {}
	
	public Sentence(Document document, String text, int order) {
		super();
		this.document = document;
		this.text = text;
		this.order=order;
	}

	
	public Sentence(String[] id2, Integer order, String text, Double score, Document document) {
		super();
		this.document = document;
		this.text = text;
		this.order=order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<ChemicalCompoundSentence> getChemicalCompoundSentences() {
		return chemicalCompoundSentences;
	}

	public void setChemicalCompoundSentences(List<ChemicalCompoundSentence> chemicalCompoundSentences) {
		this.chemicalCompoundSentences = chemicalCompoundSentences;
	}

	public List<HepatotoxicityTermSentence> getHepatotoxicityTermSentences() {
		return hepatotoxicityTermSentences;
	}

	public void setHepatotoxicityTermSentences(List<HepatotoxicityTermSentence> hepatotoxicityTermSentences) {
		this.hepatotoxicityTermSentences = hepatotoxicityTermSentences;
	}

//	public List<CytochromeSentence> getCytochromeSentences() {
//		return cytochromeSentences;
//	}
//
//	public void setCytochromeSentences(List<CytochromeSentence> cytochromeSentences) {
//		this.cytochromeSentences = cytochromeSentences;
//	}
//
//	public List<ChemicalCompoundCytochromeSentence> getChemicalCompoundCytochromeSentences() {
//		return chemicalCompoundCytochromeSentences;
//	}
//
//	public void setChemicalCompoundCytochromeSentences(
//			List<ChemicalCompoundCytochromeSentence> chemicalCompoundCytochromeSentences) {
//		this.chemicalCompoundCytochromeSentences = chemicalCompoundCytochromeSentences;
//	}
//
//	public List<MarkerSentence> getMarkerSentences() {
//		return markerSentences;
//	}
//
//	public void setMarkerSentences(List<MarkerSentence> markerSentences) {
//		this.markerSentences = markerSentences;
//	}
//
//	public List<MarkerChemicalCompoundSentence> getMarkerChemicalCompoundSentences() {
//		return markerChemicalCompoundSentences;
//	}
//
//	public void setMarkerChemicalCompoundSentences(List<MarkerChemicalCompoundSentence> markerChemicalCompoundSentences) {
//		this.markerChemicalCompoundSentences = markerChemicalCompoundSentences;
//	}
//
//	public List<HepatotoxicityTermChemicalCompoundSentence> getHepatotoxicityTermChemicalCompoundSentences() {
//		return hepatotoxicityTermChemicalCompoundSentences;
//	}
//
//	public void setHepatotoxicityTermChemicalCompoundSentences(
//			List<HepatotoxicityTermChemicalCompoundSentence> hepatotoxicityTermChemicalCompoundSentences) {
//		this.hepatotoxicityTermChemicalCompoundSentences = hepatotoxicityTermChemicalCompoundSentences;
//	}
//
//	
//	public List<TaxonomySentence> getTaxonomySentences() {
//		return taxonomySentences;
//	}
//
//	public void setTaxonomySentences(List<TaxonomySentence> taxonomySentences) {
//		this.taxonomySentences = taxonomySentences;
//	}

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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	
	
	
	
}
