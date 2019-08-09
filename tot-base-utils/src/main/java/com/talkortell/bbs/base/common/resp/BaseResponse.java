package com.talkortell.bbs.base.common.resp;

import java.io.Serializable;
import java.util.List;

import com.talkortell.bbs.base.common.enums.RespCodeEnum;

public class BaseResponse<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = -6559652888485205475L;
	/**
	 * 返回码
	 */
	private Integer code;
	/**
	 * 返回消息
	 */
	private String msg;
	
	/**
	 * 响应数据
	 */
	private T model;
	
	/**
	 * 异常消息
	 */
	private List<String> validations;
	
	public BaseResponse() {}
	
	public BaseResponse(T model) {
		this.model = model;
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

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public List<String> getValidations() {
		return validations;
	}

	public void setValidations(List<String> validations) {
		this.validations = validations;
	}

	public void setSuccess(boolean success) {
		this.setCode(success ? RespCodeEnum.ACCESS_SUCCESS.getCode() : RespCodeEnum.ACCESS_FAIL.getCode());
	}
	
	public boolean success() {
		return this.code != null && this.code.equals(RespCodeEnum.ACCESS_SUCCESS.getCode());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseResponse [");
		if (code != null)
			builder.append("code=").append(code).append(", ");
		if (msg != null)
			builder.append("msg=").append(msg).append(", ");
		if (model != null)
			builder.append("model=").append(model).append(", ");
		if (validations != null)
			builder.append("validations=").append(validations);
		builder.append("]");
		return builder.toString();
	}
	
}