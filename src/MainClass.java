import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class MainClass {

	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private DesiredCapabilities cap;

	public void startServer(int port) {
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");
		cap.setCapability("platformName", "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "d83bcc68");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");

		// Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(port);
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

		// Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	public void stopServer() {
		if (service != null)
			service.stop();
	}
	
	public void clearPort(int iPOrtNumber) {
		ServerSocket ss = null;
		
	}

	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void main(String[] args) throws IOException, ParseException {
		readJsonFromFile();
		int portNumber = 4724;
		MainClass mainClass = new MainClass();

		if (!mainClass.checkIfServerIsRunnning(portNumber)) {
			mainClass.startServer(portNumber);
			runMobileApp(mainClass.cap);
		}
		mainClass.stopServer();
		
	}

	public static void runMobileApp(DesiredCapabilities cap) throws MalformedURLException {

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);
		driver.get("http://m.kohls.com");

		// System.out.println("Context:"+ ((Object) driver).getContext() );

		driver.findElement(By.xpath("//div[@id='menu-section']")).click();
		driver.findElement(By.xpath("//li[@class='shop-by-category hb-mid-block']")).click();

		driver.findElement(By.xpath("//div[@id='mcom-category-menu']/descendant::ul/li[text()='Women']")).click();

		driver.findElement(By.xpath("//div[@id='mcom-category-menu']/descendant::ul/li[text()='Patriotic for Women']")).click();

		String OriginalPrice = driver.findElement(By.xpath("(//div[@class='product-name'])[1]/ancestor::a[@class='productDtls']/descendant::div[@id='origprice']")).getText();
		System.out.println("Original Price = " + OriginalPrice);

		String SalePrice = driver.findElement(By.xpath(
				"(//div[@class='product-name'])[1]/ancestor::a[@class='productDtls']/descendant::div[@class='sale-price']")).getText();

		System.out.println(SalePrice);

		driver.findElement(By.xpath("(//div[@class='product-name'])[1]/ancestor::a[@class='productDtls']")).click();

		driver.findElement(By.xpath("//*[@name='sizeRef1' and text()='Select a size']")).click();

		if (driver.findElement(By.xpath("//*[@class=\"sizelist-title\"]/ancestor::*[@class='size-enhance-dropdown']"))
				.isDisplayed()) {
			driver.findElement(By.xpath(
					"//*[@class=\"sizelist-title\"]/ancestor::*[@class='size-enhance-dropdown']/descendant::div[@id='productSizeCln-1' and text()='SMALL']"))
					.click();
		}

		String currentQuantity = driver
				.findElement(By.xpath("//*[@class='quantity-selector']/descendant::input[@id='inpQuantity']")).getAttribute("value");

		System.out.println("Current Prod Qty = " + currentQuantity);

		driver.findElement(By.xpath("//*[@id='add-to-bag-btn' and text()='Add to Cart' and @class='enabled']")).click();

		driver.findElement(By.xpath("//*[text()='Item added to Cart']")).isDisplayed();

		driver.findElement(By.xpath("//*[text()='VIEW CART & APPLY PROMOS']")).click();

		driver.findElement(By.xpath("//*[@class='mcom-cart-icon cart-enabled']/span[text()='1']")).isDisplayed();

		driver.findElement(By.xpath("//a[@class='btn btn-success big btn-block btn-move-to-checkout ng-binding' and text()='Checkout']")).click();

		driver.findElement(By.xpath("//button[@class='guest-checkout-button' and text()='Guest Checkout']")).click();

	}
	private Integer findRandomOpenPortOnAllLocalInterfaces() throws IOException {
	    try (
	        ServerSocket socket = new ServerSocket(0);
	    ) {
	      return socket.getLocalPort();
	    }
	  }

	public static <E> void readJsonFromFile() throws FileNotFoundException, IOException, ParseException {

		JSONObject deviceJsonObject;
		String fileName = "C:\\Users\\balaji3257\\eclipse-workspace\\AppiumMobileTabletWeb\\TestData\\Device.json";
		Map<String, String> deviceMap = new HashMap<>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(fileName));
		JSONObject jsonObject = (JSONObject) obj;

		// System.out.println(jsonObject);
		Iterator<E> iterator = jsonObject.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (jsonObject.get(key) instanceof JSONObject) {
				deviceJsonObject = (JSONObject) jsonObject.get(key);
				if ((deviceJsonObject != null)
						&& deviceJsonObject.get("executionStatus").toString().equalsIgnoreCase("YES")) {
					deviceMap.put(key, deviceJsonObject.toJSONString());
				}
			}

		}
		System.out.println(deviceMap.toString());
	}
}
