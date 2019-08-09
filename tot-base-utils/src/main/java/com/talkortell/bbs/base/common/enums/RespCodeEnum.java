package com.talkortell.bbs.base.common.enums;

public enum RespCodeEnum {
	ACCESS_SUCCESS(200, "成功"),
	ILLEGAL_PARAMS(400, "请求参数错误"),
	AUTH_FAIL(401, "请求需要身份认证"),
	ACCESS_FORBIDDEN(403, "请求被禁止"),
	NOT_EXIST(404, "资源找不到"),
	ACCESS_FAIL(500, "服务器内部错误");
	
	private int code;
	private String desc;
	
	private RespCodeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDescByCode(int code) {
		for(RespCodeEnum rce : RespCodeEnum.values()) {
			if(rce.getCode() == code) {
				return rce.getDesc();
			}
		}
		return "";
	}
}
