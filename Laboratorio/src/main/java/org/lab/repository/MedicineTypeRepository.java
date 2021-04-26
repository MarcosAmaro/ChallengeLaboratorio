package org.lab.repository;

import java.util.List;

import org.lab.model.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineTypeRepository extends JpaRepository<MedicineType, Long> {
	
	@Query(value = "select * from Medicine_type m " + "where (:type is null or m.id_type=:type) "
			+ "and (:name is null or upper(m.name) like upper(:name) || '%')", nativeQuery = true)
	public List<MedicineType> searchMedicineType(@Param(value = "name") String name, 
										 @Param(value = "type") Long type);
}
