package cn.org.imaginary.web.restapi.common.config.datasource;

import cn.org.imaginary.web.restapi.util.StringUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/26 23:45
 * @see : 数据库配置基类，抽象出公有的sqlSession配置,mybatis路径由具体实现类提供
 */
public abstract class BaseDataSourceConfig {
    protected SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PageHelper pageHelper = new PageHelper();
        Properties prop = new Properties();
        prop.put("properties", "dialect=mysql");
        pageHelper.setProperties(prop);
        bean.setPlugins(new Interceptor[]{pageHelper});

        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(getMybatisPath()));
        return bean.getObject();
    }

    protected DataSource getDataSource(String url, String username, String password, String driverClassName) {
        if (StringUtil.isEmpty(url, username, password, driverClassName)) {
            throw new RuntimeException("database config error");
        }
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    protected abstract String getMybatisPath();
}
