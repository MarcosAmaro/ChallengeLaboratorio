package org.lab.repository;

import java.util.List;

import org.lab.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{

	@Query(value = "select * from Medicine m "
				 + "where (:type is null or m.id_med_type=:type) "
				 + "and (:name is null or upper(m.commercial_name) like upper(:name) || '%')", nativeQuery = true)
	public List<Medicine> searchMedicine(@Param(value = "name") String name, @Param(value="type") Long type);
	
	//In case of matching only active types
	//@Query(value = "select m from Medicine m where m.medType.idType = :#{#mt.idType} and m.medType.isActive = :#{#mt.isActive}")
	public List<Medicine> findByType_idType(Long type);
}
