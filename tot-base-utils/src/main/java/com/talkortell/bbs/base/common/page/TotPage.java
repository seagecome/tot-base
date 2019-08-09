package com.talkortell.bbs.base.common.page;

import java.io.Serializable;

public class TotPage implements Serializable{

	private static final long serialVersionUID = 3012201059434066336L;
	private int begin;
	private int end;
	private int length;
	private long totalRecords;
	private int pageNo;
	private int pageCount;
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public TotPage(Integer pageNo, Integer length) {
		if(pageNo == null || pageNo <= 0) {
			pageNo=1;
		}
		if(length == null || length <= 0) {
			length=10;
		}
		this.length = length.intValue();
		this.pageNo = pageNo.intValue();
		this.begin = this.length * (this.getPageNo() - 1);
		this.end = this.length * this.getPageNo();
	}
}
