package com.genName.device;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Device {
	private DesiredCapabilities dc;
	private String deviceID;
	private String deviceOS;
	private boolean deviceUsed = false;
	private long lockTime;

	public Device(DesiredCapabilities dc) {
		this.dc = dc;
	}

	public DesiredCapabilities getDc() {
		return dc;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public String getDeviceOS() {
		return deviceOS;
	}

	public long getLockTime() {
		return lockTime;
	}

	public boolean isDeviceUsed() {
		return deviceUsed;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public void setDeviceUsed(boolean deviceUsed) {
		this.deviceUsed = deviceUsed;
	}

	public void setLockTime(long lockTime) {
		this.lockTime = lockTime;
	}

}
