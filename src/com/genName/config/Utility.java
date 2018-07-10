package com.genName.config;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utility {
	private Properties cP = new Properties();
	private static String userDir = System.getProperty("user.dir");
	private static Utility singleton = new Utility();

	private Utility() {
		try {
			cP.load(ClassLoader.getSystemClassLoader().getResourceAsStream("com/genName/config/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Utility instance() {
		return singleton;
	}

	public String getProperty(String propertyName) {
		String systemOverride = System.getProperty(propertyName);
		if (systemOverride != null && !systemOverride.isEmpty() && !systemOverride.startsWith("$"))
			return systemOverride;
		else
			return cP.getProperty(propertyName);
	}

	public String getConfigProperty(String propertyName) {
		return cP.getProperty(propertyName);
	}

	public List<Map<String, String>> deviceJsonAsMap() {
		JSONObject deviceJsonObject;
		List<Map<String, String>> deviceList = new ArrayList<>();
		Map<String, String> innerDeviceMap = new HashMap<>();
		JSONParser parser = new JSONParser();
		String fileName = userDir + "\\TestData\\Device.json";
		try {
			Object obj = parser.parse(new FileReader(fileName));
			JSONObject jsonObject = (JSONObject) obj;
			Iterator iterator = jsonObject.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if (jsonObject.get(key) instanceof JSONObject) {
					deviceJsonObject = (JSONObject) jsonObject.get(key);
					if ((deviceJsonObject != null)
							&& deviceJsonObject.get("executionStatus").toString().equalsIgnoreCase("YES")) {
						innerDeviceMap = toMap(deviceJsonObject);
					}
				}
			}
			innerDeviceMap.put("ReUsability", "Yes");
			deviceList.add(innerDeviceMap);
		} catch (Exception e) {
			System.out.println("problem in reading " + fileName);
		}

		return deviceList;

	}

	private Map<String, String> toMap(JSONObject jsonobj) {
		Map<String, String> map = new HashMap<>();
		Iterator<String> keys = (Iterator<String>) jsonobj.keySet();
		while (keys.hasNext()) {
			String key = keys.next();
			Object value = jsonobj.get(key);

			if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, (String) value);
		}
		return map;
	}

	public String getLocator(String strKeyValue) {
		String strObjectFromJson;
		JSONObject ORJsonObject;

		return null;

	}
}
