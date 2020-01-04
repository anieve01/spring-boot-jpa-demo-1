package com.dayflash.zipcode.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dayflash.zipcode.model.Zipcode;

public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {
	
	List<Zipcode> findAllByTotalPopulationBetween(Integer min, Integer max);
	
	List<Zipcode> findAllByMedianAgeBetween(BigDecimal min, BigDecimal max);
	
	@Query(value = "SELECT * FROM zipcode ORDER BY total_population DESC LIMIT :limit", nativeQuery = true)
	List<Zipcode> findTopN(@Param("limit") int limit);
	
	@Query(value = "SELECT * FROM zipcode where total_females > total_males order by (total_females - total_males) desc", nativeQuery = true)
	List<Zipcode> findByMoreFemalesThanMalesOrderedByDiff();
	
}
