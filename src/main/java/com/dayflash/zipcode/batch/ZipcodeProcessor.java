package com.dayflash.zipcode.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.dayflash.zipcode.model.Zipcode;

@Component
public class ZipcodeProcessor implements ItemProcessor<Zipcode, Zipcode> {


    public ZipcodeProcessor() {
    }

    @Override
    public Zipcode process(Zipcode zipcode) throws Exception {
    	// Process the zipcode entity here if needed before saving to the database
        return zipcode;
    }
}