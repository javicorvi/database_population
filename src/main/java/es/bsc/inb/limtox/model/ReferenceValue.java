package es.bsc.inb.limtox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//@Entity
public class ReferenceValue {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String referenceName;
	
	private String value="";
	
	@ManyToOne
	private EntityInstance entityInstance;
	
	@ManyToOne
	private Reference reference;

	
	public ReferenceValue() {
		super();
	}

	public ReferenceValue(String name, String value) {
		super();
		this.value = value;
		//this.reference = reference;
		this.referenceName=name;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public EntityInstance getEntityInstance() {
		return entityInstance;
	}

	public void setEntityInstance(EntityInstance entityInstance) {
		this.entityInstance = entityInstance;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	
	
}
