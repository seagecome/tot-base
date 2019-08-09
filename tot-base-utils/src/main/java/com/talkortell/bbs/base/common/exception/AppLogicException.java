package com.talkortell.bbs.base.common.exception;

import com.talkortell.bbs.base.common.enums.RespCodeEnum;

public class AppLogicException extends RuntimeException{

	private static final long serialVersionUID = 7094875852318436755L;
	
	private final Integer errorCode;

	public AppLogicException(Integer code, String msg) {
		super(msg);
		this.errorCode = RespCodeEnum.ACCESS_FAIL.getCode();
	}

	public Integer getErrorCode() {
		return errorCode;
	}
	
}
