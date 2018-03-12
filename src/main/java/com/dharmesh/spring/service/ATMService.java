package com.dharmesh.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.dharmesh.spring.model.ATM;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ATMService {
	
	private static final Logger logger = LoggerFactory.getLogger(ATMService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String restUri;

	private ObjectMapper objectMapper = new ObjectMapper();

	public List<ATM> getAllATM() {
		logger.info("Start get All ATM list.");	
		return Arrays.asList(populateATMdata()) ;
	}

	public List<ATM> getATMByCity(String city)  {
		logger.info("Start ATM list by city. city = "+city);	
		ATM atms[] = populateATMdata();
		List<ATM> atmList = new ArrayList<ATM>();
		for (ATM atm : atms) {
			if(city.equalsIgnoreCase(atm.getAddress().getCity())){
				atmList.add(atm);
			}
		}		
		return atmList;
	}

	private ATM[] populateATMdata() {
		logger.info("Start populating ATM data.");	
		String response = restTemplate.getForObject(restUri, String.class);
		String toBeParsed = response.substring(6, response.length());
		try {
			return objectMapper.readValue(toBeParsed, ATM[].class);
		} catch (IOException e) {
			logger.error("Fail to populate ATM data : "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void setRestUri(String restUri) {
		this.restUri = restUri;
	}
	
	
	
}
