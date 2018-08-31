package es.bsc.inb.limtox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ocurrence")
public class Ocurrence {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer start;
	
	@Column(name="n_end")
	private Integer end;
	
	@ManyToOne
	private ChemicalCompoundSentence chemicalCompoundSentence;
	
	public Ocurrence() {}
	
	public Ocurrence(Integer start, Integer end) {
		super();
		this.start = start;
		this.end = end;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChemicalCompoundSentence getChemicalCompoundSentence() {
		return chemicalCompoundSentence;
	}

	public void setChemicalCompoundSentence(ChemicalCompoundSentence chemicalCompoundSentence) {
		this.chemicalCompoundSentence = chemicalCompoundSentence;
	}

	
	

	
	
	
	
}
