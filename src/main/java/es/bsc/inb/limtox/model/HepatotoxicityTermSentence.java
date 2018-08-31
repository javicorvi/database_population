package es.bsc.inb.limtox.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="hepatotoxicityterm_sentence")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HepatotoxicityTermSentence /*extends RelevantTermSentence*/{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private HepatotoxicityTerm hepatotoxicityTerm;
	
	@JsonIgnore
	@ManyToOne
	private Sentence sentence;
	
	public HepatotoxicityTermSentence() {}
	
	public HepatotoxicityTermSentence(HepatotoxicityTerm hepatotoxicityTerm, Float score, Integer quantity, List<Ocurrence> ocurrences, Sentence sentence) {
		//super(score, quantity, sentence, ocurrences);
		/*this.score=score;
		this.quantity=quantity;
		this.sentence=sentence;
		this.ocurrences=ocurrences;*/
		this.hepatotoxicityTerm = hepatotoxicityTerm;
	}

	public HepatotoxicityTerm getHepatotoxicityTerm() {
		return hepatotoxicityTerm;
	}

	public void setHepatotoxicityTerm(HepatotoxicityTerm hepatotoxicityTerm) {
		this.hepatotoxicityTerm = hepatotoxicityTerm;
	}
	
}
