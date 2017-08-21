/**
 * 
 */
package sprittr.exception;

/**   
 * @Title:
 * @Package 
 * @Description:  自定义异常（通用）
 * @author huangxw
 * @date 2017年04月10日
 * @version V1.0   
 */

public class BaseDataException extends RuntimeException{
	private static String msg = "数据库异常";
	private static String data ;
	public BaseDataException() {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public BaseDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BaseDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public static String getMsg() {
		return msg;
	}

	public static void setMsg(String msg) {
		BaseDataException.msg = msg;
	}

	public static String getData() {
		return data;
	}

	public static void setData(String data) {
		BaseDataException.data = data;
	}
		
 
	
}
