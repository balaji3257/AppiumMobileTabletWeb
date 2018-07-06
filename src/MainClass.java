import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.genName.config.ConfigManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainClass {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		readJsonFromFile();
	}

	public static <E> void readJsonFromFile() throws FileNotFoundException, IOException, ParseException {

		JSONObject deviceJsonObject;
		String fileName = "C:\\Users\\balaji3257\\eclipse-workspace\\AppiumMobileTabletWeb\\TestData\\Device.json";
		Map<String, String> deviceMap = new HashMap<>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(fileName));
		JSONObject jsonObject = (JSONObject) obj;

		System.out.println(jsonObject);
		Iterator<E> iterator = jsonObject.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (jsonObject.get(key) instanceof JSONObject) {
				deviceJsonObject = (JSONObject) jsonObject.get(key);
				if ( (deviceJsonObject != null) && deviceJsonObject.get("executionStatus").toString().equalsIgnoreCase("YES") ) {
					deviceMap.put(key, deviceJsonObject.toJSONString());
				}
			}

		}
	}

}
