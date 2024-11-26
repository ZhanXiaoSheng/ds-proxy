package com.dscomm.proxy.vo;

public class FindTalkPageRequest {
	private Integer pageNo=1;
	private Integer pageSize=20;
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
