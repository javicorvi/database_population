package es.bsc.inb.limtox.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class RelevantDocumentTopicInformation extends RelevantTopicInformation {
	
	@ManyToOne(optional=true)
	private Document document;
	
	public RelevantDocumentTopicInformation() {
		super();
	}
	
	public RelevantDocumentTopicInformation(String topicName, Float score) {
		super(topicName, score);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	
}
