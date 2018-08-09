package es.bsc.inb.limtox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="cytochrome")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Cytochrome implements LimtoxEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="\"entityId\"")
	private String entityId;
	@Column(name="name")
	private String name;
	@Column(name="type")
	private String type;
	@Column(name="canonical")
	private String canonical;
	@Transient
	private Integer keyId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCanonical() {
		return canonical;
	}
	public void setCanonical(String canonical) {
		this.canonical = canonical;
	}
	
	public Integer getKeyId() {
		return keyId;
	}
	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}
	@Override
    public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }else if(name==null || ((Cytochrome)obj).name==null) {
	    	return false;
	    }else {
	    	if(name.equals(((Cytochrome)obj).name)) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	}

	@Override
	public int hashCode() {
	    return name.hashCode();
	}
	
}
