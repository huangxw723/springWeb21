package sprittr.config.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Title:
 * @Package
 * @Description:
 * @author huangxw
 * @date 2017年04月10日
 * @version V1.0
 */
@Aspect
public class SpringAopAudivce{
   //统计接口访问量
	private Map<Integer, Integer> countMap =new HashMap<Integer, Integer>();
    
	
	/*@Pointcut("execution(* sprittr.web.HahaTestController.asy(..))")
	public void tack() {
		// TODO Auto-generated method stub

	}*/
	//元注解
	@Pointcut("execution(* sprittr.web.HahaTestController.asycount(int))&&args(num)")
	public void tack(int num) {}
	
	/**
	 * @Title: countWeb
	 * @Description: TODO
	 * @param    
	 * @return void
	 * @throws
	 */
	@Before("tack(num)")
	public void countWeb(int num) {
		int mum = getCount(num);
		countMap.put(num,mum+1);
	}
   
   /**
	* @Title: getCount
	* @Description: TODO
	* @param @return   
	* @return int
	* @throws
	*/
	public int getCount(int num) {
		return countMap.containsKey(num)?countMap.get(num):0;
	}

/**
 * @Title: adviceAop
 * @Description: TODO
 * @param    
 * @return void
 * @throws
 */
	@Before("execution(* sprittr.web.HahaTestController.asy(..))")
	public void adviceAopBefore() {
	  
		System.out.println("set down !!!");
	}
	@AfterReturning("execution(* sprittr.web.HahaTestController.asy(..))")
	public void adviceAopAfterReturning() {
	  
		System.out.println("hlup hlup  !!!");
	}
	@AfterThrowing("execution(* sprittr.web.HahaTestController.asy(..))")
	public void adviceAopAfterThrowing() {
	  
		System.out.println("over over !!!");
	}
	
	
}
