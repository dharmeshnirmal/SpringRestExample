package com.dharmesh.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dharmesh.spring.model.ATM;
import com.dharmesh.spring.service.ATMService;

/**
 * Handles requests for the ATM service.
 */
@Controller
public class ATMController {
	
	private static final Logger logger = LoggerFactory.getLogger(ATMController.class);
	
	@Autowired
	private ATMService atmService;
	
	@RequestMapping(value = "/rest/atms", method = RequestMethod.GET)
	public @ResponseBody List<ATM> getATMList() {
		logger.info("Start get All ATM list.");	
		return atmService.getAllATM();
	}
	
	@RequestMapping(value = "/rest/atms/{city}", method = RequestMethod.GET)
	public @ResponseBody List<ATM> getATMByCity(@PathVariable("city") String city) {
		logger.info("Start get ATM list by city="+city);	
		return atmService.getATMByCity(city);
	}
	
	
}
