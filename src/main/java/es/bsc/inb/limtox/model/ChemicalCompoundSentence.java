package es.bsc.inb.limtox.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="chemicalcompound_sentence")
public class ChemicalCompoundSentence /*extends RelevantTermSentence*/{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Float score;
	
	private Integer quantity;
	
	@JsonIgnore
	@ManyToOne
	private Sentence sentence;
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "chemicalCompoundSentence", orphanRemoval = true)
	private List<Ocurrence> ocurrences;
	
	@ManyToOne
	private ChemicalCompound chemicalCompound;
	
	private String chemicalCompoundValueTypeFounded;
	
	public ChemicalCompoundSentence() {}
	
	public ChemicalCompoundSentence(ChemicalCompound chemicalCompound, String chemicalCompoundValueTypeFounded, Float score, Integer quantity, List<Ocurrence> ocurrences, Sentence sentence) {
		//super(score, quantity, sentence, ocurrences);
		this.score=score;
		this.quantity=quantity;
		this.sentence=sentence;
		this.ocurrences=ocurrences;
		this.chemicalCompound = chemicalCompound;
		this.chemicalCompoundValueTypeFounded = chemicalCompoundValueTypeFounded;
	}

	public ChemicalCompound getChemicalCompound() {
		return chemicalCompound;
	}

	public void setChemicalCompound(ChemicalCompound chemicalCompound) {
		this.chemicalCompound = chemicalCompound;
	}

	public String getChemicalCompoundValueTypeFounded() {
		return chemicalCompoundValueTypeFounded;
	}

	public void setChemicalCompoundValueTypeFounded(String chemicalCompoundValueTypeFounded) {
		this.chemicalCompoundValueTypeFounded = chemicalCompoundValueTypeFounded;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}

	public List<Ocurrence> getOcurrences() {
		return ocurrences;
	}

	public void setOcurrences(List<Ocurrence> ocurrences) {
		this.ocurrences = ocurrences;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
}
