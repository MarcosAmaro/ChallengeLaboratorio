package org.lab.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

@CrossOrigin(origins="*")
@Api(tags = {"Product Operations"})
@RestController
@RequestMapping("product/")
public class ProductController {
	
	@ApiOperation(value = "Evaluates if the code is from a prority product")
	@ApiImplicitParam(value = "The product code", example = "PMT-1111-5", name="prdCode")
	@GetMapping("isPriority")
	public Boolean isPriority(String prdCode) {
		return prdCode == null ? false : prdCode.toUpperCase().matches("^(P|W).*$");
	}
	
	@ApiOperation(value = "Validates a product code")
	@ApiImplicitParam(value = "The product code", example = "PMT-1111-5", name="prdCode")
	@GetMapping("isValid")
	public Boolean isValid(String prdCode) {
		if (prdCode == null || !prdCode.matches("(?i:[a-z]{3}-[0-9]{5}-[0-9])")){
			return false;
		}
		
		String prdCodeParts[] = prdCode.split("-");
		String regionCode = prdCodeParts[1];
		String vDigit = prdCodeParts[2];
		
		String prdVDigit = "";
		while (prdVDigit.length() != 1) {
			prdVDigit = regionCode.chars().mapToObj(x -> Character.getNumericValue(x))
						.collect(Collectors.summingInt(Integer::intValue)).toString();
			regionCode = prdVDigit;
		}
		
		return prdVDigit.equals(vDigit);
	}
	
	@ApiOperation(value = "Sorts the list alphabetically")
	@PostMapping("sort")
	public List<String> sortPrds(
			@ApiParam(value="A list of product codes")
			@RequestBody List<String> lstPrdCode){
		return lstPrdCode.stream().sorted().collect(Collectors.toList());
	}
	
	@ApiOperation(value = "Returns a list of the union of the lists")
	@PostMapping("prdUnion")
	public Set<String> unionPrds(
			@ApiParam(value="A list containing lists of products")
			@RequestBody List<HashSet<String>> lstPrdCode){
		Set<String> union = new HashSet<String>();
		
		if (lstPrdCode != null && !lstPrdCode.isEmpty()) {
			union.addAll(lstPrdCode.get(0));
			lstPrdCode.forEach(union::addAll);			
		}
		
		return union;
	}
	
	@ApiOperation(value = "Returns a list of the intersection of the lists")
	@PostMapping("prdIntersection")
	public Set<String> intersectPrds(
			@ApiParam(value="A list containing lists of products")
			@RequestBody List<HashSet<String>> lstPrdCode){
		Set<String> intersection = new HashSet<String>();
		
		if (lstPrdCode != null && !lstPrdCode.isEmpty()) {
			intersection.addAll(lstPrdCode.get(0));
			lstPrdCode.forEach(intersection::retainAll);			
		}
		
		return intersection;
	}
}
