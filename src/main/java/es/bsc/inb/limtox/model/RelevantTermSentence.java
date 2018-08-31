package es.bsc.inb.limtox.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RelevantTermSentence {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relevanttermsentence_id_seq")
	//@SequenceGenerator(name="termsentece_generator", sequenceName = "termsentece_seq", allocationSize=50)
	private Integer id;
	
	private Float score;
	
	private Integer quantity;
	
	@JsonIgnore
	@ManyToOne
	private Sentence sentence;
	
	@OneToMany(cascade = CascadeType.ALL, 
	mappedBy = "relevantTermSentence", orphanRemoval = true)
	private List<Ocurrence> ocurrences;

	public RelevantTermSentence() {}

	public RelevantTermSentence(Float score, Integer quantity, Sentence sentence,	List<Ocurrence> ocurrences) {
		super();
		this.score = score;
		this.quantity = quantity;
		this.sentence = sentence;
		this.ocurrences = ocurrences;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	
}
