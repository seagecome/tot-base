package com.talkortell.bbs.base.common.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.talkortell.bbs.base.common.enums.RespCodeEnum;
import com.talkortell.bbs.base.common.exception.AppLogicException;
import com.talkortell.bbs.base.common.page.TotPage;
import com.talkortell.bbs.base.common.req.BasePaginationRequest;
import com.talkortell.bbs.base.common.resp.BasePaginationResponse;
import com.talkortell.bbs.base.common.resp.BaseResponse;
import com.talkortell.bbs.base.common.validator.IValidator;
import com.talkortell.bbs.base.utils.BeanCopyUtil;
import com.talkortell.bbs.base.utils.SpringContextUtil;

public class ServiceHandler {
	private static final Logger log = LoggerFactory.getLogger(ServiceHandler.class);
	private static final String ERROR_FORMAT_STRING = "[call with error] class=%s, method=%s, params=%s, error=%s";
	private static final String EXCEPTION_FORMAT_STRING = "[call with exception] class=%s, method=%s, params=%s, error=%s";

	private ServiceHandler() {

	}

	public static <R, S extends IValidator, P, D extends Serializable> BaseResponse<D> call(
			P reqParams, Class<S> reqValidatorClazz, Class<D> respDtoClazz, SingleServiceSolveInvoker<P, R> invoker) {
		Long start = System.currentTimeMillis();
		StackTraceElement[] traceArray = Thread.currentThread().getStackTrace();
		String className = traceArray[3].getClassName().substring(traceArray[3].getClassName().lastIndexOf('.') + 1);
		String methodName = traceArray[3].getMethodName();
		log.info("inokestart===class={}, method={}, params={}", className, methodName, reqParams);
		BaseResponse<D> resp = new BaseResponse<>();

		List<String> warnMsgs = Lists.newArrayList();
		if (null != reqValidatorClazz) {
			S validator = BeanCopyUtil.copy(reqParams, reqValidatorClazz);
			BeanValidator beanValidator = SpringContextUtil.getBean("beanValidator", BeanValidator.class);
			warnMsgs = beanValidator.validate(validator, BeanValidator.CASE_DEFAULT);
		}

		if (warnMsgs.isEmpty()) {
			try {
				resp.setModel(BeanCopyUtil.copy(invoker.solve(reqParams), respDtoClazz));
				resp.setSuccess(true);
			} catch (AppLogicException apperr) {
				log.error(String.format(ERROR_FORMAT_STRING, className, methodName, reqParams, apperr.getMessage()));
				resp.setCode(apperr.getErrorCode());
				resp.setMsg(apperr.getMessage());
			} catch (Exception e) {
				log.error(String.format(EXCEPTION_FORMAT_STRING, className, methodName, reqParams, e.getMessage()), e);
				resp.setCode(RespCodeEnum.ACCESS_FAIL.getCode());
				resp.setMsg("服务处理出错");
			}
		} else {
			resp.setCode(RespCodeEnum.ILLEGAL_PARAMS.getCode());
			resp.setValidations(warnMsgs);
			resp.setMsg("请求参数错误");
		}
		Long used = System.currentTimeMillis() - start;
		log.info("invokeend===class={}, method={}, used={}, resp={}", className, methodName, used, resp);
		return resp;
	}

	public static <R, S extends IValidator, P extends BasePaginationRequest, D extends Serializable> BasePaginationResponse<D> callList(
			P reqParams, Class<S> reqValidatorClazz, Class<D> respDtoClazz, BatchServiceSolveInvoker<P, R> invoker) {
		Long start = System.currentTimeMillis();
		StackTraceElement[] traceArray = Thread.currentThread().getStackTrace();
		String className = traceArray[2].getClassName();
		String methodName = traceArray[2].getMethodName();
		log.info("inokestart===class={}, method={}, request={}", className, methodName, reqParams);
		BasePaginationResponse<D> resp = new BasePaginationResponse<>();

		List<String> warnMsgs = Lists.newArrayList();
		if (null != reqValidatorClazz) {
			S validator = BeanCopyUtil.copy(reqParams, reqValidatorClazz);
			BeanValidator beanValidator = SpringContextUtil.getBean("beanValidator", BeanValidator.class);
			warnMsgs = beanValidator.validate(validator, BeanValidator.CASE_DEFAULT);
		}

		if (warnMsgs.isEmpty()) {
			try {
				TotPage page = new TotPage(reqParams.getPageIndex(), reqParams.getPageSize());
				resp.setModelList(BeanCopyUtil.copyList(invoker.solve(reqParams, page), respDtoClazz));
				resp.setSuccess(true);
				resp.setPageCount(page.getPageCount());
				resp.setTotalCount(page.getTotalRecords());
			} catch (AppLogicException apperr) {
				log.warn(String.format(ERROR_FORMAT_STRING, className, methodName, reqParams, apperr.getMessage()));
				resp.setCode(apperr.getErrorCode());
				resp.setMsg(apperr.getMessage());
			} catch (Exception e) {
				log.error(String.format(EXCEPTION_FORMAT_STRING, className, methodName, reqParams, e.getMessage()), e);
				resp.setCode(RespCodeEnum.ACCESS_FAIL.getCode());
				resp.setMsg("服务处理出错");
			}
		} else {
			resp.setCode(RespCodeEnum.ILLEGAL_PARAMS.getCode());
			resp.setValidations(warnMsgs);
			resp.setMsg("请求参数错误");
		}
		Long used = System.currentTimeMillis() - start;
		log.info("inokeend===class={}, method={}, used={}, resp={}", className, methodName, used, resp.success());
		return resp;
	}
}
