package org.lab.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Represents a medicine type", value = "Medicine type")
@Entity
@Table(name = "Medicine_Type")
public class MedicineType {
	@ApiModelProperty(value = "unique identifier", example = "0")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idType;
	
	@ApiModelProperty(value="name of the medicine type", example = "Aerosol")
	@Column(name = "name", nullable = false)
	private String name;
	
	@ApiModelProperty(value="whether the type is active or not")
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;
	
	@JsonIgnore
	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Medicine> lstMedicine;
	
	public MedicineType() {
		super();
	}
	
	public MedicineType(Long idType) {
		this.idType = idType;
	}
	
	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	@JsonIgnore
	public List<Medicine> getLstMedicine() {
		return lstMedicine;
	}

	public void setLstMedicine(List<Medicine> lstMedicine) {
		this.lstMedicine = lstMedicine;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
