package cn.org.imaginary.web.restapi.common.config.datasource;

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
 * @see :
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

    protected abstract String getMybatisPath();
}
