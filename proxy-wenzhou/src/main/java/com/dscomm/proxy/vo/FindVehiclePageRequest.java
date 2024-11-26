package com.dscomm.proxy.vo;

public class FindVehiclePageRequest {
	private Integer pageNo;
	private Integer pageSize;
	private ExactCondition exactCondition;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public ExactCondition getExactCondition() {
		return exactCondition;
	}

	public void setExactCondition(ExactCondition exactCondition) {
		this.exactCondition = exactCondition;
	}
}
