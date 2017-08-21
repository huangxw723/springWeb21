/**
 * 
 */
package sprittr.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**   
 * @Title:
 * @Package 
 * @Description: spring容器初始化替代web.xml
 * @author huangxw
 * @date 2017年04月10日
 * @version V1.0   
 */
public class SpringWebServletInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	/* (non-Javadoc)
	 * 应用后端的中间层和数据层组件 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{RootConfig.class};
	}

	/* (non-Javadoc)
	 * spring的上下文组件
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return  new Class<?>[]{WebConfig.class};
	}

	/* (non-Javadoc)   映射所用路径处理所有请求
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	/* (non-Javadoc)
	 * 多文件上传注册
	 * 
	 */
	/*@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("/上传目录"));
//		super.customizeRegistration(registration);
	}*/
	//自定义过滤器初始化器
	/*@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
//		return super.getServletFilters();
		return new Filter[]{ };// new定义过滤器;
	  }*/
	
	//自定义servlet初始化器
	/*@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub
		servletContext.addServlet("myservlet", "");
//		super.onStartup(servletContext);
	}*/
}
