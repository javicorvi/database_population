package es.bsc.inb.limtox.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
@DiscriminatorValue("3")
public class RelevantSentenceTopicInformation extends RelevantTopicInformation {

	@ManyToOne
	private Sentence sentence;
	
	public RelevantSentenceTopicInformation() {
		super();
	}
	
	public RelevantSentenceTopicInformation(String topicName, Float score) {
		super(topicName, score);
	}

	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}

	
	
}
