package com.dayflash.zipcode.error;

public class ZipcodeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5274433013412857527L;
	
	public ZipcodeNotFoundException(Long id) {
        super("Zipcode id not found : " + id);
    }

}
