package util;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import sprittr.config.Response;
import sprittr.exception.BaseDataException;
import sprittr.exception.StatusSuccess;

/**
 * ExceptionAdvice
 * 
 * @author huangxw
 * @date 17/3/21
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
	Logger logger = Logger.getGlobal();

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logger.info("参数解析失败" + e);
		return new Response().failure("could_not_read_json");
	}

	/**
	 * 405 - Method Not Allowed
	 */
//	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//		logger.info("不支持当前请求方法" + e);
//		return new Response().failure("request_method_not_supported");
//	}

	/**
	 * 415 - Unsupported Media Type
	 */
//	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//	public Response handleHttpMediaTypeNotSupportedException(Exception e) {
//		logger.info("不支持当前媒体类型" + e);
//		return new Response().failure("content_type_not_supported");
//	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e) {
		e.printStackTrace();
		return new Response().failure(e.getMessage());
	}

	/**
	 * - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(BaseDataException.class)
	public Response handleSuccessStatus(BaseDataException e) {
		logger.info("StatusSuccess");
		return new Response(Response.SUCCESS_CODE, e.getMessage(), e.getData());
	}
	
	/**
	 * - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(StatusSuccess.class)
	public Response handleSuccessStatus(StatusSuccess e) {
		logger.info("StatusSuccess");
		return new Response(Response.SUCCESS_CODE, e.getMessage(), e.getData());
	}

}
