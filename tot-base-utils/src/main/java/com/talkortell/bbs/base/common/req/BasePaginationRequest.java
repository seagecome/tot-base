package com.talkortell.bbs.base.common.req;

public class BasePaginationRequest extends BaseRequest {
	private static final long serialVersionUID = 2991343818777539985L;
	/**
	 * ����ҳ�룬��һҳ��1��ʼ
	 */
	private Integer pageIndex;
	/**
	 * ÿҳ����
	 */
	private Integer pageSize;
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BasePaginationRequest [");
		if (pageIndex != null)
			builder.append("pageIndex=").append(pageIndex).append(", ");
		if (pageSize != null)
			builder.append("pageSize=").append(pageSize);
		builder.append("]");
		return builder.toString();
	}
}
