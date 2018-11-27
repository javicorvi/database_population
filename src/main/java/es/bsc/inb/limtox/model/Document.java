package es.bsc.inb.limtox.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Document implements LimtoxEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="document_id",length=20,nullable=false, unique=true)
	private String documentId;
	
	@Column(name="title",length=1000,nullable=true)
	private String title;
	
	@ManyToOne(optional=false)
	private DocumentSource documentSource;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "document", orphanRemoval = true)
	private List<RelevantDocumentTopicInformation> relevantTopicsInformation = new ArrayList<RelevantDocumentTopicInformation>(); 
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "document", orphanRemoval = true)
	private List<Section> sections = new ArrayList<Section>();

	public Document() {
		super();
	}
	
	public Document(String documentId, DocumentSource documentSource, String title) {
		this.documentId = documentId;
		this.title = title;
		this.documentSource = documentSource;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public DocumentSource getDocumentSource() {
		return documentSource;
	}

	public void setDocumentSource(DocumentSource documentSource) {
		this.documentSource = documentSource;
	}

	public void addRelevantDocumentTopicInformation(RelevantDocumentTopicInformation relevantTopicInformation) {
		relevantTopicsInformation.add(relevantTopicInformation);
	}

	public void addSection(Section section) {
		sections.add(section);
	}

	public List<RelevantDocumentTopicInformation> getRelevantTopicsInformation() {
		return relevantTopicsInformation;
	}

	public void setRelevantTopicsInformation(List<RelevantDocumentTopicInformation> relevantTopicsInformation) {
		this.relevantTopicsInformation = relevantTopicsInformation;
	}
	
	
	
}
