package es.bsc.inb.limtox.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Entity that describes a RelevantTop of a Text Entity.
 * @author jcorvi
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",length=60)
public class RelevantTopicInformation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(length=50, nullable=false)
	private String topicName;
	
	@Column(nullable = false)
	private Float classifierScore=0f;
	
	@Column(nullable = false)
	private Integer numberOfTermsScore=0;
	
	@Column(nullable=false)
	private Integer coOcurrenceScore=0;

	@Column(nullable = false)
	private Integer patternScore=0;
	
	@Column(nullable = false)
	private Float customWeightScore=0f;
	
	public RelevantTopicInformation() {
		super();
	}

	public RelevantTopicInformation(String topicName, Float classifierScore) {
		this.topicName=topicName;
		this.classifierScore=classifierScore;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Float getClassifierScore() {
		return classifierScore;
	}

	public void setClassifierScore(Float classifierScore) {
		this.classifierScore = classifierScore;
	}

	

	public Integer getNumberOfTermsScore() {
		return numberOfTermsScore;
	}

	public void setNumberOfTermsScore(Integer numberOfTermsScore) {
		this.numberOfTermsScore = numberOfTermsScore;
	}

	public Integer getCoOcurrenceScore() {
		return coOcurrenceScore;
	}

	public void setCoOcurrenceScore(Integer coOcurrenceScore) {
		this.coOcurrenceScore = coOcurrenceScore;
	}

	public Integer getPatternScore() {
		return patternScore;
	}

	public void setPatternScore(Integer patternScore) {
		this.patternScore = patternScore;
	}

	public Float getCustomWeightScore() {
		return customWeightScore;
	}

	public void setCustomWeightScore(Float customWeightScore) {
		this.customWeightScore = customWeightScore;
	}

	
}
