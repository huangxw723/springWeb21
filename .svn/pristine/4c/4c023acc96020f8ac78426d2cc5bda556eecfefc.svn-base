package sprittr.config;



import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author a135
 * @ClassName:
 * @Description: 上下文配置文件  本配置可以替代 spring/ApplicationContext.XML
 * @date 2017/7/7 9:58
 */
@Configuration
@ComponentScan( basePackages = {"sprittr"}, 
excludeFilters={@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
public class RootConfig {

	 
	
	
	//开发数据源profile   
//	@Profile(value = { "development" })//开发数据源
	@Bean(name = "dataSource")
	public DataSource dataSource(){
		DruidDataSource data = new DruidDataSource();
		data.setDriverClassName("com.mysql.jdbc.Driver");
		data.setUrl("jdbc:mysql://192.168.1.176:3306/redrain?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
		data.setUsername("redRain");
		data.setPassword("redRain@2016");
		return data;
	}
	//数据源事物管理
	@Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(this.dataSource());
        return dataSourceTransactionManager;
    }
	//数据源sqlSessionFactory
	@Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dataSource());
        // sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml")); // 这里可以通过mybatis-config.xml 来设置 typeAliasPackage和mapper。
        // Resource[] mapperLocations = new Resource[] { new ClassPathResource("com.expert.dao") }; // 这个和@MapperScan冲突吗？这个设置有问题。
        // sqlSessionFactoryBean.setMapperLocations(mapperLocations);//<mappers>
//        sqlSessionFactoryBean.setTypeAliasesPackage("sprittr.dao.AsResultMap");
        // sqlSessionFactoryBean.setCache(cache);
        /** 添加mapper 扫描路径 */
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/mybatis/*.xml";
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        // It can be specified a Configuration instance directly without MyBatis XML configuration file.
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);// 开启驼峰映射
        sqlSessionFactory.getConfiguration().setCacheEnabled(true);
        sqlSessionFactory.getConfiguration().setLazyLoadingEnabled(true);
        sqlSessionFactory.getConfiguration().setAggressiveLazyLoading(false);
        // Class<Object> logImpl = sqlSessionFactory.getConfiguration().getTypeAliasRegistry().resolveAlias("SLF4J");
        //sqlSessionFactory.getConfiguration().setLogImpl(Slf4jImpl.class);// logImpl
        sqlSessionFactory.getConfiguration().setLogPrefix("###SPRING_BOOT###MYBATIS###");
        // sqlSessionFactory.getConfiguration().setDefaultExecutorType(ExecutorType.REUSE);
        sqlSessionFactory.getConfiguration().setUseGeneratedKeys(true);
        return sqlSessionFactory;
    }
	
	//dao接口扫描
	@Bean
	public MapperScannerConfigurer getScannerConfigurer(DataSource dataSource, SqlSessionFactory sqlSessionFactory) throws Exception{
		MapperScannerConfigurer cannerConfigurer = new MapperScannerConfigurer();
		cannerConfigurer.setBasePackage("sprittr.dao");
		cannerConfigurer.setSqlSessionFactory(this.sqlSessionFactory());
		return cannerConfigurer;
	}
	//数据源模板
	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(this.sqlSessionFactory());
    }
	
}
