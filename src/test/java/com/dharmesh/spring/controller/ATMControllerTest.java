package com.dharmesh.spring.controller;


import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dharmesh.spring.model.ATM;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
@WebAppConfiguration
public class ATMControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testServletContextAndATMController() {
	    ServletContext servletContext = wac.getServletContext();	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("ATMController"));
	}
	
	@Test
	public void testGetAllATMs() throws Exception {
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/atms"))
	      .andExpect(MockMvcResultMatchers.status().isOk())
	      .andReturn();
	    Assert.assertEquals("application/json;charset=UTF-8", 
	      mvcResult.getResponse().getContentType());
	    String response = mvcResult.getResponse().getContentAsString();
	    Assert.assertNotNull(response);
	    List<ATM> atms = objectMapper.readValue(response, new TypeReference<List<ATM>>() {});
	    Assert.assertTrue(atms.size()>0);
	}
	
	@Test
	public void testATMByCity() throws Exception {
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/atms/Amsterdam"))
	      .andExpect(MockMvcResultMatchers.status().isOk())
	      .andReturn();
	    Assert.assertEquals("application/json;charset=UTF-8", 
	      mvcResult.getResponse().getContentType());
	    String response = mvcResult.getResponse().getContentAsString();
	    Assert.assertNotNull(response);
	    List<ATM> atms = objectMapper.readValue(response, new TypeReference<List<ATM>>() {});	    
	    Assert.assertTrue(atms.size()>0);	     
	    for (ATM atm : atms) {
	    	Assert.assertTrue(atm.getAddress().getCity().equalsIgnoreCase("Amsterdam"));
		}
	}

}
