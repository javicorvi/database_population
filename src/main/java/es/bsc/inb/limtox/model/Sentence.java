package es.bsc.inb.limtox.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="sentence")
public class Sentence implements LimtoxEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Document document;
	
	private String text;
	@ManyToOne
	private Section section;
	
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "sentence", orphanRemoval = true)
	private List<ChemicalCompoundSentence> chemicalCompoundSentences = new ArrayList<ChemicalCompoundSentence>();
	
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "sentence", orphanRemoval = true)
	private List<HepatotoxicityTermSentence> hepatotoxicityTermSentences = new ArrayList<HepatotoxicityTermSentence>();
	
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "sentence", orphanRemoval = true)
	private List<CytochromeSentence> cytochromeSentences = new ArrayList<CytochromeSentence>();
	
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "sentence", orphanRemoval = true)
	private List<MarkerSentence> markerSentences = new ArrayList<MarkerSentence>();
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "sentence", orphanRemoval = true)
	private List<ChemicalCompoundCytochromeSentence> chemicalCompoundCytochromeSentences = new ArrayList<ChemicalCompoundCytochromeSentence>();

	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "sentence", orphanRemoval = true)
	private List<HepatotoxicityTermChemicalCompoundSentence> hepatotoxicityTermChemicalCompoundSentences = new ArrayList<HepatotoxicityTermChemicalCompoundSentence>();
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "sentence", orphanRemoval = true)
	private List<MarkerChemicalCompoundSentence> markerChemicalCompoundSentences = new ArrayList<MarkerChemicalCompoundSentence>();
	
	public Sentence() {}
	
	public Sentence(Document document, String text, Section section) {
		super();
		this.document = document;
		this.text = text;
		this.section = section;
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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
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

	public List<CytochromeSentence> getCytochromeSentences() {
		return cytochromeSentences;
	}

	public void setCytochromeSentences(List<CytochromeSentence> cytochromeSentences) {
		this.cytochromeSentences = cytochromeSentences;
	}

	public List<ChemicalCompoundCytochromeSentence> getChemicalCompoundCytochromeSentences() {
		return chemicalCompoundCytochromeSentences;
	}

	public void setChemicalCompoundCytochromeSentences(
			List<ChemicalCompoundCytochromeSentence> chemicalCompoundCytochromeSentences) {
		this.chemicalCompoundCytochromeSentences = chemicalCompoundCytochromeSentences;
	}

	public List<MarkerSentence> getMarkerSentences() {
		return markerSentences;
	}

	public void setMarkerSentences(List<MarkerSentence> markerSentences) {
		this.markerSentences = markerSentences;
	}

	public List<MarkerChemicalCompoundSentence> getMarkerChemicalCompoundSentences() {
		return markerChemicalCompoundSentences;
	}

	public void setMarkerChemicalCompoundSentences(List<MarkerChemicalCompoundSentence> markerChemicalCompoundSentences) {
		this.markerChemicalCompoundSentences = markerChemicalCompoundSentences;
	}

	public List<HepatotoxicityTermChemicalCompoundSentence> getHepatotoxicityTermChemicalCompoundSentences() {
		return hepatotoxicityTermChemicalCompoundSentences;
	}

	public void setHepatotoxicityTermChemicalCompoundSentences(
			List<HepatotoxicityTermChemicalCompoundSentence> hepatotoxicityTermChemicalCompoundSentences) {
		this.hepatotoxicityTermChemicalCompoundSentences = hepatotoxicityTermChemicalCompoundSentences;
	}

	
	
}
