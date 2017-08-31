/**
 * 
 */
package sprittr.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sprittr.dao.AsUserMapper;
import sprittr.pojo.AsUser;

/**   
 * @Title:
 * @Package 
 * @Description: 
 * @author huangxw
 * @date 2017年04月10日
 * @version V1.0   
 */
@Controller
public class sprittController{
	private static Logger logger =LoggerFactory.getLogger(sprittController.class);
	
    @Autowired
	private AsUserMapper asUserMapper;
	@Autowired
	private DataSourceTransactionManager txManager;
	private TransactionStatus status;
	@RequestMapping(value="/toasyeee",method = RequestMethod.GET)
	@ResponseBody
	public Object asyeee(String ces){
		logger.debug("************************日志记录开始***************"+ces);
		AsUser obj=new AsUser();
		try{

			// 开启事务
			createTx();

//			 obj  = asUserMapper.findBySysUserIdd("0");
			obj.setAccount("xuwens");
			obj.setType(5);
			obj.setUserId(88220);
			obj.setSysUserId("2342");
			Integer d=34;
			System.out.println(d==34);

			asUserMapper.insert(obj);
			logger.debug("结果是:"+obj.getAccount());
			txManager.commit(status);
			obj  = asUserMapper.findBySysUserIdd("0");
		} catch (Exception e){

			txManager.rollback(status);
			e.printStackTrace();
		}

//		try {
//			File fl = new File("c://a.text");

//			throw new StatusSuccess("sprttController not eeror");
//		} catch (BaseException e) {
//			return "404";
//		}
return obj;
	}
	public static void main(String[] args) {
		Integer sot=23;
		System.out.println(sot.equals(23));
	}
	/**
	 * 启动事务
	 *
	 * @author yangyanchao
	 * @date 2017-03-10
	 */
	private void createTx() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		status = txManager.getTransaction(def);
	}
	
}
