package com.dayflash.zipcode.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provides base shared resources to all zipcode service sub-classes 
 */
public class AbstractZipcodeService {

	@Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected Job job;
    
    /**
     * Create job parameters in our standard way
     * @return a instance of our standard job parameters
     */
    protected JobParameters createJobParameters() {
		Map<String, JobParameter> maps = new HashMap<>();
        maps.put("timestamp", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        return parameters;
	}
	
}
