package com.genName.device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DeviceContainer {

	private List<Device> deviceList = new ArrayList<>();
	private Map<Device, Map<String, Boolean>> executionMap = new HashMap<>();
	private boolean allowReuse = true;

	public void addDevice(DesiredCapabilities dC) {
		deviceList.add(new Device(dC));
	}

	public String toString() {
		return deviceList.toString();
	}

	private class DeviceComparator implements Comparator<Device> {

		@Override
		public int compare(Device o1, Device o2) {
			return Long.compare(o1.getLockTime(), o2.getLockTime());
		}

	}

	public synchronized Device getDevice(String methodKey) {
		int retryCount = 0;

		try {
			Collections.sort(deviceList, new DeviceComparator());
		} catch (Exception e) {
		}

		while (retryCount < 600) {
			for (Device device : deviceList) {
				if (!allowReuse) {

				}
			}

			try {
				Thread.sleep(5000);
			} catch (Exception e) {

			}
		}

		throw new IllegalStateException("Unable to qcquire Device");
	}

	public boolean isAllowReuse() {
		return allowReuse;
	}

	public void setAllowReuse(boolean allowReuse) {
		this.allowReuse = allowReuse;
	}
}
