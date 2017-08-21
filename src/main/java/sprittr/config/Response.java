package sprittr.config;

import java.io.Serializable;

/**
 * Response
 * 
 * @author zf
 * @date 16/3/21
 */
public class Response implements Serializable{

	private static final String OK = "success";
	public static final int SUCCESS_CODE = 1;
	public static final int FAIL_CODE = 0;
	public static final int TOKEN_INVALID = -1;
	public static final int ACTIVITY_ABNORMAL = -2;
	private static final String ERROR = "failure";

	private int status;

	private String msg;

	private Object data;

	public Response success() {
		this.status = SUCCESS_CODE;
		this.msg = OK;
		return this;
	}

	public Response success(Object data) {
		this.status = SUCCESS_CODE;
		this.msg = OK;
		this.data = data;
		return this;
	}

	public Response failure() {
		this.status = FAIL_CODE;
		this.msg = ERROR;
		return this;
	}

	public Response() {
	}

	public Response(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Response expireException(String msg) {
		this.status = TOKEN_INVALID;
		this.msg = msg;
		return this;
	}

	public Response timeOut(String msg) {
		this.status = TOKEN_INVALID;
		this.msg = msg;
		return this;
	}

	public Response activityStatusException(String msg) {
		this.status = ACTIVITY_ABNORMAL;
		this.msg = msg;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Response failure(String message) {
		this.status = FAIL_CODE;
		this.msg = message;
		return this;
	}

	public Object getData() {
		return data;
	}

}
