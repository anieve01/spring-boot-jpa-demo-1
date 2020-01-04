package com.dayflash.zipcode.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayflash.zipcode.error.ZipcodeETLException;
import com.dayflash.zipcode.model.Zipcode;
import com.dayflash.zipcode.repository.ZipcodeRepository;

@Service
public class ZipcodeServiceImpl extends AbstractZipcodeService implements ZipcodeService {

	private static final Logger log = LoggerFactory.getLogger(ZipcodeServiceImpl.class);
	
	@Autowired
	private ZipcodeRepository zipcodeRepository;
	
	@Override
	public BatchStatus loadZipcodesFromCVSFile() {
		        
        JobExecution jobExecution;
        
		try {
			jobExecution = jobLauncher.run(job, createJobParameters());
			
			log.info("JobExecution: ", jobExecution.getStatus());

	        log.info("Batch is Running...");
	        
	        while (jobExecution.isRunning()) {
	            log.info("...");
	        }

	        return jobExecution.getStatus();
	        
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			
			e.printStackTrace();
			throw new ZipcodeETLException(e);
		}

	}

	@Override
	public List<Zipcode> findAllByTotalPopulationBetween(Integer min, Integer max) {
		return zipcodeRepository.findAllByTotalPopulationBetween(min, max);
	}

	@Override
	public List<Zipcode> findAllByMedianAgeBetween(BigDecimal min, BigDecimal max) {
		return zipcodeRepository.findAllByMedianAgeBetween(min, max);
	}

	@Override
	public List<Zipcode> findTopN(Integer limit) {
		return zipcodeRepository.findTopN(limit);
	}

	@Override
	public List<Zipcode> findByMoreFemalesThanMalesOrderedByDiff() {
		return zipcodeRepository.findByMoreFemalesThanMalesOrderedByDiff();
	}
}
