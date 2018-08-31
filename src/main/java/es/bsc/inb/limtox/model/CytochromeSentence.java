package es.bsc.inb.limtox.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CytochromeSentence extends RelevantTermSentence{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Cytochrome cytochrome;
	
	public CytochromeSentence() {}
	
	public CytochromeSentence(Cytochrome cytochrome, Float score, Integer quantity, List<Ocurrence> ocurrences, Sentence sentence) {
		super(score, quantity, sentence, ocurrences);
		this.cytochrome = cytochrome;
	}

	
	public Cytochrome getCytochrome() {
		return cytochrome;
	}

	public void setCytochrome(Cytochrome cytochrome) {
		this.cytochrome = cytochrome;
	}

	

}
