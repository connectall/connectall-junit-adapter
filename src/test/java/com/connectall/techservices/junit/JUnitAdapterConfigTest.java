package com.connectall.techservices.junit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JUnitAdapterConfigTest {

	private JUnitAdapterConfig config;

	@Before
	public void setup() {
		config = JUnitAdapterConfig.getInstance();
	}

	@Test
	public void testGetURL() {
		log.info("Base URL : {} ", config.getValue(JUnitAdapterConfig.BaseUrl,"N/A"));
		assertNotNull(config.getValue(JUnitAdapterConfig.BaseUrl));
		assertNotEquals("N/A",config.getValue(JUnitAdapterConfig.BaseUrl));
	}
	@Test
	public void testGetApiKey() {
		log.info("Apikey : {} ", config.getValue(JUnitAdapterConfig.ApiKey,"N/A"));
		assertNotNull(config.getValue(JUnitAdapterConfig.ApiKey));
		assertNotEquals("N/A",config.getValue(JUnitAdapterConfig.ApiKey));
	}
	@Test
	public void testGetApplicationLink() {
		log.info("Application link : {} ", config.getValue(JUnitAdapterConfig.ApplicationLink,"N/A"));
		assertNotNull(config.getValue(JUnitAdapterConfig.ApplicationLink));
		assertNotEquals("N/A",config.getValue(JUnitAdapterConfig.ApplicationLink));
	}
}
