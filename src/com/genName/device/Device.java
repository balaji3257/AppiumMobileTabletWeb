package com.genName.device;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Device {
	private String deviceID;
	private String deviceOS;
	private boolean deviceUsed = false;
	private DesiredCapabilities dC;
	private long lockTime;

	public Device(DesiredCapabilities dC) {
		this.dC = dC;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public String getDeviceOS() {
		return deviceOS;
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

	public long getLockTime() {
		return lockTime;
	}

	public void setLockTime(long lockTime) {
		this.lockTime = lockTime;
	}

}
