package es.bsc.inb.limtox.model;

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
public class EntityInstance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(length=120,nullable=false)
	private String value="";
	
	@Column(length=100,nullable=false)
	private String entityTypeName;
	
	@Column(length=50,nullable=false)
	private String taggerName;
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	private EntityType entityType;
	
	@Transient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entityInstance", orphanRemoval = true)
	private List<ReferenceValue> referenceValues;
	
	public EntityInstance() {
		super();
	}

	public EntityInstance(String taggerName, String value, EntityType entityType, List<ReferenceValue> referenceValues) {
		super();
		this.taggerName = taggerName;
		this.value = value;
		//this.entityType = entityType;
		this.entityTypeName = entityType.getName();
		this.referenceValues = referenceValues;
	}

	public EntityInstance(String taggerName, String value, String entityType, List<ReferenceValue> referenceValues) {
		super();
		this.taggerName = taggerName;
		this.value = value;
		//this.entityType = entityType;
		this.entityTypeName = entityType;
		this.referenceValues = referenceValues;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	public List<ReferenceValue> getReferenceValues() {
		return referenceValues;
	}

	public void setReferenceValues(List<ReferenceValue> referenceValues) {
		this.referenceValues = referenceValues;
	}

	public String getEntityTypeName() {
		return entityTypeName;
	}

	public void setEntityTypeName(String entityTypeName) {
		this.entityTypeName = entityTypeName;
	}

	public String getTaggerName() {
		return taggerName;
	}

	public void setTaggerName(String taggerName) {
		this.taggerName = taggerName;
	}
	
	
	
}
