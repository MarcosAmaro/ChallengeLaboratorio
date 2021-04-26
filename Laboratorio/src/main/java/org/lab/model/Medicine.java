package org.lab.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Represents a medicine")
@Entity
@Table(name = "Medicine")
public class Medicine {

	@ApiModelProperty(value = "uniquie identifier", example = "0")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedicine;

	@ApiModelProperty(value = "Medicine code", example = "12345")
	@Column(name = "code", nullable = false)
	private Long code;

	@ApiModelProperty(value = "The commercial name for the medicine", example="Amoxol")
	@Column(name = "commercial_name")
	private String commercialName;

	@ApiModelProperty(value = "Name of the drug", example="Amoxilina" )
	@Column(name = "drug_name", nullable = false)
	private String drugName;

	@ApiModelProperty(hidden = true)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_med_type", referencedColumnName = "idType", nullable = false)
	private MedicineType type;

	public Medicine() {
		super();
	}

	public Long getIdMedicine() {
		return idMedicine;
	}

	public void setIdMedicine(Long idMedicine) {
		this.idMedicine = idMedicine;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public MedicineType getType() {
		return type;
	}

	public void setType(MedicineType type) {
		this.type = type;
	}
}
