package es.bsc.inb.limtox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class EntityInstanceFound implements LimtoxEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="entityinstancefound_id",length=25,nullable=false)
	private String entityInstanceId;
	
	@Column(name="mentiontype",length=70,nullable=false)
	private String mentionType;
	
	@Column(name="mentionsource",length=70,nullable=false)
	private String mentionSource;
	
	@Column(name="startOffset",nullable=false)
	private Integer start;
	
	@Column(name="endOffset",nullable=false)
	private Integer end;
	
	@ManyToOne
	private Section section;
	
	@ManyToOne
	private Sentence sentence;
	
	@ManyToOne(optional=false)
	private EntityInstance entityInstance;
	
	public EntityInstanceFound() {
		super();
	}

	public EntityInstanceFound(Integer start, Integer end, EntityInstance entityInstance, String mentionType, String mentionSource) {
		super();
		this.entityInstanceId=""+start+"_"+end;
		this.start = start;
		this.end = end;
		this.entityInstance = entityInstance;
		this.mentionType = mentionType;
		this.mentionSource = mentionSource;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public EntityInstance getEntityInstance() {
		return entityInstance;
	}

	public void setEntityInstance(EntityInstance entityInstance) {
		this.entityInstance = entityInstance;
	}

	public String getMentionType() {
		return mentionType;
	}

	public void setMentionType(String mentionType) {
		this.mentionType = mentionType;
	}

	public String getMentionSource() {
		return mentionSource;
	}

	public void setMentionSource(String mentionSource) {
		this.mentionSource = mentionSource;
	}

	public String getEntityInstanceId() {
		return entityInstanceId;
	}

	public void setEntityInstanceId(String entityInstanceId) {
		this.entityInstanceId = entityInstanceId;
	}

	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}
	
	
	
	
	
	
}
