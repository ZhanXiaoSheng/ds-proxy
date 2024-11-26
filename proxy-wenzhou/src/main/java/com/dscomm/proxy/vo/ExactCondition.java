package com.dscomm.proxy.vo;

import java.util.ArrayList;

public class ExactCondition {
	private ArrayList<String> orgIndexCodes;
    private ArrayList<String> deviceIndexCodes;
    private ArrayList<String> vehicleIndexCodes;

    public ArrayList<String> getDeviceIndexCodes() {
        return deviceIndexCodes;
    }

    public void setDeviceIndexCodes(ArrayList<String> deviceIndexCodes) {
        this.deviceIndexCodes = deviceIndexCodes;
    }

    public ArrayList<String> getVehicleIndexCodes() {
        return vehicleIndexCodes;
    }

    public void setVehicleIndexCodes(ArrayList<String> vehicleIndexCodes) {
        this.vehicleIndexCodes = vehicleIndexCodes;
    }

    public ArrayList<String> getOrgIndexCodes() {
		return orgIndexCodes;
	}

	public void setOrgIndexCodes(ArrayList<String> orgIndexCodes) {
		this.orgIndexCodes = orgIndexCodes;
	}
}
