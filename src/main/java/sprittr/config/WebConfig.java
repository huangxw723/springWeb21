package sprittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import sprittr.config.aop.SpringAopAudivce;



/**
 * @Title:
 * @Package
 * @Description:
 * @author huangxw
 * @date 2017年04月10日
 * @version V1.0
 */
@Configuration
//@EnableAspectJAutoProxy
@EnableWebMvc                // 启动spring MVC
@ComponentScan("sprittr.web")  // 启动组件扫描
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * @Title: 
	 * @Description: /设置jsp视图解析器
	 * @param
	 * @return void
	 * @throws
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	/**
	 * @Title: safdsa
	 * @Description: //设置静态资源解析器
	 * @param
	 * @return void
	 * @throws
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer config) {
		{
           config.enable();
		}
	}
	
	//切面注入
    @Bean 
    public SpringAopAudivce springAopAudivce(){
    	return new SpringAopAudivce();
    }
}
