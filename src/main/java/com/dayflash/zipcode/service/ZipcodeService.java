package com.dayflash.zipcode.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.batch.core.BatchStatus;

import com.dayflash.zipcode.model.Zipcode;

/**
 * 
 * Provide a standard zipcode service interface that all concrete service implementations can conform to.
 *
 */
public interface ZipcodeService {

	public BatchStatus loadZipcodesFromCVSFile();
	
	List<Zipcode> findAllByTotalPopulationBetween(Integer min, Integer max);
	
	List<Zipcode> findAllByMedianAgeBetween(BigDecimal min, BigDecimal max);
	
	List<Zipcode> findTopN(Integer limit);
	
	List<Zipcode> findByMoreFemalesThanMalesOrderedByDiff();
	
}
