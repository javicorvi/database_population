package es.bsc.inb.limtox.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="compounddict")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChemicalCompound implements LimtoxEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")
	@JsonProperty("Chemical_name")
	private String name;
	@Column(name="name2Struct")
	private Integer nameToStruct;
	@Column(name="created")
	private Date created;
	@Column(name="updated")
	private Date updated;
	@Column(name="\"chemIdPlus\"")
	@JsonProperty("ChemIDplus_id")
	private String chemPlusId;
	@Column(name="chebi")
	@JsonProperty("ChEBI_id")
	private String chebi;
	@Column(name="\"casRegistryNumber\"")
	@JsonProperty("CAS_registry")
	private String casRegistryNumber;
	@Column(name="\"pubChemCompound\"")
	@JsonProperty("PubChem_compound_id")
	private String pubChemCompundId;
	@Column(name="\"pubChemSubstance\"")
	@JsonProperty("PubChem_substance_id")
	private String pubChemSubstance;
	@Column(name="\"inChi\"")
	@JsonProperty("IUPAC_International_Chemical_id")
	private String inchi;
	@Column(name="\"drugBank\"")
	@JsonProperty("Drugback_id")
	private String drugBankId;
	@Column(name="\"humanMetabolome\"")
	@JsonProperty("Human_metabolome_id")
	private String humanMetabolome;
	@Column(name="\"keggCompound\"")
	@JsonProperty("KEGG_compound_id")
	private String keggCompound; 
	@Column(name="mesh")
	@JsonProperty("MeSH_substance_id")
	private String mesh; 
	@Column(name="\"nrDbIds\"")
	@JsonProperty("nrDBIds")
	private String nrDBIds;
	@Column(name="smile")
	@JsonProperty("SMILES")
	private String smile;
	@Column(name="image_id")
	private String imageId;
	@Column(name="structure_id")
	private String structureId;
	
	public boolean equals(Object word_token) {
		if(word_token==null) {
			return false;
		}else {
			if(word_token.equals(this.name)) {
				return true;
			}
		}
		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNameToStruct() {
		return nameToStruct;
	}

	public void setNameToStruct(Integer nameToStruct) {
		this.nameToStruct = nameToStruct;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getChemPlusId() {
		return chemPlusId;
	}

	public void setChemPlusId(String chemPlusId) {
		this.chemPlusId = chemPlusId;
	}

	public String getChebi() {
		return chebi;
	}

	public void setChebi(String chebi) {
		this.chebi = chebi;
	}

	public String getCasRegistryNumber() {
		return casRegistryNumber;
	}

	public void setCasRegistryNumber(String casRegistryNumber) {
		this.casRegistryNumber = casRegistryNumber;
	}

	public String getPubChemCompundId() {
		return pubChemCompundId;
	}

	public void setPubChemCompundId(String pubChemCompundId) {
		this.pubChemCompundId = pubChemCompundId;
	}

	public String getPubChemSubstance() {
		return pubChemSubstance;
	}

	public void setPubChemSubstance(String pubChemSubstance) {
		this.pubChemSubstance = pubChemSubstance;
	}

	public String getInchi() {
		return inchi;
	}

	public void setInchi(String inchi) {
		this.inchi = inchi;
	}

	public String getDrugBankId() {
		return drugBankId;
	}

	public void setDrugBankId(String drugBankId) {
		this.drugBankId = drugBankId;
	}

	public String getHumanMetabolome() {
		return humanMetabolome;
	}

	public void setHumanMetabolome(String humanMetabolome) {
		this.humanMetabolome = humanMetabolome;
	}

	public String getKeggCompound() {
		return keggCompound;
	}

	public void setKeggCompound(String keggCompound) {
		this.keggCompound = keggCompound;
	}

	public String getMesh() {
		return mesh;
	}

	public void setMesh(String mesh) {
		this.mesh = mesh;
	}

	public String getNrDBIds() {
		return nrDBIds;
	}

	public void setNrDBIds(String nrDBIds) {
		this.nrDBIds = nrDBIds;
	}

	public String getSmile() {
		return smile;
	}

	public void setSmile(String smile) {
		this.smile = smile;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getStructureId() {
		return structureId;
	}

	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	
	
	
}
