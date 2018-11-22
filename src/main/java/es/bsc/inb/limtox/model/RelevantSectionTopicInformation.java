package es.bsc.inb.limtox.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class RelevantSectionTopicInformation extends RelevantTopicInformation {
	
	@ManyToOne
	private Section section;
	
	public RelevantSectionTopicInformation() {
		super();
	}
	
	public RelevantSectionTopicInformation(String topicName, Float score) {
		super(topicName, score);
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	
	
}
