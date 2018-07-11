package com.genName.device;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;


// device container class to hold device objects
public class DeviceContainer {

	private List<Device> deviceList = new ArrayList<>();

	
	public void addDevice(DesiredCapabilities dC) {
		deviceList.add(new Device(dC));
	}

	public String toString() {
		return deviceList.toString();
	}
}
