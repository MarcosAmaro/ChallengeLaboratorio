package org.lab.controller;

import java.util.List;

import org.lab.dto.MedicineTypeFilterDTO;
import org.lab.model.MedicineType;
import org.lab.repository.MedicineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin()
@Api(tags = {"Medicine Type Operations"})
@RestController
@RequestMapping("medType/")
public class MedicineTypeController {
	
	@Autowired
	private MedicineTypeRepository medTypeRepo;
	
	@ApiOperation(value = "Gets a list of Medicines types")
	@GetMapping("list")
	public List<MedicineType> getMedicineTypes(){
		return medTypeRepo.findAll();
	}
	
	@ApiOperation(value = "Gets a list of Medicines types")
	@GetMapping("listByFilter")
	public List<MedicineType> getMedicineTypesByFilter(MedicineTypeFilterDTO mt){
		return medTypeRepo.searchMedicineType(mt.getName(), mt.getIdType());
	}
	
	@ApiOperation(value = "Inserts or updates the given Medicine Type")
	@PostMapping("save")
	public MedicineType saveMedType(
			@ApiParam(name = "Medicine Type", value = "The medicine type to insert or update")
			@RequestBody MedicineType mt){
		medTypeRepo.saveAndFlush(mt);
		
		return mt;
	}
	
	@ApiOperation(value = "Updates the given list of medicine types")
	@PutMapping("update")
	public Boolean updateMedTypes(
			@ApiParam(value = "The list of medicine types to update")
			@RequestBody List<MedicineType> lstMt) {
		medTypeRepo.saveAll(lstMt);
		
		return true;
	}
}
