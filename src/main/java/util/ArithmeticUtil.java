package util;

import java.math.BigDecimal;

/**
 * 运算工具
 *
 */
public class ArithmeticUtil {

	/**
	 * 生成自定义模式的数字实例
	 * 
	 * @author yangyanchao
	 *
	 * @param number
	 * @return
	 */
	public static BigDecimal newInstance(double number) {
		return new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

	/**
	 * 加法
	 * 
	 * @author yangyanchao
	 *
	 * @param addends
	 *            迭代相加
	 * @return
	 */
	public static double ADD(double... addends) {

		BigDecimal result = newInstance(0);

		for (double num : addends) {
			result = result.add(newInstance(num));
		}

		return result.doubleValue();
	}

	/**
	 * 减法
	 * 
	 * @author yangyanchao
	 *
	 * @param divisor
	 *            减
	 * @param dividends
	 *            被减数
	 * @return
	 */
	public static double SUBTRACT(double divisor, double... dividends) {

		BigDecimal result = newInstance(divisor);

		for (double num : dividends) {
			result = result.subtract(newInstance(num));
		}

		return result.doubleValue();
	}

	/**
	 * 乘法
	 * 
	 * @author yangyanchao
	 *
	 * @param multiplicands
	 *            叠加相乘
	 * @return
	 */
	public static double MULTIPLY(double... multiplicands) {

		BigDecimal result = newInstance(1);

		for (double num : multiplicands) {
			result = result.multiply(newInstance(num));
		}

		return result.doubleValue();
	}

	/**
	 * 除法
	 * 
	 * @author yangyanchao
	 *
	 * @param divisor
	 *            除
	 * @param dividends
	 *            被除数
	 * @return
	 */
	public static double DIVIDE(double divisor, double... dividends) {

		BigDecimal result = newInstance(divisor);

		for (double num : dividends) {
			result = result.divide(newInstance(num), 2);
		}

		return result.doubleValue();
	}
}
