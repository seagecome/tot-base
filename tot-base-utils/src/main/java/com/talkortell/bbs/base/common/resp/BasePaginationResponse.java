package com.talkortell.bbs.base.common.resp;

import java.io.Serializable;
import java.util.List;

import com.talkortell.bbs.base.common.enums.RespCodeEnum;

public class BasePaginationResponse<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = -3178862011612819051L;
	/**
	 * 返回码
	 */
	private Integer code;
	/**
	 * 返回消息
	 */
	private String msg;
	
	/**
	 * 数据集合
	 */
	private List<T> modelList;
	
	/**
	 * 总条数
	 */
	private Long totalCount;
	
	/**
	 * 总页数
	 */
	private Integer pageCount;
	
	private List<String> validations;
	
	public void setSuccess(boolean success) {
		this.setCode(success ? RespCodeEnum.ACCESS_SUCCESS.getCode() : RespCodeEnum.ACCESS_FAIL.getCode());
	}
	
	public boolean success() {
		return this.code != null && this.code.equals(RespCodeEnum.ACCESS_SUCCESS.getCode());
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getModelList() {
		return modelList;
	}

	public void setModelList(List<T> modelList) {
		this.modelList = modelList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<String> getValidations() {
		return validations;
	}

	public void setValidations(List<String> validations) {
		this.validations = validations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BasePaginationResponse [");
		if (code != null)
			builder.append("code=").append(code).append(", ");
		if (msg != null)
			builder.append("msg=").append(msg).append(", ");
		if (modelList != null)
			builder.append("modelList=").append(modelList).append(", ");
		if (totalCount != null)
			builder.append("totalCount=").append(totalCount).append(", ");
		if (pageCount != null)
			builder.append("pageCount=").append(pageCount).append(", ");
		if (validations != null)
			builder.append("validations=").append(validations);
		builder.append("]");
		return builder.toString();
	}
}
