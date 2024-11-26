package com.dscomm.proxy.vo;

import java.util.ArrayList;

public class GetTopicInfoRequest {
	private ArrayList<Long> eventTypes;

	public ArrayList<Long> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(ArrayList<Long> eventTypes) {
		this.eventTypes = eventTypes;
	}
}
