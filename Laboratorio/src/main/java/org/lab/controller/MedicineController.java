package org.lab.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.lab.dto.MedicineBeanDTO;
import org.lab.dto.MedicineFilterDTO;
import org.lab.dto.MedicineResultDTO;
import org.lab.model.Medicine;
import org.lab.repository.MedicineRepository;
import org.lab.repository.MedicineTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin()
@Api(tags = { "Medicine Operations" })
@RestController
@RequestMapping("medicine/")
public class MedicineController {

	@Autowired
	private MedicineRepository medRepo;

	@Autowired
	private MedicineTypeRepository medTypeRepo;
	
	private ModelMapper mp = new ModelMapper();
	
	@ApiOperation(value = "Gets a list of medicines given its filter")
	@GetMapping("list")
	public List<MedicineResultDTO> getMedicines(MedicineFilterDTO mf) {
		return medRepo.searchMedicine(mf.getName(), mf.getType()).stream().map(x -> mp.map(x, MedicineResultDTO.class)).collect(Collectors.toList());
	}

	@ApiOperation(value = "Gets a list of Medicines matching its type")
	@GetMapping("listByType")
	@ApiImplicitParam(value = "The medicine id type", example = "4", name = "type")
	public List<Medicine> getMedicinesByType(Long type) {
		if (type != null) {
			return medRepo.findByType_idType(type);
		} else {
			return new ArrayList<Medicine>();
		}
	}

	@ApiOperation(value = "Inserts or updates a given Medicine")
	@PostMapping("saveMed")
	public MedicineBeanDTO saveMedicine(
			@ApiParam(name = "medicine", value = "The medicine to insert or update") @RequestBody MedicineBeanDTO m) {
		Medicine med = mp.map(m, Medicine.class);
		med.setType(medTypeRepo.findById(m.getIdType()).get());
		medRepo.save(med);
		return mp.map(med, MedicineBeanDTO.class);
	}
}
