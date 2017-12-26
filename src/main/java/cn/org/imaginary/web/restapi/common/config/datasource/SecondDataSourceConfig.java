package cn.org.imaginary.web.restapi.common.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/26 23:47
 * @see :
 */
@Configuration
public class SecondDataSourceConfig extends BaseDataSourceConfig {
    @Bean(name = "secondDataSource")
    @Qualifier("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
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
