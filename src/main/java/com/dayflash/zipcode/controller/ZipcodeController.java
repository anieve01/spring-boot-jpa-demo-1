package com.dayflash.zipcode.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dayflash.zipcode.model.Zipcode;
import com.dayflash.zipcode.service.ZipcodeService;

/**
 * 
 * Zipcodes REST API
 *
 */

@RequestMapping(ZipcodeController.BASE_URL)
@RestController
public class ZipcodeController {
	
	public static final String BASE_URL = "/api/v1/zipcodes";
	private static final Logger log = LoggerFactory.getLogger(ZipcodeController.class);

	@Autowired
	private ZipcodeService zipcodeService;
    
	/**
	 * Load all zipcodes from a csv file and save them into a MySQL database:
	 * GET /api/v1/zipcodes/load
	 * @return status
	 * @throws Exception
	 */
	@GetMapping("/load")
	public BatchStatus loadZipcodesToDB() throws Exception {
		
		BatchStatus batchStatus = zipcodeService.loadZipcodesFromCVSFile();
		log.info("Batch status: {}\nreturned", batchStatus);
		return batchStatus;
		
	}
	
	/**
	 * Return all zipcodes which have a total population within range provided by the client.
	 * GET /api/v1/zipcodes?minPopulation=100000&maxPopulation=200000
	 * @param minPopulation
	 * @param maxPopulation
	 * @return the results
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"minPopulation", "maxPopulation"})
    List<Zipcode> findByPopulationBetweenRange(
    		@RequestParam(value="minPopulation", required=true) Integer minPopulation, 
    		@RequestParam(value="maxPopulation", required=true) Integer maxPopulation) 
	{
    	return zipcodeService.findAllByTotalPopulationBetween(minPopulation, maxPopulation);
    }
	
	/**
	 * Return all zipcodes which have a median age within a range provided by the client.
	 * GET /api/v1/zipcodes?minAge=10&maxAge=30
	 * @param minAge
	 * @param maxAge
	 * @return the results
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"minAge", "maxAge"})
    List<Zipcode> findByMedianAgeBetweenRange(
    		@RequestParam(value="minAge", required=true) BigDecimal minAge, 
    		@RequestParam(value="maxAge", required=true) BigDecimal maxAge) 
	{
    	return zipcodeService.findAllByMedianAgeBetween(minAge, maxAge);
    }
	
	/**
	 * Return top X number of most populated zipcodes.
	 * GET /api/v1/zipcodes?top=3
	 * @param top
	 * @return the results
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"top"})
    List<Zipcode> findTopN(@RequestParam(value="top", required=true) Integer top)
	{
    	return zipcodeService.findTopN(top);
    }
	
	/**
	 * Return all zipcodes with more females than males ordered by the difference descending.
	 * GET /api/v1/zipcodes?moreFemalesThanMales=1
	 * @return the results
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"moreFemalesThanMales"})
    List<Zipcode> findByMoreFemalesThanMales()
	{
    	return zipcodeService.findByMoreFemalesThanMalesOrderedByDiff();
    }


}
