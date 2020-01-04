package com.dayflash.zipcode.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayflash.zipcode.model.Zipcode;
import com.dayflash.zipcode.repository.ZipcodeRepository;

@Component
public class ZipcodeWriter implements ItemWriter<Zipcode>  {

	private static final Logger log = LoggerFactory.getLogger(ZipcodeWriter.class);
	
	@Autowired
	private ZipcodeRepository zipcodeRespository;
	
	@Override
    public void write(List<? extends Zipcode> zipcodes) throws Exception {

        log.info("Data saved for zipcodes batch...");
        zipcodeRespository.saveAll(zipcodes);
    }
	
}
