package com.genName.config;

import java.util.Properties;

public class ConfigManager {
	private Properties cP = new Properties();

	private static ConfigManager singleton = new ConfigManager();

	private ConfigManager() {
		try {
			cP.load(ClassLoader.getSystemClassLoader().getResourceAsStream("com/genName/config/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ConfigManager instance() {
		return singleton;
	}

	public String getProperty(String propertyName) {
		String systemOverride = System.getProperty(propertyName);
		if (systemOverride != null && !systemOverride.isEmpty() && !systemOverride.startsWith("$"))
			return systemOverride;
		else
			return cP.getProperty(propertyName);
	}

	// Returns if config property is present or returns null
	public String getConfigProperty(String propertyName) {
		return cP.getProperty(propertyName);
	}
}
