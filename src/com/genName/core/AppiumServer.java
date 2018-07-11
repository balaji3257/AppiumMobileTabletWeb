package com.genName.core;

import java.io.IOException;
import java.net.ServerSocket;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Step;


/*
 *Appium server class is used to handles the appium server Operation using 'AppiumDriverLocalService' library
 */
public class AppiumServer {

	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;

	/*
	 * start local appium server using any available port
	 */
	@Step
	public String startServer() {
		builder = new AppiumServiceBuilder();
		try {
			builder.withIPAddress("0.0.0.0");
			builder.usingPort(findRandomOpenPortOnAllLocalInterfaces());
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		return service.getUrl().toString();
	}


	/*
	 * stops the server.
	 */
	@Step
	public void stopServer() {
		if (service != null)
			service.stop();
	}


	/*
	 * Check any appium server instance is running.
	 */
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


	/*
	 * Get available free port from Network interfaces
	 */
	private Integer findRandomOpenPortOnAllLocalInterfaces() throws IOException {
		try (ServerSocket socket = new ServerSocket(0);) {
			return socket.getLocalPort();
		}
	}

}
