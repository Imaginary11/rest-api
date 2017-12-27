package cn.org.imaginary.web.restapi.common.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/26 23:45
 * @see : 数据库主库配置
 */
@Configuration
@Component
public class PrimaryDataSourceConfig extends BaseDataSourceConfig {

    @Value("${datasource.primary.username}")
    private String username;
    @Value("${datasource.primary.password}")
    private String password;
    @Value("${datasource.primary.driver-class-name}")
    private String driverClassName;
    @Value("${datasource.primary.url}")
    private String url;

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    public DataSource primaryDataSource() {
        return getDataSource(url, username, password, driverClassName);
    }

    @Bean(name = "primarySqlSessionFactory")
    @Qualifier("primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource)
            throws Exception {
        return getSqlSessionFactory(dataSource);
    }

    @Bean(name = "primaryTransaction")
    public PlatformTransactionManager primaryTXManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    protected String getMybatisPath() {
        return "classpath*:mybatis/account/*.xml";
    }
}
