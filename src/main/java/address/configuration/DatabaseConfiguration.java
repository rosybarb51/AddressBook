package address.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// 이 파일은 거의 동일해서 복붙해서 사용해도 된다
@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

	@Autowired
	private ApplicationContext appContext;
	
//	스프링 ioc에 등록시키기 위한 것이 @Bean, 자동으로 이미 만들어져서 필요한 것 가져다 쓸 수 있게,,
//	사용자가 직접 만드는 것은 @component
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSouce() throws Exception {
		DataSource ds = new HikariDataSource(hikariConfig());
		return ds;
	}
	
	@Bean
	public SqlSessionFactory sqlSessFac(DataSource ds) throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(ds);
//		resource 밑에 sql 밑에 (**는 중간에 몇개의 폴더상관없이) 접두사가 sql이고 접두사가 .xml 이면 된다는 뜻
		ssfb.setMapperLocations(appContext.getResources("classpath:/sql/**/sql-*.xml"));
		ssfb.setConfiguration(mybatisConfig());
		
		return ssfb.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlsessTemp(SqlSessionFactory ssf) {
		return new SqlSessionTemplate(ssf);
	}
	
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}
}















