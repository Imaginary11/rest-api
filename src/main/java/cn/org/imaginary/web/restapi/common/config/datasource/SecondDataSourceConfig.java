package cn.org.imaginary.web.restapi.common.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/26 23:47
 * @see :
 */
@Configuration
@Component
public class SecondDataSourceConfig extends BaseDataSourceConfig {
    @Value("${datasource.second.username}")
    private String username;
    @Value("${datasource.second.password}")
    private String password;
    @Value("${datasource.second.driver-class-name}")
    private String driverClassName;
    @Value("${datasource.second.url}")
    private String url;


    @Bean(name = "secondDataSource")
    @Qualifier("secondDataSource")
    public DataSource secondDataSource() {
        return getDataSource(url, username, password, driverClassName);
    }

    @Bean(name = "secondSqlSessionFactory")
    @Qualifier("secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource)
            throws Exception {
        return getSqlSessionFactory(dataSource);
    }

    @Bean(name = "secondTransaction")
    public PlatformTransactionManager secondTXManager(@Qualifier("secondDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    protected String getMybatisPath() {
        return "classpath*:mybatis/order/*.xml";
    }
}
