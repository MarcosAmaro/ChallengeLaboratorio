package org.lab.dto;

public class MedicineBeanDTO extends MedicineDTO{
	private Long idMedicine;
	private Long idType;
	
	public MedicineBeanDTO() {
		
	}
	
	public Long getIdMedicine() {
		return idMedicine;
	}
	public void setIdMedicine(Long idMedicine) {
		this.idMedicine = idMedicine;
	}
	
	public Long getIdType() {
		return idType;
	}
	public void setIdType(Long idType) {
		this.idType = idType;
	}
	
}
