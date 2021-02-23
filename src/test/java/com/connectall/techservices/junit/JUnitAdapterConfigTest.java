package com.connectall.techservices.junit;

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
	}
}
