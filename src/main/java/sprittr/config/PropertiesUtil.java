package sprittr.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
@Component
public class PropertiesUtil {
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageBuild = new ResourceBundleMessageSource();
		messageBuild.setBasename("F:\\houtai\\springWeb1\\src\\main\\resources\\dbconfig.properties");
		messageBuild.setCacheSeconds(10);
		return messageBuild;
	}

}
