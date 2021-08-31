package com.tim40.tim40.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
	
	List<Absence> findByPharmacy(Long id);
	
    @Modifying
    @Query(value= "delete from absences as a  where a.user_id = :id and a.id =:idobject",nativeQuery = true)
    void deleteByManyProperties(@Param("id") Long id,@Param("idobject") Long idobject);
    
}
