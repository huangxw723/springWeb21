package util;

import java.io.IOException;
import java.util.Properties;






import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import tlt.TranxConfig;
import tlt.TranxServiceImpl;
import tlt.entity.service.ResponseVo;
import tlt.entity.service.SingleTransVo;

public class CapitalService {
    
	public static void main(String[] args) throws IOException {
//		Properties prop = new Properties();
//        prop.load(new ClassPathResource("config.properties").getInputStream());
//       String acctName = prop.getProperty("acctName");
//        if(StringUtils.isEmpty(acctName))
//            throw new RuntimeException(String.format("通联支付-参数缺失 # [%s]" , "acctName"));
//        
//        System.out.println("S");
		queryTrade();
	}
	/**
	 * @Title: comitTrade 单笔代收付-通联
	 * @Description: TODO
	 * @param    
	 * @return void
	 * @throws
	 */
	@SuppressWarnings("unused")
	private void comitTrade() {
		
		// 通联支付-提交申请
			String tradeSn = "02020170724";
			System.out.println("请求通联支付参数："+tradeSn);
			System.out.println("请求通联支付开始。。。。。。。。。");
			SingleTransVo singleTransVo = new SingleTransVo("6214837553021811" // 卡号
					, "黄旭文"// 持卡人
					, "0308" // 银行代号
					, "2"); // 提现金额
			ResponseVo responseVo = new TranxServiceImpl().singleTranx(
					TranxConfig.URL, tradeSn, singleTransVo);
			System.out.println("请求通联支付返回结果：");
			System.out.println(responseVo.getStatus());
			System.out.println("请求通联支付结束.");
	}

	/**
	 * @Title: queryTrade 查询交易-通联
	 * @Description: TODO
	 * @param    
	 * @return void
	 * @throws
	 */
	private static void queryTrade() {
		
		System.out.println("请求通联支付开始。。。。。。。。。");
		System.out.println("请求通联支付参数："+"6426822202");
	    ResponseVo responseVo = new TranxServiceImpl().queryTradeNew(TranxConfig.URL,"6426822202","","");
	    System.out.println(responseVo.getStatus());
	    
	    System.out.println("请求通联支付查询结束.");
	}
}
