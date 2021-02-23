package com.connectall.techservices.junit;

import java.io.File;

import javax.inject.Singleton;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Singleton
public class JUnitAdapterConfig {

	public static final String BaseUrl = "base.url";
	public static final String ApiKey = "apikey";
	public static final String ApplicationLink = "applinkName";

	private static JUnitAdapterConfig instance;
	private PropertiesConfiguration config;

	//@SneakyThrows(ConfigurationException.class)
	private JUnitAdapterConfig() {
		final Configurations configs = new Configurations();
		try {
			config = configs.properties(new File("ConnectALL.properties"));
		} catch (Exception e) {
			log.error("Exception {}", e, e);
		}
	}

	public static JUnitAdapterConfig getInstance() {
		if (instance == null)
			instance = new JUnitAdapterConfig();
		return instance;
	}

	public String getValue(String key) {
		return config.getString(key);
	}

	public String getValue(String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}

}
